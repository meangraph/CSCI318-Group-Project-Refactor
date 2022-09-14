package MercuryCyclists.CSCI318.Repository;

import MercuryCyclists.CSCI318.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
}
