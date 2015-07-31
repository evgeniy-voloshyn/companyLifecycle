package CompanyLifecycle.util;

import CompanyLifecycle.employee.Employee;
import CompanyLifecycle.employee.EmployeeImpl;
import CompanyLifecycle.position.*;

import java.util.*;

/**
 * Created by vitamin on 30.07.2015.
 */
public class Customizer {

    private static Random random = new Random();

    private static final List<Position> positions = createPositions();

    private static Map<Position, List<Employee>> positionsMap = setupMap(positions);

    private static List<Employee> employees;

    private static void createEmployees(int numberOfEmployees) {
        employees = new ArrayList<>();
        for (int i = 0; i < numberOfEmployees; i++) {
            Employee employee = new EmployeeImpl();
            setupRandomPositions(employee);
            employees.add(employee);
        }
        checkPositions();
    }

    public static List<Employee> getEmployees(int numberOfEmployees) {
        if (employees == null){
            createEmployees(numberOfEmployees);
        }
        return employees;
    }

    public static List<Position> getPositions() {
        return positions;
    }

    public static Map<Position, List<Employee>> getPositionsMap() {
        return positionsMap;
    }

    private static List<Position> createPositions(){
        List<Position> list = new ArrayList<Position>();
        list.addAll(Arrays.asList(
                Accountant.getInstance(),
                Designer.getInstance(),
                Developer.getInstance(),
                Director.getInstance(),
                Manager.getInstance(),
                Tester.getInstance()
        ));
        return list;
    }

    public static void setupRandomPositions(Employee employee) {
        int i = random.nextInt(positions.size());
        Position position = positions.get(i);
        employee.addToPositionList(position);
        addEmployeeToMap(position, employee);
        if (i < 3){
            if (!employee.getPositionList().contains(positions.get(i + i))){
                employee.addToPositionList(positions.get(i + i));
                addEmployeeToMap(positions.get(i + i), employee);
            } else {
                employee.addToPositionList(positions.get(i + i + 1));
                addEmployeeToMap(positions.get(i + i + 1), employee);
            }
        }

    }

    private static void addEmployeeToMap(Position position, Employee employee){
        List<Employee> employees = positionsMap.get(position);
        employees.add(employee);
        positionsMap.put(position, employees);
    }

    public static Map<Position, List<Employee>> setupMap(List<Position> positionsList){
        Map<Position, List<Employee>> map = new HashMap<>();
        for (Position position : positionsList){
            map.put(position, new ArrayList<Employee>());
        }
        return map;
    }

    private static void checkPositions(){
        List<Employee> directors = positionsMap.get(Director.getInstance());
        if (directors.isEmpty()){
            List<Employee> list = addPosition(directors, Director.getInstance());
            positionsMap.put(Director.getInstance(), list);

        }

        List<Employee> accountants = positionsMap.get(Accountant.getInstance());
        if (accountants.isEmpty()){
            List<Employee> list = addPosition(accountants, Accountant.getInstance());
            positionsMap.put(Accountant.getInstance(), list);

        }

        List<Employee> managers = positionsMap.get(Manager.getInstance());
        if (managers.isEmpty()){
            List<Employee> list = addPosition(managers, Manager.getInstance());
            positionsMap.put(Manager.getInstance(), list);

        }

    }

    private static List<Employee> addPosition(List<Employee> emptyList, Position position) {
        int i = random.nextInt(employees.size() - 1);
        Employee employee = employees.get(i);
        employee.addToPositionList(position);
        emptyList.add(employee);
        return emptyList;
    }
}
