package blservice;

import java.util.ArrayList;
import java.io.*;

import vo.MatchVO;

public interface MatchBLService {
	public ArrayList<MatchVO> getMatchesByTeam(String team1,String team2)throws IOException;
	public ArrayList<MatchVO> getMatchesByTime(String time)throws IOException;
}
