package sg.edu.nus.iss.day21_lecture.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day21_lecture.model.Customer;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate template;

    // 1st function
    private final String findAllSql = "select * from customer";
    // 2nd function
    private final String findByIdSql = "select * from customer where id = ?";
    // 3rd function (slide 24)
    private final String findAllLimitOffsetSql = "select * from customer limit ? offset ?";

    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();

        SqlRowSet rs = template.queryForRowSet(findAllSql);

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    public Customer getCustomerById(Integer id) {

        Customer customer = new Customer();
        customer = template.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(Customer.class), id);

        return customer;
    }

    public List<Customer> getAllCustomersWithLimitOff(int limit, int offset) {

        List<Customer> customerList = new ArrayList<>();

        SqlRowSet rs = template.queryForRowSet(findAllLimitOffsetSql, limit, offset);

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);

    }

}
