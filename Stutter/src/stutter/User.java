package stutter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    private String username;

    private String emailAddress;

    private String name;

    private List<Mumble> mumbles = new ArrayList<Mumble>();

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private User() {
    }

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

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addMumble(Mumble mumble) {
        this.mumbles.add(mumble);
    }

    public List<Mumble> getMumbles() {
        return this.mumbles;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setMumbles(List<Mumble> mumbles) {
        this.mumbles = mumbles;
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
        ToStringBuilder string = new ToStringBuilder(this);
        string.append("username", this.username);
        string.append("emailAddress", this.emailAddress);
        string.append("name", this.name);
        return string.build();
    }

}
