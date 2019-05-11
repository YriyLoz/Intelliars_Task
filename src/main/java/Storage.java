
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Storage {
    private ConnectionSql connection;
    private Statement statement;
    private ResultSet resultSet;


    public void showProduct() throws SQLException {
        connection = new ConnectionSql();
        statement = connection.getConnection().createStatement();
        resultSet = statement.executeQuery("select * from purchases");
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            float prise = resultSet.getFloat(3);
            String currency = resultSet.getString(4);
            Date data = resultSet.getDate(5);
            Product product = new Product(name, prise, currency, data);
            System.out.println(product);

        }
        connection.closeConnection();

    }

    public void addNewProduct(Product product) {
        try {
            connection = new ConnectionSql();
            statement = connection.getConnection().createStatement();

            String query = "insert into purchases(Name,Prise,Currency,Date) values('" + product.getName() + "','" + product.getPrise()
                    + "','" + product.getCurrency() + "','" + product.getDate() + "')";

            statement.addBatch(query);
            statement.executeBatch();
            statement.clearBatch();
            System.out.println(product);
            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clear(Date date) throws SQLException {
        connection = new ConnectionSql();
        statement = connection.getConnection().createStatement();

        String clear = "DELETE FROM purchases WHERE Date = " + date;
        statement.executeUpdate(clear);
    }


}


