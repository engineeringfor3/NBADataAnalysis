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
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

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
	String[] columnNames = { "球员名称", "所属球队","球员位置","最近5场提升率"};

	Object[][] result = new Object[5][columnNames.length];
	ArrayList<MemberVO> player = null;

	public JPanel go() {
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("确定");
		choice.setBounds(10,20,90,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getProgressPlayer("场均得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		for(int i=0;i<player.size();i++){
			result[i][0] = player.get(i).getName();
			result[i][1] = player.get(i).getTeam();
			result[i][2] = player.get(i).getPosition();
			result[i][3] = player.get(i).getScoreProgressRate();
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
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getScoreProgressRate();
			}
			
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均篮板")){
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getReboundProgressRate();
			}
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均助攻")){
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
				result[i][3] = player.get(i).getAssistProgressRate();
			}
			model.setData(result, columnNames);  
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		playerInfo p = new playerInfo();
		String name = (String) table.getValueAt(table.getSelectedRow(),
				0);
		JPanel p4 = p.go(name);
		HomePage.screen.get(HomePage.count-1).setVisible(false);
		HomePage.screen.add(p4);
		HomePage.count++;
		HomePage.first.add(p4);
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
