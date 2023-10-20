package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.commons.core.index.Index;

public class ViewClientCommand extends Command {

    public static final String COMMAND_WORD = "viewclient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the client identified by the index number used in the listclient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewed Client Successfully";
    public static final String MESSAGE_INVALID_INDEX = "Invalid Client index";
    public static final String MESSAGE_NOT_A_CLIENT = "The person at the specified index is not a client.";


    public static final Logger logger = LogsCenter.getLogger(ViewClientCommand.class);

    private final Index targetIndex;

    public ViewClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);


        if (targetIndex.getZeroBased() < 0) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }

        Person clientToView = model.getFilteredPersonList().get(targetIndex.getZeroBased());

        Predicate<Person> VIEW_CLIENT_PREDICATE = person -> person.equals(clientToView);

        model.updateFilteredPersonList(VIEW_CLIENT_PREDICATE);

        if (!VIEW_CLIENT_PREDICATE.test(clientToView)) {
            return new CommandResult(MESSAGE_NOT_A_CLIENT);
        }

        logger.info("Target Index: " + targetIndex.getZeroBased());
        logger.info("Client to View: " + clientToView);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}



