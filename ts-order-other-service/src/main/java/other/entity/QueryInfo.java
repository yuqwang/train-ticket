package other.entity;

import edu.fudan.common.util.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author fdse
 */
@Data
public class QueryInfo {

    /**
     * account id
     */
    private String loginId;

    private String travelDateStart;

    private String travelDateEnd;

    private String boughtDateStart;

    private String boughtDateEnd;

    private int state;

    private boolean enableTravelDateQuery;

    private boolean enableBoughtDateQuery;

    private boolean enableStateQuery;

    public QueryInfo() {
        //Default Constructor
    }

    public void enableTravelDateQuery(String startTime, String endTime) {
        enableTravelDateQuery = true;
        travelDateStart = startTime;
        travelDateEnd = endTime;
    }

    public void disableTravelDateQuery() {
        enableTravelDateQuery = false;
        travelDateStart = null;
        travelDateEnd = null;
    }

    public void enableBoughtDateQuery(String startTime, String endTime) {
        enableBoughtDateQuery = true;
        boughtDateStart = startTime;
        boughtDateEnd = endTime;
    }

    public void disableBoughtDateQuery() {
        enableBoughtDateQuery = false;
        boughtDateStart = null;
        boughtDateEnd = null;
    }

    public void enableStateQuery(int targetStatus) {
        enableStateQuery = true;
        state = targetStatus;
    }

    public void disableStateQuery() {
        enableTravelDateQuery = false;
        state = -1;
    }

    public Date getTravelDateEnd(){
        return StringUtils.String2Date(travelDateEnd);
    }

    public Date getTravelDateStart(){
        return StringUtils.String2Date(travelDateStart);
    }

    public Date getBoughtDateStart(){
        return StringUtils.String2Date(boughtDateStart);
    }

    public Date getBoughtDateEnd(){
        return StringUtils.String2Date(boughtDateEnd);
    }

    public boolean isEnableTravelDateQuery() {
        return enableTravelDateQuery;
    }

    public boolean isEnableBoughtDateQuery() {
        return enableBoughtDateQuery;
    }

    public boolean isEnableStateQuery() {
        return enableStateQuery;
    }
}
