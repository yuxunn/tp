package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MeetingTime;

/**
 * Adds a new meeting time to the person identified by the displayed index in the address book.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addmeeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new meeting time to the person identified by the displayed index in the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING_TIME + "MEETING_TIME \n"
            + "Example: " + COMMAND_WORD + " 1 m/12/10/2023 16:00";

    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added Meeting: %1$s to Person %2$s";
    public static final String MESSAGE_ALREADY_EXISTS =
            "Person already has a meeting time, use the edit command instead";

    private final Index targetIndex;
    private final MeetingTime meetingTime;

    public AddMeetingCommand(Index targetIndex, MeetingTime meetingTime) {
        requireAllNonNull(targetIndex, meetingTime);

        this.targetIndex = targetIndex;
        this.meetingTime = meetingTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Add meeting time");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherAddMeetingCommand = (AddMeetingCommand) other;
        return targetIndex.equals(otherAddMeetingCommand.targetIndex)
                && meetingTime.equals(otherAddMeetingCommand.meetingTime);
    }
}
