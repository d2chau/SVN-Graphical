package SVN.graphical;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
 
public class Graphical_SVN_SQL{

  private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_CONNECTION = "jdbc:mysql://localhost/testmysqluser?";
  private static final String DB_USER = "testmySQLUser";
  private static final String DB_PASSWORD = "asdf1234";
  
  private static Connection dbConnection = null;
  private static PreparedStatement preparedStatement = null;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  protected static void callOthers(List<String> generalInfoTableQuery, 
      List<String> trunkInfoTableQuery, List<String> trunkItemInfoTableQuery,
      List<String> branchInfoTableQuery, List<String> branchItemInfoTableQuery,
      List<String> tagInfoTableQuery, List<String> tagItemInfoTableQuery)
          throws SQLException{
    
    dbConnection = getDBConnection();
    
    insertGeneralInfoTable(generalInfoTableQuery);
    insertFiveItems(trunkInfoTableQuery, QueryStrings.insertTrunkInfo);
    insertThreeItems(trunkItemInfoTableQuery, QueryStrings.insertTrunkItemInfo);
    insertFiveItems(branchInfoTableQuery, QueryStrings.insertBranchInfo);
    insertFiveItems(branchItemInfoTableQuery,QueryStrings.insertBranchItemInfo);
    insertFiveItems(tagInfoTableQuery, QueryStrings.insertTagInfo);
    insertThreeItems(tagItemInfoTableQuery, QueryStrings.insertTagItemInfo);
    
    if (dbConnection != null) {
      dbConnection.close();
    }
  }
  
