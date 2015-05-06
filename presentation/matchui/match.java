package matchui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import mainui.HomePage;
import enumType.NBAteams;


public class match implements ActionListener,MouseListener{
	JLabel[] l = new JLabel[30];
	JLabel[] l2 = new JLabel[30];
	int location1 = 0;
	int location2 = 0;
	JButton a = new JButton("上一个");
	JButton b = new JButton("下一个");
	JButton d = new JButton("上一个");
	JButton e = new JButton("下一个");
	JLabel c = new JLabel(new ImageIcon("./picture/sv.png"));
	JPanel p2 = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p3 = new JPanel();
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
		
	public match(){
		p3.setLayout(null);
		p3.setSize(675, 610);
		a.addActionListener(this);
		b.addActionListener(this);
		c.addMouseListener(this);
		d.addActionListener(this);
		e.addActionListener(this);
		
		createLabel();
		p2.setLayout(new CardLayout());
		for(int j=location1;j<30;j++){
			p2.add(list.get(j),l[j]);
		}
		((CardLayout) p2.getLayout()).show(p2,list.get(0));
		a.setEnabled(false);
		
		p1.setLayout(new CardLayout());
		for(int j=location2;j<30;j++){
			p1.add(list2.get(j),l2[j]);
		}
		((CardLayout) p1.getLayout()).show(p1,list2.get(0));
		d.setEnabled(false);
			
		a.setBounds(100,70,80,50);
		b.setBounds(100,330,80,50);
		c.setBounds(210,170,200,112);
		p2.setBounds(90,180,100,100);
		d.setBounds(450,70,80,50);
		e.setBounds(450,330,80,50);
		p1.setBounds(440,180,100,100);
		p3.add(a);
		p3.add(c);
		p3.add(p2);
		p3.add(b);
		p3.add(e);
		p3.add(d);
		p3.add(p1);
	}

	private void createLabel() {
		NBAteams[] a = NBAteams.values();
		int i = 0;
		for(NBAteams temp:a){
			l[i] = new JLabel(new ImageIcon("./picture/"+temp.toString()+".png"));
			l2[i] = new JLabel(new ImageIcon("./picture/"+temp.toString()+".png"));
			list.add(temp.toString());
			list2.add(temp.toString());
			i = i+1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == a){
			b.setEnabled(true);
			location1 -= 1;
			((CardLayout) p2.getLayout()).show(p2,list.get(location1));
				
			if(location1 == 0)
				a.setEnabled(false);
		}
		else if(ev.getSource() == b){
			a.setEnabled(true);
			location1 += 1;
			((CardLayout) p2.getLayout()).show(p2,list.get(location1));
				
			if(location1 == 29)
				b.setEnabled(false);
		}
		else if(ev.getSource() == e){
			d.setEnabled(true);
			location2 += 1;
			((CardLayout) p1.getLayout()).show(p1,list2.get(location2));
				
			if(location2 == 29)
				e.setEnabled(false);
		}
		else if(ev.getSource() == d){
			e.setEnabled(true);
			location2 -= 1;
			((CardLayout) p1.getLayout()).show(p1,list2.get(location2));
				
			if(location2 == 0)
				d.setEnabled(false);
		}
	}
		
	public JPanel go(){
		new match();
		return p3;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MatchInformation m = new MatchInformation();
		String name1 = list.get(location1);
		String name2 = list.get(location2);
		if(name1.equals(name2)){
			JOptionPane.showMessageDialog(null,"两支球队不能相同");
		}
		else{
			Icon temp1 = new ImageIcon("./picture/"+name1+".png");
			Icon temp2 = new ImageIcon("./picture/"+name2+".png");
			JPanel result;
			if(name1.equals("NOP")){
				name1="NOH";
			}
			if(name2.equals("NOP")){
				name2="NOH";
			}
			try {
				result = m.go(name1,name2,temp1,temp2);
				HomePage.screen.get(HomePage.count-1).setVisible(false);
				HomePage.screen.add(result);
				HomePage.count++;
				HomePage.first.add(result);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"获取数据失败");
				e1.printStackTrace();
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
