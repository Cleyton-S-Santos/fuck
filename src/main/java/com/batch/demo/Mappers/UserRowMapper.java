package com.batch.demo.Mappers;

import com.batch.demo.domain.UserDomain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDomain> {

    @Override
    public UserDomain mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserDomain user = new UserDomain();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}
