
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private String name;
    private float prise;
    private String currency;
    private Date date;

    Product(String name, float prise, String currency, Date date) {

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

    public float getPrise() {
        return prise;
    }

    public void setPrise(float prise) {
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

        return "Name : " + name + "\n" + "Prise : " + prise + "\n" + "Currency : " + currency + "\n" + "Date :" + dateFormat + "\n";
    }
}
