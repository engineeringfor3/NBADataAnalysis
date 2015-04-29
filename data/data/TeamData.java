package data;
import java.io.BufferedReader;


import dataservice.TeamDataService;
import po.MatchPO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import po.MemberPO;
import po.TeamPO;
import java.io.*;

public class TeamData implements TeamDataService{
	
	public static ArrayList<String[]> getContentByLocalFile(File path) throws IOException {
		ArrayList<String[]> stringList=new ArrayList<String[]>();
		BufferedReader br=new BufferedReader(new FileReader(path));
		String a;
		while((a=br.readLine())!=null){
			String[] temp=a.split(";");
			stringList.add(temp);
		}
		br.close();
		return stringList;
    }  //读取文本文件中的内容
	public static List<File> getFiles(String path){
	    File root = new File(path);
	    List<File> files = new ArrayList<File>();
	    if(!root.isDirectory()){
	        files.add(root);
	    }else{
	        File[] subFiles = root.listFiles();
	        for(File f : subFiles){
	            files.addAll(getFiles(f.getAbsolutePath()));
	        }    
	    }
	    return files;
	}//读取一个文件夹中的所有文件名
	public TeamPO getTeamLiveData(String name)throws IOException{
		TeamPO temp=new TeamPO();
	    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("teams\\teams"),"UTF-8"));  
		String a;
		int n=0;
		while((a=br.readLine())!=null){
			n++;
			if((n>=2)&&(n<=31))
			{
				a=a.substring(1,a.length()-1);
				String[] te=a.split("│");
				for(int i=0;i<te.length;i++){
					te[i]=te[i].trim();
				}
				if(te[0].equals(name)){
					temp.name=te[0];
					if(te[1].equals("NOP"))
					temp.shorts="NOH";
					else
					temp.shorts=te[1];
					temp.location=te[2];
					temp.block=te[3];
					temp.subBlock=te[4];
					temp.mainPlace=te[5];
					temp.time=te[6];
				}
				}
		}
	    
		return temp;
		
	}
	
	
	
	
	public ArrayList<MatchPO> getTeamMatches(String name)throws IOException{
		MatchData mad=new MatchData();
		MemberData md=new MemberData();
		ArrayList<MatchPO> matchList=new ArrayList<MatchPO>();
		TeamPO team=getTeamLiveData(name);
		String shorts=team.shorts;
		List<File> files = md.getFiles("C:\\Users\\Administrator\\Desktop\\new data");
		 for(File f : files){
		        String temp=f.getName();
		        if(temp.contains(shorts)){
		        	MatchPO te=mad.getMatch(temp);
		        	matchList.add(te);
		        }
		    }
		return matchList;
	}
	
	public ArrayList<MatchPO> getLatestTeamMatches(String name)throws IOException{
		ArrayList<MatchPO> matchList=new ArrayList<MatchPO>();
		matchList=getTeamMatches(name);
		if(matchList.size()<5)
		return matchList;
		else{
			ArrayList<MatchPO> resultList=new ArrayList<MatchPO>();
			for(int i=matchList.size()-5;i<matchList.size();i++){
				resultList.add(matchList.get(i));
			}
			return resultList;
		}
		
	}
	
	public ArrayList<TeamPO> getTeamList()throws IOException{
		ArrayList<TeamPO> teamList=new ArrayList<TeamPO>();
		List<File> files = getFiles("C:\\Users\\Administrator\\Desktop\\new data");
	    for(File f : files){
	    	
	    	int first=0,second=0;
	    	ArrayList<String[]> dataList=new ArrayList<String[]>();
	        dataList=getContentByLocalFile(f);
	        
	        for(int i=0;i<dataList.size();i++){
	        	if(dataList.get(i).length==1){
	        		first=i;
	        		break;
	        	}
	        }
	        for(int i=first;i<dataList.size();i++){
	        	if(dataList.get(i).length==1){
	        		second=i;
	        	}
	        }//得到文档的队伍名称以及位置
	        
	       
	       
	        
	        boolean contain=false;
	        for(TeamPO t:teamList){
    			if(t.shorts.equals(dataList.get(first)[0])){
    				contain=true;
    			}
    			
    		}
    		if(contain==false){
				TeamPO p=new TeamPO();
				String temp=dataList.get(first)[0];
 				p=getTeamLiveData(getTeamName(temp));
				teamList.add(p);
				
			}
    		
    		boolean contain1=false;
    		 for(TeamPO t:teamList){
     			if(t.shorts.equals(dataList.get(second)[0])){
     				contain1=true;
     			}
     			
     		}
     		if(contain1==false){
 				TeamPO p=new TeamPO();
 				String temp=dataList.get(second)[0];
 				p=getTeamLiveData(getTeamName(temp));
 				teamList.add(p);
 				
 			}
	        
	        	
	        }
	   
	    for(File f : files){
	    	int first=0,second=0;
	    	ArrayList<String[]> dataList=new ArrayList<String[]>();
	        dataList=getContentByLocalFile(f);
	        for(int i=0;i<dataList.size();i++){
	        	if(dataList.get(i).length==1){
	        		first=i;
	        		break;
	        	}
	        }
	        for(int i=first;i<dataList.size();i++){
	        	if(dataList.get(i).length==1){
	        		second=i;
	        	}
	        }//得到文档的队伍名称以及位置
	       
	        for(int j=0;j<teamList.size();j++){
	        		TeamPO tem=teamList.get(j);
	        		if(tem.shorts.equals(dataList.get(first)[0])){
	        			tem.number+=1;
	        			for(int i=first+1;i<second;i++){
		        			tem.scores+=Integer.parseInt(dataList.get(i)[17]);
		        			tem.rebounds+=Integer.parseInt(dataList.get(i)[11]);
		        			tem.assists+=Integer.parseInt(dataList.get(i)[12]);
		        			tem.blockShots+=Integer.parseInt(dataList.get(i)[14]);
		        			tem.steals+=Integer.parseInt(dataList.get(i)[13]);
		        			tem.shots+=Integer.parseInt(dataList.get(i)[4]);
		        			tem.shotHits+=Integer.parseInt(dataList.get(i)[3]);
		        			tem.threeShots+=Integer.parseInt(dataList.get(i)[6]);
		        			tem.threeShotHits+=Integer.parseInt(dataList.get(i)[5]);
		        			tem.penaltyShots+=Integer.parseInt(dataList.get(i)[8]);
		        			tem.penaltyShotHits+=Integer.parseInt(dataList.get(i)[7]);
		        		}
	        		}
	        		
	        		if(tem.shorts.equals(dataList.get(second)[0])){
	        			tem.number+=1;
	        			for(int i=second+1;i<dataList.size();i++){
		        			tem.scores+=Integer.parseInt(dataList.get(i)[17]);
		        			tem.rebounds+=Integer.parseInt(dataList.get(i)[11]);
		        			tem.assists+=Integer.parseInt(dataList.get(i)[12]);
		        			tem.blockShots+=Integer.parseInt(dataList.get(i)[14]);
		        			tem.steals+=Integer.parseInt(dataList.get(i)[13]);
		        			tem.shots+=Integer.parseInt(dataList.get(i)[4]);
		        			tem.shotHits+=Integer.parseInt(dataList.get(i)[3]);
		        			tem.threeShots+=Integer.parseInt(dataList.get(i)[6]);
		        			tem.threeShotHits+=Integer.parseInt(dataList.get(i)[5]);
		        			tem.penaltyShots+=Integer.parseInt(dataList.get(i)[8]);
		        			tem.penaltyShotHits+=Integer.parseInt(dataList.get(i)[7]);
		        		}
	        		}
	        		teamList.remove(j);
	        		teamList.add(j,tem);
	        }
	        
	        
	       
	    }
	    
	
		return teamList;
	}
	
	public String getTeamName(String shorts)throws IOException{
		if(shorts.equals("NOH"))
			shorts="NOP";
		String result=null;
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("teams\\teams"),"UTF-8"));  
		String a;
		int n=0;
		while((a=br.readLine())!=null){
			n++;
			if((n>=2)&&(n<=31))
			{
				a=a.substring(1,a.length()-1);
				String[] te=a.split("│");
				for(int i=0;i<te.length;i++){
					te[i]=te[i].trim();
				}
				if(te[1].equals(shorts)){
					return te[0];
				}
				}
		}
		return result;
	}
	
	
	
	

}