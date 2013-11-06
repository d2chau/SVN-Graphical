package chau;
/*
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

class TestClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SVNException {
		SVNRepositoryFactoryImpl.setup();
		String url = "https://ucdenver.svn.cloudforge.com/prjtest/trunk";
	    
		String name = "annguyen2509";
	    String password = "Peiu050612";
		SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
	    try{
	    	repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
	    	ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name , password );
            repository.setAuthenticationManager( authManager );
	    	
            System.out.println("repo root: " + repository.getRepositoryRoot(true));
            System.out.println("latest revision: " + repository.getLatestRevision());
            
	    }catch(SVNException svne){
	    	svne.printStackTrace();
	    }
	    
	    FSRepositoryFactory.setup();
	    String url2 = "file://localhost/Users/d2chau/Documents/UCD Academics/Fall 2013/CSCI 4738 Senior Design I/visiosvn-read-only";
		SVNRepository repository2 = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url2));
	    try{
	    	repository2 = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url2 ) );
	    	ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager();
            repository2.setAuthenticationManager( authManager );
	    	
            System.out.println("repo root: " + repository2.getRepositoryRoot(true));
            System.out.println("latest revision: " + repository2.getLatestRevision());
            
	    }catch(SVNException svne){
	    	svne.printStackTrace();
	    }
	}
}
*/

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class TestClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SVNException{
		// Message
		System.out.println("Connecting to the SVN server....\n");

		// Initialize variables for the SVN connections
		// username, password, and svn_url
		String username="annguyen2509";
		String password="Peiu050612";
		//String svn_url="https://ucdenver.svn.cloudforge.com/prjtest/trunk";
		//local
		String svn_url="file:///C:/Users/An Nguyen/Desktop/myNewRepo";
		String http_url = "https://ucdenver.svn.cloudforge.com/prjtest/trunk";

		// Message
		System.out.println("!!! Connection is established !!!\n");
		
		
		SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(http_url));
		try{
			repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( http_url ) );
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( username , password );
		    repository.setAuthenticationManager( authManager );
			
			System.out.println("Testing: " + repository.getRepositoryRoot(true));
			System.out.println("Latest revision number: " + repository.getLatestRevision());
			System.out.println("Repository UUID: " + repository.getRepositoryUUID( true ) );
		    
		}catch(SVNException svne){
			svne.printStackTrace();
		}
		
		// 3 ways of accessing the svn repo
		// file:/// access (local)
		// svn:// access (via custom protocol to an svn server)
		// http:// access (via WebDAV protocol to subversion-aware Apache server)
		
		// Setting up the library
		DAVRepositoryFactory.setup();
		
		// Creating the driver
		SVNRepository repo=null;
		
		/*
		// Creating SVN factory and SVN Revision
		SvnOperationFactory factory=new SvnOperationFactory();
		SVNRevision rev=SVNRevision.create(10);
		*/
		
		// Creating the repo using the svn_url provided
		//repo=SVNRepositoryFactory.create(SVNURL.parseURIDecoded(svn_url));
		repo=SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svn_url));
		
		// Login in the repo using Authentication
		ISVNAuthenticationManager admin=SVNWCUtil.createDefaultAuthenticationManager(username, password);
		// SVN connection is established here
		repo.setAuthenticationManager(admin);
		
		// Checking if the svn connection is established
		System.out.println("Testing: " + repo.getRepositoryRoot(true));
		System.out.println("Latest revision number: " + repo.getLatestRevision());
		System.out.println("Repository UUID: " + repo.getRepositoryUUID( true ) ); 
	}
}

