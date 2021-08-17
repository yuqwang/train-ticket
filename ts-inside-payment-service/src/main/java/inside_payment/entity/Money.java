package inside_payment.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@Entity
public class Money {

    @Valid
    @NotNull
    @Id
    @Column(name = "money_id")
    private String id;

    @Valid
    @NotNull
    private String userId;

    @Valid
    @NotNull
    private String money; //NOSONAR

    @Valid
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "money_type")
    private MoneyType type;

    public Money(){
        this.id = UUID.randomUUID().toString();
        this.userId = "";
        this.money = "";

    }

}
