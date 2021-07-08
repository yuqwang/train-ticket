package consignprice.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xywu
 * @date 2021/07/06
 */
public class MemoryDefect {
    public static void injectMemoryDefect() {
        List<String> defects = new ArrayList<>();
        for (int i = 0; i < 1000000000; i++) {
            defects.add(i + "");
        }
    }
}
