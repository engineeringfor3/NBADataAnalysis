package teamui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import playerui.playerInfo;
import mainui.HomePage;
import enumType.NBAteams;


public class team implements MouseListener{
	JPanel p = new JPanel();
	String[] data = {"队伍","名称","赛区"};
	JTable table = null;
	MyTableModel model = null;
	
	public team(){
		p.setLayout(null);
		p.setSize(650,610);
		
		Object[][] team = new Object[30][data.length];
		
		int i=0;
		for(NBAteams a:NBAteams.values()){
			ImageIcon temp = new ImageIcon(a.toString()+".png");
			team[i][0] = temp;
			team[i][1] = a.toString();
			team[i][2] = "aa";
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
		public MyTableModel(Object[] cnames, int row) {
			super(cnames, row);
			}

		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			}
			         //important here
		public Class<?> getColumnClass(int col) {
			Vector<?> v = (Vector<?>) dataVector.elementAt(0);
			return v.elementAt(col).getClass();
			}

		public boolean isCellEditable(int row, int col) {
			Class<?> columnClass = getColumnClass(col);
			return columnClass != ImageIcon.class;
			}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(table.getSelectedColumn() == 1){
			teamDataAnalysis t = new teamDataAnalysis();
			String name = (String) table.getValueAt(table.getSelectedRow(),
					table.getSelectedColumn());
			ImageIcon image = (ImageIcon) table.getValueAt(table.getSelectedRow(),
					table.getSelectedColumn()-1);
			
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
