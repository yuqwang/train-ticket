package fdse.microservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
public class Station {
    @Valid
    @NotNull
    @Id
    @Column(name = "stationId")
    private String id;

    @Valid
    @NotNull
    @Column(name = "stationName")
    private String name;

    private int stayTime;

    public Station(){
        //Default Constructor
        this.id = UUID.randomUUID().toString();
        this.name = "";
    }

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Station(String id, String name, int stayTime) {
        this.id = id;
        this.name = name;
        this.stayTime = stayTime;
    }

}
