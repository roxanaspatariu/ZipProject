package logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by V3790147 on 5/16/2016.
 */
/*@Repository*/
public interface LogRepository extends CrudRepository<LogEntity, Long> {
}
