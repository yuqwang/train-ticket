package travel.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import travel.entity.consign.Consign;

/**
 * @author fdse
 */
public interface ConsignService {

    /**
     * insert consign record
     *
     * @param consignRequest consign request
     * @param headers headers
     * @return Response
     */
    Response insertConsignRecord(Consign consignRequest, HttpHeaders headers);

    /**
     * update consign record
     *
     * @param consignRequest consign request
     * @param headers headers
     * @return Response
     */
    Response updateConsignRecord(Consign consignRequest, HttpHeaders headers);

    /**
     * query by account id
     *
     * @param accountId account id
     * @param headers headers
     * @return Response
     */
    Response queryByAccountId(String accountId, HttpHeaders headers);

    /**
     * query by order id
     *
     * @param orderId order id
     * @param headers headers
     * @return Response
     */
    Response queryByOrderId(String orderId, HttpHeaders headers);

    /**
     * query by consignee
     *
     * @param consignee consignee
     * @param headers headers
     * @return Response
     */
    Response queryByConsignee(String consignee, HttpHeaders headers);
}
