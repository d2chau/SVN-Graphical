/**
 * File: Main.java
 * 
 * Description: This program connects to a SVN server and retrieves information
 * which is later saved into a file with a JSON format. Once the file is 
 * finished being written, a website will use those JSON files and display the
 * graph accordingly. 
 * 
 * Author(s): David Chau 
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