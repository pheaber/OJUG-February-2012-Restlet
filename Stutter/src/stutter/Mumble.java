package stutter;

public class Mumble {

    private final User user;
    private final String message;

    public Mumble(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.user.getUsername() + "==>" + this.message;
    }

}
