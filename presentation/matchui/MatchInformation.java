package matchui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import vo.MatchVO;
import mainui.HomePage;
import matchbl.MatchLogic;

public class MatchInformation {
	JPanel p = new JPanel();
	JTable table = null;
	DefaultTableModel model = null;
	JLabel picture1 = null;
	JLabel picture2 = null; 
	ArrayList<MatchVO> list = null;
	
	String[] match = {"比赛日期","比赛队伍","总比分","小节比分"};
	
	public JPanel go(String team1,String team2,Icon image1,Icon image2) throws IOException{
		p.setLayout(null);
		p.setSize(650,610);
		
		MatchLogic m = new MatchLogic();
		list = m.getMatchesByTeam(team1, team2);
		String[][] data = new String[list.size()][4];
		for(int i=0;i<data.length;i++){
			data[i][0] = list.get(i).getDate();
			data[i][1] = list.get(i).getTeam1()+"-"+list.get(i).getTeam2();
			data[i][2] = list.get(i).getScore();
			data[i][3] = list.get(i).getSectionScore();
		}
		
		model = new DefaultTableModel(data,match);
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new SelectListener());
		
		JScrollPane jsp = new JScrollPane(table);
		
		jsp.setBounds(0,210,655,350);
		table.setOpaque(false);
		
		picture1 = new JLabel(image1);
		picture2 = new JLabel(image2);
		JLabel vs = new JLabel(new ImageIcon("sv.png"));
		
		picture1.setBounds(100,30,100,100);
		picture2.setBounds(420,30,100,100);
		vs.setBounds(240,0,150,150);
		
		for(int k=0;k<3;k++){
			TableColumn column = table.getColumnModel().getColumn(k);
			column.setMaxWidth(120);
			column.setMinWidth(120);
		}
		
		p.add(jsp);
		p.add(picture1);
		p.add(picture2);
		p.add(vs);
		return p;
	}
	
	class SelectListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			JTable temp = (JTable) e.getSource();
			SingleMatch m = new SingleMatch();
				
			int x = temp.getSelectedRow();
			JPanel p4 = m.go(list.get(x),picture1,picture2);
			HomePage.screen.get(HomePage.count-1).setVisible(false);
			HomePage.screen.add(p4);
			HomePage.count++;
			HomePage.first.add(p4);
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
