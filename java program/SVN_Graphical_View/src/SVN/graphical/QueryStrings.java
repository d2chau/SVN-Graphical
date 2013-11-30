package SVN.graphical;

public enum QueryStrings {
  insertGeneralInfo ("INSERT INTO generalinfo (pName, pRevNum, pDate, trName,"
      + " trRevNum, taName, taRevNum, bName, bRevNum) VALUES"
      + "(?,?,?,?,?,?,?,?,?)"),
  insertTrunkInfo ("INSERT INTO trunkinfo (trName, trRevNum, trDate, trIName, "
      + "trIRevNum) VALUES(?,?,?,?,?)"),
  insertTrunkItemInfo ("INSERT INTO trunkiteminfo (trIName, trIRevNum, "
      + "trIDate) VALUES(?,?,?)"),
  insertTagInfo ("INSERT INTO taginfo "
      + "(taName, taRevNum, taDate, taIName, taIRevNum) VALUES(?,?,?,?,?)"),
  insertTagItemInfo("INSERT INTO tagiteminfo (taIName, taIRevNum, taIDate) "
      + "VALUES(?,?,?)"),
  insertBranchInfo( "INSERT INTO branchinfo (bName, bRevNum, bDate, bIName,"
      + " bIRevNum) VALUES(?,?,?,?,?)"),
  insertBranchItemInfo("INSERT INTO branchiteminfo (bIName, bIRevNum, bIDate,"
      + " parent, parentRevNum) VALUES(?,?,?,?,?)");
  
  private final String value;

  private QueryStrings(String s) {
      value = s;
  }

  public boolean equalsName(String otherName){
      return (otherName == null)? false:value.equals(otherName);
  }

  public String getValue(){
     return value;
  }
}