  private static Connection getDBConnection() {
    Connection dbConnection = null;
    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
 
    try {
      dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return dbConnection;
  }
  
  protected static void insertGeneralInfoTable(List<String> insertList) throws SQLException { 
    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        preparedStatement = dbConnection.prepareStatement(QueryStrings.insertGeneralInfo.getValue());
  
        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          
          //Converting String to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          
          ++listLocation;
          preparedStatement.setString(4, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(5, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.setString(6, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(7, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.setString(8, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(9, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.addBatch();
        }      
        
        // execute insert SQL statement
        preparedStatement.executeUpdate();
   
        System.out.println("Inserted into generalinfo table!");
   
      } catch (SQLException e) {
        String exceptionStr = e.getMessage();
        if(exceptionStr.contains("Duplicate entry")){
          updateGeneralInfoTable(insertList);
        }
        else{
          System.out.println(exceptionStr);
        }
      } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }
    }
  }

  protected static void updateGeneralInfoTable(List<String> insertList) throws SQLException { 
    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        String updateStr = "UPDATE generalinfo SET pName = ?, pRevNum = ?, pDate = ?, trName = ?, trRevNum = ?, taName = ?, taRevNum = ?, bName = ?, bRevNum = ? WHERE pName = ?";
        preparedStatement = dbConnection.prepareStatement(updateStr);
  
        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          
          //Converting String to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          
          ++listLocation;
          preparedStatement.setString(4, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(5, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.setString(6, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(7, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.setString(8, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(9, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          preparedStatement.setString(10, insertList.get(listLocation-9));
          preparedStatement.addBatch();
        }      
        
        // execute insert SQL statement
        preparedStatement.executeUpdate();
   
        System.out.println("Updated generalinfo table!");
   
      } catch (SQLException e) {
        System.out.println("generalinfo could not be updated!");
         System.out.println(e.getMessage());
      } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }
    }
  }

  protected static void insertFiveItems(List<String> insertList, QueryStrings qs) throws SQLException {
    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        preparedStatement = dbConnection.prepareStatement(qs.getValue());

        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          
          //Converting string to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          
          ++listLocation;
          preparedStatement.setString(4, insertList.get(listLocation));
          ++listLocation;
          if(insertList.get(listLocation).equals(";;;")){
            preparedStatement.setInt(5,-1);
          }
          else{
            preparedStatement.setInt(5, Integer.parseInt(insertList.get(listLocation)));
          }
          ++listLocation;
          preparedStatement.addBatch();
        }      
        
        // execute insert SQL statement
        preparedStatement.executeBatch();
   
        System.out.println("Inserted into "+ qs +" table!");
   
      } catch (SQLException e) {
        String exceptionStr = e.getMessage();
        if(exceptionStr.contains("Duplicate entry")){
          updateFiveItems(insertList, qs);
        }
        else{
          System.out.println(exceptionStr);
        }
        } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }

    }
  }

  protected static void updateFiveItems(List<String> insertList, QueryStrings qs) throws SQLException {
    String tempStr = getFirstWord(qs.getValue());
    String updateStr = "";
    int addition = 2;
    
    switch(tempStr){
      case "trunkinfo":
        updateStr = "UPDATE trunkinfo SET trName = ?, trRevNum = ?, trDate = ?, trIName = ?, trIRevNum = ? WHERE trDate = ?";
        addition = 3;
        break;
      case "taginfo":
        updateStr = "UPDATE taginfo SET taName = ?, taRevNum = ?, taDate = ?, taIName = ?, taIRevNum = ?  WHERE taIName = ?";
        addition = 2;
        break;
      case "branchinfo":
        updateStr = "UPDATE branchinfo SET bName = ?, bRevNum = ?, bDate = ?, bIName = ?, bIRevNum = ? WHERE bIName = ?";
        addition = 2;
        break;
      case "branchiteminfo":
        updateStr = "UPDATE branchiteminfo SET bIName = ?, bIRevNum = ?, bIDate = ?, parent = ?, parentRevNum = ? WHERE bIName = ?";
        addition = 5;
        break;
      default:
        System.out.println("There was an error when determining which table to update.");
        break;
    }

    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        preparedStatement = dbConnection.prepareStatement(updateStr);

        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;
          
          //Converting string to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          
          ++listLocation;
          preparedStatement.setString(4, insertList.get(listLocation));
          ++listLocation;
          if(insertList.get(listLocation).equals(";;;")){
            preparedStatement.setInt(5,-1);
          }
          else{
            preparedStatement.setInt(5, Integer.parseInt(insertList.get(listLocation)));
          }
          ++listLocation;

          preparedStatement.setString(6, insertList.get(listLocation-addition));

          preparedStatement.addBatch();
        }      
        
        // execute insert SQL statement
        preparedStatement.executeBatch();
   
        System.out.println("Updated "+ qs +" table!");
   
      } catch (SQLException e) {
        System.out.println("Could not update " + qs + " table!");
         System.out.println(e.getMessage());
      } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }

    }
  }

  protected static void insertThreeItems(List<String> insertList, QueryStrings qs) throws SQLException {
    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        preparedStatement = dbConnection.prepareStatement(qs.getValue());
  
        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;

          //Converting string to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          ++listLocation;

          preparedStatement.addBatch();
        }      

        // execute insert SQL statement
        preparedStatement.executeBatch();

        System.out.println("Inserted into "+ qs +" table!");

      } catch (SQLException e) {
        String exceptionStr = e.getMessage();
        if(exceptionStr.contains("Duplicate entry")){
          updateThreeItems(insertList, qs);
        }
        else{
          System.out.println(exceptionStr);
        }
        //Duplicate entry 'hmm2-283' for key 'PRIMARY'
      } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }
    }
  }
  
  protected static void updateThreeItems(List<String> insertList, QueryStrings qs) throws SQLException {
    String tempStr = getFirstWord(qs.getValue());
    String updateStr = "";
    
    switch(tempStr){
      case "tagiteminfo":
        updateStr = "UPDATE tagiteminfo SET taIName = ?, taIRevNum = ?, taIDate = ? WHERE taIName = ?";
        break;
      case "trunkiteminfo":
        updateStr = "UPDATE trunkiteminfo SET trIName = ?, trIRevNum = ?, trIDate = ? WHERE trIName = ?";
        break;
      default:
        System.out.println("There was an error when determining which table to update.");
        break;
    }
    
    int listLocation = 0;
    if(!insertList.isEmpty()){
      try {
        preparedStatement = dbConnection.prepareStatement(updateStr);

        while(listLocation < insertList.size()){
          preparedStatement.setString(1, insertList.get(listLocation));
          ++listLocation;
          preparedStatement.setInt(2, Integer.parseInt(insertList.get(listLocation)));
          ++listLocation;

          //Converting string to sql date format
          try {
            java.util.Date utilDate = format.parse(insertList.get(listLocation));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          ++listLocation;
          preparedStatement.setString(4, insertList.get(listLocation-3));
          preparedStatement.addBatch();
        }      

        // execute update SQL statement
        preparedStatement.executeBatch();

        System.out.println("Updated "+ qs +" table!");

      } catch (SQLException e) {
        System.out.println("Could not update " + qs + " table!");
        System.out.println(e.getMessage());
      } finally {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      }
    }
  }
  
  private static String getFirstWord(String inputStr){
    String tempStr = "";
    int counter = 0;
    int lastLocation = 0;
    
    for(int i=0; i<inputStr.length(); ++i){
      if(inputStr.charAt(i) == ' '){
        ++counter;
        if(counter == 4){
          break;
        }
        tempStr = inputStr.substring(lastLocation+1,i);
        lastLocation = i;
      }
    }

    return tempStr;
  }
}