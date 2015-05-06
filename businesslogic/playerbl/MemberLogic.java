package playerbl;
import po.MemberPO;
import po.MatchPO;
import vo.MemberVO;
import blservice.PlayerBLService;
import vo.MatchVO;
import vo.MemberVO;
import data.MemberData;

import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

public class MemberLogic implements PlayerBLService{
	public Object[][] poToList(ArrayList<MemberPO> memberList) throws IOException{
		
		Object[][] result=new Object[memberList.size()][37];
		for(int i=0;i<memberList.size();i++){
			MemberPO temp=memberList.get(i);
			result[i][0]=temp.name;
			result[i][1]=temp.team;
			result[i][2]=new Integer(temp.inMatches);
			result[i][3]=new Integer(temp.firstMatches);
			result[i][4]=new Integer(temp.rebounds);
			result[i][5]=new Integer(temp.assists);
			result[i][6]=new Integer(temp.onTime);
			result[i][7]=new Double(temp.getShotHitRate());
			result[i][8]=new Double(temp.getThreeShotHitRate());
			result[i][9]=new Double(temp.getPenaltyShotHitRate());
			result[i][10]=new Integer(temp.getOffenceTimes());
			result[i][11]=new Integer(temp.getDefenceTimes());
			result[i][12]=new Integer(temp.steals);
			result[i][13]=new Integer(temp.blockShots);
			result[i][14]=new Integer(temp.mistakes);
			result[i][15]=new Integer(temp.fouls);
			result[i][16]=new Integer(temp.scores);
			result[i][17]=new Integer(temp.getEfficiency());
			result[i][18]=new Double(temp.getGmScEfficiency());
			result[i][19]=new Double(temp.getTrueShotHitRate());
			result[i][20]=new Double(temp.getShotEfficiency());
			result[i][21]=new Double(temp.getReboundsRate());
			result[i][22]=new Double(temp.getOffensiveReboundsRate());
			result[i][23]=new Double(temp.getDefensiveReboundsRate());
			result[i][24]=new Double(temp.getAssistRate());
			result[i][25]=new Double(temp.getStealRate());
			result[i][26]=new Double(temp.getBlockShotRate());
			result[i][27]=new Double(temp.getMistakeRate());
			result[i][28]=new Double(temp.getUseRate());
			if(temp.inMatches==0){
				result[i][29]=new Double(0.0);
				result[i][30]=new Double(0.0);
				result[i][31]=new Double(0.0);
				result[i][32]=new Double(0.0);
				result[i][33]=new Double(0.0);
				result[i][34]=new Double(0.0);
				result[i][35]=new Double(0.0);
				result[i][36]=new Double(0.0);
						}
			else{
			result[i][29]=new Double(temp.rebounds/(temp.inMatches*1.0));
			result[i][30]=new Double(temp.assists/(temp.inMatches*1.0));
			result[i][31]=new Double(temp.onTime/(temp.inMatches*1.0));
			result[i][32]=new Double(temp.steals/(temp.inMatches*1.0));
			result[i][33]=new Double(temp.blockShots/(temp.inMatches*1.0));
			result[i][34]=new Double(temp.mistakes/(temp.inMatches*1.0));
			result[i][35]=new Double(temp.fouls/(temp.inMatches*1.0));
			result[i][36]=new Double(temp.scores/(temp.inMatches*1.0));
			
			}
		}
		return result;
	}
	
