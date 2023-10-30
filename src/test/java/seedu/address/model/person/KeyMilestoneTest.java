package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class KeyMilestoneTest {

    @Test
    void isValidKeyMilestone() {
        //todo: fix needed for local date time
        assertFalse(KeyMilestone.isValidKeyMilestone("01-01-2022"));
        assertFalse(KeyMilestone.isValidKeyMilestone("01/01/2022"));
        assertFalse(KeyMilestone.isValidKeyMilestone("01.01.2022"));
        //assertFalse(KeyMilestone.isValidKeyMilestone("2022-02-31"));
        assertTrue(KeyMilestone.isValidKeyMilestone("2022-01-29"));
        assertTrue(KeyMilestone.isValidKeyMilestone("2022-03-31"));
    }

    @Test
    void equals() {
        KeyMilestone keyMilestone = new KeyMilestone("2022-12-01");

        // same values -> returns true
        assertTrue(keyMilestone.equals(new KeyMilestone("2022-12-01")));

        // same object -> returns true
        assertTrue(keyMilestone.equals(keyMilestone));

        // null -> returns false
        assertFalse(keyMilestone.equals(null));

        // different types -> returns false
        assertFalse(keyMilestone.equals(5.0f));

        // different values -> returns false
        assertFalse(keyMilestone.equals(new KeyMilestone("2022-01-01")));
    }

    @Test
    void hashCode_equal() {
        assertEquals(new KeyMilestone("2022-12-01").hashCode(),
                     new KeyMilestone("2022-12-01").hashCode());
    }
}
