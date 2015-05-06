package playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import playerbl.MemberLogic;
import playerui.playerInfo;
import mainui.HomePage;
import teambl.teamsManager;
import teamui.teamDataAnalysis;
import vo.MemberVO;
import vo.teamVO;
import enumType.NBAteams;

public class SeasonHotTeam implements ActionListener, MouseListener {
	teamsManager m = new teamsManager();
	JPanel p = new JPanel();
	String[] choose = {"场均得分","场均篮板","场均助攻","场均盖帽","场均抢断","三分命中率","投篮命中率","罚球命中率"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;
	String[][] result = new String[5][choose.length];
	ArrayList<teamVO> player = null;

	public JPanel go() {
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("确定");
		choice.setBounds(10,20,90,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getSeasonHotTeam("场均得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
		
		String[][] result = new String[5][columnNames.length];
		for(int i=0;i<player.size();i++){
			result[i][0] = player.get(i).getName();
			result[i][1] = player.get(i).getZone();
			result[i][2] = player.get(i).getSubZone();
		}
		
		model = new MyTableModel(result,columnNames);  
		table = new JTable(model); 
		table.setOpaque(false);
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 70, 650, 400);
		js.setViewportView(table);
		
		DefaultTableCellRenderer cellrender = new DefaultTableCellRenderer();
		cellrender.setHorizontalAlignment(JTextField.CENTER);
		cellrender.setOpaque(false);
		table.setDefaultRenderer(String.class, cellrender);
	
		p.add(choice);
		p.add(b);
		p.add(js);
		
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ch = (String) choice.getSelectedItem();
		try {
			player = m.getSeasonHotTeam(ch);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "获取数据错误");
			e1.printStackTrace();
		}
		
		getData(ch);
		
		table.updateUI();
	}

	private void getData(String ch) {
		if(ch.equals("场均得分")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均篮板")){
			
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均助攻")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("场均盖帽")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result,columnNames); 
		}
		else if(ch.equals("场均抢断")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("三分命中率")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("投篮命中率")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("罚球命中率")){
			String[] columnNames = { "球队名称", "所属联盟","所属子联盟"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		int x = temp.getSelectedRow();
		teamDataAnalysis t = new teamDataAnalysis();
		String name = changeName(player.get(x).getShorts());
		ImageIcon image = new ImageIcon("./picture/"+player.get(x).getShorts()+".png");
			
		JPanel p4;
		try {
			p4 = t.go(name,image);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p4);
			HomePage.count++;
			HomePage.first.add(p4);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
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
