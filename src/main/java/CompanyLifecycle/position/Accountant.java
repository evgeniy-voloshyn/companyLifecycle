package CompanyLifecycle.position;

import CompanyLifecycle.employee.Employee;

import java.math.BigDecimal;
import java.util.List;


public class Accountant implements Position {

    private static final Accountant INSTANCE = new Accountant();

    private String task = "create report";

    private BigDecimal rate = new BigDecimal("1500");

    private Accountant(){}

    public static Accountant getInstance() {
        return INSTANCE;
    }

    @Override
    public BigDecimal getRate(int hours) {
        if (hours == 0){
            return new BigDecimal("0.0");
        }
        return rate;
    }

    @Override
    public String getTask() {
        return task;
    }


    public void paySalary(List<Employee> employeesList){
        employeesList.forEach(this::computeWeeklySalary);
    }

    private BigDecimal computeWeeklySalary(Employee employee) {
        BigDecimal weeklySalary = new BigDecimal("0.0");
        for (Position position : employee.getPositionList()){
            Integer workedHours = employee.getWorkedHoursMap().get(position);
            BigDecimal rate = position.getRate(workedHours);
            weeklySalary = weeklySalary.add(rate);
        }
        employee.addToSalary(weeklySalary);


        return weeklySalary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accountant that = (Accountant) o;

        if (!task.equals(that.task)) return false;
        return rate.equals(that.rate);

    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Accountant";
    }
}
