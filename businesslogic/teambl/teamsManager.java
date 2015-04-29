package teambl;

import java.io.*;
import data.TeamData;
import java.util.ArrayList;

import po.MatchPO;
import po.MemberPO;
import po.TeamPO;
import blservice.TeamBLService;
import vo.MatchVO;
import vo.teamVO;

public class teamsManager implements TeamBLService{
	static ArrayList<teamVO> list = new ArrayList<teamVO>();
	
	public Object[][] getSingleTeamData(String team) throws IOException{
		teamsManager t = new teamsManager();
		list.clear();
		
		for(int i=0;i<82;i++){			
			list.add(new teamVO(team,82,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.0,0.0,0.0,0.0,0,0.0,0.0,0.0,0.0,0.0,0.0));
		}
		
		File dir = new File("e:\\大二下\\软工三\\迭代一数据\\matches");
		File[] f = dir.listFiles();
		
		t.gainShoot(f,team);
		t.gainP();
		t.gainWinP(f,team);
		t.gainAttackRound(f,team);
		
		Object[][] result = new Object[list.size()][28];
		
		for(int i=0;i<82;i++){
			teamVO temp = list.get(i);
			result[i][0] = temp.getName();
			result[i][1] = temp.getGames();
			result[i][2] = new Integer(temp.getfreeGoalMade());
			result[i][3] = new Integer(temp.getfieldGoalAttempt());
			result[i][4] = new Integer(temp.getNumberOfThreeGoalMade());
			result[i][5] = new Integer(temp.getNumberOfThreeGoalAttempt());
			result[i][6] = new Integer(temp.getFreeThrowMade());
			result[i][7] = new Integer(temp.getFreeThrowAttempt());
			result[i][8] = new Integer(temp.getOffenseRebound());
			result[i][9] = new Integer(temp.getDefenseRebound());
			result[i][10] = new Integer(temp.getRebound());
			result[i][11] = new Integer(temp.getAssist());
			result[i][12] = new Integer(temp.getSteal());
			result[i][13] = new Integer(temp.getBlockShot());
			result[i][14] = new Integer(temp.getError());
			result[i][15] = new Integer(temp.getFoul());
			result[i][16] = new Integer(temp.getScore());
			result[i][17] = new Double(temp.getFGP());
			result[i][18] = new Double(temp.getTGP());
			result[i][19] = new Double(temp.getFTP());
			result[i][20] = new Double(temp.getWinP());
			result[i][21] = new Integer(temp.getAttackRound());
			result[i][22] = new Double(temp.getOE());
			result[i][23] = new Double(temp.getDE());
			result[i][24] = new Double(temp.getORE());
			result[i][25] = new Double(temp.getDRE());
			result[i][26] = new Double(temp.getSE());
			result[i][27] = new Double(temp.getAE());
		}
		
		return result;
	}

	private void gainAttackRound(File[] f,String team) throws IOException {
		int number = 0;
		for(int k=0;k<f.length;k++){
			FileReader file = new FileReader(f[k].getAbsolutePath());
			BufferedReader bf = new BufferedReader(file);
			String s = null;
			
			s = bf.readLine();
			String[] temp = s.split("-|;");
			
			int offenseScore = 0;
			int defenseScore = 0;
			
			if(temp[2].equals(team)||temp[3].equals(team)){
				if(temp[2].equals(team)){
					offenseScore = Integer.parseInt(temp[4]);
					defenseScore = Integer.parseInt(temp[5]);
				}
				else{
					offenseScore = Integer.parseInt(temp[5]);
					defenseScore = Integer.parseInt(temp[4]);
				}
				
				s = bf.readLine();
				
				int target = 0;
				int defenseRebound = 0;
				int offenseRebound = 0;
				
				int opponentGoalAttempt = 0;
				int opponentFreeThrow = 0;
				int opponentOffenseRebound = 0;
				int opponentDefenseRebound = 0;
				int opponentGoal = 0;
				int opponentError = 0;
			
				while((s = bf.readLine()) != null){
					String[] x = s.split(";");
					
					if(x.length==1){
						if(x[0].equals(team)){
							target=1;
						}
						else
							target=0;
						continue;
					}
					
					int rebound = Integer.parseInt(x[10]);
					
					
					if(target == 0){
						defenseRebound += rebound;
						opponentGoalAttempt += Integer.parseInt(x[4]);
						opponentFreeThrow += Integer.parseInt(x[8]);
						opponentOffenseRebound += Integer.parseInt(x[9]);
						opponentGoal += Integer.parseInt(x[3]);
						opponentError += Integer.parseInt(x[15]);
					}
					else if(target == 1){
						opponentDefenseRebound += Integer.parseInt(x[10]);
					}
				}
				
				int goal = list.get(number).getfieldGoalAttempt();
				int freeThrow = list.get(number).getFreeThrowAttempt();
				int attackRebound = list.get(number).getOffenseRebound();

				int fail = list.get(number).getfieldGoalAttempt()-list.get(number).getfreeGoalMade();
				int error = list.get(number).getError();

				int attackRound = (int) (goal+0.4*freeThrow-1.07*(attackRebound/(attackRebound+defenseRebound)*fail)+
						1.07*error);
				
				int defenseRound = (int) (opponentGoalAttempt+0.4*opponentFreeThrow-
						1.07*(opponentOffenseRebound/(opponentOffenseRebound+opponentDefenseRebound)*
						(opponentGoalAttempt-opponentGoal))+1.07*opponentError);
				
				double OE = (double) offenseScore/attackRound;
				double DE = (double) defenseScore/defenseRound;
				double ORE = (double) attackRebound/(attackRebound+defenseRebound);
				double DRE = (double) opponentOffenseRebound/(opponentOffenseRebound+
						opponentDefenseRebound);
				double SE = (double) list.get(number).getSteal()/defenseRound;
				double AE = (double) list.get(number).getAssist()/attackRound;
				OE = ((int) (OE*1000))/1000.0;
				DE = ((int) (DE*1000))/1000.0;
				ORE = ((int) (ORE*1000))/1000.0;
				DRE = ((int) (DRE*1000))/1000.0;
				SE = ((int) (SE*1000))/1000.0;
				AE = ((int) (AE*1000))/1000.0;
				
				list.get(number).setAttackRound(attackRound);
				list.get(number).setOE(OE);
				list.get(number).setDE(DE);
				list.get(number).setORE(ORE);
				list.get(number).setDRE(DRE);
				list.get(number).setSE(SE);
				list.get(number).setAE(AE);
				number++;
			}
		}
	}

