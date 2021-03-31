package fdse.microservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autoId;

    private String id;

    private String name;

    private int stayTime;
}
