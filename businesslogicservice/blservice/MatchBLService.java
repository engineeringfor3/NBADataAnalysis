package blservice;

import java.util.ArrayList;

import vo.MatchVO;

public interface MatchBLService {
	public ArrayList<MatchVO> getMatches(String team1,String team2);
}
