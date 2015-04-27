package playerui;
import dataservice.MemberData;
import playerbl.MemberLogic;
import po.MemberPO;










import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.*;
import java.io.*;

import javax.swing.table.AbstractTableModel;  

import mainui.ChoosePage;
import mainui.HomePage;




public class Member {
	MemberLogic ml = new MemberLogic();
	MemberData md = new MemberData();
	MyTableModel model = null;
	JTable table = null;
	JComboBox position,league,subLeague,sortData;	
	
	String positions[]={"G","F","C","all"};
	String leagues[]={"E","W","all"};
	String subLeagues[]={"Southeast","Atlantic","Central","all"};
	String sortDatas[]={"得分","篮板","助攻","得分/助攻/篮板","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};

	public JPanel go() throws IOException{
		ArrayList<MemberPO> memberList = new ArrayList<MemberPO>();
		memberList = md.toMemberList();
		Object[][] data = ml.poToList(memberList);
		
		final String[] columnNames = { "球员名称", "所属球队", "参赛场数",  
	            "先发场数", "篮板数","助攻数","在场时间","投篮命中率","三分命中率","罚球命中率","进攻数","防守数","抢断数","盖帽数","失误数","犯规数","得分"
	            ,"效率","GmSc效率值","真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率","场均篮板","场均助攻","场均时间","场均抢断数","场均盖帽","场均失误","场均犯规","场均得分"};
		
		model = new MyTableModel(data,columnNames);  
		TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(model); 
		table = new JTable(model);  
		table.setAutoCreateRowSorter(true); 
		table.setRowSorter(sorter); 
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane(table);  
	    table.setFillsViewportHeight(true);
	    table.setOpaque(false);
	    table.addMouseListener(new Listener());
		
	    JPanel pl=new JPanel();
		JLabel l1=new JLabel("position");
		position=new JComboBox(positions);
		JLabel l2=new JLabel("league");
		league=new JComboBox(leagues);
		JLabel l3=new JLabel("subLeagues");
		league.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				  if (e.getStateChange()==ItemEvent.SELECTED){
					  String te=league.getSelectedItem().toString();
					  if(te.equals("E")){
						  String[] subList={"Southeast","Atlantic","Central","all"};
						  subLeague.removeAllItems();
							for(int i=0;i<subList.length;i++){
								subLeague.addItem(subList[i]);
							}
					  }
					  if(te.equals("W")){
						  String[] subList={"Southwest","Atlantic","Northwest","Pacific","all"};
						  subLeague.removeAllItems();
							for(int i=0;i<subList.length;i++){
								subLeague.addItem(subList[i]);
							}
					  }
					  if(te.equals("all")){
						  String[] subList={"all"};
						  subLeague.removeAllItems();
							for(int i=0;i<subList.length;i++){
								subLeague.addItem(subList[i]);
							}
					  }
					  }
			}
		});
	
		subLeague=new JComboBox(subLeagues);
		JLabel l4=new JLabel("sortData");
		sortData=new JComboBox(sortDatas);
		JButton b=new JButton("筛选");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					ArrayList<MemberPO> memberList5=new ArrayList<MemberPO> ();
                    String position1= position.getSelectedItem().toString();
                    String league1=league.getSelectedItem().toString();
                    String subLeague1=subLeague.getSelectedItem().toString();
                    String sortData1=sortData.getSelectedItem().toString();
                    memberList5=ml.sortList(position1, league1, subLeague1, sortData1);
                    Object[][] data1=ml.poToList(memberList5);
                    model.setData(data1,columnNames); 
                    table.updateUI();
            		
				}catch(IOException e1){
					
				}          
			}
		});
		
		JButton c=new JButton("结束筛选");
		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
				memberList=md.toMemberList();
				Object[][] data=ml.poToList(memberList);
				model.setData(data,columnNames); 
                table.updateUI();
				}catch(IOException e2){
					
				}
			}
		});

		int x =80,y=30;
		pl.setLayout(null);
		pl.setSize(650,610);
		
		l1.setBounds(0, 0,x,y);
		position.setBounds(50,0,x-30,y);
		l2.setBounds(105, 0,x,y);
		league.setBounds(150, 0,x-30,y);
		l3.setBounds(200, 0,x,y);
		subLeague.setBounds(275, 0,x,y);
		l4.setBounds(360, 0,x,y);
		sortData.setBounds(410, 0,x,y);
		b.setBounds(495, 0,x,y);
		c.setBounds(575, 0,x,y);
		scrollPane.setBounds(0, 50, 650, 420);
		
		
		TableColumn tc = table.getColumnModel().getColumn(0);
		tc.setPreferredWidth(150);
		
		pl.add(l1);
		pl.add(position);
		pl.add(l2);
		pl.add(league);
		pl.add(l3);
		pl.add(subLeague);
		pl.add(l4);
		pl.add(sortData);
		pl.add(b);
		pl.add(c);
		scrollPane.setViewportView(table);
		pl.add(scrollPane);
		
		return pl;
	}
	
	class Listener implements MouseListener{
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
