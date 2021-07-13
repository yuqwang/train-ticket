package cancel.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

import java.util.concurrent.ExecutionException;

/**
 * @author fdse
 */
public interface CancelService {

    /**
     * cancel order by order id, login id
     *
     * @param orderId order id
     * @param loginId login id
     * @param headers headers
     * @throws  Exception
     * @return Response
     */
    Response cancelOrder(String orderId, String loginId, HttpHeaders headers) throws InterruptedException, ExecutionException;

    /**
     * calculate refund by login id
     *
     * @param orderId order id
     * @param headers headers
     * @return Response
     */
    Response calculateRefund(String orderId, HttpHeaders headers);

}
