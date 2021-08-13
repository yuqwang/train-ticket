package contacts.repository;

import contacts.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author fdse
 */
@Repository
public interface ContactsRepository extends JpaRepository<Contacts, String> {

    /**
     * find by id
     *
     * @param id id
     * @return Contacts
     */
    Contacts findById(String id);

    /**
     * find by account id
     *
     * @param accountId account id
     * @return ArrayList<Contacts>
     */
    ArrayList<Contacts> findByAccountId(String accountId);

    /**
     * delete by id
     *
     * @param id id
     * @return null
     */
    void deleteById(String id);

    /**
     * find all
     *
     * @return ArrayList<Contacts>
     */
    @Override
    ArrayList<Contacts> findAll();

}
