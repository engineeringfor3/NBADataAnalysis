package vo;
import java.util.*;


public class MemberVO {
	private String name;
	private String number;
	private String position;
	private String height;
	private String weight;
	private String birth;
	private String age;
	private String exp;
	private String school;
	private String team;
	private double scoreProgressRate;
	private double reboundProgressRate;
	private double assistProgressRate;
	
	
	private ArrayList<MatchVO> matchList;
	private ArrayList<String[]> matchInfo;
	
	public MemberVO(String name,String number,String position,String height,String weight,String birth,String age,String exp,String school,String team,double scoreProgressRate,double reboundProgressRate,double assistProgressRate,ArrayList<MatchVO> matchList,ArrayList<String[]> matchInfo){
		this.name=name;
		this.number=number;
		this.position=position;
		this.height=height;
		this.weight=weight;
		this.birth=birth;
		this.age=age;
		this.exp=exp;
		this.school=school;
		this.team=team;
		this.scoreProgressRate=scoreProgressRate;
		this.reboundProgressRate=reboundProgressRate;
		this.assistProgressRate=assistProgressRate;
		this.matchList=matchList;
		this.matchInfo=matchInfo;
	}
	
	public MemberVO(String name,String number,String position,String height,String weight,String birth,String age,String exp,String school,String team,ArrayList<MatchVO> matchList,ArrayList<String[]> matchInfo){
		this.name=name;
		this.number=number;
		this.position=position;
		this.height=height;
		this.weight=weight;
		this.birth=birth;
		this.age=age;
		this.exp=exp;
		this.school=school;
		this.team=team;
		this.scoreProgressRate=scoreProgressRate;
		this.reboundProgressRate=reboundProgressRate;
		this.assistProgressRate=assistProgressRate;
		this.matchList=matchList;
		this.matchInfo=matchInfo;
	}
	
	public MemberVO(String name,String number,String position,String height,String weight,String birth,String age,String exp,String school,String team,ArrayList<MatchVO> matchList){
		this.name=name;
		this.number=number;
		this.position=position;
		this.height=height;
		this.weight=weight;
		this.birth=birth;
		this.age=age;
		this.exp=exp;
		this.school=school;
		this.team=team;
		this.scoreProgressRate=scoreProgressRate;
		this.reboundProgressRate=reboundProgressRate;
		this.assistProgressRate=assistProgressRate;
		this.matchList=matchList;
		this.matchInfo=matchInfo;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public String getPosition(){
		return this.position;
	}
	
	public String getHeight(){
		return this.height;
	}
	
	public String getWeight(){
		return this.weight;
	}
	
	public String getBirth(){
		return this.birth;
	}
	
	public String getAge(){
		return this.age;
	}
	
	public String getExp(){
		return this.exp;
	}
	
	public String getSchool(){
		return this.school;
	}
	
	public String getTeam(){
		return this.team;
	}
	
	public Double getScoreProgressRate(){
		return this.scoreProgressRate;
	}
	
	public Double getReboundProgressRate(){
		return this.reboundProgressRate;
	}
	
	public Double getAssistProgressRate(){
		return this.assistProgressRate;
	}
	
	public ArrayList<MatchVO> getMatchList(){
		return this.matchList;
	}
	
	public ArrayList<String[]> getMatchInfo(){
		return this.matchInfo;
	}
	
	
}

