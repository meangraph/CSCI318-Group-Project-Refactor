package MercuryCyclists.CSCI318.Service;


import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {


    private final ContactRepo contactRepo;

    @Autowired
    public ContactService(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public void addContact(Contact contact){contactRepo.save(contact);}

    public List<Contact> getAllContacts(){return contactRepo.findAll();}

    public Contact getContactById(Long id){return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact: " + id + " Not found")); }

    public void deleteContactById(Long id){
        Contact contact = getContactById(id);

        contactRepo.delete(contact);
    }

    public Contact updateContactById(Long id, Contact newContact){
        return contactRepo.findById(id).map(contact -> {
            contact.setName(newContact.getName());
            contact.setPhone(newContact.getPhone());
            contact.setEmail(newContact.getEmail());
            contact.setPosition(newContact.getPosition());
            return contactRepo.save(contact);
        }).orElseGet(() -> {
            newContact.setID(id);
            return contactRepo.save(newContact);
        });
    }
}
