package SVN.graphical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Parser{
  private String textFilePath = "C:\\Users\\d2chau\\Documents\\UCD Academics\\Fall 2013\\CSCI 4738 Senior Design I\\java program\\SVN_Graphical_View\\src\\SVN\\graphical\\";
	private String textFileName = "output2.txt";

	private Items project    = new Items();
	private Items baseTrunk  = new Items();
	private Items baseTag    = new Items();
	private Items baseBranch = new Items();
  
	private Items trunkItems  = new Items();
	private Items tagItems    = new Items();
	private BranchItem branchItems = new BranchItem();
  
	private List<String> generalInfoTableQuery = new ArrayList<String>();
	private List<String> trunkInfoTableQuery = new ArrayList<String>();
  private List<String> trunkItemInfoTableQuery = new ArrayList<String>();
  private List<String> branchInfoTableQuery = new ArrayList<String>();
  private List<String> branchItemInfoTableQuery = new ArrayList<String>();
  private List<String> tagInfoTableQuery = new ArrayList<String>();
  private List<String> tagItemInfoTableQuery = new ArrayList<String>();

	//Default Constructor
	Parser(){}

	protected void fileReader() throws SQLException{
		BufferedReader br = null;
		 
		try {
      //GSS.initNCommit(true);
			String sCurrentLine = "";
			br = new BufferedReader(new FileReader(textFilePath + textFileName));

			while ((sCurrentLine = br.readLine()) != null) {
				lineInterpretor(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null){
          br.close();				  
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	//Received every line in the text file and parses it into project structures
	private void lineInterpretor(String inputLine) throws SQLException{
		String tempString = "";
		String remainingString = "";
		int counter = 0;
		List<String> tempList = new ArrayList<String>();
		
		for(char c:inputLine.toCharArray()){
			tempString += c;
			++counter;

			if(c == '>'){
				remainingString = inputLine.substring(counter, inputLine.length());
				break; 
			}
		}
		switch (tempString){
		case "<pr>":
			initializeEverything();
			tempList = parseBySemicolon(remainingString);

			if(project.setContainerItems(tempList) == false){
			  System.out.println("Please check your input for duplicate project names in a single project.");
			}
			break;
		case "<basetag>":
      tempList = parseBySemicolon(remainingString);
      
      if(baseTag.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate tag names in a single project.");
      }
      break;
		case "<tagitems>":
		  tempList = parseBySemicolon(remainingString);
		  
			if(tagItems.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate tag item names in a single project.");
			}
			break;
		case "<basetrunk>":
      tempList = parseBySemicolon(remainingString);

      if(baseTrunk.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate trunk names in a single project.");
      }
      break;
		case "<trunkitems>":
      tempList = parseBySemicolon(remainingString);

      if(trunkItems.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate trunk item names in a single project.");
      }
			break;
		case "<basebranch>":
      tempList = parseBySemicolon(remainingString);
      
      if(baseBranch.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate branch names in a single project.");
      }
			break;
		case "<branchinglevel>":
		  tempList = parseBySemicolon(remainingString);
		  
      if(branchItems.setContainerItems(tempList) == false){
        System.out.println("Please check your input for duplicate branch items names in a single project.");
      }
			break;
			/*
			 * Sub-projects have not yet implemented
			 */
		case "<subproject>":
			break;
			
			//This will format all queries for all tables and send it pass it to 
			//another class which will send the individual queries out per project
		case "<prend>":
		  formatGeneralInfoTable();
		  formatTrunkInfoTable();
		  formatTrunkItemInfoTable();
		  formatBranchInfoTable();
		  formatBranchItemInfoTable();
		  formatTagInfoTable();
		  formatTagItemInfoTable();
      //Un-comment the line below for debugging purposes
      //debuggingFunction();
		  
		  Graphical_SVN_SQL.callOthers(generalInfoTableQuery, trunkInfoTableQuery,
		      trunkItemInfoTableQuery, branchInfoTableQuery, 
		      branchItemInfoTableQuery, tagInfoTableQuery, tagItemInfoTableQuery);        
      System.out.println("The database has been upated!");
			break;
		default:
			System.out.println("Inside Default!");
			break;		 
		}
	}
	
	//Parses a string by semicolon and returns a list with those strings
	private static List<String> parseBySemicolon(String inputStr){
    List<String> tempList = new ArrayList<String>();
    int strLocation = 0;
    int lastCounter = 0;
    tempList.clear();
    
    //Parsing the passed in string delimited by a semicolon
    while(strLocation < inputStr.length()){
      if(inputStr.charAt(strLocation) == ';'){
        tempList.add(inputStr.substring(lastCounter, strLocation));
        lastCounter = strLocation + 1;          
      }
      ++strLocation;
    }
    
    tempList.add(inputStr.substring(lastCounter, inputStr.length()));
    return tempList;
  }
	
	//Composes the query to be used for the generalinfo table
	private void formatGeneralInfoTable(){    
    generalInfoTableQuery.add(project.getItemNameFirst());
    generalInfoTableQuery.add(project.getItemRevNumFirst());
    generalInfoTableQuery.add(project.getItemDateFirst());
    generalInfoTableQuery.add(baseTrunk.getItemNameFirst());
    generalInfoTableQuery.add(baseTrunk.getItemRevNumFirst());
    generalInfoTableQuery.add(baseTag.getItemNameFirst());
    generalInfoTableQuery.add(baseTag.getItemRevNumFirst());
    generalInfoTableQuery.add(baseBranch.getItemNameFirst());
    generalInfoTableQuery.add(baseBranch.getItemRevNumFirst());
	}
	
	//Composes the query to be used for the trunkinfo table
	private void formatTrunkInfoTable(){
    if(trunkItems.itemNameEmpty()){
      trunkInfoTableQuery.add(baseTrunk.getItemNameFirst());
      trunkInfoTableQuery.add(baseTrunk.getItemRevNumFirst());
      trunkInfoTableQuery.add(baseTrunk.getItemDateFirst());
      trunkInfoTableQuery.add(";;;");
      trunkInfoTableQuery.add("-1");
    }
    else{
      for(int i=0; i<trunkItems.itemNameSize(); ++i){
        trunkInfoTableQuery.add(baseTrunk.getItemNameFirst());
        trunkInfoTableQuery.add(baseTrunk.getItemRevNumFirst());
        trunkInfoTableQuery.add(baseTrunk.getItemDateFirst());
        trunkInfoTableQuery.add(trunkItems.getItemNameIndex(i));
        trunkInfoTableQuery.add(trunkItems.getItemRevNumIndex(i));
      }
    }
	}
	
	//Composes the query to be used for the trunkiteminfo table
	private void formatTrunkItemInfoTable(){
	   for(int i=0; i<trunkItems.itemNameSize(); ++i){
	     trunkItemInfoTableQuery.add(trunkItems.getItemNameIndex(i));
	     trunkItemInfoTableQuery.add(trunkItems.getItemRevNumIndex(i));
       trunkItemInfoTableQuery.add(trunkItems.getItemDateIndex(i));
	   }
	}
	
  //Composes the query to be used for the branchinfo table
	private void formatBranchInfoTable(){
    for(int i=0; i<branchItems.itemNameSize(); ++i){
      branchInfoTableQuery.add(baseBranch.getItemNameFirst());
      branchInfoTableQuery.add(baseBranch.getItemRevNumFirst());
      branchInfoTableQuery.add(baseBranch.getItemDateFirst());
      branchInfoTableQuery.add(branchItems.getItemNameIndex(i));
      branchInfoTableQuery.add(branchItems.getItemRevNumIndex(i));
    }
	}
	
  //Composes the query to be used for the branchItemInfo table
	private void formatBranchItemInfoTable(){
    for(int i=0; i<branchItems.itemNameSize(); ++i){
      branchItemInfoTableQuery.add(branchItems.getItemNameIndex(i));
      branchItemInfoTableQuery.add(branchItems.getItemRevNumIndex(i));
      branchItemInfoTableQuery.add(branchItems.getItemDateIndex(i));
      branchItemInfoTableQuery.add(branchItems.getParentNameIndex(i));
      branchItemInfoTableQuery.add(branchItems.getParentRevNumIndex(i));
    }
	}
	
  //Composes the query to be used for the tagInfo table
	private void formatTagInfoTable(){
    for(int i=0; i<tagItems.itemNameSize(); ++i){
      tagInfoTableQuery.add(baseTag.getItemNameFirst());
      tagInfoTableQuery.add(baseTag.getItemRevNumFirst());
      tagInfoTableQuery.add(baseTag.getItemDateFirst());
      tagInfoTableQuery.add(tagItems.getItemNameIndex(i));
      tagInfoTableQuery.add(tagItems.getItemRevNumIndex(i));
    }
	}
	
  //Composes the query to be used for the tagItemInfo table
	private void formatTagItemInfoTable(){
    for(int i=0; i<tagItems.itemNameSize(); ++i){
      tagItemInfoTableQuery.add(tagItems.getItemNameIndex(i));
      tagItemInfoTableQuery.add(tagItems.getItemRevNumIndex(i));
      tagItemInfoTableQuery.add(tagItems.getItemDateIndex(i));
    }
	}
	
	//Clears all containers to be used in the future
	private void initializeEverything(){
		project.clearItemName();
		project.clearItemRevNum();
		project.clearItemDate();
		
		baseTrunk.clearItemName();
		baseTrunk.clearItemRevNum();
		baseTrunk.clearItemDate();
		
		baseTag.clearItemName();
		baseTag.clearItemRevNum();
    baseTag.clearItemDate();
    
		baseBranch.clearItemName();
		baseBranch.clearItemRevNum();
		baseBranch.clearItemDate();
    
		trunkItems.clearItemName();
		trunkItems.clearItemRevNum();
		trunkItems.clearItemDate();
    
		tagItems.clearItemName();
		tagItems.clearItemRevNum();
		tagItems.clearItemDate();
    
		branchItems.clearItemName();
		branchItems.clearItemRevNum();
		branchItems.clearItemDate();
	}
	
	//Function which prints out all information in containers and queries.
	private void debuggingFunction(){
	  for(int i=0; i<project.itemNameSize(); ++i){
      System.out.println("Project: " + project.getItemNameIndex(i));
      System.out.println("Trunk: " + baseTrunk.getItemNameIndex(i));
      System.out.println("Tag: " + baseTag.getItemNameIndex(i));
      System.out.println("Branch: " + baseBranch.getItemNameIndex(i));
    }
    for(int i=0; i<branchItems.itemNameSize(); ++i){
      System.out.println("bIName: " + branchItems.getItemNameIndex(i));
      System.out.println("bIRevNum: " + branchItems.getItemRevNumIndex(i));
      System.out.println("bIDate: " + branchItems.getItemDateIndex(i));
      System.out.println("bIParent: " + branchItems.getParentNameIndex(i));
      System.out.println("bIParentRevNum: " + branchItems.getParentRevNumIndex(i));
    }
    for(int i=0; i<tagItems.itemNameSize(); ++i){
      System.out.println("taIName: " + tagItems.getItemNameIndex(i));
      System.out.println("taIRevNum: " + tagItems.getItemRevNumIndex(i));
      System.out.println("taIDate: " + tagItems.getItemDateIndex(i));
    }
    for(int i=0; i<trunkItems.itemNameSize(); ++i){
      System.out.println("trIName: " + trunkItems.getItemNameIndex(i));
      System.out.println("trIRevNum: " + trunkItems.getItemRevNumIndex(i));
      System.out.println("trIDate: " + trunkItems.getItemDateIndex(i));
    }
    
    for(int i=0; i<generalInfoTableQuery.size(); ++i){
      System.out.println("generalInfoTableQuery: " + generalInfoTableQuery.get(i));
    }
    for(int i=0; i<trunkInfoTableQuery.size(); ++i){
      System.out.println("trunkInfoTableQuery: " + trunkInfoTableQuery.get(i));
    }
    for(int i=0; i<trunkItemInfoTableQuery.size(); ++i){
      System.out.println("trunkItemInfoTableQuery: " + trunkItemInfoTableQuery.get(i));
    }
    for(int i=0; i<branchInfoTableQuery.size(); ++i){
      System.out.println("branchInfoTableQuery: " + branchInfoTableQuery.get(i));
    }
    for(int i=0; i<branchItemInfoTableQuery.size(); ++i){
      System.out.println("branchItemInfoTableQuery: " + branchItemInfoTableQuery.get(i));
    }
    for(int i=0; i<tagInfoTableQuery.size(); ++i){
      System.out.println("tagInfoTableQuery: " + tagInfoTableQuery.get(i));
    }
    for(int i=0; i<tagItemInfoTableQuery.size(); ++i){
      System.out.println("tagItemInfoTableQuery: " + tagItemInfoTableQuery.get(i));
    }
	}
}
