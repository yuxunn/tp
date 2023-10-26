package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Converts a Client to a Lead in the address book.
 */
public class ConvertClientToLeadCommand extends Command {
    public static final String COMMAND_WORD = "converttolead";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Converts a client to a lead by the index "
            + "number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CONVERT_SUCCESS = "Converted Client to Lead: %1$s";

    private final Index index;

    /**
     * Constructor for convertClientToLead.
     */
    public ConvertClientToLeadCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToConvert = lastShownList.get(index.getZeroBased());

        if (!(personToConvert instanceof Client)) {
            throw new CommandException("The person at the specified index is not a Client.");
        }

        Name name = personToConvert.getName();
        Phone phone = personToConvert.getPhone();
        Email email = personToConvert.getEmail();
        Address address = personToConvert.getAddress();
        Set<Tag> tags = new HashSet<Tag>();
        tags.remove(new Tag("Client"));
        tags.add(new Tag("Lead"));
        // TODO: Add more fields from client to lead


        Lead convertedLead = new Lead(name, phone, email, address, tags);

        model.setPerson(personToConvert, convertedLead);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_CONVERT_SUCCESS, Messages.format(convertedLead)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ConvertClientToLeadCommand)) {
            return false;
        }

        ConvertClientToLeadCommand otherConvertCommand = (ConvertClientToLeadCommand) other;
        return index.equals(otherConvertCommand.index);
    }
}

