package org.stofli.jquants.domain.port;

import java.sql.SQLException;
import java.util.List;

/**
 * Port to retrieve listed company codes.
 */
public interface CompanyCodeRepository {

    List<String> findAllCodes() throws SQLException;
}
