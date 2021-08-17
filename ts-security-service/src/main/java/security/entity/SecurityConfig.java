package security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityConfig {
    @Id
    private String id;

    private String name;

    private String value;

    private String description;

    public SecurityConfig() {
        //Default Constructor
    }

}
