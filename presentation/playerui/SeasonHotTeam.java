package playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import playerbl.MemberLogic;
import playerui.playerInfo;
import mainui.HomePage;
import teambl.teamsManager;
import teamui.teamDataAnalysis;
import vo.MemberVO;
import vo.teamVO;
import enumType.NBAteams;

public class SeasonHotTeam implements ActionListener, MouseListener {
	teamsManager m = new teamsManager();
	JPanel p = new JPanel();
	String[] choose = {"�����÷�","��������","��������","������ñ","��������","����������","Ͷ��������","����������"};
	JTable table = null;
	MyTableModel model = null;
	JComboBox choice = null;
	String[][] result = new String[5][choose.length];
	ArrayList<teamVO> player = null;

	public JPanel go() {
		p.setLayout(null);
		p.setSize(650,610);
		
		choice = new JComboBox(choose);
		JButton b = new JButton("ȷ��");
		choice.setBounds(10,20,150,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getSeasonHotTeam("�����÷�");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "��ȡ��Ϣ����");
			e.printStackTrace();
		}
		
		String[] columnNames = { "�������", "��������","����������"};
		
		String[][] result = new String[5][columnNames.length];
		for(int i=0;i<player.size();i++){
			result[i][0] = player.get(i).getName();
			result[i][1] = player.get(i).getZone();
			result[i][2] = player.get(i).getSubZone();
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
			player = m.getSeasonHotTeam(ch);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "��ȡ���ݴ���");
			e1.printStackTrace();
		}
		
		getData(ch);
		
		table.updateUI();
	}

	private void getData(String ch) {
		if(ch.equals("�����÷�")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			
			model.setData(result, columnNames);
		}
		else if(ch.equals("��������")){
			
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);
		}
		else if(ch.equals("��������")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("������ñ")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result,columnNames); 
		}
		else if(ch.equals("��������")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("����������")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("Ͷ��������")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("����������")){
			String[] columnNames = { "�������", "��������","����������"};
			
			String[][] result = new String[5][columnNames.length];
			for(int i=0;i<player.size();i++){
				result[i][0] = player.get(i).getName();
				result[i][1] = player.get(i).getZone();
				result[i][2] = player.get(i).getSubZone();
			}
			model.setData(result, columnNames);  
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		int x = temp.getSelectedRow();
		teamDataAnalysis t = new teamDataAnalysis();
		String name = player.get(x).getShorts();
		ImageIcon image = new ImageIcon(player.get(x).getShorts()+".png");
			
		JPanel p4;
		try {
			p4 = t.go(name,image);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p4);
			HomePage.count++;
			HomePage.first.add(p4);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
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
		// TODO �Զ����ɵķ������
		
	}
	
}
