package org.stofli.jquants.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stofli.database.util.DbUtil;
import org.stofli.jquants.domain.port.CompanyCodeRepository;

/**
 * JDBC implementation of CompanyCodeRepository.
 * Relies on the shared DatabaseConnection singleton.
 */
public class JdbcCompanyCodeRepository implements CompanyCodeRepository {

    private static final String SELECT_ALL_CODES = "SELECT code FROM stock.company";

    @Override
    public List<String> findAllCodes() throws SQLException {
        List<String> codes = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CODES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                codes.add(resultSet.getString("code"));
            }
        }
        return codes;
    }
}
