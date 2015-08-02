package CompanyLifecycle.employee;

import CompanyLifecycle.position.Position;

import java.math.BigDecimal;
import java.util.*;


public interface Employee extends Observer {

    List<Position> getPositionList();

    void addToPositionList(Position position);

    int getMonthlyWorkedHours();

    BigDecimal getSalary();

    void addToSalary(BigDecimal salary);

    Map<Position, Integer> getWorkedHoursMap();

    boolean doTask(Position position);

}
