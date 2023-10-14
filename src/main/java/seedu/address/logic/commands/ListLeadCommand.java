package seedu.address.logic.commands;

import java.util.logging.Logger;
import java.util.function.Predicate;

import seedu.address.commons.core.LogsCenter;

import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

/**
 * Lists all persons in the address book to the user.
 */
public class ListLeadCommand extends Command {

    public static final String COMMAND_WORD = "listlead";
    public static final String MESSAGE_SUCCESS = "Listed all leads";
    public static final String CLIENT_TAG = "[[Lead]]";

    private static final Logger logger = LogsCenter.getLogger(ListClientCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> predicate = person -> {
            boolean result = person.getTags().toString().equals(CLIENT_TAG);
            return result;
        };
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

