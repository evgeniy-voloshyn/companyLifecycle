package CompanyLifecycle.position;

import java.math.BigDecimal;


/**
 * Created by vitamin on 24.07.2015.
 */
public interface Position {

    String getTask();

    BigDecimal getRate(int hours);

}
