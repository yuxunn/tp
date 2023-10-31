package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists persons in the address book sorted by their meeting times.
 */
public class SortMeetingTimeCommand extends Command {

    public static final String COMMAND_WORD = "sortmeeting";

    public static final String MESSAGE_SUCCESS = "Sorted all meeting times chronologically";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
