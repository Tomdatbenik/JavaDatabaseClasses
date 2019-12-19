package database.models.data;

import java.util.ArrayList;
import java.util.List;

public class DataCollection {

    public final List<Data> dataList = new ArrayList<>();

    public Data getDataByName(String name)
    {
        return dataList.stream().filter(d -> d.getName().equals(name)).findAny().orElse(null);
    }
}
