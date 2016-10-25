package kr.co.swtest.springframework.dataaccess.dao;

import java.util.List;

import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * 고객 DAO 
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
public interface CustomerDao {

    /**
     * 고객 등록
     *
     * @param customer 고객
     */
    void createCustomer(CustomerDto customer);

    /**
     * 고객 조회
     *
     * @param customerId 고객아이디
     * @return 고객. 없을 경우 <code>null</code>을 리턴함.
     */
    CustomerDto readCustomerById(int customerId);

    /**
     * 고객목록 조회 : 조건검색 <br/>
     * 검색조건 : 고객명(like), 이메일(like) <br/>
     * 정렬조건 : 고객명, 고객아이디
     *
     * @param customer 검색조건
     * @return 조건에 해당하는 고객목록. 없을 경우 빈 목록(Empty List)를 리턴함.
     */
    List<CustomerDto> readCustomersByCondition(CustomerDto customer);

    /**
     * 고객 변경
     *
     * @param customer 변경할 고객
     */
    void updateCustomer(CustomerDto customer);

    /**
     * 고객 삭제
     *
     * @param customerId 고객아이디
     */
    void deleteCustomerById(int customerId);

}
