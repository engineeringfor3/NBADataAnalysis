package playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import playerbl.MemberLogic;

public class TodayHotPlayer implements ActionListener{
	JPanel p = new JPanel();
	String[] choose = {"得分","篮板","助攻","盖帽","抢断"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;

	public JPanel go() {
		MemberLogic m = new MemberLogic();
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("确定");
		choice.setBounds(10,20,70,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		String[][] player = null;
		try {
			player = m.getHotPlayer("得分");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取信息错误");
			e.printStackTrace();
		}
		
		String[] columnNames = { "球员名称", "所属球队","球员位置","得分"};
		
		String[][] result = new String[5][3];
		for(int i=0;i<player.length;i++){
			result[i][0] = player[i][0];
			result[i][1] = player[i][1];
			result[i][2] = player[i][1];
		}
		
		model = new MyTableModel(result,columnNames);  
		table = new JTable(model); 
		table.setOpaque(false);
		table.setFillsViewportHeight(true);
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
		
	}
}

/*class MyTableModel extends AbstractTableModel  { 
	MemberLogic ml=new MemberLogic();
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
  }  */
