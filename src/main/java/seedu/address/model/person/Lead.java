package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Lead in the address book.
 */
public class Lead extends Person {
    public static final String TYPE_LEAD = "lead";

    /**
     * Every field must be present and not null.
     */
    public Lead(Name name, Phone phone, Email email, Address address, MeetingTime meetingTime, Set<Tag> tags) {
        super(name, phone, email, new Type(TYPE_LEAD), address, meetingTime, tags);
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public boolean isLead() {
        return true;
    }
}
