package assurance.init;

import assurance.entity.Assurance;
import assurance.entity.AssuranceType;
import assurance.repository.AssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author fdse
 */
@Component
public class InitData implements CommandLineRunner {
    @Autowired
    AssuranceRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //do nothing
//        Assurance assurance=new Assurance();
//        String id="ff8080817b3e4c27017b3e4c3bee0000";
//        assurance.setId(id);
//        assurance.setType(AssuranceType.TRAFFIC_ACCIDENT);
//
//        repository.save(assurance);
//
//        Assurance res=repository.findById(id);
    }
}
