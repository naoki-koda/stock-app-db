package org.stofli.jquants.infrastructure.config;

import java.util.Properties;

import org.stofli.jquants.infrastructure.api.auth.CredentialProvider;
import org.stofli.jquants.infrastructure.api.auth.JQuantsCredentials;

/**
 * Loads J-Quants credentials from a properties file.
 */
public class PropertiesCredentialProvider implements CredentialProvider {

    private static final String DEFAULT_PROPERTIES_FILE = "JQuants.properties";
    private static final String DEFAULT_MAIL_KEY = "mailAdress";
    private static final String DEFAULT_PASSWORD_KEY = "password";

    private final String propertiesFile;
    private final String mailKey;
    private final String passwordKey;

    public PropertiesCredentialProvider() {
        this(DEFAULT_PROPERTIES_FILE, DEFAULT_MAIL_KEY, DEFAULT_PASSWORD_KEY);
    }

    public PropertiesCredentialProvider(String propertiesFile, String mailKey, String passwordKey) {
        this.propertiesFile = propertiesFile;
        this.mailKey = mailKey;
        this.passwordKey = passwordKey;
    }

    @Override
    public JQuantsCredentials loadCredentials() {
        Properties props = PropertiesUtil.loadProperties(propertiesFile);
        String mailAddress = props.getProperty(mailKey);
        String password = props.getProperty(passwordKey);
        if (mailAddress == null || password == null) {
            throw new IllegalStateException("J-Quants credentials are not configured in " + propertiesFile);
        }
        return new JQuantsCredentials(mailAddress, password);
    }
}
