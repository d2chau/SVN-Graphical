package SVN.graphical;

public class OS_Check {
  private static String OS = "";
  private static String cwd = "";

  OS_Check(){}
  
  protected String getOS(){
    return OS;
  }
  
  protected String getCWD(){
    return cwd;
  }
  
  protected void setOS(){
    OS = System.getProperty("os.name").toLowerCase();
  }
  
  protected void setCWD(){
    cwd = System.getProperty("user.dir");
  }
  
  /**
   * Sets the current working directory along with checks the operating system
   * as well. Prompts the user with more information. 
   */
  protected void find_OS_CWD(){
    OS = System.getProperty("os.name").toLowerCase();
    cwd = System.getProperty("user.dir");

    if (isWindows()) {
      System.out.println("This is Windows Device");
      cwd += '\\';
    } else if (isMac()) {
      System.out.println("This is Mac Device");
      cwd += '/';
    } else if (isUnix()) {
      System.out.println("This is Unix or Linux Device");
      cwd += '/';
    } else if (isSolaris()) {
      System.out.println("This is Solaris Device");
      System.out.println("Sorry Solaris is not supported!");
    } else {
      System.out.println("Sorry your OS is not supported!");
    }
    
    System.out.println("All files will be created here: ");
    System.out.println(cwd);
    System.out.println("\n<== Please be advised, only Mozilla Firefox and"
        + " Google Chrome with HTML5 support is capable of viewing the SVN "
        + "graph. ==>\n");
  }
  
  /**
   * Checks to see if the operating system is Windows based. 
   * @return true if the operating system is Windows base
   *         false if the operating system is not Windows base
   */
  public static boolean isWindows() {
    return (OS.indexOf("win") >= 0);
  }
 
  /**
   * Checks to see if the operating system is Mac based. 
   * @return true if the operating system is Mac base
   *         false if the operating system is not Mac base
   */
  public static boolean isMac() {
    return (OS.indexOf("mac") >= 0);
  }
 
  /**
   * Checks to see if the operating system is Linux or Unix based. 
   * @return true if the operating system is Linux or Unix base
   *         false if the operating system is not Linux or Unix base
   */
  public static boolean isUnix() {
    return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 ||
        OS.indexOf("aix") > 0 );
  }
 
  /**
   * Checks to see if the operating system is Solaris based. 
   * @return true if the operating system is Solaris base
   *         false if the operating system is not Solaris base
   */
  public static boolean isSolaris() {
    return (OS.indexOf("sunos") >= 0);
  }
}
