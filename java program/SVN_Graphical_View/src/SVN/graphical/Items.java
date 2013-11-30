package SVN.graphical;

import java.util.List;
import java.util.ArrayList;

public class Items {
  private List<String> itemName   = new ArrayList<String>();
  private List<String> itemRevNum = new ArrayList<String>();
  private List<String> itemDate   = new ArrayList<String>();
  
  //Default Constructor
  Items(){}
  
  //Constructor
  Items(List<String> inputList){
    setContainerItems(inputList);
  }
  
  public boolean setContainerItems(List<String> inputList){
    int listLocation = 0;      

    //There should not be duplicate names in the same project
    if(itemName.contains(inputList.get(listLocation))){
      return false;
    }
    //Adding the data in the string to the class
    itemName.add(inputList.get(listLocation));
    ++listLocation;
    itemRevNum.add(inputList.get(listLocation));
    ++listLocation;
    itemDate.add(inputList.get(listLocation));
    
    return true;
  }
  
  //Returns the container's first item
  public String getItemNameFirst(){
    return itemName.get(0);
  }

  public String getItemRevNumFirst(){
    return itemRevNum.get(0);
  }
  
  public String getItemDateFirst(){
    return itemDate.get(0);
  }
  
  public String getItemNameIndex(int idx){
    return itemName.get(idx);
  }

  public String getItemRevNumIndex(int idx){
    return itemRevNum.get(idx);
  }
  
  public String getItemDateIndex(int idx){
    return itemDate.get(idx);
  }
  
  //Returns the container's size
  public int itemNameSize(){
    return itemName.size();
  }
  
  public int itemRevNumSize(){
    return itemRevNum.size();
  }
  
  public int itemDateSize(){
    return itemDate.size();
  }
  
  public void removeItemNameFirst(){
    itemName.remove(0);
  }

  public void removeItemRevNumFirst(){
    itemRevNum.remove(0);
  }

  public void removeItemDateFirst(){
    itemDate.remove(0);
  }

  public void removeIndexOfItemName(int idx){
    itemName.remove(idx);
  }
  
  public void removeIndexOfItemRevNum(int idx){
    itemRevNum.remove(idx);
  }
  
  public void removeIndexOfItemDate(int idx){
    itemDate.remove(idx);
  }
  
  //Clears what is in the containers
  public void clearItemName(){
    itemName.clear();
  }
  
  public void clearItemRevNum(){
    itemRevNum.clear();
  }
  
  public void clearItemDate(){
    itemDate.clear();
  }
  
  //Returns a boolean to say whether the container is empty or not
  public boolean itemNameEmpty(){
    return itemName.isEmpty();
  }
  
  public boolean itemRevNumEmpty(){
    return itemRevNum.isEmpty();
  }
  
  public boolean itemDateEmpty(){
    return itemDate.isEmpty();
  }
}
