package matchui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import playerui.SinglePlayer;
import vo.MatchVO;
import vo.playerInMatchesVO;

public class SingleMatch implements MouseListener{
	JPanel p = new JPanel();
	JTable team1 = null;
	JTable team2 = null;
	DefaultTableModel model1 = null;
	DefaultTableModel model2 = null;
	
	String[] players = {"姓名","位置","上场时间"};
	String[][] first = null;
	String[][] second = null;
	JWindow info = new JWindow();

	public JPanel go(MatchVO matchVO, JLabel picture1, JLabel picture2) {
		p.setLayout(null);
		p.setSize(650,610);
		
		String score = matchVO.getScore();
		String[] sectionScore = matchVO.getSectionScore().split(";");
		
		JLabel sco = new JLabel(score);
		for(int i=0;i<sectionScore.length;i++){
			JLabel section = new JLabel(sectionScore[i]);
			section.setBounds(300,50+20*i,100,40);
			p.add(section);
		}
		
		sco.setFont(new Font("Dialog",1,19));
		picture1.setBounds(100,20,100,100);
		picture2.setBounds(420,20,100,100);
		sco.setBounds(285,0,100,100);
		
		first = matchVO.getPlayer1();
		second = matchVO.getPlayer2();
	
		
		model1 = new DefaultTableModel(first,players);
		model2 = new DefaultTableModel(second,players);
		
		team1 = new JTable(model1);
		team2 = new JTable(model2);
		team1.addMouseListener(this);
		team2.addMouseListener(this);
			
		
		JScrollPane jsp1 = new JScrollPane(team1);
		JScrollPane jsp2 = new JScrollPane(team2);
		
		team1.setOpaque(false);
		team2.setOpaque(false);
		jsp1.setBounds(0,140,280,350);
		jsp2.setBounds(350,140,280 ,350);
		
		p.add(picture2);
		p.add(picture1);
		p.add(jsp2);
		p.add(jsp1);
		p.add(sco);
		
		return p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		SinglePlayer s = new SinglePlayer();
		int x = temp.getSelectedRow();
		if(e.getSource()==team1){
			info = s.go(first[x]);
		}
		else{
			info = s.go(second[x]);
		}
		
		info.setLocation(e.getLocationOnScreen());
		info.setVisible(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		info.setVisible(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}
		

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
