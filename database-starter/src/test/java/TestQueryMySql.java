
import org.junit.Assert;
import org.junit.Test;
import tomdatbenik.database.models.LocalhostDatabase;
import tomdatbenik.database.models.abstractClasses.Database;
import tomdatbenik.database.models.abstractClasses.Parameter;
import tomdatbenik.database.models.abstractClasses.mySql.MySqlParameter;
import tomdatbenik.database.models.data.DataCollection;

import java.util.ArrayList;
import java.util.List;

public class TestQueryMySql {

    private final Database database = new LocalhostDatabase();

    @Test
    public void TestQueryWithListReturn()
    {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new MySqlParameter(-1));
        parameters.add(new MySqlParameter(0));

        String query = "SELECT * FROM user WHERE id = ? OR id = ?";

        DataCollection dataCollection = database.excecuteQuery(query, parameters);

        String name1 = dataCollection.getRows().get(0).getDataByName("username").getValue().toString();
        String name2 = dataCollection.getRows().get(1).getDataByName("username").getValue().toString();

        Assert.assertEquals(name1,"Test");
        Assert.assertEquals(name2,"Test1");
    }

    @Test
    public void TestQueryWithParameter() {
        String query = "SELECT * FROM stockpile WHERE id = ?";

        DataCollection dataCollection = database.excecuteQuery(query, new MySqlParameter(-1));

        Integer autonomy = (Integer) dataCollection.getDataByName("autonomy").getValue();
        Integer gold = (Integer) dataCollection.getDataByName("gold").getValue();
        Integer population = (Integer) dataCollection.getDataByName("population").getValue();
        Integer adventurers = (Integer) dataCollection.getDataByName("adventurers").getValue();
        Integer food = (Integer) dataCollection.getDataByName("food").getValue();
        Integer wood = (Integer) dataCollection.getDataByName("wood").getValue();
        Integer stone = (Integer) dataCollection.getDataByName("stone").getValue();
        Integer iron = (Integer) dataCollection.getDataByName("iron").getValue();

        Assert.assertEquals(autonomy, Integer.valueOf(1));
        Assert.assertEquals(gold, Integer.valueOf(2));
        Assert.assertEquals(population, Integer.valueOf(3));
        Assert.assertEquals(adventurers, Integer.valueOf(4));
        Assert.assertEquals(food, Integer.valueOf(5));
        Assert.assertEquals(wood, Integer.valueOf(6));
        Assert.assertEquals(stone, Integer.valueOf(7));
        Assert.assertEquals(iron, Integer.valueOf(8));
    }

    @Test
    public void TestQueryWithParameterList() {
        Database database = new LocalhostDatabase();

        List<Parameter> parameterList = new ArrayList<Parameter>();

        parameterList.add(new MySqlParameter(-1));
        parameterList.add(new MySqlParameter(1));

        String query = "SELECT * FROM stockpile WHERE id = ? AND autonomy = ?";

        DataCollection dataCollection = database.excecuteQuery(query, parameterList);

        Integer autonomy = (Integer) dataCollection.getDataByName("autonomy").getValue();
        Integer gold = (Integer) dataCollection.getDataByName("gold").getValue();
        Integer population = (Integer) dataCollection.getDataByName("population").getValue();
        Integer adventurers = (Integer) dataCollection.getDataByName("adventurers").getValue();
        Integer food = (Integer) dataCollection.getDataByName("food").getValue();
        Integer wood = (Integer) dataCollection.getDataByName("wood").getValue();
        Integer stone = (Integer) dataCollection.getDataByName("stone").getValue();
        Integer iron = (Integer) dataCollection.getDataByName("iron").getValue();

        Assert.assertEquals(autonomy, Integer.valueOf(1));
        Assert.assertEquals(gold, Integer.valueOf(2));
        Assert.assertEquals(population, Integer.valueOf(3));
        Assert.assertEquals(adventurers, Integer.valueOf(4));
        Assert.assertEquals(food, Integer.valueOf(5));
        Assert.assertEquals(wood, Integer.valueOf(6));
        Assert.assertEquals(stone, Integer.valueOf(7));
        Assert.assertEquals(iron, Integer.valueOf(8));
    }
}
