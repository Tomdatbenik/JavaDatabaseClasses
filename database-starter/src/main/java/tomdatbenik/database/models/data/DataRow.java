package tomdatbenik.database.models.data;

import java.util.ArrayList;
import java.util.List;

public class DataRow {
    private final List<Data> dataList = new ArrayList<>();

    public List<Data> getDataList() {
        return dataList;
    }

    public Data getDataByName(String name)
    {
        return dataList.stream().filter(d -> d.getName().equals(name)).findAny().orElse(null);
    }
}
