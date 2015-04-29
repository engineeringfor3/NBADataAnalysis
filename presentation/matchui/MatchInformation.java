package matchui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
		
		JLabel picture1 = new JLabel(image1);
		JLabel picture2 = new JLabel(image2);
		
		picture1.setBounds(100,20,100,100);
		picture2.setBounds(420,20,100,100);
		
		p.add(jsp);
		p.add(picture1);
		p.add(picture2);
		return p;
	}
	
	class SelectListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			if(table.getSelectedColumn() == 0){
				SingleMatch m = new SingleMatch();
				String temp = (String) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
				
				for(int i = 0;i<list.size();i++){
					if(list.get(i).getDate().equals(temp)){
						JPanel p4 = m.go(list.get(i),picture1,picture2);
						HomePage.screen.get(HomePage.count-1).setVisible(false);
						HomePage.screen.add(p4);
						HomePage.count++;
						HomePage.first.add(p4);
					}
				}
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
