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
import javax.swing.JWindow;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import mainui.HomePage;
import playerbl.MemberLogic;
import vo.MemberVO;

public class SeasonHotPlayer implements ActionListener, MouseListener {
	MemberLogic m = new MemberLogic();
	JPanel p = new JPanel();
	String[] choose = {"场均得分","场均篮板","场均助攻","场均盖帽","场均抢断","三分命中率","投篮命中率","罚球命中率"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;
	String[] columnNames = { "球员名称", "所属球队","球员位置"};
	String[][] result;
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
			player = m.getSeasonHotPlayer("场均得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
	
		result = new String[5][columnNames.length];
	
		for(int i=0;i<player.size();i++){
			result[i][0] = player.get(i).getName();
			result[i][1] = player.get(i).getTeam();
			result[i][2] = player.get(i).getPosition();
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
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			
			model.setData(result, columnNames);
		}
		else if(ch.equals("场均篮板")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
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
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			model.setData(result,columnNames); 
		}
		else if(ch.equals("场均抢断")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("三分命中率")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("投篮命中率")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("罚球命中率")){
			String[] columnNames = { "球员名称", "所属球队","球员位置"};
			
			String[][] result = new String[5][3];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getTeam();
				result[i][2] = player.get(i).getPosition();
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
	
	class MyTableModel extends AbstractTableModel  { 
	    private String[] columnNames ; 
	    private Object[][] data ;  
	    
	    public MyTableModel(Object[][] data,String[] columnNames) {
	        super();
	        this.data = data;
	        this.columnNames=columnNames;
	         
	    }
	 
	    public int getColumnCount() {  
	      return columnNames.length;  
	    }  
	  
	    public int getRowCount() {  
	      return data.length;  
	    }  
	    
	    
	    public void setData(Object[][] data,String[] columnNames){
	    	this.data = data;
	        this.columnNames=columnNames;
	    	
	    }
	  
	    public String getColumnName(int col) {  
	      return columnNames[col];  
	    }  
	  
	    public Object getValueAt(int row, int col) {  
	      return data[row][col];  
	    }  
	 
	    public Class getColumnClass(int c) {  
	      return getValueAt(0, c).getClass();  
	    }  
	}
}
