package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalLeads.getTypicalLeadsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditLeadDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditLeadDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

//todo: fix this
class EditLeadCommandTest {
    private Model model = new ModelManager(getTypicalLeadsAddressBook(), new UserPrefs());
    EditLeadDescriptor DESC_AMY_LEAD = new EditLeadDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
    //fix thiaddressboos
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        //edit lead string got problem for meeting time, todo v
        Lead editedLead = new PersonBuilder().buildLead();
        EditLeadDescriptor descriptor = new EditLeadDescriptorBuilder(editedLead).build();
        EditLeadCommand editLeadCommand = new EditLeadCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditLeadCommand.MESSAGE_EDIT_LEAD_SUCCESS, Messages.format(editedLead));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedLead);
        assertCommandSuccess(editLeadCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Lead lastPerson = (Lead) model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        assert (lastPerson.isLead());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Lead editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).withKeyMilestone(VALID_KEYMILESTONE_BOB).buildLead();

        EditLeadDescriptor descriptor = new EditLeadDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).withKeyMilestone(VALID_KEYMILESTONE_BOB)
                .build();

        EditLeadCommand editCommand = new EditLeadCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LEAD_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditLeadCommand editCommand = new EditLeadCommand(INDEX_FIRST_PERSON, new EditLeadDescriptor());
        Lead editedPerson = (Lead) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert (editedPerson.isLead());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LEAD_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Lead personInFilteredList = (Lead) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert(personInFilteredList.isLead());
        Lead editedLead = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).buildLead();
        EditLeadCommand editCommand = new EditLeadCommand(INDEX_FIRST_PERSON,
                new EditLeadDescriptorBuilder().withName(VALID_NAME_BOB).withKeyMilestone(VALID_KEYMILESTONE_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LEAD_SUCCESS, Messages.format(editedLead));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedLead);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Lead firstPerson =  (Lead) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert(firstPerson.isLead());
        EditLeadDescriptor descriptor = new EditLeadDescriptorBuilder(firstPerson).build();
        EditLeadCommand editCommand = new EditLeadCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        assert(personInList.isLead());
        EditLeadCommand editCommand = new EditLeadCommand(INDEX_FIRST_PERSON,
                new EditLeadDescriptorBuilder((Lead) personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditLeadDescriptor descriptor = new EditLeadDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditLeadCommand editCommand = new EditLeadCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditLeadCommand editCommand = new EditLeadCommand(outOfBoundIndex,
                new EditLeadDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditLeadCommand standardCommand = new EditLeadCommand(INDEX_FIRST_PERSON, DESC_AMY_LEAD);

        // same values -> returns true
        EditLeadDescriptor copyDescriptor = new EditLeadDescriptor(DESC_AMY_LEAD);
        EditLeadCommand commandWithSameValues = new EditLeadCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY_LEAD)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditLeadDescriptor editLeadDescriptor = new EditLeadDescriptor();
        EditLeadCommand editLeadCommand = new EditLeadCommand(index, editLeadDescriptor);
        String expected = EditLeadCommand.class.getCanonicalName() + "{index=" + index + ", editLeadDescriptor="
                + editLeadDescriptor + "}";
        assertEquals(expected, editLeadCommand.toString());
    }
}