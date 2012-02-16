package stutter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {

    private static final String TEST_USERNAME = "dreadPirateRoberts";

    private static final String TEST_EMAIL = "westley@asyouwish.fl";

    private static final String TEST_NAME = "Westley";

    private static final User USER_WITH_ONLY_USERNAME_EMAIL = new User(TEST_USERNAME, TEST_EMAIL);

    private static final User USER_WITH_ALL_FIELDS = new User(TEST_USERNAME, TEST_EMAIL, TEST_NAME);

    @Test
    public void hashCodeOnlyIncludesUsernameAndEmailAddress() {
        assertEquals("hashCode should be the same!", USER_WITH_ONLY_USERNAME_EMAIL.hashCode(),
                USER_WITH_ALL_FIELDS.hashCode());
    }

    @Test
    public void equalsMatchesInstancesWithJustUsernameEmailAddress() {
        User testUser = new User(TEST_USERNAME, TEST_EMAIL);

        // must test equality on both sides!
        assertTrue("Didn't match testUser.equals(USER)!", testUser.equals(USER_WITH_ONLY_USERNAME_EMAIL));
        assertTrue("Didn't match USER.equals(testUser)!", USER_WITH_ONLY_USERNAME_EMAIL.equals(testUser));
    }

    @Test
    public void equalsDoesNotMatchInstancesWithDifferentEmailAddress() {
        User testUser = new User(TEST_USERNAME, TEST_EMAIL + "diff");

        // must test equality on both sides!
        assertFalse("Matched testUser.equals(USER) but shouldn't!", testUser.equals(USER_WITH_ONLY_USERNAME_EMAIL));
        assertFalse("Matched USER.equals(testUser) but shouldn't!", USER_WITH_ONLY_USERNAME_EMAIL.equals(testUser));
    }

    @Test
    public void equalsDoesNotMatchInstancesWithJustUsernameEmailAddressToInstancesWithAllFields() {
        // must test equality on both sides!
        assertFalse("Matched all.equals(USER) but shouldn't!",
                USER_WITH_ALL_FIELDS.equals(USER_WITH_ONLY_USERNAME_EMAIL));
        assertFalse("Matched USER.equals(all) but shouldn't!",
                USER_WITH_ONLY_USERNAME_EMAIL.equals(USER_WITH_ALL_FIELDS));
    }

    @Test
    public void equalsMatchesInstancesWithAllFields() {
        User testUser = new User(TEST_USERNAME, TEST_EMAIL, TEST_NAME);

        // must test equality on both sides!
        assertTrue("Didn't match testUser.equals(ALL)!", testUser.equals(USER_WITH_ALL_FIELDS));
        assertTrue("Didn't match ALL.equals(testUser)!", USER_WITH_ALL_FIELDS.equals(testUser));
    }

}
