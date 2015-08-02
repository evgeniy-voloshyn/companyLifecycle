package CompanyLifecycle.position;

import java.math.BigDecimal;


public class Designer implements Position {

    private static final Designer INSTANCE = new Designer();

    private final String task = "draw layout";

    private BigDecimal hourlyRate = new BigDecimal("10.1");

    private Designer(){}

    public static Designer getInstance() {
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

        Designer designer = (Designer) o;

        if (!task.equals(designer.task)) return false;
        return hourlyRate.equals(designer.hourlyRate);

    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + hourlyRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Designer";
    }
}
