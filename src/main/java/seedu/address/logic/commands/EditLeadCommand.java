package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class EditLeadCommand extends EditCommand {
    private final Index index;
    private final EditLeadDescriptor editLeadDescriptor;


    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_KEYMILESTONE+ "KEY MILESTONE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public EditLeadCommand(Index index, EditLeadDescriptor editLeadDescriptor) {
        super(index, editLeadDescriptor);
        requireNonNull(index);
        requireNonNull(editLeadDescriptor);

        this.index = index;
        this.editLeadDescriptor = new EditLeadDescriptor(editLeadDescriptor);
    }


    private static Lead createEditedLead (Lead leadToEdit, EditLeadDescriptor editLeadDescriptor) {
        assert leadToEdit != null;

        Name updatedName = editLeadDescriptor.getName().orElse(leadToEdit.getName());
        Phone updatedPhone = editLeadDescriptor.getPhone().orElse(leadToEdit.getPhone());
        Email updatedEmail = editLeadDescriptor.getEmail().orElse(leadToEdit.getEmail());
        Address updatedAddress = editLeadDescriptor.getAddress().orElse(leadToEdit.getAddress());
        Set<Tag> updatedTags = editLeadDescriptor.getTags().orElse(leadToEdit.getTags());
        KeyMilestone updatedKeyMilestone = editLeadDescriptor.getKeyMilestone().orElse(leadToEdit.getKeyMilestone());

        return new Lead(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedKeyMilestone, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            //todo: fix the message so it is for lead
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedLead((Lead) personToEdit, editLeadDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLeadCommand)) {
            return false;
        }

        EditLeadCommand otherEditCommand = (EditLeadCommand) other;
        return index.equals(otherEditCommand.index)
                && editLeadDescriptor.equals(otherEditCommand.editLeadDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editLeadDescriptor", editLeadDescriptor)
                .toString();
    }


}
