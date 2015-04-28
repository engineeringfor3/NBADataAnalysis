package dataservice;
import java.io.IOException;

import po.MatchPO;
public interface MatchDataService {
	public MatchPO getMatch(String name)throws IOException;

}

