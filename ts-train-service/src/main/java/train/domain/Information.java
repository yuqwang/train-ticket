package train.domain;

import org.springframework.data.annotation.Id;

import javax.validation.Valid;


public class Information {

    @Valid
    @Id
    private String id;

    @Valid
    private long economyClass;

    @Valid
    private long confortClass;

    private long averageSpeed;

    public Information() {
        //Default Constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(long economyClass) {
        this.economyClass = economyClass;
    }

    public long getConfortClass() {
        return confortClass;
    }

    public void setConfortClass(long confortClass) {
        this.confortClass = confortClass;
    }

    public long getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(long averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
