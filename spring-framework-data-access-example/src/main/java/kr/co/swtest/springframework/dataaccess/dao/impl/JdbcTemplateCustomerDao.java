package kr.co.swtest.springframework.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.swtest.springframework.dataaccess.dao.CustomerDao;
import kr.co.swtest.springframework.dataaccess.dao.mapper.impl.CustomerDtoMapper;
import kr.co.swtest.springframework.dataaccess.dao.template.JdbcTemplate;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * JdbcTemplateCustomerDao
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@Repository("JdbcTemplateCustomerDao")
public class JdbcTemplateCustomerDao implements CustomerDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("insert into customer (cust_id, cust_nm, cust_email) values ?, ?, ?");
        this.jdbcTemplate.executeUpdate(sql.toString(), customer.getId(), customer.getName(), customer.getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto readCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("select cust_id, cust_nm, cust_email from customer where cust_id = ?");
        return this.jdbcTemplate.getSingleResult(new CustomerDtoMapper(), sql.toString(), customerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> readCustomersByCondition(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("select cust_id, cust_nm, cust_email from customer where 1 = 1");
        if (customer != null && customer.getId() != null) {
            sql.append("and cust_id = ?");
        }
        if (customer != null && customer.getName() != null) {
            sql.append("and cust_nm like ?");
        }
        if (customer != null && customer.getEmail() != null) {
            sql.append("and cust_email = ?");
        }

        List<Object> params = new ArrayList<Object>();
        if (customer != null && customer.getId() != null) {
            params.add(customer.getId());
        }
        if (customer != null && customer.getName() != null) {
            params.add(customer.getName());
        }
        if (customer != null && customer.getEmail() != null) {
            params.add(customer.getEmail());
        }

        return this.jdbcTemplate.getResults(new CustomerDtoMapper(), sql.toString(), params.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("update customer set cust_nm = ?, cust_email = ? where cust_id = ?");
        this.jdbcTemplate.executeUpdate(sql.toString(), customer.getName(), customer.getEmail(), customer.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("delete from customer where cust_id = ?");
        this.jdbcTemplate.executeUpdate(sql.toString(), customerId);
    }

}
