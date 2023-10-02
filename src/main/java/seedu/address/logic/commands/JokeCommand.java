
package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class JokeCommand extends Command{

    public static final String COMMAND_WORD = "joke";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": look in to the mirror you sick fork!";
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(
                String.format(MESSAGE_USAGE, true, false)
        );
    }

}

