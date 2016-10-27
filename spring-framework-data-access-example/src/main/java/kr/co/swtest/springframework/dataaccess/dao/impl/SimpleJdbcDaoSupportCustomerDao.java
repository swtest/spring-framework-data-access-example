package kr.co.swtest.springframework.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import kr.co.swtest.springframework.dataaccess.dao.CustomerDao;
import kr.co.swtest.springframework.dataaccess.dao.mapper.impl.CustomerDtoRowMapper;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * 고객 DAO 구현체(Spring JdbcDaoSupport)
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@Repository("SimpleJdbcDaoSupportCustomerDao")
public class SimpleJdbcDaoSupportCustomerDao extends JdbcDaoSupport implements CustomerDao {
    
    /**
     * 생성자
     */
    @Autowired
    public SimpleJdbcDaoSupportCustomerDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("insert into customer (cust_id, cust_nm, cust_email) values ?, ?, ?");
        getJdbcTemplate().update(sql.toString(), customer.getId(), customer.getName(), customer.getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto readCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("select cust_id, cust_nm, cust_email from customer where cust_id = ?");
        try {
            return getJdbcTemplate().queryForObject(sql.toString(), new CustomerDtoRowMapper(), customerId);
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

        return getJdbcTemplate().query(sql.toString(), new CustomerDtoRowMapper(), params.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(CustomerDto customer) {
        StringBuffer sql = new StringBuffer("update customer set cust_nm = ?, cust_email = ? where cust_id = ?");
        getJdbcTemplate().update(sql.toString(), customer.getName(), customer.getEmail(), customer.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerById(int customerId) {
        StringBuffer sql = new StringBuffer("delete from customer where cust_id = ?");
        getJdbcTemplate().update(sql.toString(), customerId);
    }

}
