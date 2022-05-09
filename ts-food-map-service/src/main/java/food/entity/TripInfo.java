package food.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Calendar;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
public class TripInfo {

    private String startingPlace;

    private String endPlace;

    private Date departureTime;

    public TripInfo() {
        // Default Constructor
    }

    public TripInfo(String s, String e) {
        Date d = new Date();
        startingPlace = s;
        endPlace = e;
        departureTime = d;
    }
}
