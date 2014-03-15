package SVN.graphical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HttpControl {
  private static String alias = "";
  private static String HTTPAddr = "";
  private static String userName = "";
  private static String pw = "";
  private String httpConfigFileLocation = "";
  private String httpJsonFileLocation = "";
  
  HttpControl(){}
  
  HttpControl(String fileLocation){
    alias = "";
    HTTPAddr = "";
    userName = "";
    pw = "";
    this.httpConfigFileLocation = fileLocation;
  }
  
  protected String getAlias(){
    return alias;
  }
  
  protected String getHTTPAddr(){
    return HTTPAddr;
  }
  
  protected String getUserName(){
    return userName;
  }

  protected String getPW(){
    return pw;
  }

  protected void setAlias(String alias){
    this.alias = alias;
  }
  
  protected void setHTTPAddr(String HTTPAddr){
    this.HTTPAddr = HTTPAddr;
  }
  
  protected void setUserName(String userName){
    this.userName = userName;
  }

  protected void setPW(String pw){
    this.pw = pw;
  }
}
