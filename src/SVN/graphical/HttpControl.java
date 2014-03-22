/**
 * File: Main.java
 * 
 * Description: This file contains and handles the login and aliasing
 *              information associated with a repository.
 * 
 * Author(s): David Chau 
 */

package SVN.graphical;

public class HttpControl {
  private static String alias = "";
  private static String HTTPAddr = "";
  private static String userName = "";
  private static String pw = "";
  private String httpConfigFileLocation = "";
  
  /**
   * Default Constructor
   */
  HttpControl(){}
  
  /**
   * Constructor
   * 
   * @param fileLocation  Location of the http configuration file
   */
  HttpControl(String fileLocation){
    alias = "";
    HTTPAddr = "";
    userName = "";
    pw = "";
    this.httpConfigFileLocation = fileLocation;
  }
  
  /**
   * Returns the alias associated with the repository
   * 
   * @return Alias specific to the repository
   */
  protected String getAlias(){
    return alias;
  }
  
  /**
   * Retrieves the http address that is associated with the repository
   * 
   * @return Returns the https address of the repository to be connected
   */
  protected String getHTTPAddr(){
    return HTTPAddr;
  }
  
  /**
   * Retrieves the user name associated with a specific repository
   * 
   * @return The user name of the specific repository
   */
  protected String getUserName(){
    return userName;
  }

  /**
   * Retrieves the password associated with the specific repository
   * 
   * @return Returns the password for the associated repository
   */
  protected String getPW(){
    return pw;
  }

  /**
   * Sets the alias associated to the repository within the http configuration
   * file
   * 
   * @param alias Alias assoricated with the specific repository
   */
  protected void setAlias(String alias){
    this.alias = alias;
  }
  
  /**
   * Sets the HTTP address to connect to the repository
   * 
   * @param HTTPAddr
   */
  protected void setHTTPAddr(String HTTPAddr){
    this.HTTPAddr = HTTPAddr;
  }
  
  /**
   * Sets the user name login credential for a repository
   * 
   * @param userName  User name of the repository
   */
  protected void setUserName(String userName){
    this.userName = userName;
  }

  /**
   * Sets the password pertaining to a repository
   * 
   * @param pw  Password of the repository
   */
  protected void setPW(String pw){
    this.pw = pw;
  }
}
