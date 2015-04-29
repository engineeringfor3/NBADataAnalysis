package teamui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.*;

import mainui.HomePage;
import matchui.SingleMatch;
import playerbl.MemberLogic;
import teambl.teamsManager;
import vo.MatchVO;
import vo.teamVO;

public class teamDataAnalysis implements MouseListener{
	JPanel panel = new JPanel();
	String name = null;
	Icon icon = null;
	teamsManager t = new teamsManager();
	String[] team = {"比赛时间","主队","客队","总比分","小节比分"};
	JLabel historyData = new JLabel("<html><u>历史数据</u><html>");
	JTable table = null;
	ArrayList<MatchVO> list = null;
	
	public JPanel go(String n,Icon i) throws IOException{
		panel.setLayout(null);
		panel.setSize(650,610);
		
		
		name = changeName(n);
		icon = i;
		
		teamVO vo = t.getTeamData(name);
		
		String[][] result = new String[5][team.length];
		list = vo.getMatchList();
		for(int j=0;j<result.length;j++){
			result[j][0] = list.get(j).getDate();
			result[j][1] = list.get(j).getTeam1();
			result[j][2] = list.get(j).getTeam2();
			result[j][3] = list.get(j).getScore();
			result[j][4] = list.get(j).getSectionScore();
		}
		
		MyTableModel tableModel = new MyTableModel(result,team);
		TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(tableModel);
		table = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(table);
		table.setAutoCreateRowSorter(true); 
		table.setRowSorter(sorter); 	
		table.addMouseListener(this);
		
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
		
		historyData.addMouseListener(this);
		historyData.setForeground(Color.red);
		
		historyData.setBounds(400, 60, 150, 50);
		
		for(int k=0;k<4;k++){
			TableColumn column = table.getColumnModel().getColumn(k);
			column.setMaxWidth(90);
			column.setMinWidth(90);
		}
		
		panel.add(image);
		panel.add(jsp);
		panel.add(name);
		panel.add(shorts);
		panel.add(place);
		panel.add(zone);
		panel.add(subZone);
		panel.add(homecourt);
		panel.add(launchTime);
		panel.add(historyData);
		return panel;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == historyData){
			teamAllData all = new teamAllData();
			teamVO vo;
			try {
				vo = t.getTeamData(name);
				JPanel p = all.go(vo,icon);
				HomePage.screen.get(HomePage.count-1).setVisible(false);
				HomePage.screen.add(p);
				HomePage.count++;
				HomePage.first.add(p);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "获取数据错误");
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == table){
			JTable temp = (JTable) e.getSource();
			int x = temp.getSelectedRow();
			MatchVO vo = list.get(x);
			SingleMatch s = new SingleMatch();
			JLabel first = new JLabel(new ImageIcon(vo.getTeam1()+".png"));
			JLabel second = new JLabel(new ImageIcon(vo.getTeam2()+".png"));
			if(vo.getTeam1().equals("NOH")){
				first = new JLabel(new ImageIcon("NOP.png"));
			}
			if(vo.getTeam2().equals("NOH")){
				second = new JLabel(new ImageIcon("NOP.png"));
			}
			
			JPanel p = s.go(vo,first,second);
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
