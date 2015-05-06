package playerui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mainui.HomePage;
import matchui.SingleMatch;
import playerbl.MemberLogic;
import vo.MatchVO;
import vo.MemberVO;

public class playerAllData implements MouseListener{
	JTable table = null;
	String[] info = {"��������","����","�Ͷ�","�ܱȷ�","�ϳ�ʱ��","�÷�"};
	ArrayList<MatchVO> matchInfo = new ArrayList<MatchVO>();
	ArrayList<String[]> playerInfo = new ArrayList<String[]>();	
	
	public JPanel go(MemberVO player, String name) {
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setSize(650,610);
		
		ImageIcon i = new ImageIcon(".\\players\\action\\"+name+".png");
		ImageIcon i2 = new ImageIcon(".\\players\\portrait\\"+name+".png");
		JLabel l = new JLabel(i);
		JLabel l2 = new JLabel(i2);
		
		l.setBounds(450,0,200,400);
		l2.setBounds(40,40,150,200);
		
		JLabel firstName = new JLabel("����:  "+player.getName());
		JLabel number = new JLabel("���º���:   "+player.getNumber());
		JLabel position = new JLabel("λ��:   "+player.getPosition());
		JLabel height = new JLabel("���:    "+player.getHeight());
		JLabel weight = new JLabel("����:    "+player.getWeight());
		JLabel birth = new JLabel("����:   "+player.getBirth());
		JLabel age = new JLabel("����:   "+player.getAge());
		JLabel exp = new JLabel("����:    "+player.getExp());
		JLabel school = new JLabel("��ҵѧУ:   "+player.getSchool());
		JLabel team = new JLabel("����:    "+player.getTeam());
		
		matchInfo = player.getMatchList();
		playerInfo = player.getMatchInfo();
		System.out.println(matchInfo.size()+"  "+playerInfo.size());
		String[][] result = new String[matchInfo.size()][info.length];
		
		for(int j=0;j<matchInfo.size();j++){
			result[j][0] = matchInfo.get(j).getDate();
			result[j][1] = matchInfo.get(j).getTeam1();
			result[j][2] = matchInfo.get(j).getTeam2();
			result[j][3] = matchInfo.get(j).getScore();
			result[j][4] = playerInfo.get(j)[2];
			result[j][5] = playerInfo.get(j)[17];
		}
		
		DefaultTableModel model = new DefaultTableModel(result,info);
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		table.setOpaque(false);
		table.addMouseListener(this);
		
		firstName.setBounds(230, 50, 150, 50);
		number.setBounds(390, 50, 150, 50);
		position.setBounds(230, 70, 150, 50);
		height.setBounds(390, 70, 150, 50);
		weight.setBounds(230,90,150,50);
		birth.setBounds(390, 90, 150, 50);
		age.setBounds(230, 110, 150, 50);
		exp.setBounds(390, 110, 150, 50);
		school.setBounds(230,130,150,50);
		team.setBounds(390, 130, 150, 50);
		js.setBounds(40, 260, 400, 200);
		
		p.add(l);
		p.add(l2);
		p.add(firstName);
		p.add(number);
		p.add(position);
		p.add(height);
		p.add(weight);
		p.add(birth);
		p.add(age);
		p.add(exp);
		p.add(school);
		p.add(team);
		p.add(js);
		
		return p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable temp = (JTable) e.getSource();
		SingleMatch m = new SingleMatch();
		int x = temp.getSelectedRow();
		MatchVO vo = matchInfo.get(x);
		JLabel first = new JLabel(new ImageIcon(vo.getTeam1()+".png"));
		JLabel second = new JLabel(new ImageIcon(vo.getTeam2()+".png"));
		if(vo.getTeam1().equals("NOH")){
			first = new JLabel(new ImageIcon("NOP.png"));
		}
		if(vo.getTeam2().equals("NOH")){
			second = new JLabel(new ImageIcon("NOP.png"));
		}
		
		JPanel p = m.go(vo,first,second);
		HomePage.screen.get(HomePage.count-1).setVisible(false);
		HomePage.screen.add(p);
		HomePage.count++;
		HomePage.first.add(p);
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