	private void gainP() {
		for(int k=0;k<list.size();k++){
			double FGP = (double) list.get(k).getfreeGoalMade()/list.get(k).getfieldGoalAttempt();
			double FTP = (double) list.get(k).getFreeThrowMade()/list.get(k).getFreeThrowAttempt();
			double TGP = (double) list.get(k).getNumberOfThreeGoalMade()/list.get(k).getNumberOfThreeGoalAttempt();
	
			FGP = ((int) (FGP*1000))/1000.0;
			FTP = ((int) (FTP*1000))/1000.0;
			TGP = ((int) (TGP*1000))/1000.0;
			
			list.get(k).setFGP(FGP);
			list.get(k).setFTP(FTP);
			list.get(k).setTGP(TGP);
		}
	}
	
	private void gainShoot(File[] f,String team) throws IOException {
		int number = 0;      //第几场标志
		for(int i=0;i<f.length;i++){
			FileReader file = new FileReader(f[i].getAbsolutePath());
			BufferedReader bf = new BufferedReader(file);
			String s = null;
			    
			s = bf.readLine();
			String[] temp = s.split("-|;");
			if(temp[2].equals(team)||temp[3].equals(team)){
				s = bf.readLine();
				
				int target = 0;
				while((s = bf.readLine()) != null){
					String[] x = s.split(";");
					
					if(x.length==1){
						if(x[0].equals(team)){
							target=1;
						}
						else
							target=0;
						continue;
					}
				
					int goal = Integer.parseInt(x[3]);
					int goalAttempt = Integer.parseInt(x[4]);
					int threeGoal = Integer.parseInt(x[5]);
					int threeAttempt = Integer.parseInt(x[6]);
					int freeThrow = Integer.parseInt(x[7]);
					int freeAttempt = Integer.parseInt(x[8]);
					int attackRebound = Integer.parseInt(x[9]);
					int defenseRebound = Integer.parseInt(x[10]);
					int rebound = Integer.parseInt(x[11]);
					int assist = Integer.parseInt(x[12]);
					int steal = Integer.parseInt(x[13]);
					int block = Integer.parseInt(x[14]);
					int error = Integer.parseInt(x[15]);
					int foul = Integer.parseInt(x[16]);
					int score = Integer.parseInt(x[17]);
					
					if(target==1){
						list.get(number).setfreeGoalMade(list.get(number).getfreeGoalMade()+
								goal);
						list.get(number).setfieldGoalAttempt(list.get(number).getfieldGoalAttempt()+goalAttempt);
						list.get(number).setNumberOfThreeGoalMade(list.get(number).getNumberOfThreeGoalMade()+threeGoal);
						list.get(number).setNumberOfThreeGoalAttempt(list.get(number).getNumberOfThreeGoalAttempt()+threeAttempt);
						list.get(number).setFreeThrowMade(list.get(number).getFreeThrowMade()+freeThrow);
						list.get(number).setFreeThrowAttempt(list.get(number).getFreeThrowAttempt()+freeAttempt);
						list.get(number).setOffenseRebound(list.get(number).getOffenseRebound()+attackRebound);
						list.get(number).setDefenseRebound(list.get(number).getDefenseRebound()+defenseRebound);
						list.get(number).setRebound(list.get(number).getRebound()+rebound);
						list.get(number).setAssist(list.get(number).getAssist()+assist);
						list.get(number).setSteal(list.get(number).getSteal()+steal);
						list.get(number).setBlockShot(list.get(number).getBlockShot()+block);
						list.get(number).setError(list.get(number).getError()+error);
						list.get(number).setFoul(list.get(number).getFoul()+foul);
						list.get(number).setScore(list.get(number).getScore()+score);
					}		
				}
				number++;
			}
		}
	}

