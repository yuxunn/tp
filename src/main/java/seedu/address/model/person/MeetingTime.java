package seedu.address.model.person;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.MeetingTimeFormatter.DATE_TIME_FORMAT;

public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS = "Meeting time should be in the format of " + DATE_TIME_FORMAT;

    public final LocalDateTime value;

    public MeetingTime(String meetingTime) {
        requireNonNull(meetingTime);
        checkArgument(isValidMeetingTime(meetingTime), MESSAGE_CONSTRAINTS);
        this.value = MeetingTimeFormatter.parse(meetingTime);
    }

    public static boolean isValidMeetingTime(String test) {
        return MeetingTimeFormatter.isValid(test);
    }

    @Override
    public String toString() {
        return MeetingTimeFormatter.format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingTime)) {
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
