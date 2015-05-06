package teamui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import playerui.playerInfo;
import teambl.teamsManager;
import vo.teamVO;
import mainui.HomePage;
import enumType.NBAteams;


public class team implements MouseListener{
	JPanel p = new JPanel();
	String[] data = {"队伍","名称"};
	JTable table = null;
	MyTableModel model = null;
	
	public team(){
		p.setLayout(null);
		p.setSize(650,610);
		
		Object[][] team = new Object[30][data.length];
		
		int i=0;
		for(NBAteams a:NBAteams.values()){
			ImageIcon temp = new ImageIcon("./picture/"+a.toString()+".png");
			team[i][0] = temp;
			team[i][1] = changeName(a.toString());
			i++;
		}
		
		model = new MyTableModel(team,data);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(20,0,600,450);
		
		table.setRowHeight(80);
		table.setOpaque(false);
		DefaultTableCellRenderer cellrender = new DefaultTableCellRenderer();
		cellrender.setHorizontalAlignment(JTextField.CENTER);
		cellrender.setOpaque(false);
		table.setDefaultRenderer(String.class, cellrender);
		table.addMouseListener(this);
		p.add(scroll);
	}
		
	public JPanel go(){
		new team();
		return p;
	}
	
	class MyTableModel extends DefaultTableModel{
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			}
			         //important here
		public Class<?> getColumnClass(int col) {
			Vector<?> v = (Vector<?>) dataVector.elementAt(0);
			return v.elementAt(col).getClass();
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		int x = temp.getSelectedRow();                //根据所选球队跳转
		teamDataAnalysis t = new teamDataAnalysis();
		String name = (String) table.getValueAt(x,1);
		ImageIcon image = (ImageIcon) table.getValueAt(x,0);
			
		JPanel p4;
		try {
			p4 = t.go(name,image);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p4);
			HomePage.count++;
			HomePage.first.add(p4);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,"获取数据失败");
			e1.printStackTrace();
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
	
	private String changeName(String n) {
		if(n.equals("ATL")){
			return "Hawks";
		}
		else if(n.equals("BKN")){
			return "Nets";
		}
		else if(n.equals("BOS")){
			return "Celtics";
		}
		else if(n.equals("CHA")){
			return "Hornets";
		}
		else if(n.equals("CHI")){
			return "Bulls";
		}
		else if(n.equals("CLE")){
			return "Cavaliers";
		}
		else if(n.equals("DAL")){
			return "Mavericks";
		}
		else if(n.equals("DEN")){
			return "Nuggets";
		}
		else if(n.equals("DET")){
			return "Pistons";
		}
		else if(n.equals("GSW")){
			return "Warriors";
		}
		else if(n.equals("HOU")){
			return "Rockets";
		}
		else if(n.equals("IND")){
			return "Pacers";
		}
		else if(n.equals("LAC")){
			return "Clippers";
		}
		else if(n.equals("LAL")){
			return "Lakers";
		}
		else if(n.equals("MEM")){
			return "Grizzlies";
		}
		else if(n.equals("MIA")){
			return "Heat";
		}
		else if(n.equals("MIL")){
			return "Bucks";
		}
		else if(n.equals("MIN")){
			return "Timberwolves";
		}
		else if(n.equals("NOP")){
			return "Pelicans";
		}
		else if(n.equals("NYK")){
			return "Knicks";
		}
		else if(n.equals("OKC")){
			return "Thunder";
		}
		else if(n.equals("ORL")){
			return "Magic";
		}
		else if(n.equals("PHI")){
			return "76ers";
		}
		else if(n.equals("PHX")){
			return "Suns";
		}
		else if(n.equals("POR")){
			return "Trail Blazers";
		}
		else if(n.equals("SAC")){
			return "Kings";
		}
		else if(n.equals("SAS")){
			return "Spurs";
		}
		else if(n.equals("TOR")){
			return "Raptors";
		}
		else if(n.equals("UTA")){
			return "Jazz";
		}
		else{
			return "Wizards";
		}
	}
}
