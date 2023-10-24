package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ConvertLeadToClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ConvertLeadToClientCommandParser implements Parser<ConvertLeadToClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public ConvertLeadToClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertLeadToClientCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new ConvertLeadToClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertLeadToClientCommand.MESSAGE_USAGE), pe);
        }
    }

}
