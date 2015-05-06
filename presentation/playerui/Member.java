package playerui;
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

import data.MemberData;
import mainui.ChoosePage;
import mainui.HomePage;




public class Member {
	MemberLogic ml = new MemberLogic();
	MyTableModel model = null;
	JTable table = null;
	JComboBox position,league,subLeague,sortData;	
	
	String positions[]={"G","F","C","all"};
	String leagues[]={"E","W","all"};
	String subLeagues[]={"Southeast","Atlantic","Central","all"};
	String sortDatas[]={"�÷�","����","����","�÷�/����/����","��ñ","����","����","ʧ��","����","Ч��","Ͷ��","����","����","��˫"};

	public JPanel go() throws IOException{
		ArrayList<MemberPO> memberList = new ArrayList<MemberPO>();
		memberList = ml.toMemberList();
		Object[][] data = ml.poToList(memberList);
		
		final String[] columnNames = { "��Ա����", "�������", "��������",  
	            "�ȷ�����", "������","������","�ڳ�ʱ��","Ͷ��������","����������","����������","������","������","������","��ñ��","ʧ����","������","�÷�"
	            ,"Ч��","GmScЧ��ֵ","��ʵ������","Ͷ��Ч��","������","����������","����������","������","������","��ñ��","ʧ����","ʹ����","��������","��������","����ʱ��","����������","������ñ","����ʧ��","��������","�����÷�"};
		
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
		JButton b=new JButton("ɸѡ");
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
		
		JButton c=new JButton("����ɸѡ");
		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				ArrayList<MemberPO> memberList=new ArrayList<MemberPO>();
				memberList = ml.toMemberList();
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
				String name = (String) table.getValueAt(table.getSelectedRow(),0);
				JPanel p4 = p.go(name);
				HomePage.screen.get(HomePage.count-1).setVisible(false);
				HomePage.screen.add(p4);
				HomePage.count++;
				HomePage.first.add(p4);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}
		
	}
	

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