	private void gainWinP(File[] f,String t) throws IOException {
		for(int i=0;i<f.length;i++){
			FileReader file = new FileReader(f[i].getAbsolutePath());
			BufferedReader bf = new BufferedReader(file);
			String s = null;
			
			s=bf.readLine();
			String[] win = s.split(";");
			String[] teams = win[1].split("-");
			String[] scores = win[2].split("-");
			int first = Integer.parseInt(scores[0]);
			int second = Integer.parseInt(scores[1]);
			
			if(teams[0].equals(t)){
				if(first>second){
					for(int j=0;j<list.size();j++){
						list.get(j).setWinP(list.get(j).getWinP()+1.0);
					}
				}
			}
			
			else if(teams[1].equals(t)){
				if(first<second){
					for(int j=0;j<list.size();j++){
						list.get(j).setWinP(list.get(j).getWinP()+1.0);
					}
				}
			}
		}
		
		for(int i=0;i<list.size();i++){
			double p = list.get(i).getWinP()/82;
			p = ((int) (p*1000))/1000.0;
			list.get(i).setWinP(p);
		}
	}

	
	public ArrayList<teamVO> getSeasonHotTeam(String condition) throws IOException{
		ArrayList<teamVO> teamList=new ArrayList<teamVO>();
		ArrayList<TeamPO> poList=new ArrayList<TeamPO>();
		TeamData td=new TeamData();
		poList=td.getTeamList();
		if(condition.equals("场均得分")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getAverageScore())>Double.valueOf(poList.get(j-1).getAverageScore())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均篮板")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getAverageRebound())>Double.valueOf(poList.get(j-1).getAverageRebound())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均助攻")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getAverageAssist())>Double.valueOf(poList.get(j-1).getAverageAssist())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均盖帽")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getAverageAssist())>Double.valueOf(poList.get(j-1).getAverageAssist())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("场均抢断")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getAverageAssist())>Double.valueOf(poList.get(j-1).getAverageAssist())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("投篮命中率")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getShotRate())>Double.valueOf(poList.get(j-1).getShotRate())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("三分命中率")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getThreeShotRate())>Double.valueOf(poList.get(j-1).getThreeShotRate())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		if(condition.equals("罚球命中率")){
			for(int i=0;i<poList.size()-1;i++){
				for(int j=poList.size()-1;j>i;j--){
					if(Double.valueOf(poList.get(j).getPenaltyShotRate())>Double.valueOf(poList.get(j-1).getPenaltyShotRate())){
						TeamPO temp=poList.get(j);
						TeamPO  temp1=poList.get(j-1);
						poList.remove(j);
						poList.add(j,temp1);
						poList.remove(j-1);
						poList.add(j-1,temp);
						
					}
				}
			}
		}
		
		for(int i=0;i<5;i++){
			TeamPO p=poList.get(i);
			teamVO v=new teamVO(p);
			ArrayList<MatchPO> matchList=td.getTeamMatches(v.getName());
			ArrayList<MatchVO> t=new ArrayList<MatchVO>();
			for(MatchPO e:matchList){
				MatchVO m=new MatchVO(e);
				t.add(m);
			}
			v.setMatchList(t);
			teamList.add(v);
		}
		return teamList;
		
	}

	
	public teamVO getTeamData(String team) throws IOException {
		TeamData td=new TeamData();
		TeamPO p=td.getTeamLiveData(team);
		teamVO v=new teamVO(p);
		ArrayList<MatchPO> poList=td.getTeamMatches(team);
		ArrayList<MatchVO> t=new ArrayList<MatchVO>();
		for(MatchPO w:poList){
			MatchVO k=new MatchVO(w);
			t.add(k);
		}
		v.setMatchList(t);
		return v;
	}
	
	public static void main(String[] args)throws IOException{
		teamsManager tm=new teamsManager();
		ArrayList<teamVO> teamList=tm.getSeasonHotTeam("场均得分");
		for(teamVO v:teamList){
			System.out.println(v.getShorts());
		}
	}
}

