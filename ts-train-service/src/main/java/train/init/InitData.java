package train.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import train.domain.Information;
import train.service.TrainService;


@Component
public class InitData implements CommandLineRunner {

    @Autowired
    TrainService service;

    @Override
    public void run(String... args) throws Exception {
        Information info = new Information();

        info.setId("GaoTieOne");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);

        info.setId("GaoTieTwo");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);

        info.setId("DongCheOne");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);

        info.setId("ZhiDa");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);

        info.setId("TeKuai");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);

        info.setId("KuaiSu");
        info.setConfortClass(Long.MAX_VALUE);
        info.setEconomyClass(Long.MAX_VALUE);
        info.setAverageSpeed(Long.MAX_VALUE);
        service.create(info, null);
    }
}
