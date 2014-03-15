/**
 * File: Main.java
 * Description: This program connects to a SVN server and retrieves information
 * which is later saved into a file. Once the file is finished being written to
 * a connection is made to the mySQL database using JDBC connections, parses the
 * file into projects and uploads it to the mySQL database; making sure that it 
 * either updates or inserts the correct information.
 * Author(s): An Nguyen, David Chau 
 */

package SVN.graphical;

import java.io.IOException;
import org.tmatesoft.svn.core.SVNException;

 
public class Main {
  /**
   * Description: Main function to start program
   *
   * @author  David Chau
   * @throws SVNException 
   * @throws IOException 
   * @throws SQLExecption if connection is not established and/or modification
   *         in database was not successful
   */
  
  public static void main(String[] argv) throws SVNException{
    SVNFunctionality.connectToRepo();
  }
}