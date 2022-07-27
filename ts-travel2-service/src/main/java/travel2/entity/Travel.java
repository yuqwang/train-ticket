package travel2.entity;

import travel2.entity.Trip;
import edu.fudan.common.util.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author fdse
 */
@Data
public class Travel {

    private Trip trip;

    private String startPlace;

    private String endPlace;

    private String departureTime;

    public Travel(){
        //Default Constructor
    }

    public Date getDepartureTime(){
        return StringUtils.String2Date(this.departureTime);
    }
    public void setDepartureTime(Date departureTime){
        this.departureTime = StringUtils.Date2String(departureTime);
    }

}
