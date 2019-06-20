package io.demo.jony.jony.core.security;

/**
 * Credentials of the Account.
 *
 * @author Virtus
 *
 */
public class AccountCredentials {

    /**
     * Login.
     */
    private String login;

    /**
     * Password.
     */
    private String password;

    /**
     * Gets the login.
     *
     * @return Login.
     */
    public String getLogin() {

        return this.login;
    }

    /**
     * Sets the login.
     *
     * @param login
     *            Login.
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return Password.
     */
    public String getPassword() {

        return this.password;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            Password.
     */
    public void setPassword(String password) {

        this.password = password;
    }
}
