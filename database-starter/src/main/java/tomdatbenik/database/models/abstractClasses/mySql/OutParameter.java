package tomdatbenik.database.models.abstractClasses.mySql;

public abstract class OutParameter {
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public OutParameter(Object value) {
        this.value = value;
    }
}
