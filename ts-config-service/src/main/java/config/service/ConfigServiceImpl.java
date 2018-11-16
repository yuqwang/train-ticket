package config.service;

import config.domain.Config;
import config.domain.Information;
import config.domain.Information2;
import config.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigRepository repository;

//    public String create(String name, String value,String description){
//        if(repository.findByName(name) != null){
//            String result = "Config " + name + " already exists.";
//            return result;
//        }else{
//            Config config = new Config(name,value,description);
//            repository.save(config);
//            return "true";
//        }
//    }

    public String create(Information info, HttpHeaders headers){
        if(repository.findByName(info.getName()) != null){
            String result = "Config " + info.getName() + " already exists.";
            return result;
        }else{
            Config config = new Config(info.getName(),info.getValue(),info.getDescription());
            repository.save(config);
            return "true";
        }
    }

    public String update(Information info, HttpHeaders headers){
        if(repository.findByName(info.getName()) == null){
            String result = "Config " + info.getName() + " doesn't exist.";
            return result;
        }else{
            Config config = new Config(info.getName(),info.getValue(),info.getDescription());
            repository.save(config);
            return "true";
        }
    }

    public Config retrieve(Information2 info, HttpHeaders headers){
        if(repository.findByName(info.getName()) == null){
            return null;
        }else{

            /*----------------------
              ----- CPU Defect------
              ----------------------*/
            //injectCPUDefect();
            return repository.findByName(info.getName());
        }
    }

    public String query(Information2 info, HttpHeaders headers){
        if(repository.findByName(info.getName()) == null){
            return null;
        }else{
            /*----------------------
              ----- OOM Defect------
              ----------------------*/
            //injectMemoryDefect(1);

            return repository.findByName(info.getName()).getValue();
        }
    }

    public String delete(Information2 info, HttpHeaders headers){
        if(repository.findByName(info.getName()) == null){
            String result = "Config " + info.getName() + " doesn't exist.";
            return result;
        }else{
            repository.deleteByName(info.getName());
            return "true";
        }
    }

    @Override
    public List<Config> queryAll(HttpHeaders headers){
        return repository.findAll();
    }

    private void injectMemoryDefect(int defectType) {
        Set<Config> configs = new HashSet<>();

        switch (defectType) {
            case 1:
                for (int i = 0; i < 10000000; i++) {
                    Config config = new Config("TestName", "TestValue", "TestDescription");
                    configs.add(config);
                }
                break;
        }
    }
}
