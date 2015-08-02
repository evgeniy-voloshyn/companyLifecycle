package CompanyLifecycle.position;

import java.math.BigDecimal;


public class Developer implements Position {

    private static final Developer INSTANCE = new Developer();

    private final String task = "write code";

    private BigDecimal hourlyRate = new BigDecimal("15.1");

    private Developer(){}

    public static Developer getInstance() {
        return INSTANCE;
    }

    @Override
    public BigDecimal getRate(int hours) {
        BigDecimal decimal = BigDecimal.valueOf(hours);
        decimal = decimal.multiply(hourlyRate);
        return decimal;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (!task.equals(developer.task)) return false;
        return hourlyRate.equals(developer.hourlyRate);

    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + hourlyRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Developer";
    }
}
