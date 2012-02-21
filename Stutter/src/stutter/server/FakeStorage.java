package stutter.server;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import stutter.Mumble;
import stutter.User;

public class FakeStorage {

    private final Map<String, User> userStorage;

    private final Map<String, Mumble> mumbleStorage;

    private final List<Mumble> mumbleTimeline;

    private static final FakeStorage INSTANCE = new FakeStorage(new ConcurrentHashMap<String, User>(),
            new ConcurrentHashMap<String, Mumble>(), new CopyOnWriteArrayList<Mumble>());

    // TODO: remove me!!
    static {
        User westley = new User("farmBoy", "westley@asyouwish.fl", "Westley");
        INSTANCE.addUser(westley);
        INSTANCE.newMumble("farmBoy", "As you wish.");

        User inigo = new User("inigoSeeksRevenge", "inigo.montoya@revengeismybusiness.fl", "Inigo Montoya");
        INSTANCE.addUser(inigo);
        INSTANCE.newMumble("inigoSeeksRevenge",
                "Hello. My name is Inigo Montoya. You killed my father. Prepare to die.");
    }

    public static FakeStorage getInstance() {
        return INSTANCE;
    }

    /* package */FakeStorage(Map<String, User> userStorage, Map<String, Mumble> mumbleStorage,
            List<Mumble> mumbleTimeline) {
        this.userStorage = userStorage;
        this.mumbleStorage = mumbleStorage;
        this.mumbleTimeline = mumbleTimeline;
    }

    public Collection<User> listUsers() {
        return this.userStorage.values();
    }

    public List<Mumble> listMumbles() {
        return this.mumbleTimeline;
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
        User user = this.userStorage.get(username);

        // TODO: what if user doesn't exist?

        Mumble newMumble = new Mumble(username, message);
        user.addMumble(newMumble);
        this.mumbleStorage.put(newMumble.getId(), newMumble);
        this.mumbleTimeline.add(newMumble);
    }

    public Mumble getMumble(String mumbleId) {
        return this.mumbleStorage.get(mumbleId);
    }

    public void removeMumble(String mumbleId) {
        // TODO: handle if mumble doesn't exist!

        Mumble removedMumble = this.mumbleStorage.remove(mumbleId);
        this.mumbleTimeline.remove(removedMumble);

        // remove the mumble from the User!
        User user = this.userStorage.get(removedMumble.getUsername());
        user.getMumbles().remove(removedMumble);
    }

}
