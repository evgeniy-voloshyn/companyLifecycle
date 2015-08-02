package CompanyLifecycle.position;

import java.math.BigDecimal;


public class Manager implements Position {

    private static final Manager INSTANCE = new Manager();

    private final String task = "sell service";

    private BigDecimal rate = new BigDecimal("1000");

    private Manager(){}

    public static Manager getInstance() {
        return INSTANCE;
    }

    @Override
    public BigDecimal getRate(int hours) {
        if (hours == 0){
            return new BigDecimal("0.0");
        }
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (!task.equals(manager.task)) return false;
        return rate.equals(manager.rate);

    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Manager";
    }
}
