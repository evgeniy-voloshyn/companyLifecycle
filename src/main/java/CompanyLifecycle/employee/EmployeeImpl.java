package CompanyLifecycle.employee;

import CompanyLifecycle.position.Position;

import java.math.BigDecimal;
import java.util.*;


public class EmployeeImpl implements Employee, Observer {

    private static final long MAX_WORK_HOURS_PER_WEEK = 40;

    Random random = new Random();

    private static int createdEmployees = 0;

    private final int id = createdEmployees + 1;

    private int timeOnTask = 0;

    private int weeklyWorkedHours = 0;

    private int monthlyWorkedHours = 0;

    private int hoursPast = 0;

    private boolean isWorking;

    private BigDecimal monthlySalary = new BigDecimal("0.0");

    private List<Position> positionList = new ArrayList<>();

    private Map<Position, Integer> workedHoursMap;

    public EmployeeImpl() {
        createdEmployees++;
    }

    @Override
    public List<Position> getPositionList() {
        return positionList;
    }

    @Override
    public void addToPositionList(Position position) {
        positionList.add(position);
    }

    @Override
    public BigDecimal getSalary() {
        return monthlySalary;
    }

    @Override
    public void addToSalary(BigDecimal salary) {
        this.monthlySalary = this.monthlySalary.add(salary);
    }

    public int getMonthlyWorkedHours() {
        if (monthlyWorkedHours == 0) {
            for (Map.Entry entry : workedHoursMap.entrySet()) {
                monthlyWorkedHours += (Integer) entry.getValue();
            }
        }
        return monthlyWorkedHours;
    }

    @Override
    public Map<Position, Integer> getWorkedHoursMap() {
        if (workedHoursMap == null) {
            workedHoursMap = setupMap();
        }
        return workedHoursMap;
    }


    @Override
    public boolean doTask(Position position) {
        if (isWorking) {
            return false;
        }
        timeOnTask = random.nextInt(2) + 1;
        if ((weeklyWorkedHours + timeOnTask) <= MAX_WORK_HOURS_PER_WEEK) {
            weeklyWorkedHours += timeOnTask;
            addWorkedHours(position, timeOnTask);
            isWorking = true;
            return isWorking;
        }
        return false;

    }

    @Override
    public void update(Observable o, Object arg) {
        timeOnTask--;
        if (timeOnTask == 0)
            isWorking = false;
        hoursPast++;
        if (hoursPast == MAX_WORK_HOURS_PER_WEEK) {
            weeklyWorkedHours = 0;
            hoursPast = 0;
        }


    }

    private void addWorkedHours(Position position, Integer hours) {
        Map<Position, Integer> workedHoursMap = getWorkedHoursMap();
        Integer hoursWorked = workedHoursMap.get(position);
        hoursWorked += hours;
        workedHoursMap.put(position, hoursWorked);
        this.workedHoursMap = workedHoursMap;
    }

    private Map<Position, Integer> setupMap() {
        Map<Position, Integer> map = new HashMap<>();
        for (Position position : positionList) {
            map.put(position, 0);
        }
        return map;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeImpl employee = (EmployeeImpl) o;

        if (id != employee.id) return false;
        return positionList.equals(employee.positionList);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + positionList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append(" employee with positions: ");
        for (Position position : positionList) {
            builder.append(position.toString());
            builder.append(", ");
        }
        return builder.toString();
    }
}
