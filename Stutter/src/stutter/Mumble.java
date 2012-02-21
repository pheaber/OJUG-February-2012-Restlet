package stutter;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Mumble {

    private String id;

    private String username;

    private Date time;

    private String message;

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private Mumble() {
    }

    /* package */Mumble(String id, String user, String message, Date time) {
        this.id = id;
        this.username = user;
        this.message = message;
        this.time = time;
    }

    public Mumble(String user, String message) {
        this(IdGenerator.generateId(), user, message, new Date());
    }

    public String getId() {
        return this.id;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return this.time;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    // just here to make Jackson happy
    @SuppressWarnings("unused")
    private void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Mumble)) {
            return false;
        }

        Mumble other = (Mumble) obj;

        EqualsBuilder equals = new EqualsBuilder();
        equals.append(this.id, other.id);
        equals.append(this.username, other.username);
        equals.append(this.message, other.message);

        return equals.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(7, 19);

        hash.append(this.id);

        return hash.toHashCode();
    }

    @Override
    public String toString() {
        return this.username + "==>" + this.message;
    }

}
