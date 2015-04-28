package dataservice;

import java.io.IOException;
import java.util.ArrayList;

import po.MatchPO;

import po.TeamPO;

public interface TeamDataService {
	public ArrayList<TeamPO> getTeamList()throws IOException;
	public TeamPO getTeamLiveData(String name)throws IOException;
	public ArrayList<MatchPO> getTeamMatches(String name)throws IOException;
	public ArrayList<MatchPO> getLatestTeamMatches(String name)throws IOException;

}

