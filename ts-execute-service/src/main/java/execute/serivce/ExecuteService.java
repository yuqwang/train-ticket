package execute.serivce;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

import java.util.concurrent.ExecutionException;

/**
 * @author fdse
 */
public interface ExecuteService {

    /**
     * ticket execute by order id
     *
     * @param orderId order id
     * @param headers headers
     * @return Response
     */
    Response ticketExecute(String orderId, HttpHeaders headers) throws ExecutionException, InterruptedException;

    /**
     * ticker collect
     *
     * @param orderId order id
     * @param headers headers
     * @return Response
     */
    Response ticketCollect(String orderId, HttpHeaders headers);

}
