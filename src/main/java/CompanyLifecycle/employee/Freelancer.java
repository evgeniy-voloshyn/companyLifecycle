package CompanyLifecycle.employee;

import CompanyLifecycle.position.Position;


import java.math.BigDecimal;
import java.util.*;


public class Freelancer implements Employee {

    Random random = new Random();

    private static int createdFreelancers = 0;

    private final int id = createdFreelancers + 1;

    private int workedHours = 0;

    private int timeOnTask = 0;

    private BigDecimal salary = new BigDecimal("0.0");

    private List<Position> positionList = new ArrayList<>();

    private Map<Position, Integer> workedHoursMap;

    public Freelancer() {
        createdFreelancers++;
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
        return salary;
    }

    @Override
    public void addToSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getMonthlyWorkedHours(){
        return workedHours;
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
        timeOnTask = random.nextInt(2) + 1;
        workedHours = timeOnTask;
        positionList.add(position);
        addWorkedHours(position, timeOnTask);
        return true;

    }

    @Override
    public void update(Observable o, Object arg) {
        timeOnTask--;
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

        Freelancer that = (Freelancer) o;

        if (id != that.id) return false;
        return positionList.equals(that.positionList);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime* result + positionList.hashCode();
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return positionList.toString();

    }
}
