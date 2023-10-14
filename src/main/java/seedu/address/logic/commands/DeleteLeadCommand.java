package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteLeadCommand extends Command {

    public static final String COMMAND_WORD = "delete_lead";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lead identified by the index number used in the displayed lead list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_LEAD_SUCCESS = "Deleted Lead: %1$s";

    private final Index targetIndex;

    public DeleteLeadCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (personToDelete.getTags().contains(new Tag("Lead"))) {
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_LEAD_SUCCESS, Messages.format(personToDelete)));
        }
            throw new CommandException(Messages.MESSAGE_INVALID_LEAD_DISPLAYED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteLeadCommand)) {
            return false;
        }

        DeleteLeadCommand otherDeleteCommand = (DeleteLeadCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}