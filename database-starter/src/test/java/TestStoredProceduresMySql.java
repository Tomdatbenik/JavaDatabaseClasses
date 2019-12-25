
import org.junit.Assert;
import org.junit.Test;
import tomdatbenik.database.models.LocalhostDatabase;
import tomdatbenik.database.models.abstractClasses.Database;
import tomdatbenik.database.models.abstractClasses.mySql.MySqlParameter;
import tomdatbenik.database.models.data.DataCollection;

public class TestStoredProceduresMySql {
    private final Database database = new LocalhostDatabase();

    @Test
    public void TestStoredProcedureWithParameter() {
        String storedProcedure = "CALL `getUserTest`(?)";

        DataCollection dataCollection = database.excecuteQuery(storedProcedure,new MySqlParameter(-1));

        Integer id = (Integer)dataCollection.getDataByName("id").getValue();
        String username = (String)dataCollection.getDataByName("username").getValue();
        String email = (String)dataCollection.getDataByName("email").getValue();

        Assert.assertEquals(id,Integer.valueOf(-1));
        Assert.assertEquals(username,"Test");
        Assert.assertEquals(email,"Test");
    }
}
