package blservice;

import java.io.IOException;
import java.util.ArrayList;

import vo.teamVO;

public interface TeamBLService {
	public Object[][] getSingleTeamData(String team) throws IOException;
	public teamVO getTeamData(String team) throws IOException;
	public ArrayList<teamVO> getSeasonHotTeam(String condition);
}
