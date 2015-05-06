package matchui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableCellRenderer;
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
	MyTableModel model = null;
	MyTableModel model2 = null;
	
	String[] players = {"球员","位置","上场时间"};
	Object[][] first = null;
	Object[][] second = null;
	JWindow info = new JWindow();
	
	String[][] f = null;
	String[][] se = null;

	public JPanel go(MatchVO matchVO, JLabel picture1, JLabel picture2) {
		p.setLayout(null);
		p.setSize(650,610);
		
		f = matchVO.getPlayer1();
		se = matchVO.getPlayer2();
		
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
		
		first = new Object[matchVO.getPlayer1().length][players.length];
		String[][] temp1 = matchVO.getPlayer1();
		for(int i=0;i<matchVO.getPlayer1().length;i++){
			first[i][0] =  new ImageIcon("./players/portrait/"+temp1[i][0]+".png");
			first[i][1] = temp1[i][1];
			first[i][2] = temp1[i][2];
		}
		
		second = new Object[matchVO.getPlayer2().length][players.length];
		String[][] temp2 = matchVO.getPlayer2();
		for(int i=0;i<matchVO.getPlayer2().length;i++){
			second[i][0] =  new ImageIcon("./players/portrait/"+temp2[i][0]+".png");
			second[i][1] = temp2[i][1];
			second[i][2] = temp2[i][2];
		}
		
		model = new MyTableModel(first,players);
		model2 = new MyTableModel(second,players);
		
		team1 = new JTable(model);
		team2 = new JTable(model2);
		team1.addMouseListener(this);
		team2.addMouseListener(this);
		
		team1.setRowHeight(100);
		team2.setRowHeight(100);
		
		TableColumn column = team1.getColumnModel().getColumn(0);
		column.setMaxWidth(130);
		column.setMinWidth(130);
		
		column = team2.getColumnModel().getColumn(0);
		column.setMaxWidth(130);
		column.setMinWidth(130);		
		
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
			info = s.go(f[x]);
		}
		else{
			info = s.go(se[x]);
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

	class MyTableModel extends DefaultTableModel{
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			}
			         //important here
		public Class<?> getColumnClass(int col) {
			Vector<?> v = (Vector<?>) dataVector.elementAt(0);
			return v.elementAt(col).getClass();
			}
	}
}
