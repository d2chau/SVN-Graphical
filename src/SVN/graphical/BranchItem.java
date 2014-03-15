/**
 * File: BranchItem.java
 * 
 * Description: This class is a container for the information to be retrieved. 
 * All Items within a project have specific names, revision numbers, and dates
 * which will be utilized accordingly but branch items specifically have more
 * information including possible parents and parent revision numbers. This 
 * class contains additional functions to assist with the retrieval and setting
 * of info but also is extended from the Items container to have the same 
 * functionality  
 * 
 * Author: David Chau
 * 
 */

package SVN.graphical;

import java.util.ArrayList;
import java.util.List;

public class BranchItem extends Items{
  private List<String> branchItemName   = new ArrayList<String>();
  private List<String> branchItemRevNum = new ArrayList<String>();
  private List<String> branchItemDate   = new ArrayList<String>();
  private List<String> parentName       = new ArrayList<String>();
  private List<String> parentRevNum     = new ArrayList<String>();
  
  BranchItem(){}
  
  BranchItem(List<String> inputList){
    setContainerItems(inputList);
  }
  
  @Override
  public boolean setContainerItems(List<String> inputList){
    int listLocation = 0;      
    
    //This is if the branch does not have parents
    if(inputList.size() == 3){     
      //There should not be duplicate names in the same project
      if(branchItemName.contains(inputList.get(listLocation))){
        return false;
      }
      //Adding the data in the string to the class
      branchItemName.add(inputList.get(listLocation));
      ++listLocation;
      branchItemRevNum.add(inputList.get(listLocation));
      ++listLocation;
      branchItemDate.add(inputList.get(listLocation));
      //Using three consecutive semicolons to be a unique identifier meaning
      //there is no parent and parentDate.
      parentName.add(";;;");
      parentRevNum.add(";;;");
    }
    //If the branch does contain branches
    else{
      //There should not be duplicate names in the same project
      if(branchItemName.contains(inputList.get(listLocation))){
        return false;
      }
      //Adding the data in the string to the class
      branchItemName.add(inputList.get(listLocation));
      ++listLocation;
      branchItemRevNum.add(inputList.get(listLocation));
      ++listLocation;
      branchItemDate.add(inputList.get(listLocation));
      ++listLocation;
      parentName.add(inputList.get(listLocation));
      ++listLocation;
      parentRevNum.add(inputList.get(listLocation));
    }
    return true;
  }
  
  protected static int semicolonCount(String inputStr){
    int count = 0;
    
    for(char c:inputStr.toCharArray()){
      if(c == ';'){
        ++count;
      }
    }    
    return count;
  }
  
  //Returns the container's first item
  @Override
  public String getItemNameFirst(){
    return branchItemName.get(0);
  }

  @Override
  public String getItemRevNumFirst(){
    return branchItemRevNum.get(0);
  }
  
  @Override
  public String getItemDateFirst(){
    return branchItemDate.get(0);
  }
  
  public String getParentNameFirst(){
    return parentName.get(0);
  }
  
  public String getParentRevNumFirst(){
    return parentRevNum.get(0);
  }
  
  //Returns the container's indexed value
  @Override
  public String getItemNameIndex(int idx){
    return branchItemName.get(idx);
  }

  @Override
  public String getItemRevNumIndex(int idx){
    return branchItemRevNum.get(idx);
  }
  
  @Override
  public String getItemDateIndex(int idx){
    return branchItemDate.get(idx);
  }
  
  public String getParentNameIndex(int idx){
    return parentName.get(idx);
  }
  
  public String getParentRevNumIndex(int idx){
    return parentRevNum.get(idx);
  }
  
  //Returns the container's size
  @Override
  public int itemNameSize(){
    return branchItemName.size();
  }
  
  @Override
  public int itemRevNumSize(){
    return branchItemRevNum.size();
  }
  
  @Override
  public int itemDateSize(){
    return branchItemDate.size();
  }
  
  public int itemParentNameSize(){
    return parentName.size();
  }
  
  public int itemParentRevNumSize(){
    return parentRevNum.size();
  }
  
  //Removes the containers first  item
  @Override
  public void removeItemNameFirst(){
    branchItemName.remove(0);
  }

  @Override
  public void removeItemRevNumFirst(){
    branchItemRevNum.remove(0);
  }

  @Override
  public void removeItemDateFirst(){
    branchItemDate.remove(0);
  }
  
  public void removeParentNameFirst(){
    parentName.remove(0);
  }
  
  public void removeParentRevNumFirst(){
    parentRevNum.remove(0);
  }

  //Removed the indexed value within the container
  @Override
  public void removeIndexOfItemName(int idx){
    branchItemName.remove(idx);
  }
  
  @Override
  public void removeIndexOfItemRevNum(int idx){
    branchItemRevNum.remove(idx);
  }
  
  @Override
  public void removeIndexOfItemDate(int idx){
    branchItemDate.remove(idx);
  }
  
  public void removeIndexOfParentName(int idx){
    parentName.remove(idx);
  }
  
  public void removeIndexOfParentRevNum(int idx){
    parentRevNum.remove(idx);
  }
  
  //Clears what is in the containers
  @Override
  public void clearItemName(){
    branchItemName.clear();
  }
  
  @Override
  public void clearItemRevNum(){
    branchItemRevNum.clear();
  }
  
  @Override
  public void clearItemDate(){
    branchItemDate.clear();
  }
  
  public void clearParentName(){
    parentName.clear();
  }
  
  public void clearParentRevNum(){
    parentRevNum.clear();
  }
  
  //Returns a boolean to say whether the container is empty or not
  @Override
  public boolean itemNameEmpty(){
    return branchItemName.isEmpty();
  }
  
  @Override
  public boolean itemRevNumEmpty(){
    return branchItemRevNum.isEmpty();
  }
  
  @Override
  public boolean itemDateEmpty(){
    return branchItemDate.isEmpty();
  }
  
  public boolean parentNameEmpty(){
    return parentName.isEmpty();
  }
  
  public boolean parentRevNumEmpty(){
    return parentRevNum.isEmpty();
  }
}