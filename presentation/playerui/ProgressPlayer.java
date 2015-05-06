package playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mainui.HomePage;
import playerbl.MemberLogic;
import vo.MemberVO;

public class ProgressPlayer implements ActionListener, MouseListener {
	MemberLogic m = new MemberLogic();
	JPanel p = new JPanel();
	String[] choose = {"场均得分","场均篮板","场均助攻"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;
	String[][] result = new String[5][choose.length];
	ArrayList<MemberVO> player = null;

	public JPanel go() {
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("确定");
		choice.setBounds(10,20,150,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getSeasonHotPlayer("场均得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		String[] columnNames = { "球员名称", "所属球队","球员位置","场均数据"};
		
		String[][] result = new String[5][columnNames.length];
		for(int i=0;i<player.size();i++){
			result[i][0] = player.get(i).getName();
			result[i][1] = player.get(i).getTeam();
			result[i][2] = player.get(i).getPosition();
			result[i][3] = player.get(i).getScoreProgressRate().toString();
		}
		
		model = new MyTableModel(result,columnNames);  
		table = new JTable(model); 
		table.setOpaque(false);
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 70, 650, 400);
		js.setViewportView(table);
	
		p.add(choice);
		p.add(b);
		p.add(js);
		
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ch = (String) choice.getSelectedItem();
		try {
			player = m.getSeasonHotPlayer(ch);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "获取数据错误");
			e1.printStackTrace();
		}
		
		getData(ch);
		
		table.updateUI();
	}

	private void getData(String ch) {
		if(ch.equals("场均得分")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","场均数据"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getScoreProgressRate().toString();
			}
			
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均篮板")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","场均数据"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getReboundProgressRate().toString();
			}
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均助攻")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("场均盖帽")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","场均数据"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getReboundProgressRate().toString();
			}
			model.setData(result,columnNames); 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(table.getSelectedColumn() == 0){
			playerInfo p = new playerInfo();
			String name = (String) table.getValueAt(table.getSelectedRow(),
					table.getSelectedColumn());
			JPanel p4 = p.go(name);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p4);
			HomePage.count++;
			HomePage.first.add(p4);
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
}
