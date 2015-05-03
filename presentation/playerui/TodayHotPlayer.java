package playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import playerbl.MemberLogic;

public class TodayHotPlayer implements ActionListener,MouseListener{
	MemberLogic m = new MemberLogic();
	JPanel p = new JPanel();
	String[] choose = {"得分","篮板","助攻","盖帽","抢断"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;
	String[][] result = new String[5][4];
	JWindow info = new JWindow();
	String[][] player = null;

	public JPanel go() {
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("确定");
		choice.setBounds(10,20,70,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getHotPlayer("得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
		
		String[][] result = new String[5][4];
		for(int i=0;i<player.length;i++){
			result[i][0] = player[i][0];
			result[i][1] = player[i][1];
			result[i][2] = player[i][1];
			result[i][3] = player[i][17];
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
			player = m.getHotPlayer(ch);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "获取数据错误");
			e1.printStackTrace();
		}
		
		getData(ch);
		info = null;
		
		table.updateUI();
	}

	private void getData(String ch) {
		if(ch.equals("得分")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "得分:"+player[i][17];
			}
			model.setData(result, columnNames);
			model.getColumnName(3);
		}
		else if(ch.equals("篮板")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "篮板:"+player[i][11];
			}
			model.setData(result, columnNames);
			model.getColumnName(3);
		}
		else if(ch.equals("助攻")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "助攻:"+player[i][12];
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("盖帽")){
			String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "盖帽:"+player[i][14];
			}
			model.setData(result,columnNames); 
		}
		else {
			String[] columnNames = { "球员名称", "所属球队","球员位置","筛选数据"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "抢断:"+player[i][13];
			}
			model.setData(result, columnNames);  
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		SinglePlayer s = new SinglePlayer();
		int x = temp.getSelectedRow();
		info = s.go(player[x]);
		info.setLocation(e.getLocationOnScreen());
		info.setVisible(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		info.setVisible(false);
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



