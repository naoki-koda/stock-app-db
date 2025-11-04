package org.stofli.adopter.webapi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
  

    private static final String DEFAULT_PROPERTIES_FILE = "JQuants.properties";  // 読み込むプロパティファイル名（resources直下）

    public static Properties loadProperties() {
        return loadProperties(DEFAULT_PROPERTIES_FILE);
    }

    public static Properties loadProperties(String filename) {
        Properties props = new Properties();

        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new RuntimeException("プロパティファイルが見つかりません: " + filename);
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("プロパティファイルの読み込みに失敗しました: " + filename, e);
        }

        return props;
    }
}
