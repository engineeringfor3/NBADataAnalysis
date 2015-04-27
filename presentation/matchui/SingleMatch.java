package matchui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.MatchVO;
import vo.playerInMatchesVO;

public class SingleMatch {
	JPanel p = new JPanel();
	JTable team1 = null;
	JTable team2 = null;
	DefaultTableModel model1 = null;
	DefaultTableModel model2 = null;
	
	String[] players = {"姓名","位置","上场时间","投篮命中数","投篮数","三分命中数","三分投篮数","罚球命中数",
			"罚球数","进攻篮板数","防守篮板数","篮板数","助攻数","抢断数","盖帽数","失误数","犯规数","得分"};

	public JPanel go(MatchVO matchVO, JLabel picture1, JLabel picture2) {
		p.setLayout(null);
		p.setSize(650,610);
		
		String score = matchVO.getScore();
		String[] sectionScore = matchVO.getSectionScore().split(";");
		
		JLabel sco = new JLabel(score);
		
		sco.setFont(new Font("Dialog",1,19));
		picture1.setBounds(100,20,100,100);
		picture2.setBounds(420,20,100,100);
		sco.setBounds(280,30,100,100);
		
		ArrayList<playerInMatchesVO> first = matchVO.getPlayer1();
		ArrayList<playerInMatchesVO> second = matchVO.getPlayer2();
		
		Object[][] data1 = new String[first.size()][players.length];
		Object[][] data2 = new String[second.size()][players.length];
		
		for(int i=0;i<data1.length;i++){
			data1[i][0] = first.get(i).getName();
			data1[i][1] = first.get(i).getPosition();
			data1[i][2] = first.get(i).getTime();
			data1[i][3] = first.get(i).getShotHits();
			data1[i][4] = first.get(i).getShot();
			data1[i][5] = first.get(i).getThreeShotHit();
			data1[i][6] = first.get(i).getThreeShot();
			data1[i][7] = first.get(i).getPenaltyShotHit();
			data1[i][8] = first.get(i).getPenaltyShot();
			data1[i][9] = first.get(i).getOffensiveRebound();
			data1[i][10] = first.get(i).getDefensiveRebound();
			data1[i][11] = first.get(i).getRebound();
			data1[i][12] = first.get(i).getAssist();
			data1[i][13] = first.get(i).getSteal();
			data1[i][14] = first.get(i).getBlockShot();
			data1[i][15] = first.get(i).getMistake();
			data1[i][16] = first.get(i).getFoul();
			data1[i][17] = first.get(i).getScore();
		}
		
		for(int i=0;i<data2.length;i++){
			data2[i][0] = second.get(i).getName();
			data2[i][1] = second.get(i).getPosition();
			data2[i][2] = second.get(i).getTime();
			data2[i][3] = second.get(i).getShotHits();
			data2[i][4] = second.get(i).getShot();
			data2[i][5] = second.get(i).getThreeShotHit();
			data2[i][6] = second.get(i).getThreeShot();
			data2[i][7] = second.get(i).getPenaltyShotHit();
			data2[i][8] = second.get(i).getPenaltyShot();
			data2[i][9] = second.get(i).getOffensiveRebound();
			data2[i][10] = second.get(i).getDefensiveRebound();
			data2[i][11] = second.get(i).getRebound();
			data2[i][12] = second.get(i).getAssist();
			data2[i][13] = second.get(i).getSteal();
			data2[i][14] = second.get(i).getBlockShot();
			data2[i][15] = second.get(i).getMistake();
			data2[i][16] = second.get(i).getFoul();
			data2[i][17] = second.get(i).getScore();
		}
		
		model1 = new DefaultTableModel(data1,players);
		model2 = new DefaultTableModel(data2,players);
		
		team1 = new JTable(model1);
		team2 = new JTable(model2);
		
		JScrollPane jsp1 = new JScrollPane(team1);
		JScrollPane jsp2 = new JScrollPane(team2);
		
		team1.setOpaque(false);
		team2.setOpaque(false);
		jsp1.setBounds(0,210,280,350);
		jsp2.setBounds(350,210,280 ,350);
		
		p.add(picture2);
		p.add(picture1);
		p.add(jsp2);
		p.add(jsp1);
		p.add(sco);
		
		return p;
	}

}
