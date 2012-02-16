package stutter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

    private final String username;

    private final String emailAddress;

    private String name;

    private final List<Mumble> mumbles = new ArrayList<Mumble>();

    public User(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public User(String username, String emailAddress, String name) {
        this(username, emailAddress);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void addMumble(Mumble mumble) {
        this.mumbles.add(mumble);
    }

    public List<Mumble> getMumbles() {
        return this.mumbles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        EqualsBuilder equals = new EqualsBuilder();
        equals.append(this.username, other.username);
        equals.append(this.emailAddress, other.emailAddress);
        equals.append(this.name, other.name);

        return equals.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(7, 19);

        hash.append(this.username);
        hash.append(this.emailAddress);

        return hash.toHashCode();
    }

    @Override
    public String toString() {
        return "User=" + this.username;
    }

}
