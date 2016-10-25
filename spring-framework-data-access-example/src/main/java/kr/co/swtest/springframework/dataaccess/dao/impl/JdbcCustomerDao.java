package kr.co.swtest.springframework.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.swtest.springframework.dataaccess.dao.CustomerDao;
import kr.co.swtest.springframework.dataaccess.dto.CustomerDto;

/**
 * 고객 DAO 구현체(JDBC)
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@Repository("JdbcCustomerDao")
public class JdbcCustomerDao implements CustomerDao {
    
    /** 데이터소스 */
    @Autowired
    private DataSource dataSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomer(CustomerDto customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.dataSource.getConnection();

            StringBuffer sql = new StringBuffer("insert into customer (cust_id, cust_nm, cust_email) values ?, ?, ?");
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // 예외처리 필요
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto readCustomerById(int customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        CustomerDto customer = null;
        try {
            connection = this.dataSource.getConnection();

            StringBuffer sql = new StringBuffer("select cust_id, cust_nm, cust_email from customer where cust_id = ?");
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setInt(1, customerId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new CustomerDto();
                customer.setId(resultSet.getInt("cust_id"));
                customer.setName(resultSet.getString("cust_nm"));
                customer.setEmail(resultSet.getString("cust_email"));
            }
        } catch (Exception e) {
            // 예외처리 필요
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }
        }
        return customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> readCustomersByCondition(CustomerDto customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<CustomerDto> customers = new ArrayList<CustomerDto>();
        try {
            connection = this.dataSource.getConnection();

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

            preparedStatement = connection.prepareStatement(sql.toString());
            int parameterIndex = 1;
            if (customer != null && customer.getId() != null) {
                preparedStatement.setInt(parameterIndex++, customer.getId());
            }
            if (customer != null && customer.getName() != null) {
                preparedStatement.setString(parameterIndex++, customer.getName());
            }
            if (customer != null && customer.getEmail() != null) {
                preparedStatement.setString(parameterIndex++, customer.getEmail());
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerDto dto = new CustomerDto();
                dto.setId(resultSet.getInt("cust_id"));
                dto.setName(resultSet.getString("cust_nm"));
                dto.setEmail(resultSet.getString("cust_email"));
                customers.add(dto);
            }
        } catch (Exception e) {
            // 예외처리 필요
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }
        }
        return customers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(CustomerDto customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.dataSource.getConnection();
            
            StringBuffer sql = new StringBuffer("update customer set cust_nm = ?, cust_email = ? where cust_id = ?");
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setInt(3, customer.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // 예외처리 필요
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerById(int customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.dataSource.getConnection();

            StringBuffer sql = new StringBuffer("delete from customer where cust_id = ?");
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // 예외처리 필요
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 예외처리 필요
                }
            }
        }
    }

}
