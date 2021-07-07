package preserve.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import preserve.entity.OrderTicketsInfo;

import java.util.concurrent.ExecutionException;

/**
 * @author fdse
 */
public interface PreserveService {

    Response preserve(OrderTicketsInfo oti, HttpHeaders headers) throws InterruptedException, ExecutionException;
}
