package database.models.abstractclasses;

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
