package CompanyLifecycle.employee;

import CompanyLifecycle.position.Position;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by vitamin on 23.07.2015.
 */
public interface Employee extends Observer {

    List<Position> getPositionList();

    void addToPositionList(Position position);

    int getMonthlyWorkedHours();

    BigDecimal getSalary();

    void addToSalary(BigDecimal salary);

    Map<Position, Integer> getWorkedHoursMap();

    boolean doTask(Position position);

}
