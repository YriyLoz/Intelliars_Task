
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Scanner;


public class Store {

    static Scanner scanner = new Scanner(System.in);
    static Storage storage = new Storage(new ConnectionSql());

    public static void main(String[] args) throws SQLException {

        System.out.println("Write the command: ");

        // choose necessary command
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("purchase")) {

                System.out.println("Write name");
                String name = Store.name();

                System.out.println("Write price");
                double price = Store.price();

                System.out.println("Choose currency   USD, EUR, PLN;");
                String currency = Store.currency();

                Date dateDB = Store.date();
                storage.addNewProduct(new Product(name, price, currency, dateDB));
            } else if (command.equals("all")) {
                storage.showProduct();
            } else if (command.equals("clear")) {
                Store.clear();
            } else if (command.equals("report")) {
                Store.report();
            } else if (command.equals("stop")) {
                return;
            } else {
                System.out.println("It's bad command try again");
            }
        }

    }

    //methods for input and processing commands
    public static String currency() {
        String currency = scanner.nextLine();
        if (currency.equals("EUR") && currency.equals("USD") && currency.equals("PLN")) {
            return currency;
        }else {
            System.out.println("Currency is not correct");
            currency();
        }
        return currency;
    }

    public static String name() {
        String name = scanner.nextLine();
        if (name.matches("[0-9]")) {
            System.out.println("Name is not correct");
            name();
        }
        return name;
    }

    public static double price() {
        double price = 0;
        try {
            price = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Prise is not correct");
            System.out.println("Tree try again");
            price();
        }
        return price;
    }

    public static Date date() {
        System.out.println("Write date");
        System.out.println("like that yyyy-MM-dd");
        String date = scanner.nextLine();
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateStr = null;
        try {
            dateStr = sFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Date is not correct");
            date();
        }
        return new Date(dateStr.getTime());
    }

    public static void report() {
        System.out.println("Choose currency   USD, EUR, PLN;");
        String currency = Store.currency();
        System.out.println("Write year");
        Year year = Year.parse(scanner.nextLine());
        try {
            storage.report(currency, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clear() throws SQLException {
        Date date = Store.date();
        storage.clear(date);
    }


}


