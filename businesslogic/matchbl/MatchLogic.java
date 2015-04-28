package matchbl;

import java.io.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import data.MatchData;
import po.MatchPO;
import vo.MatchVO;

import blservice.MatchBLService;

public class MatchLogic implements MatchBLService{

	MatchData md=new MatchData();

	public static List<File> getFiles(String path)throws IOException{
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
	
	
	public ArrayList<MatchVO> getMatchesByTeam(String team1, String team2)throws IOException {
		String twoTeam1=team1+"-"+team2;
		String twoTeam2=team2+"-"+team1;
		ArrayList<MatchVO> matchList=new ArrayList<MatchVO>();
		List<File> files = getFiles("C:\\Users\\Administrator\\Desktop\\new data");
	    for(File f : files){
	        String matchName=f.getName();
	        if(matchName.contains(twoTeam1)){
	        	MatchPO t=md.getMatch(matchName);
	        	MatchVO result=new MatchVO(t.getDate(),t.getTeam1(),t.getTeam2(),t.getScore(),t.getSectionScore(),t.getPlayers1(),t.getPlayers2());
	        	matchList.add(result);
	        }
	        if(matchName.contains(twoTeam2)){
	        	MatchPO t=md.getMatch(matchName);
	        	MatchVO result=new MatchVO(t.getDate(),t.getTeam1(),t.getTeam2(),t.getScore(),t.getSectionScore(),t.getPlayers1(),t.getPlayers2());
	        	matchList.add(result);
	        }
	    }
		return matchList;
	}

	
	public ArrayList<MatchVO> getMatchesByTime(String time) throws IOException{
		ArrayList<MatchVO> matchList=new ArrayList<MatchVO>();
		List<File> files = getFiles("C:\\Users\\Administrator\\Desktop\\new data");
	    for(File f : files){
	        String matchName=f.getName();
	        if(matchName.contains(time)){
	        	MatchPO t=md.getMatch(matchName);
	        	MatchVO result=new MatchVO(t.getDate(),t.getTeam1(),t.getTeam2(),t.getScore(),t.getSectionScore(),t.getPlayers1(),t.getPlayers2());
	        	matchList.add(result);
	        }
	        
	    }
		return matchList;
	}
	
	

}
