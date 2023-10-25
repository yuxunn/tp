package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Lead;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Lead} objects to be used in tests.
 */
public class TypicalLeads {
    public static final Lead ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withMeetingTime("24/10/2023 12:30").buildLead();

    // Manually added
    public static final Lead HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withMeetingTime("24/10/2023 12:30").buildLead();
    public static final Lead IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withMeetingTime("24/10/2023 12:30").buildLead();

    // Manually added - Lead's details found in {@code CommandTestUtil}
    public static final Lead AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withMeetingTime(VALID_MEETING_TIME_AMY).buildLead();
    public static final Lead BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withMeetingTime(VALID_MEETING_TIME_BOB).buildLead();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalLeads() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalLeadsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Lead person : getTypicalLeads()) {
            ab.addLead(person);
        }
        return ab;
    }

    public static List<Lead> getTypicalLeads() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
