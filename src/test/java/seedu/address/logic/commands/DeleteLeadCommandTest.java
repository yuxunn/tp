package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteLeadCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    // cannot be tested till add lead implemented
    //    public void execute_validIndexUnfilteredList_success() {
    //        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        DeleteLeadCommand deleteCommand = new DeleteLeadCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteLeadCommand.MESSAGE_DELETE_LEAD_SUCCESS,
    //                Messages.format(personToDelete));
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        expectedModel.deletePerson(personToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteLeadCommand deleteLeadCommand = new DeleteLeadCommand(outOfBoundIndex);

        assertCommandFailure(deleteLeadCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteLeadCommand deleteFirstCommand = new DeleteLeadCommand(INDEX_FIRST_PERSON);
        DeleteLeadCommand deleteSecondCommand = new DeleteLeadCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteLeadCommand deleteFirstCommandCopy = new DeleteLeadCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteLeadCommand deleteLeadCommand = new DeleteLeadCommand(targetIndex);
        String expected = DeleteLeadCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteLeadCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
