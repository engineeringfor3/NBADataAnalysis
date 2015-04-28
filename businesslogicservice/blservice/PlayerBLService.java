package blservice;

import java.util.ArrayList;

import vo.MemberVO;
import java.io.*;
public interface PlayerBLService {
	public MemberVO getPlayerData(String name)throws IOException;
	public String[][] getHotPlayer(String condition)throws IOException;
	public ArrayList<MemberVO> getSeasonHotPlayer(String condition)throws IOException;
	public ArrayList<MemberVO> getProgressPlayer(String condition)throws IOException;
}
