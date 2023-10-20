package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "View person success";

    private final int targetIndex;

    public ViewCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Person personToView = model.getPersonByIndex(targetIndex);

        if (personToView != null) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult("Person not found at index " + targetIndex);
        }
    }
}

