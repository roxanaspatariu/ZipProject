package logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by V3790147 on 5/19/2016.
 */
@Component
public class ServiceImpl implements Service {

    @Autowired
    LogRepository logRepository;

    @Override
    public LogEntity findById(Long id){
        return logRepository.findOne(id);
    }

    @Override
    public Iterable<LogEntity> getAll(){
        return logRepository.findAll();
    }

    @Override
    public LogRepository upload(){
        return logRepository;
    }

    @Override
    public List<LogEntity> findByIp(String ip) {
        List list = new ArrayList<LogEntity>();
        for (LogEntity e : logRepository.findAll()) {
            if (e.getIp().equals(ip)){
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public int findBytesByIp(String ip) {
        int numberOfBytes = 0;
        for (LogEntity e : logRepository.findAll()) {
            if (e.getIp().equals(ip)){
                numberOfBytes = numberOfBytes + Integer.parseInt(e.getbytesSent());
            }
        }
        return numberOfBytes;
    }

    @Override
    public void save(LogEntity entity) {
        logRepository.save(entity);
    }

    @Override
    public void displayByIp(String ip) {

        List list = new ArrayList<LogEntity>();
        for (LogEntity e : logRepository.findAll()) {
            if (e.getIp().equals(ip)){
                list.add(e);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("C:\\Users\\v3790147\\Documents\\ZipProject\\src\\main\\java\\resources\\"), list);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<LogEntity> findAll() {
            List list = new ArrayList<LogEntity>();
            for (LogEntity e : logRepository.findAll()) {
                    list.add(e);

            }
            return list;

    }
}
