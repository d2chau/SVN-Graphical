package SVN.graphical;

public class ItemInfo {
  private String itemName = "";
  private Long revNum;
  private String date = "";
  
  ItemInfo(){}
  
  ItemInfo(String itemName, Long revNum, String date){
    this.itemName = itemName;
    this.revNum = revNum;
    this.date = date;
  }
  
  String getName(){
    return itemName;
  }
  
  Long getRevNum(){
    return revNum;
  }
  
  String getDate(){
    return date;
  }
  
  void setName(String itemName){
    this.itemName = itemName;
  }
  
  void setRevNum(Long revNum){
    this.revNum = revNum;
  }
  
  void setDate(String date){
    this.date = date;
  }
}
