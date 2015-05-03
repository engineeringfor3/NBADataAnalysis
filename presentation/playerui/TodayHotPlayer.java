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
	String[] choose = {"�÷�","����","����","��ñ","����"};
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
		JButton b = new JButton("ȷ��");
		choice.setBounds(10,20,70,30);
		b.setBounds(550, 20, 70, 30);
		b.addActionListener(this);
		
		try {
			player = m.getHotPlayer("�÷�");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "��ȡ��Ϣ����");
			e.printStackTrace();
		}
		
		String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
		
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
			JOptionPane.showMessageDialog(null, "��ȡ���ݴ���");
			e1.printStackTrace();
		}
		
		getData(ch);
		info = null;
		
		table.updateUI();
	}

	private void getData(String ch) {
		if(ch.equals("�÷�")){
			String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "�÷�:"+player[i][17];
			}
			model.setData(result, columnNames);
			model.getColumnName(3);
		}
		else if(ch.equals("����")){
			String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "����:"+player[i][11];
			}
			model.setData(result, columnNames);
			model.getColumnName(3);
		}
		else if(ch.equals("����")){
			String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "����:"+player[i][12];
			}
			model.setData(result, columnNames);  
		}
		else if(ch.equals("��ñ")){
			String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "��ñ:"+player[i][14];
			}
			model.setData(result,columnNames); 
		}
		else {
			String[] columnNames = { "��Ա����", "�������","��Աλ��","ɸѡ����"};
			
			String[][] result = new String[5][4];
			for(int i=0;i<player.length;i++){
				result[i][0] = player[i][0];
				result[i][1] = player[i][1];
				result[i][2] = player[i][1];
				result[i][3] = "����:"+player[i][13];
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	
}



