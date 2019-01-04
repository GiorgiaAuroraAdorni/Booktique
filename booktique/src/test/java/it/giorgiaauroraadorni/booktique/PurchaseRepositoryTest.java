package it.giorgiaauroraadorni.booktique;

import it.giorgiaauroraadorni.booktique.model.*;
import it.giorgiaauroraadorni.booktique.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class PurchaseRepositoryTest {
    // Set automatically the attribute to the purchaseRepository instance
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BookRepository bookRepository;

    private List<Purchase> dummyPurchases;
    private List<Payment> dummyPayments;
    private List<Employee> dummyEmployees;
    private List<Address> dummyAddresses;
    private List<Customer> dummyCustomers;
    private List<Item> dummyItems;
    private List<Supplier> dummySuppliers;
    private List<Book> dummyBooks;

    /**
     * Create a list of employees entities that will be use in the test
     */
    private void createDummyEmployee() {
        dummyEmployees = IntStream
                .range(0, 2)
                .mapToObj(i -> new Employee())
                .collect(Collectors.toList());

        // create some employees
        dummyEmployees.get(0).setFiscalCode("GRGBVR75C13G224W");
        dummyEmployees.get(0).setName("Beverley");
        dummyEmployees.get(0).setSurname("Gregory");
        dummyEmployees.get(0).setUsername("BeverleyGregory75");
        dummyEmployees.get(0).setSupervisor(dummyEmployees.get(1));
        dummyEmployees.get(0).setPassword("yJmKKSjRJX4HZXrvxjBs");

        dummyEmployees.get(1).setFiscalCode("STNPTR70A11C933C");
        dummyEmployees.get(1).setName("Peter");
        dummyEmployees.get(1).setSurname("Stone");
        dummyEmployees.get(1).setUsername("PeterStone70");
        dummyEmployees.get(1).setPassword("XxzNh9jMkfWaHhzG2YVG");
        dummyEmployees.get(1).setSupervisor(dummyEmployees.get(1));
        dummyEmployees.get(1).setDateOfBirth(LocalDate.of(1970, 11, 3));
        dummyEmployees.get(1).setEmail("peter.stone40@example.com");
        dummyEmployees.get(1).setMobilePhone("+393733733730");

        // save the employees in the repository
        dummyEmployees = employeeRepository.saveAll(dummyEmployees);
    }

    /**
     * Create a list of addresses entities that will be use in the test
     */
    private void createDummyAddress() {
        dummyAddresses = IntStream
                .range(0, 2)
                .mapToObj(i -> new Address())
                .collect(Collectors.toList());

        // create some addresses
        dummyAddresses.get(0).setStreetAddress("Via Vinicio 59");
        dummyAddresses.get(0).setCity("Montecassiano");
        dummyAddresses.get(0).setProvince("MC");
        dummyAddresses.get(0).setPostalCode("04017");
        dummyAddresses.get(0).setCountry("Italia");

        dummyAddresses.get(1).setStreetAddress("Via Tancredi 96");
        dummyAddresses.get(1).setCity("Fonteblanda");
        dummyAddresses.get(1).setProvince("GR");
        dummyAddresses.get(1).setRegion("Toscana");
        dummyAddresses.get(1).setPostalCode("32349");
        dummyAddresses.get(1).setCountry("Italia");
        dummyAddresses.get(1).setBuilding("Appartamento 62 De Santis del friuli");

        // save the addresses in the repository
        dummyAddresses = addressRepository.saveAll(dummyAddresses);
    }

    /**
     * Create a list of customers entities that will be use in the test
     */
    private void createDummyCustomer() {
        dummyCustomers = IntStream
                .range(0, 2)
                .mapToObj(i -> new Customer())
                .collect(Collectors.toList());

        // create some customers
        dummyCustomers.get(0).setFiscalCode("MTCKLN83C13G224W");
        dummyCustomers.get(0).setName("Kaitlin");
        dummyCustomers.get(0).setSurname("Mitchell");
        Address address = dummyAddresses.get(0);
        dummyCustomers.get(0).setAddress(address);
        dummyCustomers.get(0).setUsername("KaitlinMitchell83");
        dummyCustomers.get(0).setPassword("W422g31C38nLkCtM");

        dummyCustomers.get(1).setFiscalCode("DVSMGN49A01C933C");
        dummyCustomers.get(1).setName("Morgan");
        dummyCustomers.get(1).setSurname("Davison");
        dummyCustomers.get(1).setAddress(dummyAddresses.get(1));
        dummyCustomers.get(1).setUsername("MorganDavison49");
        dummyCustomers.get(1).setPassword("C339c10A94nGmSvD");
        dummyCustomers.get(1).setDateOfBirth(LocalDate.of(1949, 1, 1));
        dummyCustomers.get(1).setEmail("MorganDavidson@mail.com");
        dummyCustomers.get(1).setMobilePhone("+393733733730");

        // save the customers in the repository
        dummyCustomers = customerRepository.saveAll(dummyCustomers);
    }

    /**
     * Create a list of books entities that will be use in the test
     */
    private void createDummyBook() {
        dummyBooks = IntStream
                .range(0, 3)
                .mapToObj(i -> new Book())
                .collect(Collectors.toList());

        // Create some books
        dummyBooks.get(0).setIsbn("978-84-08-04364-5");
        dummyBooks.get(0).setTitle("Mountain Of Dreams");
        dummyBooks.get(0).setPublisher("Adventure Publications");

        dummyBooks.get(1).setIsbn("8877827025");
        dummyBooks.get(1).setTitle("Young In The West");
        dummyBooks.get(1).setSubtitle("The A - Z Guide");
        dummyBooks.get(1).setPublisher("Lyon Publishing");
        dummyBooks.get(1).setBookFormat(Book.Format.hardcover);
        dummyBooks.get(1).setEdition(1);
        dummyBooks.get(1).setLanguage("english");
        dummyBooks.get(1).setPublicationDate(LocalDate.of(1999, 1, 1));

        dummyBooks.get(2).setIsbn("88-7782-702-6");
        dummyBooks.get(2).setTitle("After Young In The West");
        dummyBooks.get(2).setPublisher("Lyon Publishing");
        dummyBooks.get(2).setBookFormat(Book.Format.hardcover);
        dummyBooks.get(2).setEdition(1);
        dummyBooks.get(2).setLanguage("english");
        dummyBooks.get(2).setPublicationDate(LocalDate.of(2000, 1, 1));
        dummyBooks.get(2).addPrequel(dummyBooks.get(2));

        // save the books in the repository
        dummyBooks = bookRepository.saveAll(dummyBooks);
    }

    /**
     * Create a list of suppliers entities that will be use in the test
     */
    private void createDummySupplier() {
        dummySuppliers = IntStream
                .range(0, 2)
                .mapToObj(i -> new Supplier())
                .collect(Collectors.toList());

        // create some suppliers
        dummySuppliers.get(0).setCompanyName("Centibook Supplier S.r.l.s.");

        dummySuppliers.get(1).setCompanyName("Speed Book S.r.l.");
        dummySuppliers.get(1).setEmail("speedbook@srl.com");
        dummySuppliers.get(1).setPhoneNumber("026512158");

        // save the suppliers in the repository
        dummySuppliers = supplierRepository.saveAll(dummySuppliers);
    }

    /**
     * Create a list of items entities that will be use in the test
     */
    private void createDummyItem() {
        dummyItems = IntStream
                .range(0, 3)
                .mapToObj(i -> new Item())
                .collect(Collectors.toList());

        // create some items
        dummyItems.get(0).setBookItem(dummyBooks.get(0));
        dummyItems.get(0).setSupplier(dummySuppliers.get(0));
        dummyItems.get(0).setUnitPrice(BigDecimal.valueOf(17.49));
        dummyItems.get(0).setQuantityPerUnit(1);

        dummyItems.get(1).setBookItem(dummyBooks.get(1));
        dummyItems.get(1).setSupplier(dummySuppliers.get(1));
        dummyItems.get(1).setUnitPrice(BigDecimal.valueOf(28.99));
        dummyItems.get(1).setQuantityPerUnit(1);

        dummyItems.get(2).setBookItem(dummyBooks.get(2));
        dummyItems.get(2).setSupplier(dummySuppliers.get(1));
        dummyItems.get(2).setUnitPrice(BigDecimal.valueOf(56.00));
        dummyItems.get(2).setQuantityPerUnit(2);

        // save the items in the repository
        dummyItems = itemRepository.saveAll(dummyItems);
    }

    /**
     * Create a list of payments entities that will be use in the test
     */
    private void createDummyPayment() {
        dummyPayments = IntStream
                .range(0, 2)
                .mapToObj(i -> new Payment())
                .collect(Collectors.toList());

        // create a payment with only the mandatory parameters
        dummyPayments.get(0).setCardNumber("4643417337747076");
        dummyPayments.get(0).setCardholderName("Kaitlin Mitchell");
        dummyPayments.get(0).setExpireDate(LocalDate.of(2025, 12, 1));
        dummyPayments.get(0).setCVC("123");

        // create a payment with all the parameters
        dummyPayments.get(1).setCardNumber("9876543212346789");
        dummyPayments.get(1).setCardholderName("Morgan Davison");
        dummyPayments.get(1).setExpireDate(LocalDate.of(2028, 6, 20));
        dummyPayments.get(1).setCVC("456");
        dummyPayments.get(1).setPaymentDate(LocalDate.now());
    }

    /**
     * Create a list of purchases entities that will be use in the test
     */
    private void createDummyPurchase() {
        dummyPurchases = IntStream
                .range(0, 2)
                .mapToObj(i -> new Purchase())
                .collect(Collectors.toList());

        // create a purchase with only the mandatory parameter
        dummyPurchases.get(0).setCustomer(dummyCustomers.get(0));
        dummyPurchases.get(0).setEmployee(dummyEmployees.get(0));
        Set<Item> itemsP1 = new HashSet<>();
        itemsP1.add(dummyItems.get(0));
        itemsP1.add(dummyItems.get(1));
        dummyPurchases.get(0).setItems(itemsP1);
        dummyPurchases.get(0).setOrderDate(LocalDate.of(2019, 10, 10));
        dummyPurchases.get(0).setPaymentMethod(dummyPayments.get(0));

        // create a purchase with all the attributes
        dummyPurchases.get(1).setCustomer(dummyCustomers.get(1));
        dummyPurchases.get(1).setEmployee(dummyEmployees.get(1));
        Set<Item> itemsP2 = new HashSet<>();
        itemsP2.add(dummyItems.get(2));
        dummyPurchases.get(1).setItems(itemsP2);
        dummyPurchases.get(1).setOrderDate(LocalDate.of(2019, 1, 5));
        dummyPurchases.get(1).setPaymentMethod(dummyPayments.get(1));
        dummyPurchases.get(1).setShippingDate(LocalDate.of(2019, 1, 8));
        dummyPurchases.get(1).setStatus(Purchase.Status.shipped);

        dummyPurchases = purchaseRepository.saveAll(dummyPurchases);
    }

    @BeforeEach
    void createDummyEntities() {
        createDummyEmployee();
        createDummyAddress();
        createDummyCustomer();
        createDummyPayment();
        createDummyBook();
        createDummySupplier();
        createDummyItem();
        createDummyPurchase();
    }

    @Test
    void repositoryLoads() {}

    @Test
    void repositoryFindAll() {
        var savedEmployees = employeeRepository.findAll();
        var savedAddresses = addressRepository.findAll();
        var savedCustomers = customerRepository.findAll();
        var savedBooks = bookRepository.findAll();
        var savedSuppliers = supplierRepository.findAll();
        var savedItems = itemRepository.findAll();
        var savedPurchases = purchaseRepository.findAll();

        // check if all the purchases are correctly added to the repository
        assertTrue(savedEmployees.containsAll(dummyEmployees), "findAll should fetch all dummy employees");
        assertTrue(savedAddresses.containsAll(dummyAddresses), "findAll should fetch all dummy addresses");
        assertTrue(savedCustomers.containsAll(dummyCustomers), "findAll should fetch all dummy customers");
        assertTrue(savedBooks.containsAll(dummyBooks), "findAll should fetch all dummy books");
        assertTrue(savedSuppliers.containsAll(dummySuppliers), "findAll should fetch all dummy suppliers");
        assertTrue(savedItems.containsAll(dummyItems), "findAll should fetch all dummy items");
        assertTrue(savedPurchases.containsAll(dummyPurchases), "findAll should fetch all dummy purchases");
    }

}
