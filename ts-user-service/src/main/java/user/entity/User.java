package user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 5655276309372918765L;

    @Id
    @Column(name = "userID")
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "assigned")
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private int gender;

    @Column(name = "documentType")
    private int documentType;

    @Column(name = "documentNum")
    private String documentNum;

    @Column(name = "email")
    private String email;

}
