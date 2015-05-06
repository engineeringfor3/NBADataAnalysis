package dataservice;

import java.io.IOException;
import java.util.ArrayList;

import po.MemberPO;

public interface MemberDataService {
	public  ArrayList<MemberPO> toMemberList() throws IOException;
	public MemberPO getMemberLiveData(String name)throws IOException;

}

