package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class KeyMilestone {
    public static final String MESSAGE_CONSTRAINTS =
            "Key milestone is the date of Lead's milestone moment";
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public final String keyMilestone;
    //todo: add comment
    public KeyMilestone(String keyMilestone) {
        requireNonNull(keyMilestone);
        checkArgument(isValidKeyMilestone(keyMilestone), MESSAGE_CONSTRAINTS);
        //todo: use SimpleDateFormat if needed
        // https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
        this.keyMilestone = keyMilestone;
    }

    //todo: add comment
    public static boolean isValidKeyMilestone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return keyMilestone;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof KeyMilestone)) {
            return false;
        }

        KeyMilestone otherName = (KeyMilestone) other;
        return keyMilestone.equals(otherName.keyMilestone);
    }

    @Override
    public int hashCode() {
        return keyMilestone.hashCode();
    }

}
