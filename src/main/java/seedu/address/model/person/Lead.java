package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Lead in the address book.
 */
public class Lead extends Person {
    public static final String TYPE_LEAD = "lead";
    public KeyMilestone keyMilestone;

    /**
     * Every field must be present and not null.
     */
    public Lead(Name name, Phone phone, Email email, Address address, KeyMilestone keyMilestone, Set<Tag> tags) {
        super(name, phone, email, new Type(TYPE_LEAD), address, tags);
        this.keyMilestone = keyMilestone;
    }

    public KeyMilestone getKeyMilestone() {
        return this.keyMilestone;
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
