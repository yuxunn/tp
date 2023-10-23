package seedu.address.model.person;

import java.time.format.DateTimeFormatter;

public class MeetingTimeFormatter {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    }
}
