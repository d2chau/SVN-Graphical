/**
 * File: Items.java
 * 
 * Description: This container class is for information to be retrieved.
 * A project has specific names, revision numbers, and dates. Most of the time
 * each project will contain more than one name, revision number, and date.
 * This container will store a list of those values for the project.
 * 
 * This class contains additional functions to assist with the retrieval 
 * and setting of item information. 
 * 
 * Author(s): d2chau
 */

package SVN.graphical;

import java.util.List;
import java.util.ArrayList;

public class Items {
  private List<String> itemName   = new ArrayList<String>();
  private List<String> itemRevNum = new ArrayList<String>();
  private List<String> itemDate   = new ArrayList<String>();
  
  /**
   * Default constructor
   */
  Items(){}
  
  /**
   * Constructor  Calling a separate function in the constructor since that can
   *  return possible errors. 
   * 
   * @param inputList List of strings to be inputed into the list of Items, 
   *  Names, Revision numbers, and Dates
   * @throws Runtime Exception Throws a runtime exception if the name of an
   *  item already exists within the container.
   */
  Items(List<String> inputList){
    if(!setContainerItems(inputList)){
      throw new RuntimeException("The list of items already contains this"
          + " value");
    }
  }
  
  /**
   * This adds project items to the lists of Names, Revision numbers, and 
   * dates. This function will check to see if the project contains duplicate
   * file names and return false if that's the case. Otherwise, it will add
   * the contents of the passed in list. 
   * 
   * @param inputList An list containing the information in regards to each of
   * items in a project.
   * 
   * @return          <code>True</code if the items are inputed into the class
   *                  successfully
   *                  <code>False</code> if the project name is already present
   *                  
   */
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
  
  /**
   * Retrieves the first Item Name that was inserted into the List of names. 
   * @return  First name of the list of Item Names.
   * 
   */
  public String getItemNameFirst(){
    return itemName.get(0);
  }

  /**
   * Retrieves the first Item Revision number that was inserted into the List 
   * of revision numbers. 
   * @return  First Revision number of the list of Item revision number.
   * 
   */
  public String getItemRevNumFirst(){
    return itemRevNum.get(0);
  }
  
  /**
   * Retrieves the first Item Date that was inserted into the List of date. 
   * @return  First date of the list of Item date.
   * 
   */
  public String getItemDateFirst(){
    return itemDate.get(0);
  }
  
  /**
   * Given a specific index, the name at that position is returned.
   * 
   * @param idx Index of the item within the list of Names
   * @return Returns null if the indexed value is greater than the size of the
   *         container. Otherwise, it will return the indexed Name.
   *         
   */
  public String getItemNameIndex(int idx){
    if(idx>itemName.size()){
      return null;
    }
    else{
      return itemName.get(idx);      
    }
  }

  /**
   * Given a specific index, the Revision number at that position is returned.
   * 
   * @param idx Index of the item within the list of Revision numbers
   * @return Returns null if the indexed value is greater than the size of the
   *         container. Otherwise, it will return the indexed Revision number.
   *         
   */
  public String getItemRevNumIndex(int idx){
    if(idx>itemName.size()){
      return null;
    }
    else{
      return itemRevNum.get(idx);
    }
  }
  
  /**
   * Given a specific index, the date at that position is returned.
   * 
   * @param idx Index of the item within the list of dates.
   * @return Returns null if the indexed value is greater than the size of the
   *         container. Otherwise, it will return the indexed date.
   *         
   */
  public String getItemDateIndex(int idx){
    return itemDate.get(idx);
  }
  
  /**
   * Returns the size of the list of Names
   * @return The number of elements within the List of Names
   */
  public int itemNameSize(){
    return itemName.size();
  }
  
  /**
   * Returns the size of the list of Revision numbers
   * @return The number of elements within the List of Revision numbers
   */
  public int itemRevNumSize(){
    return itemRevNum.size();
  }

  /**
   * Returns the size of the list of Dates
   * @return The number of elements within the List of Dates
   */
  public int itemDateSize(){
    return itemDate.size();
  }
  
  /**
   * Removes the first Name in the List of Names
   */
  public void removeItemNameFirst(){
    itemName.remove(0);
  }

  /**
   * Removes the first Revision number in the List of Revision numbers
   */
  public void removeItemRevNumFirst(){
    itemRevNum.remove(0);
  }

  /**
   * Removes the first Date in the List of Dates
   */
  public void removeItemDateFirst(){
    itemDate.remove(0);
  }

  /**
   * Given an index, the indexed location in the list of Names will be removed.
   * 
   * @param idx Index of the Name to be removed
   * @throws IndexOutOfBoundsException  If the index is larger than the size of
   *                                    the container, an exception is thrown.
   */
  public void removeIndexOfItemName(int idx){
    if(idx>itemName.size()){
      throw new IndexOutOfBoundsException("Index " + idx + " is out of "
          + "bounds!");
    }
    else{
      itemName.remove(idx);      
    }
  }
  
  /**
   * Given an index, the indexed location in the list of Revision numbers
   * will be removed.
   * 
   * @param idx Index of the Revision numbers to be removed
   * @throws IndexOutOfBoundsException  If the index is larger than the size of
   *                                    the container, an exception is thrown.
   */
  public void removeIndexOfItemRevNum(int idx){
    if(idx>itemRevNum.size()){
      throw new IndexOutOfBoundsException("Index " + idx + " is out of "
          + "bounds!");      
    }
    else{
      itemRevNum.remove(idx);      
    }
  }
  
  /**
   * Given an index, the indexed location in the list of Dates will be removed.
   * 
   * @param idx Index of the Dates to be removed
   * @throws IndexOutOfBoundsException  If the index is larger than the size of
   *                                    the container, an exception is thrown.
   */
  public void removeIndexOfItemDate(int idx){
    itemDate.remove(idx);
  }
  
  /**
   * Clears the entire list of Names inside of the container.
   */
  public void clearItemName(){
    itemName.clear();
  }
  
  /**
   * Clears the entire list of Revision numbers inside of the container.
   */
  public void clearItemRevNum(){
    itemRevNum.clear();
  }
  
  /**
   * Clears the entire list of Dates inside of the container.
   */
  public void clearItemDate(){
    itemDate.clear();
  }
  
  /**
   * Tells the calling function if the Names list is empty.
   * 
   * @return <code>True</code> if the list of names is empty.
   *         <code>False</code> if the list of names is not empty.
   */
  public boolean itemNameEmpty(){
    return itemName.isEmpty();
  }

  /**
   * Tells the calling function if the Revision numbers list is empty.
   * 
   * @return <code>True</code> if the list of Revision number is empty.
   *         <code>False</code> if the list of revision number is not empty.
   */
  public boolean itemRevNumEmpty(){
    return itemRevNum.isEmpty();
  }
  
  /**
   * Tells the calling function if the Date list is empty.
   * 
   * @return <code>True</code> if the list of Dates is empty.
   *         <code>False</code> if the list of Dates is not empty.
   */
  public boolean itemDateEmpty(){
    return itemDate.isEmpty();
  }
}
