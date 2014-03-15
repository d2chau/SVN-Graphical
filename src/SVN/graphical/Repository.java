package SVN.graphical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Repository {
  private HttpControl httpObj;
  private ArrayList<HttpControl> httpArrayObj = new ArrayList<HttpControl>();
  private JSONObject repoJSONObject;
  private JSONObject httpJSONObject;
  private JSONArray projects;
  private ArrayList<Project> projectsList = new ArrayList<Project>();

  //Default constructor which will parse the http config file when called upon.
  public Repository(){
    parseHTTPFile();
  }
  
  private void parseHTTPFile(){
    System.out.println("Parsing your httpConfig file has been parsed!");

    BufferedReader br = null;
    
    try {
      String tempStr = "";
      String sCurrentLine;
      String httpConfigFileLocation = "C:\\Users\\d2chau\\Documents\\UCD Academics\\Fall 2013\\CSCI 4738 Senior Design I\\java program\\SVN_Graphical_View\\src\\SVN\\graphical\\httpConfig.txt"; 
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
              //System.out.println(sCurrentLine.substring(i+1, sCurrentLine.length()));
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
              //System.out.println(sCurrentLine.substring(i+1, sCurrentLine.length()));
              tempStr = sCurrentLine.substring(i+2, sCurrentLine.length());
              httpObj.setUserName(tempStr);
            }
          }
          ++counter;
          break;
          
        case 3:
          for(int i=0; i<sCurrentLine.length(); ++i){
            if(sCurrentLine.charAt(i) == ':'){
              //System.out.println(sCurrentLine.substring(i+1, sCurrentLine.length()));
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
  
  protected String getAlias(){
    return httpObj.getAlias();
  }
  
  private String getRepoHTTPAddress(){
    return httpObj.getHTTPAddr();
  }
  
  private String getUserName(){
    return httpObj.getUserName();
  }
  
  private String getPassword(){
    return httpObj.getPW();
  }
  
  protected ArrayList<HttpControl> getHttpArrayList(){
    return httpArrayObj;
  }
  
  protected ArrayList<Project> getProjectArrayList(){
    return projectsList;
  }
  
  private JSONArray getJSONRepoObjects(){
    return projects;
  }

  protected void addProject(Project proj){
    this.projectsList.add(proj);
  }
  
  private void addProjectName(String projectName){
    projects.add(projectName);
  }
}
