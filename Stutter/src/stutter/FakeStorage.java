package stutter;

import java.util.Map;

public class FakeStorage {

    private final Map<String, User> userStorage;

    private final Map<String, Mumble> mumbleStorage;

    /* package */FakeStorage(Map<String, User> userStorage, Map<String, Mumble> mumbleStorage) {
        this.userStorage = userStorage;
        this.mumbleStorage = mumbleStorage;
    }

    public void addUser(User user) {
        // TODO: what if we already have that username?

        this.userStorage.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return this.userStorage.get(username);
    }

    public void updateUser(User user) {
        // TODO: handle if user doesn't exist!

        this.userStorage.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        // TODO: handle if user doesn't exist!

        this.userStorage.remove(username);
    }

    public void newMumble(String username, String message) {

    }

    public Mumble getMumble(String mumbleId) {
        return null;
    }

    public void removeMumble(String mumbleId) {

    }

}
