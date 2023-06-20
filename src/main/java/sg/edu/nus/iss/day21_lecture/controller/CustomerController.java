package sg.edu.nus.iss.day21_lecture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day21_lecture.model.Customer;
import sg.edu.nus.iss.day21_lecture.service.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getallCustomers();
    }

    @GetMapping(path = "/limit")
    public List<Customer> getAllCustomersWithLimitAndOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return customerService.getAllCustomersWithLimitOff(limit, offset);
    }

    @GetMapping("/{cust-id}")
    public Customer getCustomer(@PathVariable("cust-id") int custId) {
        return customerService.getCustomerById(custId);
    }
    
    
}
