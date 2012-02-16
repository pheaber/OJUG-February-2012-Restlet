package stutter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Mumble {

    private final String id;

    private final User user;

    private final String message;

    public Mumble(User user, String message) {
        this.id = IdGenerator.generateId();
        this.user = user;
        this.message = message;
    }

    public String getId() {
        return this.id;
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
        equals.append(this.user, other.user);
        equals.append(this.message, other.message);

        return equals.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(7, 19);

        hash.append(this.user);
        hash.append(this.message);

        return hash.toHashCode();
    }

    @Override
    public String toString() {
        return this.user.getUsername() + "==>" + this.message;
    }

}
