package org.stofli.HttpClient;

public class JQuantsUser {
    public String mailaddress;
    public String password;

    JQuantsUser(String mailadress, String password) {
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
