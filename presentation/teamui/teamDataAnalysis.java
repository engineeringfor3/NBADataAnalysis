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

import playerbl.MemberLogic;
import teambl.teamsManager;
import vo.teamVO;

public class teamDataAnalysis implements MouseListener{
	JPanel panel = new JPanel();
	
	String[] team = {"�������","��������","Ͷ��������","Ͷ�����ִ���","����������","���ֳ�����",
			"����������","���������","����������","����������","������","������","������","��ñ��","ʧ����",
			"������","�����÷�","Ͷ��������","����������","����������","ʤ��","�����غ�","����Ч��","����Ч��",
			"����Ч��","����Ч��","������"};
	
	public JPanel go(String n,Icon i) throws IOException{
		panel.setLayout(null);
		panel.setSize(650,610);
		teamsManager t = new teamsManager();
		teamDataAnalysis td = new teamDataAnalysis();
		
		Object[][] data=t.getSingleTeamData(n);
		
		MyTableModel tableModel = new MyTableModel(data,team);
		TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(tableModel);
		JTable table = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(table);
		table.setAutoCreateRowSorter(true); 
		table.setRowSorter(sorter); 
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		
		JLabel image = new JLabel(i);
		image.setBackground(Color.DARK_GRAY);
		
		image.setBounds(50,0,100,100);
		jsp.setBounds(20,110,600,340);
		
		table.setFillsViewportHeight(true);
		table.setOpaque(false);
		
		JLabel name =       new JLabel("�������:   ");
		JLabel shorts =     new JLabel("��д:      ");
		JLabel place =      new JLabel("���ڵ�:     ");
		JLabel zone =       new JLabel("����:      ");
		JLabel subZone =    new JLabel("����:      ");
		JLabel homecourt =  new JLabel("����:      ");
		JLabel launchTime = new JLabel("����ʱ��:   ");
		
		name.setBounds(220, 0, 150,50);
		shorts.setBounds(400, 0, 150, 50);
		place.setBounds(220, 20, 150, 50);
		zone.setBounds(400, 20,150,50);
		subZone.setBounds(220, 40, 150, 50);
		homecourt.setBounds(400,40, 150, 50);
		launchTime.setBounds(220, 60, 150, 50);
		
		JLabel historyData = new JLabel("<html><u>��ʷ����</u><html>");
		historyData.addMouseListener(this);
		historyData.setForeground(Color.red);
		
		historyData.setBounds(400, 60, 150, 50);
		
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
		JFrame fr = new JFrame();
		fr.getContentPane().add(new JLabel("hdf"));
		fr.setSize(200,200);
		fr.setVisible(true);
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
