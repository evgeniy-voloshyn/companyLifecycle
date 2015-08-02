package CompanyLifecycle.position;

import java.math.BigDecimal;


public interface Position {

    String getTask();

    BigDecimal getRate(int hours);

}
