package org.stofli.adapter.webapi.auth;

import java.util.Properties;

import org.stofli.adapter.webapi.auth.core.CredentialProvider;
import org.stofli.adapter.webapi.config.PropertiesUtil;

public class PropertiesCredentialProvider implements CredentialProvider<JQuantsCredentials> {

    private static final String DEFAULT_FILE = "JQuants.properties";
    private static final String KEY_MAIL = "mailAddress";
    private static final String KEY_PASSWORD = "password";

    private final String propertiesFile;
    private final String mailKey;
    private final String passwordKey;

    // デフォルト: JQuants.propertiesを読み込む
    public PropertiesCredentialProvider() {
        this(DEFAULT_FILE, KEY_MAIL, KEY_PASSWORD);
    }

    // 任意のファイル・キー名を指定できる拡張版
    public PropertiesCredentialProvider(String propertiesFile, String mailKey, String passwordKey) {
        this.propertiesFile = propertiesFile;
        this.mailKey = mailKey;
        this.passwordKey = passwordKey;
    }

    @Override
    public JQuantsCredentials loadCredentials() {
        Properties props = PropertiesUtil.loadProperties(propertiesFile);
        String mail = props.getProperty(mailKey);
        String pass = props.getProperty(passwordKey);

        if (mail == null || pass == null) {
            throw new IllegalStateException("認証情報が不足しています: " + propertiesFile);
        }
        return new JQuantsCredentials(mail, pass);
    }
}