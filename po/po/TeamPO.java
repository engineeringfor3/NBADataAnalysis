package po;

import java.text.DecimalFormat;

public class TeamPO {
	DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat cf=new DecimalFormat("#.000");
    
	public String name;
	public String shorts;
	public String location;
	public String block;
	public String subBlock;
	public String mainPlace;
	public String time;
	
	
	public int scores;
	public int rebounds;
	public int assists;
	public int blockShots;
	public int steals;
	public int number;
	public int shots;
	public int shotHits;
	public int threeShots;
	public int threeShotHits;
	public int penaltyShots;
	public int penaltyShotHits;
	public double shotRate;
	public double threeShotRate;
	public double penaltyShotRate;
	
	public TeamPO(){
		name=null;
		shorts=null;
		location=null;
		block=null;
		subBlock=null;
		mainPlace=null;
		time=null;
		scores=0;
		rebounds=0;
		assists=0;
		blockShots=0;
		steals=0;
		number=0;
		shots=0;
		shotHits=0;
		threeShots=0;
		threeShotHits=0;
		penaltyShots=0;
		penaltyShotHits=0;
		shotRate=0.0;
		threeShotRate=0.0;
		penaltyShotRate=0.0;
	}
	
	public double getAverageScore(){
		double averageScore=scores/(number*1.0);
		averageScore=Double.valueOf(df.format(averageScore));
		return averageScore;
	}
	
	public double getAverageRebound(){
		double averageRebound=rebounds/(number*1.0);
		averageRebound=Double.valueOf(df.format(averageRebound));
		return averageRebound;
	}
	
	public double getAverageAssist(){
		double averageAssist=assists/(number*1.0);
		averageAssist=Double.valueOf(df.format(averageAssist));
		return averageAssist;
	}
	
	public double getAverageBlockShot(){
		double averageBlockShot=blockShots/(number*1.0);
		averageBlockShot=Double.valueOf(df.format(averageBlockShot));
		return averageBlockShot;
	}
	
	public double getAverageSteal(){
		double averageSteal=steals/(number*1.0);
		averageSteal=Double.valueOf(df.format(averageSteal));
		return averageSteal;
	}
	
	public double getShotRate(){
		double shotRate=shotHits/(shots*1.0);
		shotRate=Double.valueOf(cf.format(shotRate));
		return shotRate;
	}
	
	public double getThreeShotRate(){
		double threeShotRate=threeShotHits/(threeShots*1.0);
		threeShotRate=Double.valueOf(cf.format(threeShotRate));
		return threeShotRate;
	}
	
	public double getPenaltyShotRate(){
		double penaltyShotRate=penaltyShotHits/(penaltyShots*1.0);
		penaltyShotRate=Double.valueOf(cf.format(penaltyShotRate));
		return penaltyShotRate;
	}

}
