/**
 * File: Repository.java
 * 
 * Description: A repository container which contains all information that a
 *              repository contains such as a location and projects.
 * 
 * Author(s): David Chau 
 */

package SVN.graphical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Repository {
  private HttpControl httpObj;
  private ArrayList<HttpControl> httpArrayObj = new ArrayList<HttpControl>();
  private ArrayList<Project> projectsList = new ArrayList<Project>();
  private static final String httpFile = "httpConfig.txt";

  //Default constructor which will parse the http config file when called upon.
  public Repository(){
    parseHTTPFile();
  }
  
  /**
   * Scans the http configuration file and puts all relevant data into its
   * container.
   */
  private void parseHTTPFile(){
    System.out.println("Parsing your httpConfig file has been parsed!");

    BufferedReader br = null;
    
    try {
      String tempStr = "";
      String sCurrentLine;
      String file = System.getProperty("user.dir");
      String httpConfigFileLocation = file + httpFile; 
      httpObj = new HttpControl(httpConfigFileLocation);

      br = new BufferedReader(new FileReader(httpConfigFileLocation));
      int counter = 0;
      boolean first = false;
      
      //Reading the file line by line until the end of file
      while ((sCurrentLine = br.readLine()) != null) {
        
        //Getting the information for each line
        switch(counter){
        case 0:
          for(int i=0; i<sCurrentLine.length(); ++i){
            if(sCurrentLine.charAt(i) == ':' && first == false){
              
              tempStr = sCurrentLine.substring(i+2, sCurrentLine.length());
              httpObj.setHTTPAddr(tempStr);
              first = true;
            }
          }
          
          ++counter;
          break;
          
        case 1:
          for(int i=0; i<sCurrentLine.length(); ++i){
            if(sCurrentLine.charAt(i) == ':'){
              tempStr = sCurrentLine.substring(i+2, sCurrentLine.length());
              httpObj.setAlias(tempStr);
            }
            first = false;
          }
          ++counter;
          break;
          
        case 2:
          for(int i=0; i<sCurrentLine.length(); ++i){
            if(sCurrentLine.charAt(i) == ':'){
              tempStr = sCurrentLine.substring(i+2, sCurrentLine.length());
              httpObj.setUserName(tempStr);
            }
          }
          ++counter;
          break;
          
        case 3:
          for(int i=0; i<sCurrentLine.length(); ++i){
            if(sCurrentLine.charAt(i) == ':'){
              tempStr = sCurrentLine.substring(i+2, sCurrentLine.length());
              httpObj.setPW(tempStr);
            }
          }
          
          httpArrayObj.add(httpObj);          
          httpObj = new HttpControl();
          counter = 0;
          break;
          
        default:
          System.out.println("Please check your HTTP configuration file.");
          break;
        }
      }
 
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null){
          br.close();
        }
        System.out.println("Your httpConfig file has been parsed!");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
  
  /**
   * Returns the Alias of the repository read from the http configuration file.
   * 
   * @return A string of the alias belonging to a repository.
   */
  protected String getAlias(){
    return httpObj.getAlias();
  }

  /**
   * Returns a List of http objects which are used for outputting the http JSON
   * file.
   * @return ArrayList of HttpControl Class objects to be used for the JSON
   *         output file.
   */
  protected ArrayList<HttpControl> getHttpArrayList(){
    return httpArrayObj;
  }
  
  /**
   * Returns the list of projects that are within one repository
   * 
   * @return List of projects within a repository
   */
  protected ArrayList<Project> getProjectArrayList(){
    return projectsList;
  }

  /**
   * Adds in a new project class to the repository
   * 
   * @param proj  A project that contains all of its information i.e. Name,
   *              Date, Revision number for itself and all of its items.
   */
  protected void addProject(Project proj){
    this.projectsList.add(proj);
  }
}
