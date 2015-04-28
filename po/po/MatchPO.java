package po;


public class MatchPO {
	private String date;
	private String team1;
	private String team2;
	private String scores;
	private String sectionScore;
	private String [][] players1;
	private String [][] players2;
	
	
	public MatchPO(){
		this.date = null;
		this.team1 = null;
		this.team2 = null;
		this.scores = null;
		this.sectionScore = null;
		
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
	
	public String[][] getPlayers1(){
		return this.players1;
	}
	
	public void setPlayers1(String[][] a){
		this.players1 = a;
	}
	
	public String[][] getPlayers2(){
		return this.players2;
	}
	
	public void setPlayers2(String[][] a){
		this.players2 = a;
	}
}