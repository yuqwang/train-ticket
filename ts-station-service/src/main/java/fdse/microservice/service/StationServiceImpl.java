package fdse.microservice.service;

import edu.fudan.common.util.Response;
import fdse.microservice.entity.*;
import fdse.microservice.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Service
public class StationServiceImpl implements StationService {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private StationRepository repository;

    @Override
    public String insertRedis(Integer times) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(new InsertTask(times));
            t1.start();
            t1.join();
        }
        return "Success" + times;
    }

    class InsertTask implements Runnable {

        int times;

        InsertTask(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            List<String> keys = generateKeys(10, times);
            for (String key : keys) {
                ops.set(key, "1");
            }
        }
    }

    /**
     *
     *   跑数据的时候在需要的地方加上这几个就可以了
     *     ScheduledExecutorService timer = Executors.newScheduledThreadPool(8);
     *
     *         timer.scheduleWithFixedDelay(new SearchTask(), 20, 3, TimeUnit.MILLISECONDS);
     *         timer.scheduleWithFixedDelay(new SearchTask(), 20, 5, TimeUnit.MILLISECONDS);
     *         timer.scheduleWithFixedDelay(new SearchTask(), 20, 8, TimeUnit.MILLISECONDS);
     *         timer.scheduleWithFixedDelay(new SearchTask(), 20, 1, TimeUnit.MILLISECONDS);
     *
     */

    class SearchTask implements Runnable {
        @Override
        public void run() {
            long s1 = System.currentTimeMillis();
            Set<String> a = redisTemplate.keys("*a*a*-*1*-*1*-*");
            System.out.println(a.size() + " ---- szie -- time: ");
            long e1 = System.currentTimeMillis();
            System.out.println(e1 - s1);
        }
    }

    // 生成 times个不同的keys ，每个keys分为keyLen段
    private List<String> generateKeys(int keyLen, int times) {
        List<String> keysList = new ArrayList<String>(times);
        for (int i = 0; i < times; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < keyLen; j++) {
                sb.append(new Random().nextInt());
            }
            keysList.add("aaaaaa-" + sb.toString());
        }
        return keysList;
    }


    @Override
    public Response create(Station station, HttpHeaders headers) {
        if (repository.findStationById(station.getId()) == null) {
            station.setStayTime(station.getStayTime());
            repository.save(station);
            return new Response<>(1, "Create success", station);
        }
        return new Response<>(0, "Already exists", station);
    }


    @Override
    public boolean exist(String stationName, HttpHeaders headers) {
        boolean result = false;
        if (repository.findByName(stationName) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public Response update(Station info, HttpHeaders headers) {

        if (repository.findStationById(info.getId()) == null) {
            return new Response<>(0, "Station not exist", info);
        } else {
            Station station = new Station(info.getId(), info.getName());
            station.setStayTime(info.getStayTime());
            repository.save(station);
            return new Response<>(1, "Update success", station);
        }
    }


    @Override
    public Response delete(Station info, HttpHeaders headers) {

        if (repository.findStationById(info.getId()) != null) {
            Station station = new Station(info.getId(), info.getName());
            repository.delete(station);
            return new Response<>(1, "Delete success", station);
        }
        return new Response<>(0, "Station not exist", info);
    }

    @Override
    public Response query(HttpHeaders headers) {
        List<Station> stations = repository.findAll();
        if (stations != null && stations.size() > 0) {
            return new Response<>(1, "Find all content", stations);
        } else {
            return new Response<>(0, "No content", null);
        }
    }

    @Override
    public Response queryForId(String stationName, HttpHeaders headers) {
        Station station = repository.findByName(stationName);

        if (station != null) {
            return new Response<>(1, "Success", station.getId());
        } else {
            return new Response<>(0, "Not exists", stationName);
        }
    }


    @Override
    public Response queryForIdBatch(List<String> nameList, HttpHeaders headers) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            Station station = repository.findByName(nameList.get(i));
            if (station == null) {
                result.add("Not Exist");
            } else {
                result.add(station.getId());
            }
        }

        if (result.size() > 0) {
            return new Response<>(1, "Success", result);
        } else {
            return new Response<>(0, "No content according to name list", nameList);
        }

    }

    @Override
    public Response queryById(String stationId, HttpHeaders headers) {
        Station station = repository.findStationById(stationId);
        if (station != null) {
            return new Response<>(1, "Success", station.getName());
        } else {
            return new Response<>(0, "No that stationId", stationId);
        }
    }

    @Override
    public Response queryByIdBatch(List<String> idList, HttpHeaders headers) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Station station = repository.findStationById(idList.get(i));
            if (station != null) {
                result.add(station.getName());
            }
        }

        if (result.size() > 0) {
            return new Response<>(1, "Success", result);
        } else {
            return new Response<>(0, "No stationNamelist according to stationIdList", result);
        }

    }
}
