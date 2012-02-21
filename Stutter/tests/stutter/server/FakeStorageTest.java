package stutter.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import stutter.Mumble;
import stutter.MumbleTestUtil;
import stutter.User;

public class FakeStorageTest {

    private static final String TEST_USERNAME = "inigoSeeksRevenge";

    private static final String TEST_EMAIL_ADDRESS = "inigo.montoya@revengeismybusiness.fl";

    private static final User TEST_SIMPLE_USER = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS);

    private static final User TEST_USER = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS, "Inigo Montoya");

    private static final String TEST_MUMBLE_ID = "123-4987-560";

    private static final String TEST_MESSAGE = "Hello. My name is Inigo Montoya. You killed my father. Prepare to die.";

    private final Map<String, User> userStorage = new HashMap<String, User>();

    private final Map<String, Mumble> mumbleStorage = new HashMap<String, Mumble>();

    private final List<Mumble> mumbleTimeline = new ArrayList<Mumble>();

    private final FakeStorage storage = new FakeStorage(this.userStorage, this.mumbleStorage, this.mumbleTimeline);

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
    public void removeUserAlsoRemovesMumbles() {
        // add in the test data
        User testUser = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS);
        this.userStorage.put(TEST_USERNAME, testUser);
        Mumble testMumble = MumbleTestUtil.buildMumble(TEST_MUMBLE_ID, testUser, TEST_MESSAGE);
        testUser.addMumble(testMumble);
        this.mumbleStorage.put(TEST_MUMBLE_ID, testMumble);
        this.mumbleTimeline.add(testMumble);

        this.storage.removeUser(TEST_USERNAME);

        // make sure it's gone
        assertTrue("Didn't remove user!", this.userStorage.isEmpty());
        assertTrue("Didn't remove from mumble storage!", this.mumbleStorage.isEmpty());
        assertTrue("Didn't remove from mumble timeline!", this.mumbleTimeline.isEmpty());
    }

    @Test
    public void newMumble() {
        // add in the test data
        this.userStorage.put(TEST_USERNAME, TEST_USER);

        this.storage.newMumble(TEST_USERNAME, TEST_MESSAGE);

        assertEquals("Didn't add new mumble to map", 1, this.mumbleStorage.size());
        final Mumble mumble = this.mumbleStorage.values().iterator().next();
        assertMumbleMatches(TEST_USER, TEST_MESSAGE, mumble);

        assertEquals("Didn't add new mumble to timeline", 1, this.mumbleTimeline.size());
        final Mumble timelineMumble = this.mumbleTimeline.get(0);
        assertMumbleMatches(TEST_USER, TEST_MESSAGE, timelineMumble);

        // check that mumble was added to the User
        final List<Mumble> mumbles = TEST_USER.getMumbles();
        assertEquals("Didn't add mumble to User", 1, mumbles.size());
    }

    @Test
    public void getMumble() {
        // add in the test data
        Mumble testMumble = MumbleTestUtil.buildMumble(TEST_MUMBLE_ID, TEST_USER, TEST_MESSAGE);
        this.mumbleStorage.put(TEST_MUMBLE_ID, testMumble);

        final Mumble foundMumble = this.storage.getMumble(TEST_MUMBLE_ID);

        assertNotNull("Didn't find mumble!", foundMumble);
        assertSame("Found the wrong mumble", testMumble, foundMumble);
    }

    @Test
    public void removeMumble() {
        // add in the test data
        User testUser = new User(TEST_USERNAME, TEST_EMAIL_ADDRESS);
        this.userStorage.put(TEST_USERNAME, testUser);
        Mumble testMumble = MumbleTestUtil.buildMumble(TEST_MUMBLE_ID, testUser, TEST_MESSAGE);
        testUser.addMumble(testMumble);
        this.mumbleStorage.put(TEST_MUMBLE_ID, testMumble);
        this.mumbleTimeline.add(testMumble);

        this.storage.removeMumble(TEST_MUMBLE_ID);

        assertTrue("Didn't remove mumble from map!", this.mumbleStorage.isEmpty());
        assertTrue("Didn't remove mumble from timeline!", this.mumbleTimeline.isEmpty());
        assertTrue("Didn't remove mumble from user!", testUser.getMumbles().isEmpty());
    }

    private static void assertMumbleMatches(User expectedUser, String expectedMessage, Mumble testMumble) {
        assertNotNull("Didn't have mumble!", testMumble);
        final String mumbleUsername = testMumble.getUsername();
        assertEquals("Wrong user!", expectedUser.getUsername(), mumbleUsername);
        assertEquals("Wrong message!", expectedMessage, testMumble.getMessage());
    }

}
