package data;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import po.MatchPO;
import dataservice.MatchDataService;

public class MatchData implements MatchDataService{
	public MatchPO getMatch(String name)throws IOException{
		MatchPO result=new MatchPO();
		String path="C:\\Users\\Administrator\\Desktop\\new data\\"+name;
		int first=0,second=0,count=0;
    	ArrayList<String[]> dataList=new ArrayList<String[]>();
    	BufferedReader br=new BufferedReader(new FileReader(path));
		String a;
		while((a=br.readLine())!=null){
			count++;
			String[] temp=a.split(";");
			dataList.add(temp);
			if(count==2)
				result.setSectionScore(a);
		}
		br.close();
		result.setDate(dataList.get(0)[0]);
		result.setScore(dataList.get(0)[2]);
		
        for(int i=0;i<dataList.size();i++){
        	if(dataList.get(i).length==1){
        		result.setTeam1(dataList.get(i)[0]);//only shorts
        		first=i;
        		break;
        	}
        }
        for(int i=first;i<dataList.size();i++){
        	if(dataList.get(i).length==1){
        		result.setTeam2(dataList.get(i)[0]);//only shorts
        		second=i;
        	}
        }
        String temp1[][]=new String[second-first-1][18];
        for(int i=first+1;i<second;i++){
        	temp1[i-first-1]=dataList.get(i);
        }
        result.setPlayers1(temp1);
        
        String temp2[][]=new String[dataList.size()-second-1][18];
        for(int i=second+1;i<dataList.size();i++){
        	temp2[i-second-1]=dataList.get(i);
        }
        result.setPlayers2(temp2);
        
		return result;
	}
	
	
	

}
