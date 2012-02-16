package stutter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FakeStorageTest {

    private static final String TEST_USERNAME = "inigoSeeksRevenge";

    private static final String TEST_EMAIL_ADDRESS = "inigo.montoya@revengeismybusiness.fl";

    private static final User TEST_SIMPLE_USER = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS);

    private static final User TEST_USER = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS, "Inigo Montoya");

    private final Map<String, User> userStorage = new HashMap<String, User>();
    private final Map<String, Mumble> mumbleStorage = new HashMap<String, Mumble>();

    private final FakeStorage storage = new FakeStorage(this.userStorage, this.mumbleStorage);

    @Test
    public void addUser() {
        this.storage.addUser(TEST_USER);

        assertEquals("Did not add user!", 1, this.userStorage.size());
        // check that it was added properly
        final User addedUser = this.userStorage.get(TEST_USERNAME);
        assertSame("Didn't add user correctly!", TEST_USER, addedUser);
    }

    @Test
    public void getUser() {
        // add in the test data
        this.userStorage.put(TEST_USERNAME, TEST_USER);

        final User foundUser = this.storage.getUser(TEST_USERNAME);
        assertNotNull("Did not find user!", foundUser);
        assertSame("Found wrong user!", TEST_USER, foundUser);
    }

    @Test
    public void updateUser() {
        // add in the test data
        this.userStorage.put(TEST_USERNAME, TEST_SIMPLE_USER);

        this.storage.updateUser(TEST_USER);

        assertEquals("Did not update user correctly!", 1, this.userStorage.size());
        // check that it was updated properly
        final User updatedUser = this.userStorage.get(TEST_USERNAME);
        assertSame("Didn't add user correctly!", TEST_USER, updatedUser);
    }

    @Test
    public void removeUser() {
        // add in the test data
        this.userStorage.put(TEST_USERNAME, TEST_USER);

        this.storage.removeUser(TEST_USERNAME);

        // make sure it's gone
        assertTrue("Didn't remove user!", this.userStorage.isEmpty());
    }

    @Test
    public void newMumble() {

    }

    @Test
    public void getMumble() {

    }

    @Test
    public void removeMumble() {

    }

}
