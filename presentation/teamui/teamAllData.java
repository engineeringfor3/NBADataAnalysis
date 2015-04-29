package teamui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import playerbl.MemberLogic;
import teamui.teamDataAnalysis.MyTableModel;
import vo.MatchVO;
import vo.teamVO;

public class teamAllData {
	JPanel panel = new JPanel();
	String[] team = {"比赛时间","主队","客队","总比分","小节比分"};
	
	public JPanel go(teamVO vo,Icon i){
		panel.setLayout(null);
		panel.setSize(650,610);
		ArrayList<MatchVO> list = vo.getMatchList();
		String[][] result = new String[list.size()][team.length];
		
		for(int j=0;j<result.length;j++){
			result[j][0] = list.get(j).getDate();
			result[j][1] = list.get(j).getTeam1();
			result[j][2] = list.get(j).getTeam2();
			result[j][3] = list.get(j).getScore();
			result[j][4] = list.get(j).getSectionScore();
		}
		
		MyTableModel tableModel = new MyTableModel(result,team);
		TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(tableModel);
		JTable table = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(table);
		table.setAutoCreateRowSorter(true); 
		table.setRowSorter(sorter); 
		
		JLabel image = new JLabel(i);
		image.setBackground(Color.DARK_GRAY);
		
		image.setBounds(50,0,100,100);
		jsp.setBounds(20,110,600,340);
		
		table.setFillsViewportHeight(true);
		table.setOpaque(false);
		
		JLabel name =       new JLabel("球队名称:   "+vo.getName());
		JLabel shorts =     new JLabel("缩写:      "+vo.getShorts());
		JLabel place =      new JLabel("所在地:      "+vo.getPlace());
		JLabel zone =       new JLabel("赛区:      "+vo.getZone());
		JLabel subZone =    new JLabel("分区:         "+vo.getSubZone());
		JLabel homecourt =  new JLabel("主场:      "+vo.getHomeCourt());
		JLabel launchTime = new JLabel("建立时间:   "+vo.getLaunchTime());
		
		name.setBounds(220, 0, 150,50);
		shorts.setBounds(400, 0, 150, 50);
		place.setBounds(220, 20, 150, 50);
		zone.setBounds(400, 20,150,50);
		subZone.setBounds(220, 40, 150, 50);
		homecourt.setBounds(400,40, 150, 50);
		launchTime.setBounds(220, 60, 150, 50);
		
		panel.add(image);
		panel.add(jsp);
		panel.add(name);
		panel.add(shorts);
		panel.add(place);
		panel.add(zone);
		panel.add(subZone);
		panel.add(homecourt);
		panel.add(launchTime);
		return panel;
	}
	
	class MyTableModel extends AbstractTableModel  { 
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
