package fdse.microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Station")
public class Station {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "assigned")
    private String id;

    private String name;

    private int stayTime;

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
