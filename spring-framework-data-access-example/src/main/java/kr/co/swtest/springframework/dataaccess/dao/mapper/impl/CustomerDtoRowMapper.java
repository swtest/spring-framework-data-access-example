package kr.co.swtest.springframework.dataaccess.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * CustomerDtoRowMapper <br/>
 * SimpleJdbcTemplate 용도
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
public class CustomerDtoRowMapper implements RowMapper<CustomerDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            CustomerDto dto = new CustomerDto();
            dto.setId(rs.getInt("cust_id"));
            dto.setName(rs.getString("cust_nm"));
            dto.setEmail(rs.getString("cust_email"));
            return dto;
        } catch (SQLException e) {
            // 예외처리 필요
            throw new RuntimeException(e);
        }
    }

}
