package edu.fudan.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.fudan.common.entity.SeatClass;
import edu.fudan.common.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class Order {
    @ApiModelProperty(name = "id",value = "id",dataType = "String",example = "5ad7750b-a68b-49c0-a8c0-32776b067703")
    private String id;

    @ApiModelProperty(name = "boughtDate",value = "boughtDate",dataType = "String",example = "1652520330126")
    private String boughtDate;

    @ApiModelProperty(name = "travelDate",value = "travelDate",dataType = "String",example = "1501257600000")
    private String travelDate;

    @ApiModelProperty(name = "travelTime",value = "travelTime",dataType = "String",example = "1367629320000")
    private String travelTime;

    /**
     * Which Account Bought it
     */
    @ApiModelProperty(name = "accountId",value = "accountId",dataType = "String",example = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
    private String accountId;

    /**
     * Tickets bought for whom....
     */
    @ApiModelProperty(name = "contactsName",value = "contactsName",dataType = "String",example = "Contacts_One")
    private String contactsName;

    @ApiModelProperty(name = "documentType",value = "documentType",dataType = "int",example = "1")
    private int documentType;

    @ApiModelProperty(name = "contactsDocumentNumber",value = "contactsDocumentNumber",dataType = "String",example = "DocumentNumber_One")
    private String contactsDocumentNumber;

    @ApiModelProperty(name = "trainNumber",value = "trainNumber",dataType = "String",example = "G1237")
    private String trainNumber;

    @ApiModelProperty(name = "coachNumber",value = "coachNumber",dataType = "int",example = "5")
    private int coachNumber;

    @ApiModelProperty(name = "seatClass",notes = "edu.fudan.common.entity.SeatClass",value = "seatClass",dataType = "int",example = "2")
    private int seatClass;

    @ApiModelProperty(name = "seatNumber",value = "seatNumber",dataType = "String",example = "FirstClass-30")
    private String seatNumber;

    @ApiModelProperty(name = "from",value = "from",dataType = "String",example = "nanjing")
    private String from;

    @ApiModelProperty(name = "to",value = "to",dataType = "String",example = "shanghaihongqiao")
    private String to;

    @ApiModelProperty(name = "status",notes = "edu.fudan.common.entity.OrderStatus",value = "status",dataType = "int",example = "0")
    private int status;

    @ApiModelProperty(name = "price",value = "price",dataType = "String",example = "100.0")
    private String price;

    private String differenceMoney;

    public Order(){
        boughtDate = StringUtils.Date2String(new Date(System.currentTimeMillis()));
        travelDate = StringUtils.Date2String(new Date(123456789));
        trainNumber = "G1235";
        coachNumber = 5;
        seatClass = SeatClass.FIRSTCLASS.getCode();
        seatNumber = "5A";
        from = "shanghai";
        to = "taiyuan";
        status = OrderStatus.PAID.getCode();
        price = "0.0";
        differenceMoney ="0.0";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return getBoughtDate().equals(other.getBoughtDate())
                && getBoughtDate().equals(other.getTravelDate())
                && getTravelTime().equals(other.getTravelTime())
                && accountId .equals( other.getAccountId() )
                && contactsName.equals(other.getContactsName())
                && contactsDocumentNumber.equals(other.getContactsDocumentNumber())
                && documentType == other.getDocumentType()
                && trainNumber.equals(other.getTrainNumber())
                && coachNumber == other.getCoachNumber()
                && seatClass == other.getSeatClass()
                && seatNumber .equals(other.getSeatNumber())
                && from.equals(other.getFrom())
                && to.equals(other.getTo())
                && status == other.getStatus()
                && price.equals(other.price);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        return result;
    }

}
