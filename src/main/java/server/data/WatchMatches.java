package server.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;

import presentation.mainui.MainFrame;
import server.po.MatchPO;
import console.Console;

public  final class WatchMatches implements Runnable {
	public  void run() {
		// TODO Auto-generated method stub
		try {
			String path=Console.path+"/matches/";
			WatchService watchService=FileSystems.getDefault().newWatchService();
			 Paths.get(path).register(watchService,   
		                StandardWatchEventKinds.ENTRY_CREATE,  
		                StandardWatchEventKinds.ENTRY_DELETE,  
		                StandardWatchEventKinds.ENTRY_MODIFY);  
			  char a='n';//前一次事件
		        while(true)  
		        {  
		            WatchKey key=watchService.take();  
		            for(WatchEvent<?> event:key.pollEvents())  
		            {  
		               // System.out.println(event.context()+"发生了"+event.kind()+"事件"); 
		            	if(event.kind().name().equals("ENTRY_MODIFY")){
		            		if(a=='C'){
		                	String name= event.context().toString();
		                	File f=new File(path+name);
		                	MatchesData.add(f);
		                	//MainFrame.refresh();
		                	a='n';//事件归元
		            		}
		            		else{
		            		a='M';//事件记为修改
		            		}
		                }
		                else if(event.kind().name().equals("ENTRY_DELETE")){
		                	if(a=='M'){
		                	MatchesData.remove(event.context().toString());
		                	a='n';//事件归元
		                	}
		                	else{
		                	a='D';//事件记为删除
		                	}
		                }
		                else{
		                	a='C';//事件记为创建
		                }
		                
		            }  
		            if(!key.reset())  
		            {  
		                break;  
		            }  
		        }  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
