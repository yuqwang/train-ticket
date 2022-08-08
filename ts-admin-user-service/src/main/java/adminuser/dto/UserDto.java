package adminuser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author fdse
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class UserDto {

    private String accountId;

    @ApiModelProperty(name = "userName",value = "userName",dataType = "String",example = "test4fuzzing")
    private String userName;

    @ApiModelProperty(name = "password",value = "password",dataType = "String",example = "test4fuzzing")
    private String password;

    @ApiModelProperty(name = "gender",notes = "adminuser.entity.Gender",value = "gender",dataType = "int",example = "3")
    private int gender;

    @ApiModelProperty(name = "documentType",notes = "adminuser.entity.DocumentType",value = "documentType",dataType = "int",example = "0")
    private int documentType;

    @ApiModelProperty(name = "documentNum",value = "documentNum",dataType = "String",example = "0")
    private String documentNum;

    @ApiModelProperty(name = "email",value = "email",dataType = "String")
    private String email;
}
