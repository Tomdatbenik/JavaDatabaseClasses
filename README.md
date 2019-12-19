# JavaDatabaseClasses
Only supports Strings and ints. Will be changed in the future.

MySql example:

public static void GetStockPileByEmail()
    {
        Database database = new LocalhostDatabase();

        List<Parameter> parameterList = new ArrayList<Parameter>();

        parameterList.add(new MySqlParameter("tomtrolt@gmail.com"));
        parameterList.add(new MySqlParameter(1));

        String query = "select * from stockpile where user_email = ? and id = ?";

        DataCollection dataCollection = database.excecuteQuery(query, parameterList);

        Stockpile stockpile = new Stockpile();

        stockpile.setFood((Integer) dataCollection.getDataByName("food").getValue());

        System.out.println(stockpile.getFood());
    }