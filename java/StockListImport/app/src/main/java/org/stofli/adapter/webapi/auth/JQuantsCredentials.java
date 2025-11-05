package org.stofli.adapter.webapi.auth;

public class JQuantsCredentials {
    private final String mailaddress;
    private final String password;

    public JQuantsCredentials(String mailaddress, String password) {
        this.mailaddress = mailaddress;
        this.password = password;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public String getPassword() {
        return password;
    }
}
