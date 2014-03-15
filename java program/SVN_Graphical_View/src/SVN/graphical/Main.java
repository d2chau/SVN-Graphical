package SVN.graphical;

import java.sql.SQLException;

public class Main extends Parser{

  /**
   * Description: Main function to start program
   *
   * @author  David Chau
   * @throws SQLExecption if connection is not established and/or modiication
   *         in database was not successful
   */
  public static void main(String[] argv) throws SQLException{
	  Parser parserVar = new Parser();
	  parserVar.fileReader();
  }
  
}