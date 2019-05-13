
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Scanner;


public class Store {


    public static void main(String[] args) throws SQLException {
        Storage storage = new Storage();
        System.out.println("Write the command: ");

        while (true) {

            Scanner scanner_comand = new Scanner(System.in);
            String command = scanner_comand.nextLine();

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

            } else if (command.equals("report")) {
                Store.report();
            } else if (command.equals("stop")) {
                return;
            } else {
                System.out.println("It's bad command try again");
            }
        }

    }


    public static String currency() {
        Scanner scanner_currency = new Scanner(System.in);
        String currency = scanner_currency.nextLine();
        if (!currency.equals("EUR") && !currency.equals("USD") && !currency.equals("PLN")) {
            System.out.println("Currency is not correct");
            Store.currency();
        }
        return currency;
    }

    public static String name() {
        Scanner scanner_name = new Scanner(System.in);
        String name = scanner_name.nextLine();
        if (name.matches("[0-9]")) {
            System.out.println("Name is not correct");

            Store.name();
        }
        return name;
    }

    public static double price() {
        Scanner scanner_prise = new Scanner(System.in);
       double price = 0;
        try {
            price = scanner_prise.nextDouble();
        } catch (Exception e) {
            System.out.println("Prise is not correct");
            System.out.println("Tree try again");
            Store.price();
        }
        return price;
    }

    public static Date date() {
        System.out.println("Write date");
        System.out.println("like that yyyy-MM-dd");
        Scanner scanner_date = new Scanner(System.in);
        String date = scanner_date.nextLine();
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateStr = null;
        try {
            dateStr = sFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Date is not correct");
            Store.date();
        }
        return new Date(dateStr.getTime());
    }

    public static void report() {
        System.out.println("Choose currency   USD, EUR, PLN;");
        String currency = Store.currency();
        System.out.println("Write year");
        Scanner scanner_year = new Scanner(System.in);
        Year year = Year.parse(scanner_year.nextLine());
        try {
            new Storage().report(currency, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


