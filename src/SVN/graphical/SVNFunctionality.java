/**
 * File: SVNFunctionality.java
 * 
 * Description: This program connects to a SVN server and retrieves information
 * which is later saved into a file. Once the file is finished being written to
 * a connection is made to the mySQL database using JDBC connections, parses the
 * file into projects and uploads it to the mySQL database; making sure that it 
 * either updates or inserts the correct information.
 * 
 * Author(s): An Nguyen, David Chau 
 */

package SVN.graphical;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

//a class that control all needed functions
public class SVNFunctionality{
  private static Repository repo;
  private static SVNRepository svnRepo = null;
  private static String cwd = "";
  private static String OS = "";
  private static final String httpJsonFile = "HTTPOutput.json";
  private static final String allOutputTextFile = "all_output.txt";
  /**
   * Makes a connection to each of the repositories and calls helper function
   * to put the correct information in pertaining containers. 
   * 
   * @throws SVNException If the repository could not be connected with, a SVN
   *                      Exception is thrown.
   */
  public static void connectToRepo() throws SVNException{
    
    getCWD();
    
    //Declaring local variables so a new instantiation does not need to be made
    //each time
    String userName = "";
    String pw = "";
    String repoHttpAddr = "";
    
    //Making repositories and reading the httpConfig file
    repo = new Repository();
    
    ArrayList<HttpControl> httpArrayList = new ArrayList<HttpControl>();
    httpArrayList = repo.getHttpArrayList();
    
    //Retrieving svn login information. This needs to run code for each repo
    //and make the connection using each of credentials
    for(HttpControl hcObj : httpArrayList){
      //Initializing variables for connecting to SVN Repository
      userName = hcObj.getUserName();
      pw = hcObj.getPW();
      repoHttpAddr = hcObj.getHTTPAddr();

      System.out.println("Connecting to the SVN server...\n");
      //Creating the repo using the svn_url provided
      svnRepo = SVNRepositoryFactory.create(SVNURL.parseURIEncoded
          (repoHttpAddr));
      
      //Login in the repo using Authentication credentials
      ISVNAuthenticationManager admin = 
          SVNWCUtil.createDefaultAuthenticationManager(userName, pw);
      
      //SVN connection is established here
      svnRepo.setAuthenticationManager(admin);
      
      //testSVNFunction();
      try {
        System.out.println("Established Connection to: " + svnRepo.getRepositoryRoot(true));
        System.out.println("\n<======== Initialize Testing =========>");
        System.out.println("Latest revision number of the repo: " + svnRepo.getLatestRevision());
        System.out.println("<======== Testing  Complete =========>\n");
      } catch (SVNException e) {
        e.printStackTrace();
      }
      
      
      try {
        listPrj(svnRepo);
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      System.out.println("Closing connection to SVN server...\n");      
    }
    writeHttpJSONFile();
    writeProjectJSONFile();
  }
  
  /**
   * Function used to retrieve the necessary information from the repository at
   * the base level. It will get a repository to connect to and call the 
   * listElemPrj function in it to get items within a project. This function
   * will also create both a text and JSON file for the user and web
   * application. 
   * 
   * @param svnRepo Repository that the user would like to connect with
   * @return  Returns true is the traversal and retrieval of information was
   *          successful.
   *          Returns false otherwise.
   *          
   * @throws SVNException Throws SVN Exception due to function call to 
   *                      listElemPrj which will tell the user the stack error.
   * @throws IOException  Throws IO Exception if the creation or closing of the
   *                      file was not properly done.
   */
  @SuppressWarnings({ "finally", "unchecked" })
  public static boolean listPrj(SVNRepository svnRepo) throws SVNException, IOException{
    boolean localFlag = false;
    Project tempProject = new Project();
    
    // Creating a list of project names
    List<?> pr_names_list=(List<?>) svnRepo.getDir("",-1,null,(List<?>) null);
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    // Using iterator to handle traverse in the pr_names list
    Iterator<?> itr=pr_names_list.iterator();
    
    File myfile = new File(cwd + allOutputTextFile);
    System.out.println("Creating " + myfile);
    
    // If the file exist delete it first
    if (myfile.exists()){
      myfile.delete();     
    }
    
    PrintStream ps = null;

    // Create and output to a file 
    try
    {
      ps=new PrintStream(new FileOutputStream(myfile,true));
 
      // If the pr_name list has more than one element
      // Continue print out the project names
      while (itr.hasNext()){
        
        // A project name is the next element of the iteration
        SVNDirEntry prjName=(SVNDirEntry) itr.next();
        
        // Printing project names
        System.out.println("Project Name: " + prjName.getName());
        tempProject.setProjectName(prjName.getName());
        
        //retrieving date
        tempProject.setProjectDate(dateFormat.format(prjName.getDate()));
        
        // Printing latest revision of the project
        tempProject.setProjectRevNum(prjName.getRevision());
        
        // keep track directory level
        int dir_lev=0;
        
        // Access each project to output trunk, tags and branches
        listElemPrj(svnRepo, '/'+prjName.getName(), dir_lev, tempProject, ps);
        
        repo.addProject(tempProject);
        tempProject = new Project();
         
        localFlag = false;
      }
      localFlag = true;
    }
    catch (FileNotFoundException e) 
    {
      e.printStackTrace();
    }
    finally{
      if(ps != null){
        ps.close();
      }
      System.out.println("Finished creating " + myfile + '\n');

      return localFlag;
    }
  }
  
  /**
   * This function will query the repository and will recursively traverse
   * deeper to retrieve more files/folders. While traversing the repository,
   * this function will also start to insert the items and projects within the
   * repository into its corresponding container.
   * 
   * @param repo  Repository that is wanting to be traversed.
   * @param url   Recursive parameter which starts off as "" to be at the base
   *              level.
   * @param level A counter to keep track of how far down the function has 
   *              traversed.
   * @param tempProject Temporary project used to store information from and
   *                     will be used to copy from once traversal is complete.
   * @param ps    PrintStream used for debugging and printing to a file the
   *              files/folders within the directory
   *              
   * @throws SVNException If the repository cannot get into a directory or has
   *                      or cannot connect to the directory, an exception will
   *                      be thrown.
   */
  public static void listElemPrj(SVNRepository repo, String url, int level, Project tempProject,
      PrintStream ps) throws SVNException{
   // Creating a list of directories
   // trunk, tags and branches
   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
   List<?> names_list=(List<?>) repo.getDir(url,-1,null,(List<?>) null);
     
   // Using iterator to handle traverse in the names_list
   Iterator<?> itr = names_list.iterator();
   
   // A project always has trunk, tags and branches
   // Continue print out the trunk, tags and branches
   while (itr.hasNext()){

     //  The next element of the iteration
     SVNDirEntry dName = (SVNDirEntry) itr.next();
     
     //BASE
     if ((dName.getName()).toLowerCase().equals("trunk"))
     {
       tempProject.setBaseTrunk(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
       ps.print("<basetrunk>"+dName.getName());
       // Printing latest revision of the trunk, tags, and branches
       ps.print(";" + dName.getRevision());
       ps.println(";" + dateFormat.format(dName.getDate()));
       System.out.println(dName.getName() + ' ' + dName.getRevision());
     }
     //BASE
     else if ((dName.getName()).toLowerCase().equals("branches"))
     {
       tempProject.setBaseBranch(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
       ps.print("<basebranch>"+dName.getName());
       // Printing latest revision of the trunk, tags, and branches
       ps.print(";" + dName.getRevision());
       ps.println(";" + dateFormat.format(dName.getDate()));
       System.out.println(dName.getName() + ' ' + dName.getRevision());
     }
     //BASE
     else if ((dName.getName()).toLowerCase().equals("tags"))
     {
       tempProject.setBaseTag(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));       
       ps.print("<basetag>"+dName.getName());
       // Printing latest revision of the trunk, tags, and branches
       ps.print(";" + dName.getRevision());
       ps.println(";" + dateFormat.format(dName.getDate()));
       System.out.println(dName.getName() + ' ' + dName.getRevision());
     }
     //ITEMS
     else if (dName.getKind()==SVNNodeKind.FILE)
     {
       String u=url+"/"+dName.getName();
       if(u.contains("trunk")||u.contains("Trunk"))
       {
         //tempProject.addTrunkItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
         ps.print("<trunkitems>"+ dName.getName());
         // Printing latest revision of the trunk, tags, and branches
         ps.print(";" + dName.getRevision());
         ps.println(";" + dateFormat.format(dName.getDate()));
         continue;
       } 
       if (u.contains("tags")||u.contains("Tags"))
       {
         //tempProject.addTagItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
         ps.print("<tagitems>" + dName.getName()); 
         // Printing latest revision of the trunk, tags, and branches
         ps.print(";" + dName.getRevision());
         ps.println(";" + dateFormat.format(dName.getDate()));
         continue;
       }
     }
     else if(dName.getKind()==SVNNodeKind.DIR)
     {
       String u=url+"/"+dName.getName();
       
       if (u.indexOf("branches")!=-1||u.indexOf("tags")!=-1||u.indexOf("trunk")!=-1){
           
         if(u.contains("trunk"))
         {
           ps.print("<trunkitems>"+ dName.getName());
           //tempProject.addTrunkItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
           // Printing latest revision of the trunk, tags, and branches
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
           continue;
         }   
         if (u.contains("tags")){
           //tempProject.addTagItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
           ps.print("<tagitems>" + dName.getName()); 
           // Printing latest revision of the trunk, tags, and branches
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
           continue;
         }
         else
         {
         //  long max=0;
           tempProject.addBranchItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
           ps.print("<branchinglevel>" + dName.getName()); 
           // Printing latest revision of the trunk, tags, and branches
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
         }
       }
       
       /*
       // if u does not contain branches, tags, or trunk 
       // this directory is a subproject
       else
       {
         // special case
         // might not need if correct format 
         if(u.toLowerCase().contains("trunk"))
         {
           //tempProject.addTrunkItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
           ps.print("<trunkitems>"+dName.getName());
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
           continue;
         }
         if(u.toLowerCase().contains("tags"))
         {
           //tempProject.addTagItems(dName.getName(), dName.getRevision(), dateFormat.format(dName.getDate()));
           ps.print("<tagitems>"+dName.getName());
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
           continue;
         }
         else
         {
           ps.print("<subproject>"+dName.getName());
           ps.print(";" + dName.getRevision());
           ps.println(";" + dateFormat.format(dName.getDate()));
         }
       }
       */
     }
     /*
     else
     {
       ps.print(dName.getName());  
       // Printing latest revision of the trunk, tags, and branches
       ps.print(";" + dName.getRevision());
       ps.println(";" + dateFormat.format(dName.getDate()));
     }
     */
     
     // Check if current selected item is a directory
     if (dName.getKind()==SVNNodeKind.DIR)
     {
       if (url==""){
         listElemPrj(repo, dName.getName(), level, tempProject, ps);
       }
       // Access sub-directories if available and get directory name
       else{
         listElemPrj(repo, url+"/"+dName.getName(), level, tempProject, ps);   
       }
     }
   }
  }
  
  /**
   * This function goes to the Subversion Repository and tries to retrieve
   * information to make sure it is connected and working properly. 
   */
  private static void testSVNFunction(){
    try {
      System.out.println("Established Connection to: " 
          + svnRepo.getRepositoryRoot(true));
      System.out.println("\n<======== Initialize Testing =========>");
      System.out.println("Latest revision number of the repo: " + 
          svnRepo.getLatestRevision());
      System.out.println("<======== Testing  Complete =========>\n");
    } catch (SVNException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This function creates the httpjson file that is used to display to user
   * on the website. This function will be called by each of the repositories
   * to make it all in one file.
   */
  @SuppressWarnings("unchecked")
  private static void writeHttpJSONFile(){
    JSONObject httpJSONObject = new JSONObject();
    JSONArray httpJSONArray = new JSONArray();
    ArrayList<Project> projectsList = repo.getProjectArrayList();
    String Alias = repo.getAlias();

    for(Project p : projectsList){
      httpJSONArray.add(p.getProjectName());
    }
    
    httpJSONObject.put(Alias, httpJSONArray);
    
    try {
      FileWriter file = new FileWriter(cwd + httpJsonFile);
      System.out.println("Creating " + cwd + httpJsonFile);
      file.write(httpJSONObject.toJSONString());
      file.flush();
      file.close();
      System.out.println("Finished creating " + cwd + httpJsonFile + '\n');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Writes the JSON files of each of the Repository. This file will be called
   * multiple times to write a JSON file for each of the repositories. 
   */
  private static void writeProjectJSONFile(){
    JSONObject parentJObj = new JSONObject();
    JSONObject tempJObj = new JSONObject();
    JSONArray projectJSONArray = new JSONArray();
    JSONArray tempJArray = new JSONArray();
    
    ArrayList<Project> projectsList = repo.getProjectArrayList();
    
    String Alias = repo.getAlias();
    String fileLocation = cwd + Alias + ".json";
    System.out.println("Creating " + fileLocation);
    
    for(Project p : projectsList){
      tempJArray.add(p.formatTrunkToJSON());
      tempJArray.add(p.formatTagToJSON());
      tempJArray.add(p.formatBranchToJSON());
      
      //Getting the project details at the base level
      tempJObj.put("children", tempJArray);
      tempJObj.put("Name", p.getProjectName());
      tempJObj.put("date", p.getProjectDate());
      tempJObj.put("Rev", p.getProjectRevNum());
      
      System.out.println("In Function Name: " + p.getProjectName());
      System.out.println("In Function Trunk: " + p.formatTrunkToJSON());
      System.out.println("In Function J Array: " + tempJArray);
      
      projectJSONArray.add(tempJObj);
      tempJArray = new JSONArray();
      tempJObj = new JSONObject();
    }
    //Makes the general Project parent 
    parentJObj.put("Projects", projectJSONArray);
    
    try {
      FileWriter file = new FileWriter(fileLocation);
      file.write(parentJObj.toJSONString());
      file.flush();
      file.close();
      System.out.println("Finished creating " + fileLocation + '\n');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Sets the current working directory along with checks the operating system
   * as well. Prompts the user with more information. 
   */
  private static void getCWD(){
    OS = System.getProperty("os.name").toLowerCase();
    cwd = System.getProperty("user.dir");

    if (isWindows()) {
      System.out.println("This is Windows Device");
      System.out.println("Your current working directory is: ");
      cwd += '\\';
    } else if (isMac()) {
      System.out.println("This is Mac Device");
      System.out.println("Your current working directory is: ");
      cwd += '/';
    } else if (isUnix()) {
      System.out.println("This is Unix or Linux Device");
      System.out.println("Your current working directory is: ");
      cwd += '/';
    } else if (isSolaris()) {
      System.out.println("This is Solaris Device");
      System.out.println("Sorry Solaris is not supported!");
    } else {
      System.out.println("Sorry your OS is not supported!");
    }
    
    System.out.println("All files will be created here: ");
    System.out.println(cwd);
    System.out.println("\n<- Please be advised, only Mozilla Firefox and"
        + " Google Chrome with HTML5 support is capable of viewing the SVN "
        + "graph. ->\n");
  }
  
  /**
   * Checks to see if the operating system is Windows based. 
   * @return true if the operating system is Windows base
   *         false if the operating system is not Windows base
   */
  public static boolean isWindows() {
    return (OS.indexOf("win") >= 0);
  }
 
  /**
   * Checks to see if the operating system is Mac based. 
   * @return true if the operating system is Mac base
   *         false if the operating system is not Mac base
   */
  public static boolean isMac() {
    return (OS.indexOf("mac") >= 0);
  }
 
  /**
   * Checks to see if the operating system is Linux or Unix based. 
   * @return true if the operating system is Linux or Unix base
   *         false if the operating system is not Linux or Unix base
   */
  public static boolean isUnix() {
    return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 ||
        OS.indexOf("aix") > 0 );
  }
 
  /**
   * Checks to see if the operating system is Solaris based. 
   * @return true if the operating system is Solaris base
   *         false if the operating system is not Solaris base
   */
  public static boolean isSolaris() {
    return (OS.indexOf("sunos") >= 0);
  }
}