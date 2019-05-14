
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {     //Class Product for creating an object which is added to the database
    private String name;
    private double prise;
    private String currency;
    private Date date;

    Product(String name, double prise, String currency, Date date) {
        this.name = name;
        this.prise = prise;
        this.currency = currency;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);

        return "Name : " + name + "\n" + "Prise : " + prise + " " + currency + "\n" + "Date :" + dateFormat + "\n";
    }
}
