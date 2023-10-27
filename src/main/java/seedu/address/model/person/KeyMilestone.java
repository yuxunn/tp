package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class KeyMilestone {
    public static final String MESSAGE_CONSTRAINTS =
            "Key milestone is the date of Lead's milestone moment, should be in the format of yyyy-MM-dd";
    public final String value;
    /**
     * Constructs an {@code KeyMilestone}.
     *
     * @param keyMilestone A valid date.
     */
    public KeyMilestone(String keyMilestone) {
        requireNonNull(keyMilestone);
        checkArgument(isValidKeyMilestone(keyMilestone), MESSAGE_CONSTRAINTS);
        this.value = keyMilestone;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidKeyMilestone(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
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
        return value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
