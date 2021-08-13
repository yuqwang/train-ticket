package preserve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assurance {

    private String id;

    /**
     * which order the assurance is related to
     */
    @NotNull
    private String orderId;

    /**
     * the type of assurance
     */
    private AssuranceType type;

    public Assurance(){
        this.orderId = UUID.randomUUID().toString();
    }

    public Assurance(String id, String orderId, AssuranceType type){
        this.id = id;
        this.orderId = orderId;
        this.type = type;
    }

}
