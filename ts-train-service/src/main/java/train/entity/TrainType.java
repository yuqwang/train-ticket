package train.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TrainType")
public class TrainType {

    @Id
    @Column(name = "trainTypeId")
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "assigned")
    private String trainTypeId;

    private int economyClass;

    private int confortClass;

    private int averageSpeed;

    public TrainType(String trainTypeId, int economyClass, int confortClass) {
        this.trainTypeId = trainTypeId;
        this.economyClass = economyClass;
        this.confortClass = confortClass;
    }
}
