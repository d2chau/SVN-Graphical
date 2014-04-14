/**
 * File: Project.java
 * 
 * Description: This container class is for information to be retrieved that
 * belongs in a project. This follows the ideal why of how projects should be
 * setup in Subversion. All projects  will contain the following
 *  1 Name
 *  1 Last Modified Date
 *  1 Revision Number
 *  
 *  1 Main Trunk Folder
 *  1 Main Branch Folder
 *  1 Main Tag Folder
 * 
 *  0:n Trunk Items (Items within the Trunk folder of an object)
 *  0:n Branch Items (Items within the Branch folder of an object)
 *  0:n Tag Items (Items within the Tag folder of an object)
 *  
 * This class contains additional functions to assist with the retrieval 
 * and manipulation of a Project. 
 * 
 * Author(s): David Chau 
 */

package SVN.graphical;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Project {
  private String projectName;
  private String projectDate;
  private Long projectRevNum;
  
  private ItemInfo baseTrunk;
  private ItemInfo baseBranch;
  private ItemInfo baseTag;
  
  //add items which is a data structure to hold many
  private ArrayList<ItemInfo> trunkItems = new ArrayList<ItemInfo>();
  private ArrayList<ItemInfo> branchItems = new ArrayList<ItemInfo>();
  private ArrayList<ItemInfo> tagItems = new ArrayList<ItemInfo>();
  
  /**
   * Default constructor
   */
  Project(){
    baseTrunk = new ItemInfo();
    baseBranch = new ItemInfo();
    baseTag = new ItemInfo();
    
    trunkItems = new ArrayList<ItemInfo>();
    branchItems = new ArrayList<ItemInfo>();
    tagItems = new ArrayList<ItemInfo>();
  }
  
  /**
   * Takes in all of the information of one of the items in a project and adds
   * it into the container by calling other helper functions.
   * 
   * @param trunkName  A Name to be entered into the trunk container within 
   *                   the trunk folder.
   * @param revNum     Revision number to be entered into the trunk contain 
   *                   within the trunk folder
   * @param date       Date to be entered into the trunk contain within the
   *                   trunk folder
   */
  protected void addTrunkItems(String trunkName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(trunkName, revNum, date);
    trunkItems.add(tempItem);
  }

  /**
   * Takes in all of the information of one of the items in a project and adds
   * it into the container by calling other helper functions.
   * 
   * @param branchName A Name to be entered into the branch container within 
   *                   the branch folder.
   * @param revNum     Revision number to be entered into the branch contain 
   *                   within the branch folder
   * @param date       Date to be entered into the branch contain within the
   *                   branch folder
   */
  protected void addBranchItems(String branchName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(branchName, revNum, date);
    branchItems.add(tempItem);
  }

  /**
   * Takes in all of the information of one of the items in a project and adds
   * it into the container by calling other helper functions.
   * 
   * @param tagName A Name to be entered into the tag container within the tag
   *                folder.
   * @param revNum  Revision number to be entered into the tag contain within
   *                the tag folder
   * @param date    Date to be entered into the tag contain within the tag
   *                folder
   */
  protected void addTagItems(String tagName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(tagName, revNum, date);
    tagItems.add(tempItem);
  }

  /**
   * Sets the project's name
   * @param name Name of the project
   */
  protected void setProjectName(String name){
    projectName = name;
  }
  
  /**
   * Sets the project's last modified date
   * @param date  Last modified date of the project
   */
  protected void setProjectDate(String date){
    projectDate = date;
  }
  
  /**
   * Sets the project's latest revision number
   * @param revNum  Latest revision number of the project
   */
  protected void setProjectRevNum(Long revNum){
    projectRevNum = revNum;
  }  
  
  /**
   * Sets all of the information of the Base Trunk all at once
   * 
   * @param trunkName Name of the Base Trunk
   * @param revNum  Latest Revision number of the Base Trunk
   * @param date    Last modified date of the Base Trunk
   */
  protected void setBaseTrunk(String trunkName, Long revNum, String date){
    baseTrunk.setName(trunkName);
    baseTrunk.setRevNum(revNum);
    baseTrunk.setDate(date);
  }

  /**
   * Sets all of the information of the Base Branch all at once
   * 
   * @param branchName Name of the Base Branch
   * @param revNum  Latest Revision number of the Base Branch
   * @param date    Last modified date of the Base Branch
   */
  protected void setBaseBranch(String branchName, Long revNum, String date){
    baseBranch.setName(branchName);
    baseBranch.setRevNum(revNum);
    baseBranch.setDate(date);
  }
  
  /**
   * Sets all of the information of the Base Tag all at once
   * 
   * @param tagName Name of the Base Tag
   * @param revNum  Latest Revision number of the Base Tag
   * @param date    Last modified date of the Base Tag
   */
  protected void setBaseTag(String tagName, Long revNum, String date){
    baseTag.setName(tagName);
    baseTag.setRevNum(revNum);
    baseTag.setDate(date);
  }
  
  /**
   * Sets the latest Name of the base trunk belonging to a project
   * 
   * @param revNum  The Name of base trunk
   */
  protected void setBaseTrunkName(String trunkName){
    baseTrunk.setName(trunkName);
  }
  
  /**
   * Sets the latest Revision number of the base trunk belonging to a project
   * 
   * @param revNum  The latest Revision number of base trunk
   */
  protected void setBaseTrunkRevNum(Long revNum){
    baseTrunk.setRevNum(revNum);
  }
  
  /**
   * Sets the Date of the Base Trunk belonging to the project
   * 
   * @param date  Last modified Date of the Base Trunk
   */
  protected void setBaseTrunkDate(String date){
    baseTrunk.setName(date);
  }

  /**
   * Sets the latest Name of the base branch belonging to a project
   * 
   * @param revNum  The name of base branch
   */
  protected void setBaseBranchName(String branchName){
    baseBranch.setName(branchName);
  }
  
  /**
   * Sets the latest Revision number of the base branch belonging to a project
   * 
   * @param revNum  The latest Revision number of base branch
   */
  protected void setBaseBranchRevNum(Long revNum){
    baseBranch.setRevNum(revNum);
  }
  
  /**
   * Sets the Date of the Base Branch belonging to the project
   * 
   * @param date  Last modified Date of the Base Branch
   */
  protected void setBaseBranchDate(String date){
    baseBranch.setName(date);
  }
  
  /**
   * Sets the latest Name of the base tag belonging to a project
   * 
   * @param revNum  The Name of base tag
   */
  protected void setBaseTagName(String tagName){
    baseTag.setName(tagName);
  }
  
  /**
   * Sets the latest Revision number of the base tag belonging to a project
   * 
   * @param revNum  The latest Revision number of base tag
   */
  protected void setBaseTagRevNum(Long revNum){
    baseTag.setRevNum(revNum);
  }
  
  /**
   * Sets the Date of the Base Tag belonging to the project
   * 
   * @param date  Last modified Date of the Base Tag
   */
  protected void setBaseTagDate(String date){
    baseTag.setName(date);
  }  
  
  /**
   * Returns the latest Name pertaining to the Project
   * 
   * @return A String of the latest Name of the Project
   */
  protected String getProjectName(){
    return projectName;
  }
  
  /**
   * Returns the latest modified date of the Project
   *  
   * @return A string of the latest date belonging to the project
   */
  protected String getProjectDate(){
    return projectDate;
  }
  
  /**
   * Returns the latest Revision number of the Project
   *  
   * @return A long of the Project's Revision number
   */
  protected Long getProjectRevNum(){
    return projectRevNum;
  }
  
  /**
   * Returns the latest Name pertaining to the Base Trunk
   * 
   * @return A String of the latest Name of the Base Trunk
   */
  protected String getBaseTrunkName(){
    return baseTrunk.getName();
  }

  /**
   * Returns the latest Revision number of the Base Trunk
   *  
   * @return A long of the latest Revision number
   */
  protected Long getBaseTrunkRevNum(){
    return baseTrunk.getRevNum();
  }
  
  /**
   * Returns the latest modified date of the Base Trunk
   *  
   * @return A string of the latest date
   */
  protected String getBaseTrunkDate(){
    return baseTrunk.getDate();
  }
  
  /**
   * Returns the latest Name pertaining to the Base Branch
   * 
   * @return A String of the latest Name of the Base Branch
   */
  protected String getBaseBranchName(){
    return baseBranch.getName();
  }

  /**
   * Returns the latest Revision number of the Base Branch
   *  
   * @return A long of the latest Revision number
   */
  protected Long getBaseBranchRevNum(){
    return baseBranch.getRevNum();
  }
  
  /**
   * Returns the latest modified date of the Base Branch
   *  
   * @return A string of the latest date
   */
  protected String getBaseBranchDate(){
    return baseBranch.getDate();
  }
  
  /**
   * Returns the latest Name pertaining to the Base Tag
   * 
   * @return A String of the latest Name of the Base Tag
   */
  protected String getBaseTagName(){
    return baseTag.getName();
  }

  /**
   * Returns the latest Revision number pertaining to the Base Tag
   * 
   * @return A long of the latest Revision number
   */
  protected Long getBaseTagRevNum(){
    return baseTag.getRevNum();
  }
  
  /**
   * Returns the latest modified date of the Base Tag
   *  
   * @return A string of the latest date
   */
  protected String getBaseTagDate(){
    return baseTag.getDate();
  }
  
  /**
   * Returns Base Trunk Item Info
   * @return An ItemInfo class with all of the information pertaining to the
   *  Base Trunk. 
   */
  protected ItemInfo getBaseTrunk(){
    return baseTrunk;
  }
  
  /**
   * Returns Base Branch Item Info
   * @return An ItemInfo class with all of the information pertaining to the
   *  Base Branch. 
   */
  protected ItemInfo getBaseBranch(){
    return baseBranch;
  }
  
  /**
   * Returns Base Tag Item Info
   * @return An ItemInfo class with all of the information pertaining to the
   *  Base Tag. 
   */
  protected ItemInfo getBaseTag(){
    return baseTag;
  }
  
  /**
   * Retrieves the list of trunk items 
   * @return trunk items list
   */
  protected ArrayList<ItemInfo> getTrunkItems(){
    return trunkItems;
  }
  
  /**
   * Retrieves the list of tag items 
   * @return tag items list
   */
  protected ArrayList<ItemInfo> getTagItems(){
    return tagItems;
  }
  
  /**
   * Retrieves the list of branch items 
   * @return Branch items list
   */
  protected ArrayList<ItemInfo> getBranchItems(){
    return branchItems;
  }
  
  /**
   * Iterates through all the trunk items and formats them in preparation of
   * creating a JSON file.
   * 
   * @return A JSONObject with the proper formatting agreed upon for the trunk
   * items. 
   */
  private JSONObject formatTrunkItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : trunkItems){
      jObject.put("Name", i.getName());
      jObject.put("date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("children", jArray);
    
    return parentJObj;
  }

  /**
   * Iterates through all the tag items and formats them in preparation of
   * creating a JSON file.
   * 
   * @return A JSONObject with the proper formatting agreed upon for the tag
   * items. 
   */
  private JSONObject formatTagItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : tagItems){
      jObject.put("Name", i.getName());
      jObject.put("date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("children", jArray);
    
    return parentJObj;
  }
  
  /**
   * Iterates through all the branch items and formats them in preparation of
   * creating a JSON file.
   * 
   * @return A JSONObject with the proper formatting agreed upon for the
   * branch items. 
   */
  private JSONObject formatBranchItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : branchItems){
      jObject.put("Name", i.getName());
      jObject.put("date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("children", jArray);
    
    return parentJObj;
  }
  
  /**
   * Formats the Trunk for preparation of creating a JSON file. 
   * 
   * @return A JSONObject formated with the appropriate Base trunk in it
   */
  protected JSONObject formatTrunkToJSON(){
    JSONObject trunkJObj = new JSONObject();
    
    trunkJObj = formatTrunkItems();
    trunkJObj.put("Name", baseTrunk.getName());
    trunkJObj.put("date", baseTrunk.getDate());
    trunkJObj.put("Rev", baseTrunk.getRevNum());
    
    return trunkJObj;
  }
  
  /**
   * Formats the Tags for preparation of creating a JSON file.
   * 
   * @return A JSONObject formated with the appropriate Base tag in it
   */
  protected JSONObject formatTagToJSON(){
    JSONObject tagJObj = new JSONObject();
    
    tagJObj = formatTagItems();
    tagJObj.put("Name", baseTag.getName());
    tagJObj.put("date", baseTag.getDate());
    tagJObj.put("Rev", baseTag.getRevNum());
    
    return tagJObj;
  }
  
  /**
   * Formats the branches for preparation of creating a JSON file.
   * 
   * @return A JSONObject formated with the appropriate Base branch in it
   */
  protected JSONObject formatBranchToJSON(){
    JSONObject branchJObj = new JSONObject();
    
    branchJObj = formatBranchItems();
    branchJObj.put("Name", baseBranch.getName());
    branchJObj.put("date", baseBranch.getDate());
    branchJObj.put("Rev", baseBranch.getRevNum());
    
    return branchJObj;
  }
}
