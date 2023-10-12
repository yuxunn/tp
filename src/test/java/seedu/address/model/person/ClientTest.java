package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    public void isClient() {
        assertTrue(ALICE.isClient());
        assertFalse(ALICE.isLead());
    }
}
