package sg.edu.nus.iss.day21_lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day21_lecture.model.Customer;
import sg.edu.nus.iss.day21_lecture.repo.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> getallCustomers() {
        return customerRepo.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerRepo.getCustomerById(id);
    }

    public List<Customer> getAllCustomersWithLimitOff(int limit, int offset) {
        return customerRepo.getAllCustomersWithLimitOff(limit, offset);
    }

}
