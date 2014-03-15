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
  
  Project(){
    baseTrunk = new ItemInfo();
    baseBranch = new ItemInfo();
    baseTag = new ItemInfo();
    
    trunkItems.clear();
    branchItems.clear();
    tagItems.clear();

  }
  
  protected void addTrunkItems(String trunkName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(trunkName, revNum, date);
    trunkItems.add(tempItem);
  }

  protected void addBranchItems(String branchName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(branchName, revNum, date);
    branchItems.add(tempItem);
  }

  protected void addTagItems(String tagName, Long revNum, String date){
    ItemInfo tempItem = new ItemInfo(tagName, revNum, date);
    tagItems.add(tempItem);
  }
  
  protected void setProjectName(String name){
    projectName = name;
  }
  
  protected void setProjectDate(String date){
    projectDate = date;
  }
  
  protected void setProjectRevNum(Long revNum){
    projectRevNum = revNum;
  }  
  
  protected void setBaseTrunk(String trunkName, Long revNum, String date){
    baseTrunk.setName(trunkName);
    baseTrunk.setRevNum(revNum);
    baseTrunk.setDate(date);
  }

  protected void setBaseBranch(String branchName, Long revNum, String date){
    baseBranch.setName(branchName);
    baseBranch.setRevNum(revNum);
    baseBranch.setDate(date);
  }
  
  protected void setBaseTag(String tagName, Long revNum, String date){
    baseTag.setName(tagName);
    baseTag.setRevNum(revNum);
    baseTag.setDate(date);
  }
  
  protected void setBaseTrunkName(String trunkName){
    baseTrunk.setName(trunkName);
  }
  
  protected void setBaseTrunkRevNum(Long revNum){
    baseTrunk.setRevNum(revNum);
  }
  
  protected void setBaseTrunkDate(String date){
    baseTrunk.setName(date);
  }

  protected void setBaseBranchName(String branchName){
    baseBranch.setName(branchName);
  }
  
  protected void setBaseBranchRevNum(Long revNum){
    baseBranch.setRevNum(revNum);
  }
  
  protected void setBaseBranchDate(String date){
    baseBranch.setName(date);
  }
  
  protected void setBaseTagName(String tagName){
    baseTag.setName(tagName);
  }
  
  protected void setBaseTagRevNum(Long revNum){
    baseTag.setRevNum(revNum);
  }
  
  protected void setBaseTagDate(String date){
    baseTag.setName(date);
  }  
  
  protected String getProjectName(){
    return projectName;
  }
  
  protected String getProjectDate(){
    return projectDate;
  }
  
  protected Long getProjectRevNum(){
    return projectRevNum;
  }
  
  protected String getBaseTrunkName(){
    return baseTrunk.getName();
  }

  protected Long getBaseTrunkRevNum(){
    return baseTrunk.getRevNum();
  }
  
  protected String getBaseTrunkDate(){
    return baseTrunk.getDate();
  }

  protected String getBaseBranchName(){
    return baseBranch.getName();
  }

  protected Long getBaseBranchRevNum(){
    return baseBranch.getRevNum();
  }
  
  protected String getBaseBranchDate(){
    return baseBranch.getDate();
  }
  
  protected String getBaseTagName(){
    return baseTag.getName();
  }

  protected Long getBaseTagRevNum(){
    return baseTag.getRevNum();
  }
  
  protected String getBaseTagDate(){
    return baseTag.getDate();
  }
  
  protected ItemInfo getBaseTrunk(){
    return baseTrunk;
  }
  
  protected ItemInfo getBaseBranch(){
    return baseBranch;
  }
  
  protected ItemInfo getBaseTag(){
    return baseTag;
  }
  
  protected ArrayList<ItemInfo> getTrunkItems(){
    return trunkItems;
  }
  
  protected ArrayList<ItemInfo> getTagItems(){
    return tagItems;
  }
  
  protected ArrayList<ItemInfo> getBranchItems(){
    return branchItems;
  }
  
  private JSONObject formatTrunkItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : trunkItems){
      jObject.put("Name", i.getName());
      jObject.put("Date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("Children", jArray);
    
    return parentJObj;
  }

  private JSONObject formatTagItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : tagItems){
      jObject.put("Name", i.getName());
      jObject.put("Date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("Children", jArray);
    
    return parentJObj;
  }
  
  private JSONObject formatBranchItems(){
    //put items in objects first
    JSONArray jArray = new JSONArray();
    JSONObject parentJObj = new JSONObject();
    JSONObject jObject = new JSONObject();
        
    for(ItemInfo i : branchItems){
      jObject.put("Name", i.getName());
      jObject.put("Date", i.getDate());
      jObject.put("Rev", i.getRevNum());
      
      jArray.add(jObject);
      jObject = new JSONObject();
    }
    parentJObj.put("Children", jArray);
    
    return parentJObj;
  }
  
  protected JSONObject formatTrunkToJSON(){
    JSONObject trunkJObj = new JSONObject();
    
    trunkJObj = formatTrunkItems();
    trunkJObj.put("Name", baseTrunk.getName());
    trunkJObj.put("Date", baseTrunk.getDate());
    trunkJObj.put("Rev", baseTrunk.getRevNum());
    
    return trunkJObj;
  }
  
  protected JSONObject formatTagToJSON(){
    JSONObject tagJObj = new JSONObject();
    
    tagJObj = formatTagItems();
    tagJObj.put("Name", baseTag.getName());
    tagJObj.put("Date", baseTag.getDate());
    tagJObj.put("Rev", baseTag.getRevNum());
    
    return tagJObj;
  }

  protected JSONObject formatBranchToJSON(){
    JSONObject branchJObj = new JSONObject();
    
    branchJObj = formatBranchItems();
    branchJObj.put("Name", baseBranch.getName());
    branchJObj.put("Date", baseBranch.getDate());
    branchJObj.put("Rev", baseBranch.getRevNum());
    
    return branchJObj;
  }
}
