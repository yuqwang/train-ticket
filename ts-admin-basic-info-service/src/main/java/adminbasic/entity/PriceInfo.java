package adminbasic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fdse
 */
@Data
@NoArgsConstructor
@ApiModel
public class PriceInfo {

    @ApiModelProperty(name = "id",value = "id",dataType = "String",example = "6d20b8cb-039c-474c-ae25-b6177ea41152")
    private String id;
    @ApiModelProperty(name = "trainType",value = "trainType",dataType = "String",example = "GaoTieOne")
    private String trainType;
    @ApiModelProperty(name = "routeId",value = "routeId",dataType = "String",example = "92708982-77af-4318-be25-57ccb0ff69ad")
    private String routeId;
    @ApiModelProperty(name = "basicPriceRate",value = "basicPriceRate",dataType = "double",example = "0.38")
    private double basicPriceRate;
    @ApiModelProperty(name = "firstClassPriceRate",value = "firstClassPriceRate",dataType = "double",example = "1.0")
    private double firstClassPriceRate;

}
