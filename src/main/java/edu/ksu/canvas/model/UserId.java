package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent the user ID of a user.
 * See the <a href="https://canvas.instructure.com/doc/api/users.html#User">Canvas User</a> documentation.
 */
@CanvasObject(postKey = "user")
public class UserId extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 2L;

    private int id;

    public UserId() {
    }

    public UserId(UserId other) {
        this.id = other.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        UserId user = (UserId) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
