
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

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

            String query = "insert into purchases(name,prise,currency,date) values('" + product.getName() + "','" + product.getPrise()
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
        String clear = "delete from purchases WHERE Date = " + date;
        statement.executeUpdate(clear);
        connection.closeConnection();
    }


    public void report(String currency_conversion, Year year) throws SQLException {
        connection = new ConnectionSql();
        statement = connection.getConnection().createStatement();
        resultSet = statement.executeQuery("SELECT sum(price), currency from purchases where year(date) =" + year + " GROUP BY currency;");
        float sum = 0;
        String currency = null;
        Map<String, Float> map = new HashMap<>();
        while (resultSet.next()) {

            sum = resultSet.getFloat(1);
            currency = resultSet.getString(2);
            System.out.println(2019 + " " + currency + " " + sum);
            map.put(currency, sum);

        }

    }


}


