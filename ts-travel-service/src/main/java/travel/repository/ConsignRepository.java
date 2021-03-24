package travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import travel.entity.consign.ConsignRecord;

import java.util.ArrayList;

/**
 * @author fdse
 */
@Component
public interface ConsignRepository extends JpaRepository<ConsignRecord, Integer> {

    /**
     * find by account id
     *
     * @param accountId account id
     * @return ArrayList<ConsignRecord>
     */
    ArrayList<ConsignRecord> findByAccountId(String accountId);

    /**
     * find by order id
     *
     * @param accountId account id
     * @return ConsignRecord
     */
    ArrayList<ConsignRecord> findByOrderId(String accountId);

    /**
     * find by consignee
     *
     * @param consignee consignee
     * @return ArrayList<ConsignRecord>
     */
    ArrayList<ConsignRecord> findByConsignee(String consignee);

    /**
     * find by id
     *
     * @param id id
     * @return ConsignRecord
     */
    ConsignRecord findById(Integer id);
}
