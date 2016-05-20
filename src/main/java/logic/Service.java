package logic;

import java.util.List;

/**
 * Created by V3790147 on 5/19/2016.
 */
public interface Service {

    LogEntity findById(Long id);

    Iterable<LogEntity> getAll();

    LogRepository upload();

    List<LogEntity> findByIp(String ip);

    int findBytesByIp(String ip);

    public void save(LogEntity entity);

    public void displayByIp(String ip);

    List<LogEntity> findAll();
}
