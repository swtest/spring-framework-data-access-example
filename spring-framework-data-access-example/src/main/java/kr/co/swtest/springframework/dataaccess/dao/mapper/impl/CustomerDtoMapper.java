package kr.co.swtest.springframework.dataaccess.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.swtest.springframework.dataaccess.dao.mapper.JdbcTemplateResultMapper;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * CustomerDtoMapper
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
public class CustomerDtoMapper implements JdbcTemplateResultMapper<CustomerDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto mapping(ResultSet resultSet) {
        try {
            CustomerDto dto = new CustomerDto();
            dto.setId(resultSet.getInt("cust_id"));
            dto.setName(resultSet.getString("cust_nm"));
            dto.setEmail(resultSet.getString("cust_email"));
            return dto;
        } catch (SQLException e) {
            // 예외처리 필요
            throw new RuntimeException(e);
        }
    }

}
