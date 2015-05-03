package matchbl;

import java.io.*;
import java.util.ArrayList;
import data.MatchData;
import po.MatchPO;
import vo.MatchVO;
import blservice.MatchBLService;

public class MatchLogic implements MatchBLService{

	MatchData md=new MatchData();//�������ݲ��MatchData

	public ArrayList<MatchVO> getMatchesByTeam(String team1, String team2)throws IOException {
		ArrayList<MatchPO> matchList=md.getMatchList();
		ArrayList<MatchVO> resultList=new ArrayList<MatchVO>();
		for(MatchPO p:matchList){
			if((p.getTeam1().equals(team1))&(p.getTeam2().equals(team2))){
				MatchVO v=new MatchVO(p);
				resultList.add(v);
			}
			if((p.getTeam1().equals(team2))&(p.getTeam2().equals(team1))){
				MatchVO v=new MatchVO(p);
				resultList.add(v);
			}
		}
		
	   
		return resultList;
	}//���ݶ������Ƶõ�����

	
	public ArrayList<MatchVO> getMatchesByTime(String time) throws IOException{
		
		ArrayList<MatchPO> matchList=md.getMatchList();
		ArrayList<MatchVO> resultList=new ArrayList<MatchVO>();
		for(MatchPO p:matchList){
			if(p.getDate().equals(time)){
				MatchVO v=new MatchVO(p);
				resultList.add(v);
			}
		}
		
	   
		return resultList;
	}//����ʱ��õ�����
	
	
	
	

}
