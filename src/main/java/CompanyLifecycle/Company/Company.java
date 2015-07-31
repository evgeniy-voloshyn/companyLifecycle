package CompanyLifecycle.Company;

import CompanyLifecycle.employee.Employee;
import CompanyLifecycle.position.*;
import CompanyLifecycle.util.*;


import java.util.*;

/**
 * Created by vitamin on 23.07.2015.
 */
public class Company {

    private static final Company INSTANCE = new Company();

    private List<Employee> employees;

    private Map<Position, List<Employee>> positionsMap = Customizer.getPositionsMap();

    private List<Employee> freelancers;

    private List<Position> positionsList = Customizer.getPositions();

    private Director director;

    private Accountant accountant;

    public Company() {

    }

    public static Company getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Company company = Company.getInstance();
        company.run(20);
        company.createReport();
    }

    public void run(int numberOfEmployees){
        int numberOfWeeks = 4;
        configure(numberOfEmployees);
        for (int i = 0; i < numberOfWeeks; i++) {
            start();
        }
    }

    private void start(){
        int hoursPerWeek = 40;
        for (int i = 0; i < hoursPerWeek; i++) {
            director.setupTasks(positionsMap);
            director.notifyEmployees();
            freelancers = director.getFreelancers();
        }
        accountant.paySalary(employees);
        accountant.paySalary(freelancers);
    }


    private void configure(int numberOfEmployees) {
        employees = Customizer.getEmployees(numberOfEmployees);
        director = getDirector();
        accountant = getAccountant();
        employees.forEach(director::addObserver);
    }

    public void createReport(){
        ReportGenerator generator = ReportGenerator.getInstance();
        generator.createReport(getInstance());
    }

    public Director getDirector() {
        for (Employee employee : employees) {
            for (Position position : employee.getPositionList()) {
                if (position == Director.getInstance())
                    return (Director) position;
            }
        }
        return null;
    }

    public Accountant getAccountant(){
        for (Employee employee : employees) {
            for (Position position : employee.getPositionList()) {
                if (position == Accountant.getInstance())
                    return (Accountant) position;
            }
        }
        return null;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Position> getPositions(){
        return positionsList;
    }

    public List<Employee> getFreelancers() {
        return freelancers;
    }

}
