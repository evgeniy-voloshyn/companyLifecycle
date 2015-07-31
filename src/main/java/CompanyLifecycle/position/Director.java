package CompanyLifecycle.position;

import CompanyLifecycle.employee.Employee;
import CompanyLifecycle.employee.Freelancer;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by vitamin on 24.07.2015.
 */
public class Director extends Observable implements Position {

    private static final Director INSTANCE = new Director();

    private final String task = "directing";

    private int numberOfGivenTasks = 0;

    private BigDecimal rate = new BigDecimal("2000");

    private List<Employee> freelancers = new ArrayList<>();

    private Director(){}

    public static Director getInstance() {
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

    public int getNumberOfGivenTasks() {
        return numberOfGivenTasks;
    }

    public List<Employee> getFreelancers() {
        return freelancers;
    }

    public void notifyEmployees() {
        setChanged();
        notifyObservers();

    }


    public void setupTasks(Map<Position, List<Employee>> employeesMap) {
        Set<Position> positions = employeesMap.keySet();
        Iterator<Position> iterator = positions.iterator();
        Random random = new Random();
        int numOfTasks = random.nextInt(10) + 1;
        numberOfGivenTasks += numOfTasks;
        for (int i = 0; iterator.hasNext() && i < numOfTasks; i++) {
            Position position = iterator.next();
            List<Employee> employeeList = employeesMap.get(position);
            boolean tookTask = false;
            for (Employee employee : employeeList) {
                tookTask = employee.doTask(position);
                if (tookTask)
                    break;
            }
            if (!tookTask) {
                hireFreelancer(position);
            }
        }
    }


    private void hireFreelancer(Position position){

        Freelancer freelancer = new Freelancer();
        freelancer.doTask(position);
        freelancers.add(freelancer);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (!task.equals(director.task)) return false;
        return rate.equals(director.rate);

    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Director";
    }
}
