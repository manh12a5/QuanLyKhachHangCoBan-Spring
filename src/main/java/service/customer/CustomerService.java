package service.customer;

import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class CustomerService implements ICustomerService {

    private static List<Customer> customerList;
    private static long autoIncreaseId = 0;

    static {
        customerList = asList(
                new Customer(autoIncreaseId++, "1", "1@codegym.vn", "Da Nang"),
                new Customer(autoIncreaseId++, "2", "2@codegym.vn", "Quang Tri"),
                new Customer(autoIncreaseId++, "3", "3@codegym.vn", "Ha Noi"),
                new Customer(autoIncreaseId++, "4", "4@codegym.vn", "Sai Gon"),
                new Customer(autoIncreaseId++, "5", "5@codegym.vn", "Da Nang")
        );
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerList);
    }

    @Override
    public Customer findOne(Long id) {
        return customerList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customer.getId() == null ? persist(customer) : merge(customer);
    }

    @Override
    public List<Customer> save(List<Customer> customers) {
        return customers.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return customerList.stream().anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return ids.stream()
                .map(this::findOne)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return customerList.size();
    }

    @Override
    public void delete(Long id) {
        customerList.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public void delete(Customer customer) {
        delete(customer.getId());
    }

    @Override
    public void delete(List<Customer> customers) {
        customers.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        customerList = new ArrayList<>();
    }

    private Customer persist(Customer customer) {
        Customer clone = customer.clone();
        clone.setId(autoIncreaseId++);
        customerList.add(clone);
        return clone;
    }

    private Customer merge(Customer customer) {
        Customer origin = findOne(customer.getId());
        origin.setName(customer.getName());
        origin.setEmail(customer.getEmail());
        origin.setAddress(customer.getAddress());
        return origin;
    }

}
