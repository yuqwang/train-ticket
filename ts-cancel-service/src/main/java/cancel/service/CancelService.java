package cancel.service;

import cancel.domain.CalculateRefundResult;
import cancel.domain.CancelOrderInfo;
import cancel.domain.CancelOrderResult;
import org.springframework.http.HttpHeaders;

public interface CancelService {

    CalculateRefundResult calculateRefund(CancelOrderInfo info, HttpHeaders headers);

    CancelOrderResult cancelOrderVersion2(CancelOrderInfo info, String loginToken,
                                          String loginId, HttpHeaders headers) throws Exception;

}
