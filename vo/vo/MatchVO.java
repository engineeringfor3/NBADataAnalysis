package vo;

import java.util.ArrayList;

public class MatchVO {
	private String date;
	private String team;
	private String team2;
	private String scores;
	private String sectionScore;
	private ArrayList<playerInMatchesVO> players1;
	private ArrayList<playerInMatchesVO> players2;
	
	public MatchVO(String d,String t,String t2,String s,String ss,ArrayList<playerInMatchesVO> 
		player1,ArrayList<playerInMatchesVO> player2){
		this.date = d;
		this.team = t;
		this.team2 = t2;
		this.scores = s;
		this.sectionScore = ss;
		this.players1 = player1;
		this.players2 = player2;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String s){
		this.date = s;
	}
	
	public String getTeam(){
		return this.team;
	}
	
	public void setTeam(String s){
		this.team = s;
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
		return this.scores;
	}
	
	public void setSectionScore(String s){
		this.sectionScore = s;
	}
	
	public ArrayList<playerInMatchesVO> getPlayer1(){
		return this.players1;
	}
	
	public void setPlayer1(ArrayList<playerInMatchesVO> a){
		this.players1 = a;
	}
	
	public ArrayList<playerInMatchesVO> getPlayer2(){
		return this.players2;
	}
	
	public void setPlayer2(ArrayList<playerInMatchesVO> a){
		this.players2 = a;
	}
}
