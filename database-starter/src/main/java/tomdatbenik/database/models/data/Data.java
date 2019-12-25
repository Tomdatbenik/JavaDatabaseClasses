package tomdatbenik.database.models.data;

/**
 * Author Tom van Kaathoven
 */
public class Data {

    private String name;
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
