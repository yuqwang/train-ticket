package security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(schema = "ts-security-mysql")
public class SecurityConfig {
    @Id
    @Column(name = "security_config_id")
    private String id;

    @Column(name = "security_config_name")
    private String name;

    @Column(name = "security_config_value")
    private String value;

    @Column(name = "security_config_description")
    private String description;

    public SecurityConfig() {
        //Default Constructor
    }

}
