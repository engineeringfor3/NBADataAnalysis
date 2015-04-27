package blservice;

import java.util.ArrayList;

import vo.MemberVO;

public interface PlayerBLService {
	public MemberVO getPlayerData(String name);
	public String[][] getHotPlayer(String condition);
	public ArrayList<MemberVO> getSeasonHotPlayer(String condition);
	public ArrayList<MemberVO> getProgressPlayer(String condition);
}
