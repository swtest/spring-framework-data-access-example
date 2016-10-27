package kr.co.swtest.springframework.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.swtest.springframework.dataaccess.dao.CustomerDao;
import kr.co.swtest.springframework.dataaccess.dao.mapper.impl.CustomerDtoRowMapper;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * SimpleJdbcTemplateCustomerDao
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@Repository("SimpleJdbcTemplateCustomerDao")
public class SimpleJdbcTemplateCustomerDao implements CustomerDao {
    
    /** SimpleJdbcTemplate */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("insert into customer (cust_id, cust_nm, cust_email) values ?, ?, ?");
        this.jdbcTemplate.update(sql.toString(), customer.getId(), customer.getName(), customer.getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto readCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("select cust_id, cust_nm, cust_email from customer where cust_id = ?");
        try {
            return this.jdbcTemplate.queryForObject(sql.toString(), new CustomerDtoRowMapper(), customerId);
        } catch (Exception e) {
            return null;
        }
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

        return this.jdbcTemplate.query(sql.toString(), new CustomerDtoRowMapper(), params.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("update customer set cust_nm = ?, cust_email = ? where cust_id = ?");
        this.jdbcTemplate.update(sql.toString(), customer.getName(), customer.getEmail(), customer.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("delete from customer where cust_id = ?");
        this.jdbcTemplate.update(sql.toString(), customerId);
    }

}
