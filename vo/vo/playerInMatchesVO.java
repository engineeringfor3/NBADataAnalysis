package vo;

public class playerInMatchesVO {
	private String name;
	private String position;
	private String time;
	private int shotHits;
	private int shots;
	private int threeShotHits;
	private int threeShots;
	private int penaltyShotHits;
	private int penaltyShots;
	private int offensiveRebounds;
	private int defensiveRebounds;
	private int rebounds;
	private int assists;
	private int steals;
	private int blockShots;
	private int mistakes;
	private int fouls;
	private int scores;
	
	public playerInMatchesVO(String n,String p,String t,int tsh,int shot,int threesh,
			int ts,int psh,int ps,int or,int dr,int r,int a,int steal,int bs,int m,int f,int score){
		this.name = n;
		this.position = p;
		this.time = t;
		this.shotHits = tsh;
		this.shots = shot;
		this.threeShotHits = threesh;
		this.threeShots = ts;
		this.penaltyShotHits = psh;
		this.penaltyShots = ps;
		this.offensiveRebounds = or;
		this.defensiveRebounds = dr;
		this.rebounds = r;
		this.assists = a;
		this.steals = steal;
		this.blockShots = bs;
		this.mistakes = m;
		this.fouls = f;
		this.scores = score;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setPosition(String p){
		this.position = p;
	}
	
	public String getTime(){
		return time;
	}
	
	public void setTime(String t){
		this.time = t;
	}
	
	public int getShotHits(){
		return this.shotHits;
	}
	
	public void setShotHits(int t){
		this.shotHits = t;
	}
	
	public int getShot(){
		return this.shots;
	}
	
	public void setShot(int s){
		this.shots = s;
	}
	
	public int getThreeShotHit(){
		return this.threeShotHits;
	}
	
	public void setThreeShotHit(int s){
		this.threeShotHits = s;
	}
	
	public int getThreeShot(){
		return this.threeShots;
	}
	
	public void setThreeShot(int t){
		this.threeShots = t;
	}
	
	public int getPenaltyShotHit(){
		return this.penaltyShotHits;
	}
	
	public void setPenaltyShotHit(int p){
		this.penaltyShotHits = p;
	}
	
	public int getPenaltyShot(){
		return this.penaltyShots;
	}
	
	public void setPenaltyShot(int p){
		this.penaltyShots = p;
	}
	
	public int getOffensiveRebound(){
		return this.offensiveRebounds;
	}
	
	public void setOffensiveRebound(int o){
		this.offensiveRebounds = o;
	}
	
	public int getDefensiveRebound(){
		return this.defensiveRebounds;
	}
	
	public void setDefensiveRebound(int d){
		this.defensiveRebounds = d;
	}
	
	public int getRebound(){
		return this.rebounds;
	}
	
	public void setRebound(int r){
		this.rebounds = r;
	}
	
	public int getAssist(){
		return this.assists;
	}
	
	public void setAssist(int a){
		this.assists = a;
	}
	
	public int getSteal(){
		return this.steals;
	}
	
	public void setSteal(int s){
		this.steals = s;
	}
	
	public int getBlockShot(){
		return this.blockShots;
	}
	
	public void setBlockShot(int b){
		this.blockShots = b;
	}
	
	public int getScore(){
		return this.scores;
	}
	
	public void setScore(int s){
		this.scores = s;
	}
	
	public int getMistake(){
		return this.mistakes;
	}
	
	public void setMistake(int i){
		this.mistakes = i;
	}
	
	public int getFoul(){
		return this.fouls;
	}
	
	public void setFoul(int f){
		this.fouls = f;
	}
}
