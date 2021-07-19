package preserveOther.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import preserveOther.entity.OrderTicketsInfo;

import java.util.concurrent.ExecutionException;

/**
 * @author fdse
 */
public interface PreserveOtherService {

    Response preserve(OrderTicketsInfo oti, HttpHeaders headers) throws ExecutionException, InterruptedException;
}
