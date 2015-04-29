package vo;

import java.util.ArrayList;

import po.MatchPO;

public class MatchVO {
	private String date;
	private String team1;
	private String team2;
	private String scores;
	private String sectionScore;
	private String [][] players1;
	private String [][] players2;
	
	public MatchVO(MatchPO t){
		this.date = t.getDate();
		this.team1 = t.getTeam1();
		this.team2 = t.getTeam2();
		this.scores = t.getScore();
		this.sectionScore = t.getSectionScore();
		this.players1=t.getPlayers1();
		this.players2=t.getPlayers2();
		
	}
	public MatchVO(String date,String team1,String team2,String scores,String sectionScore,String[][] players1,String[][] players2){
		this.date = date;
		this.team1 = team1;
		this.team2 = team2;
		this.scores = scores;
		this.sectionScore = sectionScore;
		this.players1=players1;
		this.players2=players2;
		
		
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String s){
		this.date = s;
	}
	
	public String getTeam1(){
		return this.team1;
	}
	
	public void setTeam1(String s){
		this.team1 = s;
	}
	
	public String getTeam2(){
		return this.team2;
	}
	
	public void setTeam2(String t){
		this.team2 = t;
	}
	
	public String getScore(){
		return this.scores;
	}
	
	public void setScore(String s){
		this.scores = s;
	}
	
	public String getSectionScore(){
		return this.sectionScore;
	}
	
	public void setSectionScore(String s){
		this.sectionScore = s;
	}
	
	public String[][] getPlayer1(){
		return this.players1;
	}
	
	public void setPlayer1(String[][] a){
		this.players1 = a;
	}
	
	public String[][] getPlayer2(){
		return this.players2;
	}
	
	public void setPlayer2(String[][] a){
		this.players2 = a;
	}
}
