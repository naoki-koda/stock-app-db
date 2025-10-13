package org.stofli.jquants.config;

public class JQuantsUser {
    public String mailaddress;
    public String password;

    public JQuantsUser(String mailadress, String password) {
        this.mailaddress = mailadress;
        this.password = password;
    }

    public String getMailadress() {
        return mailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setMailadress(String mailadress) {
        this.mailaddress = mailadress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
