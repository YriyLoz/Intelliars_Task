
import com.google.gson.Gson;

import java.sql.*;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    private ConnectionSql connection;
    private Statement statement;
    private ResultSet resultSet;


    public void showProduct() throws SQLException {      // view all database elements
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

    public void addNewProduct(Product product) { //  add products to the database
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

    public  void clear(Date date) throws SQLException { // remove from database by date
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
        double sum = 0;
        String currency = null;
        Map<String, Double> mylist = new HashMap<>();

        while (resultSet.next()) {                 // get a necessary data from a database
            sum = resultSet.getFloat(1);
            currency = resultSet.getString(2);
            mylist.put(currency, sum);
        }

        //----------------------------------------------------------- currency conversion logic
        Map<String, Double> fixerlist = Storage.fixer();
        double result = 0;
        for (Map.Entry<String, Double> entry : mylist.entrySet()) {
            if (currency_conversion.equals(entry.getKey())) {
                result += entry.getValue();
                continue;
            }
            if (entry.getValue().equals(fixerlist.get(entry.getKey()))) {
                result += fixerlist.get(currency_conversion);
            }
            result += entry.getValue() / fixerlist.get(entry.getKey()) * fixerlist.get(currency_conversion);
        }
       // ------------------------------------------------------
        System.out.println(result); // show sum of convertible currencies
    }


    public static Map<String, Double> fixer() { // get data from fixer.io
        Map<String, Double> rates = null;
        try {
            String jsonAsString = HttpClient.get("http://data.fixer.io/api/latest?access_key=2b21f8b7b36fbdb133ebd9fd41bed7f3");
            RatesResponse response = new Gson().fromJson(jsonAsString, RatesResponse.class);
            rates = response.getRates();

        } catch (Exception e) {
            System.out.println(e);
        }
        return rates; // send data for processing in "report"
    }

}


