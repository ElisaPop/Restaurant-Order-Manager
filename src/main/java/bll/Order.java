package bll;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    public LocalDate date;

    public Order(int id)
    {
        this.id = id;
        this.date = LocalDate.now();
    }

    public int hashCode() {
        int result = (id ^ (id >>> 32));
        result = 31 * result + date.hashCode();
        return result;
    }

    boolean isWellFormed(){
        return date.isBefore(LocalDate.of(1900, 1, 1)) && id > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
