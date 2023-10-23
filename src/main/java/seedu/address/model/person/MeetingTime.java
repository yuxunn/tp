package seedu.address.model.person;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.MeetingTimeFormatter.DATE_TIME_FORMAT;

public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS = "Meeting time should be in the format of" + DATE_TIME_FORMAT;

    public static String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4} \\d{4}";

    public final LocalDateTime value;

    public MeetingTime(String meetingTime) {
        requireNonNull(meetingTime);
        this.value = LocalDateTime.parse(meetingTime, MeetingTimeFormatter.getFormatter());
    }

    public static boolean isValidMeetingTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.format(MeetingTimeFormatter.getFormatter());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Address)) {
            return false;
        }

        MeetingTime otherMeetingTime = (MeetingTime) other;
        return value.equals(otherMeetingTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
