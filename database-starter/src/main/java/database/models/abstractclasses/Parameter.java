package database.models.abstractclasses;

/**
 * Author Tom van Kaathoven
 */
public abstract class Parameter {
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Parameter(Object value) {
        this.value = value;
    }
}
