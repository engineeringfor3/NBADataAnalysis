package playerbl;
import po.MemberPO;

import dataservice.MemberData;
import java.util.*;
import java.io.*;

public class MemberLogic {
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
		
		String sortDatas[]={"µÃ·Ö","Àº°å","Öú¹¥","µÃ·Ö/Öú¹¥/Àº°å","¸ÇÃ±","ÇÀ¶Ï","·¸¹æ","Ê§Îó","·ÖÖÓ","Ð§ÂÊ","Í¶Àº","Èý·Ö","·£Çò","Á½Ë«"};
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
	
	
	
	
	

}
