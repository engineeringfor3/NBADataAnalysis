package blservice;

import java.util.ArrayList;

import vo.MatchVO;

public interface MatchBLService {
	public ArrayList<MatchVO> getMatchesByTeam(String team1,String team2);
	public ArrayList<MatchVO> getMatchesByTime(String time);
}
