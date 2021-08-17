package user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private String userId;

    @Column(name = "username")
    private String userName;
    private String password;

    private int gender;

    @Column(name = "userDocumentType")
    private int documentType;

    @Column(name = "userDocumentNumber")
    private String documentNum;

    private String email;

}
