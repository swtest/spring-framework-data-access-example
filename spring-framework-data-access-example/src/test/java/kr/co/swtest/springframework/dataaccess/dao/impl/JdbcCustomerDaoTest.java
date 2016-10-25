package kr.co.swtest.springframework.dataaccess.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.swtest.springframework.dataaccess.dao.CustomerDao;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * 고객 DAO 구현체(JDBC) 테스트
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ //
        @ContextConfiguration("/testApplicationContext.xml"), //    
        @ContextConfiguration("/applicationContext.xml") //
})
public class JdbcCustomerDaoTest {

    /** 고객 DAO */
    @Autowired
    private CustomerDao customerDao;

    // -------------------------------------------------------------------------
    // Test Method
    // -------------------------------------------------------------------------

    /** 고객 등록 테스트 */
    @Test
    public void testCreateCustomer() {
        int customerId = 3;

        // 1. 등록 전 확인
        CustomerDto customer = this.customerDao.readCustomerById(customerId);
        assertNull(customer);

        // 2. 등록
        customer = new CustomerDto(customerId, "scroogy3", "scroogy3@swtest.co.kr");
        this.customerDao.createCustomer(customer);

        // 3. 등록 후 확인 
        CustomerDto result = this.customerDao.readCustomerById(customerId);
        assertCustomer(customer, result);
    }

    /** 고객 조회 Test */
    @Test
    public void testReadCustomerById() {
        int customerId = 1;
        CustomerDto result = this.customerDao.readCustomerById(customerId);

        assertCustomer(new CustomerDto(customerId, "scroogy", "scroogy@swtest.co.kr"), result);
    }

    /** 고객 조건조회 Test */
    @Test
    public void testReadCustomersByCondition() {
        CustomerDto condition = new CustomerDto();
        condition.setName("s%");

        List<CustomerDto> customers = this.customerDao.readCustomersByCondition(condition);
        assertEquals(2, customers.size());
        assertCustomer(new CustomerDto(1, "scroogy", "scroogy@swtest.co.kr"), customers.get(0));
        assertCustomer(new CustomerDto(2, "scroogy2", "scroogy2@swtest.co.kr"), customers.get(1));
    }

    /** 고객 변경 Test */
    @Test
    public void testUpdateCustomer() {
        // 1. 변경 전 확인
        int customerId = 2;
        CustomerDto customer = this.customerDao.readCustomerById(customerId);
        assertCustomer(new CustomerDto(customerId, "scroogy2", "scroogy2@swtest.co.kr"), customer);

        // 2. 변경
        customer.setName("newName");
        customer.setEmail("newEmail@swtest.co.kr");
        this.customerDao.updateCustomer(customer);

        // 3. 변경 후 확인
        CustomerDto result = this.customerDao.readCustomerById(customerId);
        assertEquals("newName", result.getName());
        assertEquals("newEmail@swtest.co.kr", result.getEmail());
    }

    /** 고객 삭제 Test */
    @Test
    public void testDeleteCustomer() {
        // 1. 삭제 전 확인
        int customerId = 1;
        CustomerDto customer = this.customerDao.readCustomerById(customerId);
        assertNotNull(customer);

        // 2. 삭제
        this.customerDao.deleteCustomerById(customerId);

        // 3. 삭제 후 확인
        assertNull(this.customerDao.readCustomerById(customerId));
    }

    // -------------------------------------------------------------------------
    // Private Method
    // -------------------------------------------------------------------------

    /**
     * 고객 검증 <br/>
     * 검증대상 : 아이디, 고객명, 이메일
     *
     * @param expected 기대하는 고객 정보
     * @param actual 실제 고객 정보
     */
    private void assertCustomer(CustomerDto expected, CustomerDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

}