	public ArrayList<MemberPO> sortList(String position,String league,String subLeague,String sortData)throws IOException{
		MemberData md=new MemberData();
		ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
		ArrayList<MemberPO> memberList1=new ArrayList<MemberPO>();
		ArrayList<MemberPO> memberList2=new ArrayList<MemberPO>();
		ArrayList<MemberPO> memberList3=new ArrayList<MemberPO>();
		ArrayList<MemberPO> memberList4=new ArrayList<MemberPO>();
		memberList=md.toMemberList();
		for(MemberPO temp:memberList){
			if((temp.position!=null&&temp.position.equals(position))||(position.equals("all"))){
				memberList1.add(temp);
			}
		}
		
		for(MemberPO temp:memberList1){
			if((temp.league!=null&&temp.league.equals(league))||(league.equals("all"))){
				memberList2.add(temp);
			}
		}
		for(MemberPO temp:memberList2){
			if((temp.league!=null&&temp.subLeague.equals(subLeague))||(subLeague.equals("all"))){
				memberList3.add(temp);
			}
		}
		
		String sortDatas[]={"得分","篮板","助攻","得分/助攻/篮板","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
		for(int n=0;n<sortDatas.length;n++){
			if(sortDatas[n].equals(sortData)){
				memberList4=getList(n,memberList3);
				break;
			}
				
		}
		return memberList4;
		
			
		
	}
	
	
	public ArrayList<MemberPO> getList(int k,ArrayList<MemberPO> memberList3){
		ArrayList<MemberPO> memberList4=new ArrayList<MemberPO>();
		for(int i=0;i<memberList3.size()-1;i++){
			for(int j=memberList3.size()-1;j>i;j--){
				if(memberList3.get(j).getList()[k]>memberList3.get(j-1).getList()[k]){
					MemberPO temp=memberList3.get(j);
					MemberPO temp1=memberList3.get(j-1);
					memberList3.remove(j);
					memberList3.add(j,temp1);
					memberList3.remove(j-1);
					memberList3.add(j-1,temp);
					
				}
			}
		}
		if(memberList3.size()<=50)
			return memberList3;
		else{
			for(int i=0;i<50;i++){
				MemberPO temp=memberList3.get(i);
				memberList4.add(temp);
			}
			return memberList4;
		}
		
	}
	
	public MemberVO getPlayerData(String name)throws IOException{
		MemberData md=new MemberData();
		MemberPO mp=new MemberPO();
		mp=md.getMemberLiveData(name);
		ArrayList<MatchPO> poList=md.getMemberMatches(mp.name);
		ArrayList<MatchVO> t=new ArrayList<MatchVO>();
		for(MatchPO p:poList){
			MatchVO v=new MatchVO(p);
			t.add(v);
		}
		MemberVO result=new MemberVO(mp.name,mp.number,mp.position,mp.height,mp.weight,mp.birth,mp.age,mp.exp,mp.school,md.getTeamName(mp.name),t,md.getMatchInfo(mp.name));
		return result;
	}
	
	public String[][] getHotPlayer(String condition)throws IOException{
		String[][] answer=new String[5][];
		ArrayList<String[]> memberList=new ArrayList<String[]>();
		MemberData md=new MemberData();
		memberList =md.todayMembers();
		if(condition.equals("得分")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Integer.parseInt(memberList.get(j)[17])>Integer.parseInt(memberList.get(j-1)[17])){
						String[] temp=memberList.get(j);
						String[] temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("篮板")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Integer.parseInt(memberList.get(j)[11])>Integer.parseInt(memberList.get(j-1)[11])){
						String[] temp=memberList.get(j);
						String[] temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("助攻")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Integer.parseInt(memberList.get(j)[12])>Integer.parseInt(memberList.get(j-1)[12])){
						String[] temp=memberList.get(j);
						String[] temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("盖帽")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Integer.parseInt(memberList.get(j)[14])>Integer.parseInt(memberList.get(j-1)[14])){
						String[] temp=memberList.get(j);
						String[] temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("抢断")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Integer.parseInt(memberList.get(j)[13])>Integer.parseInt(memberList.get(j-1)[13])){
						String[] temp=memberList.get(j);
						String[] temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		for(int i=0;i<5;i++){
			String[] hehe=new String[19];
			String[] temp=memberList.get(i);
			for(int j=0;j<18;j++)
				hehe[j]=temp[j];
			hehe[18]=md.getTeamName(hehe[0]);
			answer[i]=hehe;
		}
		return answer;
		
	}
	public ArrayList<MemberVO> getSeasonHotPlayer(String condition)throws IOException{
		ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
		ArrayList<MemberVO> resultList=new ArrayList<MemberVO>();
		MemberData md=new MemberData();
		memberList=md.newMemberList();
		if(condition.equals("场均得分")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).averageScores)>Double.valueOf(memberList.get(j-1).averageScores)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均篮板")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).averageRebounds)>Double.valueOf(memberList.get(j-1).averageRebounds)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均助攻")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).averageAssists)>Double.valueOf(memberList.get(j-1).averageAssists)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均盖帽")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).averageBlocks)>Double.valueOf(memberList.get(j-1).averageBlocks)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均抢断")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).averageSteals)>Double.valueOf(memberList.get(j-1).averageSteals)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("三分命中率")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).threeShotRate)>Double.valueOf(memberList.get(j-1).threeShotRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("投篮命中率")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).shotHitRate)>Double.valueOf(memberList.get(j-1).shotHitRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("罚球命中率")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).penaltyShotRate)>Double.valueOf(memberList.get(j-1).penaltyShotRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
	
		for(int i=0;i<5;i++){
			MemberPO h=memberList.get(i);
			MemberPO mp=md.getMemberLiveData(h.name);
			ArrayList<MatchPO> poList=md.getMemberMatches(mp.name);
			ArrayList<MatchVO> t=new ArrayList<MatchVO>();
			for(MatchPO p:poList){
				MatchVO v=new MatchVO(p);
				t.add(v);
			}
			MemberVO v=new MemberVO(mp.name,mp.number,mp.position,mp.height,mp.weight,mp.birth,mp.age,mp.exp,mp.school,md.getTeamName(mp.name),mp.scoreProgressRate,mp.reboundProgressRate,mp.assistProgressRate,t,md.getMatchInfo(mp.name));
			resultList.add(v);
			
		}
		return resultList;
	}
	public ArrayList<MemberVO> getProgressPlayer(String condition)throws IOException{
		ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
		ArrayList<MemberVO> resultList=new ArrayList<MemberVO>();
		MemberData md=new MemberData();
		memberList=md.newMemberList();
		
		
		if(condition.equals("场均得分")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).scoreProgressRate)>Double.valueOf(memberList.get(j-1).scoreProgressRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均篮板")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).reboundProgressRate)>Double.valueOf(memberList.get(j-1).reboundProgressRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均助攻")){
			for(int i=0;i<memberList.size()-1;i++){
				for(int j=memberList.size()-1;j>i;j--){
					if(Double.valueOf(memberList.get(j).assistProgressRate)>Double.valueOf(memberList.get(j-1).assistProgressRate)){
						MemberPO temp=memberList.get(j);
						MemberPO  temp1=memberList.get(j-1);
						memberList.remove(j);
						memberList.add(j,temp1);
						memberList.remove(j-1);
						memberList.add(j-1,temp);
						
					}
				}
			}
		}
		
		for(int i=0;i<5;i++){
			MemberPO h=memberList.get(i);
			MemberPO mp=md.getMemberLiveData(h.name);
			ArrayList<MatchPO> poList=md.getMemberMatches(mp.name);
			ArrayList<MatchVO> t=new ArrayList<MatchVO>();
			for(MatchPO p:poList){
				MatchVO v=new MatchVO(p);
				t.add(v);
			}
			MemberVO v=new MemberVO(mp.name,mp.number,mp.position,mp.height,mp.weight,mp.birth,mp.age,mp.exp,mp.school,md.getTeamName(mp.name),h.scoreProgressRate,h.reboundProgressRate,h.assistProgressRate,t,md.getMatchInfo(mp.name));
			resultList.add(v);
		}
	
		return resultList;
	}
	public ArrayList<MemberPO> toMemberList() {
		MemberData md = new MemberData();
		ArrayList<MemberPO> list = new ArrayList<MemberPO>();
		try {
			list = md.toMemberList();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取数据错误");
			e.printStackTrace();
		}
		return list;
	}	
}

