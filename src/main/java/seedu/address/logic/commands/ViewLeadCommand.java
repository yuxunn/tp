package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.commons.core.index.Index;

public class ViewLeadCommand extends Command {

    public static final String COMMAND_WORD = "viewlead";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the client identified by the index number used in the listlead list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewed Client Successfully";
    public static final String MESSAGE_INVALID_INDEX = "Invalid Client index";
    public static final String MESSAGE_NOT_A_LEAD = "The person at the specified index is not a lead.";


    public static final Logger logger = LogsCenter.getLogger(ViewClientCommand.class);

    private final Index targetIndex;

    public ViewLeadCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);


        if (targetIndex.getZeroBased() < 0 || targetIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }

        Person leadToView = model.getFilteredPersonList().get(targetIndex.getZeroBased());

        Predicate<Person> VIEW_LEAD_PREDICATE = person -> person.equals(leadToView);

        model.updateFilteredPersonList(VIEW_LEAD_PREDICATE);

        if (!VIEW_LEAD_PREDICATE.test(leadToView)) {
            return new CommandResult(MESSAGE_NOT_A_LEAD);
        }

        logger.info("Target Index: " + targetIndex.getZeroBased());
        logger.info("Client to View: " + leadToView);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}



