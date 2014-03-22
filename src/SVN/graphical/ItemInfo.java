/**
 * File: ItemInfo.java
 * 
 * Description: This container class is for information to be retrieved.
 * Everything within a project has specific names, revision numbers, and dates.
 * This Items container is for each of those specific items.
 * 
 * This class contains additional functions to assist with the retrieval 
 * and manipulation of item information. 
 * 
 * Author(s): David Chau 
 */

package SVN.graphical;

public class ItemInfo {
  private String itemName = "";
  private Long revNum;
  private String date = "";
  
  /**
   * Default Constructor
   */
  ItemInfo(){}
  
  /**
   * Constructor
   * 
   * @param itemName Name of an item within a project
   * @param revNum   Revision number of an item within a project
   * @param date     Date of an item within a project
   * 
   */
  ItemInfo(String itemName, Long revNum, String date){
    this.itemName = itemName;
    this.revNum = revNum;
    this.date = date;
  }
  
  /**
   * Returns the Name of the item
   * @return Name of the Item
   */
  String getName(){
    return itemName;
  }
  
  /**
   * Returns the Revision number of the item
   * @return Revision number of the Item
   */
  Long getRevNum(){
    return revNum;
  }
  
  /**
   * Returns the Date of the item
   * @return Date of the Item
   */
  String getDate(){
    return date;
  }
  
  /**
   * Sets the Name of the item
   * @param itemName Name of the Item to be set
   */
  void setName(String itemName){
    this.itemName = itemName;
  }
  
  /**
   * Sets the Revision number of the item
   * @param revNum Revision number of the Item to be set
   */
  void setRevNum(Long revNum){
    this.revNum = revNum;
  }
  
  /**
   * Sets the Date of the item
   * @param date Date of the Item to be set
   */
  void setDate(String date){
    this.date = date;
  }
}
