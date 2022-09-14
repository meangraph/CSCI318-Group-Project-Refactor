package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Model.Supplier;
import MercuryCyclists.CSCI318.Repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final ContactService contactService;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, ContactService contactService) {
        this.supplierRepo = supplierRepo;
        this.contactService = contactService;
    }


    public void addSupplier(Supplier supplier){
        supplierRepo.save(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepo.findAll();
    }

    public Supplier getSupplierById(Long id) { return supplierRepo.findById(id).orElseThrow(() -> new RuntimeException("Supplier: " + id + " Not found")); }

    public Supplier updateSupplierById(Long id, Supplier newSupplier) {
        return supplierRepo.findById(id).map(supplier -> {
            supplier.setBase(newSupplier.getBase());
            supplier.setCompanyName(newSupplier.getCompanyName());
            supplier.setContactList(newSupplier.getContactList());
            return supplierRepo.save(supplier);
        }).orElseGet(() -> {
            newSupplier.setID(id);
            return supplierRepo.save(newSupplier);
        });
    }

    public void deleteSupplierById(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepo.delete(supplier);

    }

    public void addContactToSupplier(Long supplierID, Long contactID){
        Supplier supplier = getSupplierById(supplierID);
        Contact contact = contactService.getContactById(contactID);
        contact.setSupplier(supplier);
        supplier.addContact(contact);

        System.out.println("ContactList: " + supplier.getContactList().get(0).getName() + " Supplier:" + supplier.getContactList().get(0).getSupplier().getCompanyName());


    }

    public void removeContactFromSupplier(Long supplierID, Long contactID){
        Supplier supplier = getSupplierById(supplierID);
        Contact contact = contactService.getContactById(contactID);

        supplier.removeContact(contact);
    }
}
