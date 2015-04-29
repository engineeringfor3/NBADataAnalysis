package data;

import dataservice.*;
import po.MatchPO;

import po.MemberPO;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;



public class MemberData implements MemberDataService{
	    static DecimalFormat df = new DecimalFormat("#.00");
	    static DecimalFormat cf=new DecimalFormat("#.000");
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
		
		public static String passPosition(File file)throws IOException{
			String result=null;
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
			String a;
			int n=0;
			int forward=0,back=0;
			while((a=br.readLine())!=null){
				n++;
				if(n==6){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result=a.substring(forward+1,back).trim();
				}
			}
			return result;
		}//从文件中读取球员位置
		
		public static int minuteToSecond(String temp){
			int result=0;
			String[] a=temp.split(":");
			result=Integer.parseInt(a[0])*60+Integer.parseInt(a[1]);
			return result;
		}//转换时间为单位秒
		
		public  ArrayList<MemberPO> toMemberList()throws IOException {
			ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
		    List<File> files = getFiles("players\\info");
		    for(File f : files){
		        MemberPO temp=new MemberPO();
		        temp.name=f.getName();
		        temp.position=passPosition(f);
		        memberList.add(temp);
		    }
		    files = getFiles("matches");
		    for(File f : files){
		    	int first=0,second=0;
		    	String firstTeam=null,secondTeam=null;
		    	ArrayList<String[]> dataList=new ArrayList<String[]>();
		        dataList=getContentByLocalFile(f);
		        for(int i=0;i<dataList.size();i++){
		        	if(dataList.get(i).length==1){
		        		firstTeam=dataList.get(i)[0];
		        		first=i;
		        		break;
		        	}
		        }
		        for(int i=first;i<dataList.size();i++){
		        	if(dataList.get(i).length==1){
		        		secondTeam=dataList.get(i)[0];
		        		second=i;
		        	}
		        }//得到文档的队伍名称以及位置
		        int firstRebounds=0;
		    	int firstOffensiveRebounds=0;
		    	int firstDefensiveRebounds=0;
		    	int firstShotHits=0;
		    	int firstShots=0;
		    	int firstTwoShots=0;
		    	int firstPenaltyShots=0;
		    	int firstMistakes=0;
		    	int firstOffenceTimes=0;
		        for(int i=first+1;i<second+1;i++){
		        	String t[]=dataList.get(i);
		        	if(t.length>16){
		        	firstRebounds+=Integer.parseInt(t[11]);
		        	firstDefensiveRebounds+=Integer.parseInt(t[10]);
		        	firstOffensiveRebounds+=Integer.parseInt(t[9]);
		        	firstShotHits+=Integer.parseInt(t[3]);
		        	firstShots+=Integer.parseInt(t[4]);
		        	firstTwoShots+=Integer.parseInt(t[4])-Integer.parseInt(t[6]);
		        	firstPenaltyShots+=Integer.parseInt(t[8]);
		        	firstMistakes+=Integer.parseInt(t[15]);
		        	firstOffenceTimes+=Integer.parseInt(t[4])+Integer.parseInt(t[16])+Integer.parseInt(t[12]);
		        	}
		        	
		        
		        }
		        int secondRebounds=0;
		    	int secondOffensiveRebounds=0;
		    	int secondDefensiveRebounds=0;
		    	int secondShotHits=0;
		    	int secondShots=0;
		    	int secondTwoShots=0;
		    	int secondPenaltyShots=0;
		    	int secondMistakes=0;
		    	int secondOffenceTimes=0;
		    	for(int i=second+1;i<dataList.size();i++){
		        	String t[]=dataList.get(i);
		        	secondRebounds+=Integer.parseInt(t[11]);
		        	secondDefensiveRebounds+=Integer.parseInt(t[10]);
		        	secondOffensiveRebounds+=Integer.parseInt(t[9]);
		        	secondShotHits+=Integer.parseInt(t[3]);
		        	secondShots+=Integer.parseInt(t[4]);
		        	secondTwoShots+=Integer.parseInt(t[4])-Integer.parseInt(t[6]);
		        	secondPenaltyShots+=Integer.parseInt(t[8]);
		        	secondMistakes+=Integer.parseInt(t[15]);
		        	secondOffenceTimes+=Integer.parseInt(t[4])+Integer.parseInt(t[16])+Integer.parseInt(t[12]);
		        	
		        
		        }
		        for(int i=first+1;i<second+1;i++){
		        	String t[]=dataList.get(i);
		        	if(t.length>2){
		        		if(t[2].equals("None")){
		        			t[2]="0:0";
		        		}
		        	}
		        	for(int j=0;j<memberList.size();j++){
		        		MemberPO tem=memberList.get(j);
		        		if(tem.name.equals(t[0])){
		        			if(t[1].equals("F")||t[1].equals("G")||t[1].equals("C")){
		        				tem.firstMatches+=1;
		        			}
		        			tem.inMatches+=1;
		        			tem.onTime+=minuteToSecond(t[2]);
		        			tem.shotHits+=Integer.parseInt(t[3]);
		        			tem.shots+=Integer.parseInt(t[4]);
		        			tem.threeShotHits+=Integer.parseInt(t[5]);
		        			tem.threeShots+=Integer.parseInt(t[6]);
		        			tem.penaltyShotHits+=Integer.parseInt(t[7]);
		        			tem.penaltyShots+=Integer.parseInt(t[8]);
		        			tem.offensiveRebounds+=Integer.parseInt(t[9]);
		        			tem.defensiveRebounds+=Integer.parseInt(t[10]);
		        			tem.rebounds+=Integer.parseInt(t[11]);
		        			tem.assists+=Integer.parseInt(t[12]);
		        			tem.steals+=Integer.parseInt(t[13]);
		        			tem.blockShots+=Integer.parseInt(t[14]);
		        			tem.mistakes+=Integer.parseInt(t[15]);
		        			tem.fouls+=Integer.parseInt(t[16]);
		        			tem.scores+=Integer.parseInt(t[17]);
		        			tem.team=firstTeam;
		        			tem.bothRebounds+=firstRebounds+secondRebounds;
		        			tem.bothDefensiveRebounds+=firstDefensiveRebounds+secondDefensiveRebounds;
		        			tem.bothOffensiveRebounds+=firstOffensiveRebounds+secondOffensiveRebounds;
		        			tem.teamShotHits+=firstShotHits;
		        			tem.teamShots+=firstShots;
		        			tem.oppositeTwoShots+=secondTwoShots;
		        			tem.teamPenaltyShots+=firstPenaltyShots;
		        			tem.teamMistakes+=firstMistakes;
		        			tem.oppositeOffenceTimes+=secondOffenceTimes;
		        			int count=0;
		        			if(Integer.parseInt(t[11])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[12])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[13])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[17])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[14])>=10){
		        				count++;
		        			}
		        			if(count>=2){
		        				tem.doubleTimes++;
		        			}
		        			
		        			
		        		}
		        		memberList.remove(j);
		        		memberList.add(j,tem);
		        	}
		        }
		        for(int i=second+1;i<dataList.size();i++){
		        	String t[]=dataList.get(i);
		        	if(t.length>2){
		        		if(t[2].equals("None")){
		        			t[2]="0:0";
		        		}
		        	}
		        	for(int j=0;j<memberList.size();j++){
		        		MemberPO tem=memberList.get(j);
		        		if(tem.name.equals(t[0])){
		        			if(t[1].equals("F")||t[1].equals("G")||t[1].equals("C")){
		        				tem.firstMatches+=1;
		        			}
		        			tem.inMatches+=1;
		        			tem.onTime+=minuteToSecond(t[2]);
		        			tem.shotHits+=Integer.parseInt(t[3]);
		        			tem.shots+=Integer.parseInt(t[4]);
		        			tem.threeShotHits+=Integer.parseInt(t[5]);
		        			tem.threeShots+=Integer.parseInt(t[6]);
		        			tem.penaltyShotHits+=Integer.parseInt(t[7]);
		        			tem.penaltyShots+=Integer.parseInt(t[8]);
		        			tem.offensiveRebounds+=Integer.parseInt(t[9]);
		        			tem.defensiveRebounds+=Integer.parseInt(t[10]);
		        			tem.rebounds+=Integer.parseInt(t[11]);
		        			tem.assists+=Integer.parseInt(t[12]);
		        			tem.steals+=Integer.parseInt(t[13]);
		        			tem.blockShots+=Integer.parseInt(t[14]);
		        			tem.mistakes+=Integer.parseInt(t[15]);
		        			tem.fouls+=Integer.parseInt(t[16]);
		        			tem.scores+=Integer.parseInt(t[17]);
		        			tem.team=secondTeam;
		        			tem.bothRebounds+=secondRebounds+firstRebounds;
		        			tem.bothDefensiveRebounds+=firstDefensiveRebounds+secondDefensiveRebounds;
		        			tem.bothOffensiveRebounds+=firstOffensiveRebounds+secondOffensiveRebounds;
		        			tem.teamShotHits+=secondShotHits;
		        			tem.teamShots+=secondShots;
		        			tem.oppositeTwoShots+=firstTwoShots;
		        			tem.teamPenaltyShots+=secondPenaltyShots;
		        			tem.teamMistakes+=secondMistakes;
		        			tem.oppositeOffenceTimes+=firstOffenceTimes;
		        			int count=0;
		        			if(Integer.parseInt(t[11])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[12])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[13])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[17])>=10){
		        				count++;
		        			}
		        			if(Integer.parseInt(t[14])>=10){
		        				count++;
		        			}
		        			if(count>=2){
		        				tem.doubleTimes++;
		        			}
		        		}
		        		memberList.remove(j);
		        		memberList.add(j,tem);
		        	}
		        }//使球员得到基础属性
		        
		    }
		    ArrayList<String[]> teamList=new ArrayList<String[]>();
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
					teamList.add(te);
					}
			}
		    for(int i=0;i<memberList.size();i++){
		    	MemberPO temp=memberList.get(i);
		    	for(String[] data:teamList){
		    		if(temp.team!=null&&temp.team.equals(data[1])){
		    			temp.league=data[3];
		    			temp.subLeague=data[4];
		    		}
		    	}
		    	memberList.remove(i);
		    	memberList.add(i,temp);
		    	
		    }
		   
		    return memberList;
		   
		}
		
	
		public  ArrayList<MemberPO> newMemberList()throws IOException {
			ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
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
		        for(int i=first+1;i<second;i++){
		        	String temp=dataList.get(i)[0];
		        	MemberPO te=new MemberPO();
		        	te.name=temp;
		        	boolean contain=false;
		        	for(MemberPO a:memberList){
		        		if(a.name.equals(temp))
		        			contain=true;
		        	}
		        	if(contain==false){
		        		memberList.add(te);
		        	}
		        }
		        for(int i=second+1;i<dataList.size();i++){
		        	String temp=dataList.get(i)[0];
		        	MemberPO te=new MemberPO();
		        	te.name=temp;
		        	boolean contain=false;
		        	for(MemberPO a:memberList){
		        		if(a.name.equals(temp))
		        			contain=true;
		        	}
		        	if(contain==false){
		        		memberList.add(te);
		        	}
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
		        for(int i=first+1;i<second;i++){
		        	String t[]=dataList.get(i);
		        	for(int j=0;j<memberList.size();j++){
		        		MemberPO tem=memberList.get(j);
		        		if(tem.name.equals(t[0])){
		        			MatchData md=new MatchData();
							tem.matchList.add(md.getMatch(f.getName()));
		        			tem.inMatches+=1;
		        			tem.shotHits+=Integer.parseInt(t[3]);
		        			tem.shots+=Integer.parseInt(t[4]);
		        			tem.threeShotHits+=Integer.parseInt(t[5]);
		        			tem.threeShots+=Integer.parseInt(t[6]);
		        			tem.penaltyShotHits+=Integer.parseInt(t[7]);
		        			tem.penaltyShots+=Integer.parseInt(t[8]);
		        			tem.rebounds+=Integer.parseInt(t[11]);
		        			tem.assists+=Integer.parseInt(t[12]);
		        			tem.steals+=Integer.parseInt(t[13]);
		        			tem.blockShots+=Integer.parseInt(t[14]);
		        			tem.scores+=Integer.parseInt(t[17]);
		        			tem.averageScores=tem.scores/(tem.inMatches*1.0);	
		        			tem.averageScores=Double.valueOf(df.format(tem.averageScores));
		        			tem.averageAssists=tem.assists/(tem.inMatches*1.0);
		        			tem.averageAssists=Double.valueOf(df.format(tem.averageAssists));
		        			tem.averageBlocks=tem.blockShots/(tem.inMatches*1.0);
		        			tem.averageBlocks=Double.valueOf(df.format(tem.averageBlocks));
		        			tem.averageSteals=tem.steals/(tem.inMatches*1.0);
		        			tem.averageSteals=Double.valueOf(df.format(tem.averageSteals));
		        			tem.averageRebounds=tem.rebounds/(tem.inMatches*1.0);
		        			tem.averageRebounds=Double.valueOf(df.format(tem.averageRebounds));
		        			if(tem.shots==0)
		        				tem.shotHitRate=0.0;
		        			else{
		        			tem.shotHitRate=tem.shotHits/(tem.shots*1.0);
		        			tem.shotHitRate=Double.valueOf(cf.format(tem.shotHitRate));
		        			}
		        			if(tem.threeShots==0)
		        				tem.threeShotRate=0.0;
		        			else{
		        			tem.threeShotRate=tem.threeShotHits/(tem.threeShots*1.0);
		        			tem.threeShotRate=Double.valueOf(cf.format(tem.threeShotRate));
		        			}
		        			if(tem.penaltyShots==0)
		        				tem.penaltyShotRate=0.0;
		        			else{
		        			tem.penaltyShotRate=tem.penaltyShotHits/(tem.penaltyShots*1.0);
		        			tem.penaltyShotRate=Double.valueOf(cf.format(tem.penaltyShotRate));
		        			}
		        			if(tem.inMatches==1){
		        				tem.firstScore=Integer.parseInt(t[17]);
		        				tem.firstRebound=Integer.parseInt(t[11]);
		        				tem.firstAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==2)
		        			{
		        				tem.secondScore=Integer.parseInt(t[17]);
		        				tem.secondRebound=Integer.parseInt(t[11]);
		        				tem.secondAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==3)
		        			{
		        				tem.thirdScore=Integer.parseInt(t[17]);
		        				tem.thirdRebound=Integer.parseInt(t[11]);
		        				tem.thirdAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==4)
		        			{
		        				tem.fourthScore=Integer.parseInt(t[17]);
		        				tem.fourthRebound=Integer.parseInt(t[11]);
		        				tem.fourthAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==5)
		        			{
		        				tem.fifthScore=Integer.parseInt(t[17]);
		        				tem.fifthRebound=Integer.parseInt(t[11]);
		        				tem.fifthAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches>5){
		        				int temp=tem.firstScore;
		        				tem.firstScore=tem.secondScore;
		        				tem.secondScore=tem.thirdScore;
		        				tem.thirdScore=tem.fourthScore;
		        				tem.fourthScore=tem.fifthScore;
		        				tem.fifthScore=Integer.parseInt(t[17]);
		        				tem.beforeFiveAverageScore=(tem.beforeFiveAverageScore*(tem.inMatches-6)+temp)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageScore=(tem.firstScore+tem.secondScore+tem.thirdScore+tem.fourthScore+tem.fifthScore)/5.0;
		        				if(tem.beforeFiveAverageScore>0)
			        				tem.scoreProgressRate=(tem.fiveAverageScore-tem.beforeFiveAverageScore)/(tem.beforeFiveAverageScore*1.0);
		        				tem.scoreProgressRate=Double.valueOf(cf.format(tem.scoreProgressRate));
		        				
		        				int temp1=tem.firstRebound;
		        				tem.firstRebound=tem.secondRebound;
		        				tem.secondRebound=tem.thirdRebound;
		        				tem.thirdRebound=tem.fourthRebound;
		        				tem.fourthRebound=tem.fifthRebound;
		        				tem.fifthRebound=Integer.parseInt(t[11]);
		        				tem.beforeFiveAverageRebound=(tem.beforeFiveAverageRebound*(tem.inMatches-6)+temp1)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageRebound=(tem.firstRebound+tem.secondRebound+tem.thirdRebound+tem.fourthRebound+tem.fifthRebound)/5.0;
		        				if(tem.beforeFiveAverageRebound>0)
			        				tem.reboundProgressRate=(tem.fiveAverageRebound-tem.beforeFiveAverageRebound)/(tem.beforeFiveAverageRebound*1.0);
		        				tem.reboundProgressRate=Double.valueOf(cf.format(tem.reboundProgressRate));
		        				
		        				int temp2=tem.firstAssist;
		        				tem.firstAssist=tem.secondAssist;
		        				tem.secondAssist=tem.thirdAssist;
		        				tem.thirdAssist=tem.fourthAssist;
		        				tem.fourthAssist=tem.fifthAssist;
		        				tem.fifthAssist=Integer.parseInt(t[12]);
		        				tem.beforeFiveAverageAssist=(tem.beforeFiveAverageAssist*(tem.inMatches-6)+temp2)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageAssist=(tem.firstAssist+tem.secondAssist+tem.thirdAssist+tem.fourthAssist+tem.fifthAssist)/5.0;
		        				if(tem.beforeFiveAverageAssist>0)
			        				tem.assistProgressRate=(tem.fiveAverageAssist-tem.beforeFiveAverageAssist)/(tem.beforeFiveAverageAssist*1.0);
		        				tem.assistProgressRate=Double.valueOf(cf.format(tem.assistProgressRate));
		        				
		        			}
		        			}
		        		memberList.remove(j);
		        		memberList.add(j,tem);
		        	}
		        }
		        for(int i=second+1;i<dataList.size();i++){
		        	String t[]=dataList.get(i);
		        	for(int j=0;j<memberList.size();j++){
		        		MemberPO tem=memberList.get(j);
		        		if(tem.name.equals(t[0])){
		        			MatchData md=new MatchData();
							tem.matchList.add(md.getMatch(f.getName()));
		        			tem.inMatches+=1;
		        			tem.shotHits+=Integer.parseInt(t[3]);
		        			tem.shots+=Integer.parseInt(t[4]);
		        			tem.threeShotHits+=Integer.parseInt(t[5]);
		        			tem.threeShots+=Integer.parseInt(t[6]);
		        			tem.penaltyShotHits+=Integer.parseInt(t[7]);
		        			tem.penaltyShots+=Integer.parseInt(t[8]);
		        			tem.rebounds+=Integer.parseInt(t[11]);
		        			tem.assists+=Integer.parseInt(t[12]);
		        			tem.steals+=Integer.parseInt(t[13]);
		        			tem.blockShots+=Integer.parseInt(t[14]);
		        			tem.scores+=Integer.parseInt(t[17]);
		        			tem.averageScores=tem.scores/(tem.inMatches*1.0);	
		        			tem.averageScores=Double.valueOf(df.format(tem.averageScores));
		        			tem.averageAssists=tem.assists/(tem.inMatches*1.0);
		        			tem.averageAssists=Double.valueOf(df.format(tem.averageAssists));
		        			tem.averageBlocks=tem.blockShots/(tem.inMatches*1.0);
		        			tem.averageBlocks=Double.valueOf(df.format(tem.averageBlocks));
		        			tem.averageSteals=tem.steals/(tem.inMatches*1.0);
		        			tem.averageSteals=Double.valueOf(df.format(tem.averageSteals));
		        			tem.averageRebounds=tem.rebounds/(tem.inMatches*1.0);
		        			tem.averageRebounds=Double.valueOf(df.format(tem.averageRebounds));
		        			if(tem.shots==0)
		        				tem.shotHitRate=0.0;
		        			else{
		        			tem.shotHitRate=tem.shotHits/(tem.shots*1.0);
		        			tem.shotHitRate=Double.valueOf(cf.format(tem.shotHitRate));
		        			}
		        			if(tem.threeShots==0)
		        				tem.threeShotRate=0.0;
		        			else{
		        			tem.threeShotRate=tem.threeShotHits/(tem.threeShots*1.0);
		        			tem.threeShotRate=Double.valueOf(cf.format(tem.threeShotRate));
		        			}
		        			if(tem.penaltyShots==0)
		        				tem.penaltyShotRate=0.0;
		        			else{
		        			tem.penaltyShotRate=tem.penaltyShotHits/(tem.penaltyShots*1.0);
		        			tem.penaltyShotRate=Double.valueOf(cf.format(tem.penaltyShotRate));
		        			}
		        			if(tem.inMatches==1){
		        				tem.firstScore=Integer.parseInt(t[17]);
		        				tem.firstRebound=Integer.parseInt(t[11]);
		        				tem.firstAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==2)
		        			{
		        				tem.secondScore=Integer.parseInt(t[17]);
		        				tem.secondRebound=Integer.parseInt(t[11]);
		        				tem.secondAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==3)
		        			{
		        				tem.thirdScore=Integer.parseInt(t[17]);
		        				tem.thirdRebound=Integer.parseInt(t[11]);
		        				tem.thirdAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==4)
		        			{
		        				tem.fourthScore=Integer.parseInt(t[17]);
		        				tem.fourthRebound=Integer.parseInt(t[11]);
		        				tem.fourthAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches==5)
		        			{
		        				tem.fifthScore=Integer.parseInt(t[17]);
		        				tem.fifthRebound=Integer.parseInt(t[11]);
		        				tem.fifthAssist=Integer.parseInt(t[12]);
		        			}
		        			if(tem.inMatches>5){
		        				int temp=tem.firstScore;
		        				tem.firstScore=tem.secondScore;
		        				tem.secondScore=tem.thirdScore;
		        				tem.thirdScore=tem.fourthScore;
		        				tem.fourthScore=tem.fifthScore;
		        				tem.fifthScore=Integer.parseInt(t[17]);
		        				tem.beforeFiveAverageScore=(tem.beforeFiveAverageScore*(tem.inMatches-6)+temp)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageScore=(tem.firstScore+tem.secondScore+tem.thirdScore+tem.fourthScore+tem.fifthScore)/5.0;
		        				if(tem.beforeFiveAverageScore>0)
			        				tem.scoreProgressRate=(tem.fiveAverageScore-tem.beforeFiveAverageScore)/(tem.beforeFiveAverageScore*1.0);
		        				tem.scoreProgressRate=Double.valueOf(cf.format(tem.scoreProgressRate));
		        				
		        				int temp1=tem.firstRebound;
		        				tem.firstRebound=tem.secondRebound;
		        				tem.secondRebound=tem.thirdRebound;
		        				tem.thirdRebound=tem.fourthRebound;
		        				tem.fourthRebound=tem.fifthRebound;
		        				tem.fifthRebound=Integer.parseInt(t[11]);
		        				tem.beforeFiveAverageRebound=(tem.beforeFiveAverageRebound*(tem.inMatches-6)+temp1)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageRebound=(tem.firstRebound+tem.secondRebound+tem.thirdRebound+tem.fourthRebound+tem.fifthRebound)/5.0;
		        				if(tem.beforeFiveAverageRebound>0)
			        				tem.reboundProgressRate=(tem.fiveAverageRebound-tem.beforeFiveAverageRebound)/(tem.beforeFiveAverageRebound*1.0);
		        				tem.reboundProgressRate=Double.valueOf(cf.format(tem.reboundProgressRate));
		        				
		        				int temp2=tem.firstAssist;
		        				tem.firstAssist=tem.secondAssist;
		        				tem.secondAssist=tem.thirdAssist;
		        				tem.thirdAssist=tem.fourthAssist;
		        				tem.fourthAssist=tem.fifthAssist;
		        				tem.fifthAssist=Integer.parseInt(t[12]);
		        				tem.beforeFiveAverageAssist=(tem.beforeFiveAverageAssist*(tem.inMatches-6)+temp2)/((tem.inMatches-5)*1.0);
		        				tem.fiveAverageAssist=(tem.firstAssist+tem.secondAssist+tem.thirdAssist+tem.fourthAssist+tem.fifthAssist)/5.0;
		        				if(tem.beforeFiveAverageAssist>0)
			        				tem.assistProgressRate=(tem.fiveAverageAssist-tem.beforeFiveAverageAssist)/(tem.beforeFiveAverageAssist*1.0);
		        				tem.assistProgressRate=Double.valueOf(cf.format(tem.assistProgressRate));
		        				
		        			}
		        			}
		        		memberList.remove(j);
		        		memberList.add(j,tem);
		        	}
		        }
		    }
		    return memberList;
		}
		public MemberPO getMemberLiveData(String name)throws IOException{
			List<File> files = getFiles("players//info//");
			boolean hasIt=false;
			for(File f:files){
				String n=f.getName();
				if(n.equals(name)){
					hasIt=true;
					break;
				}
			}
			MemberPO result=new MemberPO();
			result.name=name;
			if(hasIt==true){
			String location="players//info//"+name;
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(location),"UTF-8"));  
			String a;
			int n=0;
			int forward=0,back=0;
			while((a=br.readLine())!=null){
				n++;
				if(n==6){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.position=a.substring(forward+1,back).trim();
				}
				if(n==4){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.number=a.substring(forward+1,back).trim();
				}
				if(n==8){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.height=a.substring(forward+1,back).trim();
				}
				if(n==10){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.weight=a.substring(forward+1,back).trim();
				}
				if(n==12){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.birth=a.substring(forward+1,back).trim();
				}
				if(n==14){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.age=a.substring(forward+1,back).trim();
				}
				if(n==16){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.exp=a.substring(forward+1,back).trim();
				}
				if(n==18){
					for(int i=0;i<a.length();i++){
						if(a.charAt(i)=='║'){
							back=i;
						}
						if(a.charAt(i)=='│'){
							forward=i;
						}
						
					}
					result.school=a.substring(forward+1,back).trim();
				}
			}
			}
			else{
				result.number=null;
				result.position=null;
				result.height=null;
				result.weight=null;
				result.birth=null;
				result.age=null;
				result.exp=null;
				result.school=null;
			}
			
			result.team=getTeamName(result.name);
			ArrayList<MemberPO> memberList=newMemberList();
			for(MemberPO p:memberList){
				if(p.name.equals(result.name)){
					result.matchList=p.matchList;
					result.inMatches=p.inMatches;
				}
			}
			return result;
			
		}
		
		public ArrayList<MatchPO> getMemberMatches(String name)throws IOException{
			ArrayList<MatchPO> matchList=new ArrayList<MatchPO>();
			TeamData td=new TeamData();
			matchList=td.getTeamMatches(getTeamName(name));
			return matchList;
		}
		
		
		
		public ArrayList<MatchPO> getLatestMemberMatches(String name)throws IOException{
			ArrayList<MatchPO> matchList=new ArrayList<MatchPO>();
			TeamData td=new TeamData();
			matchList=td.getLatestTeamMatches(getTeamName(name));
			return matchList;
		}
		
		public String getTeamName(String name)throws IOException{
			String result=null;
			List<File> files = getFiles("C:\\Users\\Administrator\\Desktop\\new data");
		    for(File f : files){
		    	int first=0,second=0;
		    	String firstTeam=null,secondTeam=null;
		    	ArrayList<String[]> dataList=new ArrayList<String[]>();
		        dataList=getContentByLocalFile(f);
		        for(int i=0;i<dataList.size();i++){
		        	if(dataList.get(i).length==1){
		        		firstTeam=dataList.get(i)[0];
		        		first=i;
		        		break;
		        	}
		        }
		        for(int i=first;i<dataList.size();i++){
		        	if(dataList.get(i).length==1){
		        		secondTeam=dataList.get(i)[0];
		        		second=i;
		        	}
		        }//得到文档的队伍名称以及位置
		        for(int i=first+1;i<second;i++){
		        	if(dataList.get(i)[0].equals(name)){
		        		result=firstTeam;
		        	}
		        }
		        for(int i=second+1;i<dataList.size();i++){
		        	if(dataList.get(i)[0].equals(name)){
		        		result=secondTeam;
		        	}
		        }
		        if(result!=null){
		        	break;
		        }
		    }
		    TeamData td=new TeamData();
		    result=td.getTeamName(result);
		    return result;
		       
		}
		
		public ArrayList<String[]> todayMembers()throws IOException{
			MatchData md=new MatchData();
			ArrayList<String[]> memberList=new ArrayList<String[]>();
			String time=null;
			List<File> files = getFiles("C:\\Users\\Administrator\\Desktop\\new data");
		    for(File f : files){
		    	String []temp=f.getName().split("_");
		    	time=temp[1];
		    }
		    for(File f:files){
		    	if(f.getName().contains(time)){
		    		MatchPO temp=md.getMatch(f.getName());
		    		for(int i=0;i<temp.getPlayers1().length;i++){
		    			memberList.add(temp.getPlayers1()[i]);
		    		}
		    		for(int i=0;i<temp.getPlayers2().length;i++){
		    			memberList.add(temp.getPlayers2()[i]);
		    		}
		    		
		    	}
		    }
		    return memberList;
		}
		
		public ArrayList<String[]> getMatchInfo(String name)throws IOException{
			ArrayList<String[]> resultList=new ArrayList<String[]>();
			ArrayList<MatchPO> matchList=new ArrayList<MatchPO>();
			matchList=getMemberMatches(name);
			for(MatchPO t:matchList){
			   String[][] tempList=t.getPlayers1();
			   for(int i=0;i<tempList.length;i++){
				   if(tempList[i][0].equals(name)){
					   String[] temp=new String[21];
					   for(int j=0;j<18;j++)
						   temp[j]=tempList[i][j];
					   temp[18]=t.getDate();
					   temp[19]=t.getTeam1()+"-"+t.getTeam2();
					   temp[20]=t.getScore();
					   resultList.add(temp);
				   }
			   }
			   
			   String[][] tempList1=t.getPlayers2();
			   for(int i=0;i<tempList1.length;i++){
				   if(tempList1[i][0].equals(name)){
					   String[] temp=new String[21];
					   for(int j=0;j<18;j++)
						   temp[j]=tempList1[i][j];
					   temp[18]=t.getDate();
					   temp[19]=t.getTeam1()+"-"+t.getTeam2();
					   temp[20]=t.getScore();
					   resultList.add(temp);
				   }
			   }
			}
			return resultList;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
	

}
