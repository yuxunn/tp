package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.addItemToSet;

public class Lead extends Person {
    public static final String TAG_LEAD = "Lead";

    /**
     * Every field must be present and not null.
     */
    public Lead(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, addItemToSet(tags, new Tag(TAG_LEAD)));
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
