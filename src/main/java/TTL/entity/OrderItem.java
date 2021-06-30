package TTL.entity;

import com.opencsv.bean.CsvBindByName;

public class OrderItem{
    private String dish;
    private int count;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "(" + dish + "," + count + ")";
    }
}
