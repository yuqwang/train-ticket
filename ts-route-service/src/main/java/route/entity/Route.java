package route.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fdse
 */
@Data
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autoId;

    private String id;

    @ElementCollection(fetch = FetchType.EAGER, //加载策略,延迟加载
            targetClass = String.class) //指定集合中元素的类型
    @CollectionTable(name = "stations_INFO") //指定集合生成的表
    @OrderColumn(name = "S_ID") //指定排序列的名称
    private List<String> stations = new ArrayList<String>();

    @ElementCollection(fetch = FetchType.EAGER, //加载策略,延迟加载
            targetClass = Integer.class) //指定集合中元素的类型
    @CollectionTable(name = "distances_INFO") //指定集合生成的表
    @OrderColumn(name = "D_ID") //指定排序列的名称
    private List<Integer> distances = new ArrayList<Integer>();

    private String startStationId;

    private String terminalStationId;
}
