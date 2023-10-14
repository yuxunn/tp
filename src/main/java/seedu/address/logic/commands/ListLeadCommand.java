package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


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

