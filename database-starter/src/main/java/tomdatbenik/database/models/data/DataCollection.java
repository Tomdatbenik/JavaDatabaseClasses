package tomdatbenik.database.models.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Tom van Kaathoven
 */
public class DataCollection {

    private final List<Data> dataList = new ArrayList<>();
    private final List<DataRow> rows = new ArrayList<>();

    public List<Data> getDataList() {
        return dataList;
    }

    public List<DataRow> getRows() {
        return rows;
    }

    public DataRow addRow()
    {
        DataRow dataRow = new DataRow();
        rows.add(dataRow);

        return dataRow;
    }

    public Data getDataByName(String name)
    {
        return dataList.stream().filter(d -> d.getName().equals(name)).findAny().orElse(null);
    }
}
