/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.sql;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author saiki
 */
public class Configuration  implements Serializable {
  
  private URL jdbcDriverURL;
  
  private String connectionURL;
  
  private String user;
  
  private String password;

  private String driverClassName;

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  private boolean initialized = false;

  /**
   * @return the jdbcDriverURL
   */
  public URL getJdbcDriverURL() {
    return jdbcDriverURL;
  }
  
  public String getJdbcDriverURLAsString() {
    return this.jdbcDriverURL.toString();
  }

  /**
   * @param jdbcDriverURL the jdbcDriverURL to set
   */
  public void setJdbcDriverURL(URL jdbcDriverURL) {
    this.jdbcDriverURL = jdbcDriverURL;
  }
  
  /**
   * @param jdbcDriverURL the jdbcDriverURL to set
   */
  public void setJdbcDriverURL(String jdbcDriverURL) throws MalformedURLException {
    URL url = new URL(jdbcDriverURL);
    this.jdbcDriverURL = url;
  }

  /**
   * @return the connectionURL
   */
  public String getConnectionURL() {
    return connectionURL;
  }

  /**
   * @param connectionURL the connectionURL to set
   */
  public void setConnectionURL(String connectionURL) {
    this.connectionURL = connectionURL;
  }

  /**
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

}
