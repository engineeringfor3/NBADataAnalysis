package playerui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mainui.HomePage;
import matchui.SingleMatch;
import playerbl.MemberLogic;
import vo.MatchVO;
import vo.MemberVO;

public class playerInfo implements MouseListener{
	JTable table = null;
	String[] info = {"比赛日期","主队","客队","总比分","上场时间","得分"};
	ArrayList<MatchVO> matchInfo = new ArrayList<MatchVO>();
	ArrayList<String[]> playerInfo = new ArrayList<String[]>();	
	JLabel history = null;
	String firstName = "";
	MemberVO player = null;

	public JPanel go(String name){
		MemberLogic m = new MemberLogic();
		JPanel p = new JPanel();
		firstName = name;
		history = new JLabel("<html><u>历史数据</u><html>");
		p.setLayout(null);
		p.setSize(650,610);
		
		ImageIcon i = new ImageIcon(".\\players\\action\\"+name+".png");
		ImageIcon i2 = new ImageIcon(".\\players\\portrait\\"+name+".png");
		JLabel l = new JLabel(i);
		JLabel l2 = new JLabel(i2);
		
		l.setBounds(450,0,200,400);
		l2.setBounds(40,40,150,200);
	
		try {
			player = m.getPlayerData(name);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		
		JLabel firstName = new JLabel("姓名:  "+player.getName());
		JLabel number = new JLabel("球衣号码:   "+player.getNumber());
		JLabel position = new JLabel("位置:   "+player.getPosition());
		JLabel height = new JLabel("身高:    "+player.getHeight());
		JLabel weight = new JLabel("体重:    "+player.getWeight());
		JLabel birth = new JLabel("生日:   "+player.getBirth());
		JLabel age = new JLabel("年龄:   "+player.getAge());
		JLabel exp = new JLabel("球龄:    "+player.getExp());
		JLabel school = new JLabel("毕业学校:   "+player.getSchool());
		JLabel team = new JLabel("队伍:    "+player.getTeam());

		
		history.setForeground(Color.red);
		history.addMouseListener(this);
		
		matchInfo = player.getMatchList();
		playerInfo = player.getMatchInfo();
		String[][] result;
		
		if(matchInfo.size()<=0){
			JOptionPane.showMessageDialog(null,"该球员暂无比赛");
		}
		
		if(matchInfo.size()<5){
			result = new String[matchInfo.size()][info.length];
		}
	
		else{
			result = new String[5][info.length];
		}
	
		for(int j=0;j<result.length;j++){
			result[j][0] = matchInfo.get(j).getDate();
			result[j][1] = matchInfo.get(j).getTeam1();
			result[j][2] = matchInfo.get(j).getTeam2();
			result[j][3] = matchInfo.get(j).getScore();
			result[j][4] = playerInfo.get(j)[2];
			result[j][5] = playerInfo.get(j)[17];
		}
		
		DefaultTableModel model = new DefaultTableModel(result,info);
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		table.setOpaque(false);
		table.addMouseListener(this);
		
		firstName.setBounds(230, 50, 150, 50);
		number.setBounds(390, 50, 150, 50);
		position.setBounds(230, 70, 150, 50);
		height.setBounds(390, 70, 150, 50);
		weight.setBounds(230,90,150,50);
		birth.setBounds(390, 90, 150, 50);
		age.setBounds(230, 110, 150, 50);
		exp.setBounds(390, 110, 150, 50);
		school.setBounds(230,130,150,50);
		team.setBounds(390, 130, 150, 50);
		history.setBounds(230, 150, 150, 50);
		js.setBounds(40, 260, 400, 200);
		
		p.add(l);
		p.add(l2);
		p.add(firstName);
		p.add(number);
		p.add(position);
		p.add(height);
		p.add(weight);
		p.add(birth);
		p.add(age);
		p.add(exp);
		p.add(school);
		p.add(team);
		p.add(history);
		p.add(js);
		
		return p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == history){
			playerAllData p = new playerAllData();
			JPanel temp = p.go(player,firstName);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(temp);
			HomePage.count++;
			HomePage.first.add(temp);
		}
		else if(e.getSource() == table){
			JTable temp = (JTable) e.getSource();
			SingleMatch m = new SingleMatch();
			int x = temp.getSelectedRow();
			MatchVO vo = matchInfo.get(x);
			JLabel first = new JLabel(new ImageIcon("./picture/"+vo.getTeam1()+".png"));
			JLabel second = new JLabel(new ImageIcon("./picture/"+vo.getTeam2()+".png"));
			if(vo.getTeam1().equals("NOH")){
				first = new JLabel(new ImageIcon("./picture/NOP.png"));
			}
			if(vo.getTeam2().equals("NOH")){
				second = new JLabel(new ImageIcon("./picture/NOP.png"));
			}
			
			JPanel p = m.go(vo,first,second);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p);
			HomePage.count++;
			HomePage.first.add(p);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
