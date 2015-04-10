package server.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchMatches {
private static String path="C:/Users/wjc/Desktop/nba/matches/";  
	public static void watch() {
		// TODO Auto-generated method stub
		try {
			WatchService watchService=FileSystems.getDefault().newWatchService();
			 Paths.get(path).register(watchService,   
		                StandardWatchEventKinds.ENTRY_CREATE,  
		                StandardWatchEventKinds.ENTRY_DELETE,  
		                StandardWatchEventKinds.ENTRY_MODIFY);  
		        while(true)  
		        {  
		            WatchKey key=watchService.take();  
		            for(WatchEvent<?> event:key.pollEvents())  
		            {  
		               // System.out.println(event.context()+"发生了"+event.kind()+"事件"); 
		                if(event.kind().name().equals("ENTRY_MODIFY")){
		                	String name= event.context().toString();
		                	File f=new File(path+name);
		                	MatchesData.add(f);
		                }
		                else if(event.kind().name().equals("ENTRY_DELETE")){
		                	
		                }
		            }  
		            if(!key.reset())  
		            {  
		                break;  
		            }  
		        }  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
