package teamui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import mainui.HomePage;
import enumType.NBAteams;


public class team implements ActionListener{
	JLabel[] l = new JLabel[30];
	int location = 0;
	JButton a = new JButton("上一个");
	JButton b = new JButton("下一个");
	JButton c = new JButton("确定");
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	ArrayList<String> list = new ArrayList<String>();
		
	public team(){
		p3.setLayout(null);
		p3.setSize(675, 610);
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		createLabel();
		p2.setLayout(new CardLayout());
		for(int j=location;j<30;j++){
			p2.add(list.get(j),l[j]);
		}
		((CardLayout) p2.getLayout()).show(p2,list.get(0));
		a.setEnabled(false);
			
		a.setBounds(100,120,80,50);
		b.setBounds(400,120,80,50);
		c.setBounds(250,250,80,50);
		p2.setBounds(240,100,100,100);
		p3.add(a);
		p3.add(c);
		p3.add(p2);
		p3.add(b);
	}

	private void createLabel() {
		NBAteams[] a = NBAteams.values();
		int i = 0;
		for(NBAteams temp:a){
			l[i] = new JLabel(new ImageIcon(temp.toString()+".png"));
			list.add(temp.toString());
			i = i+1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("上一个")){
			b.setEnabled(true);
			location -= 1;
			((CardLayout) p2.getLayout()).show(p2,list.get(location));
				
			if(location == 0)
				a.setEnabled(false);
		}
		else if(e.getActionCommand().equals("下一个")){
			a.setEnabled(true);
			location += 1;
			((CardLayout) p2.getLayout()).show(p2,list.get(location));
				
			if(location == 29)
				b.setEnabled(false);
		}
		else{
			String name = list.get(location);
			Icon temp = new ImageIcon(name+".png");
			teamDataAnalysis data = new teamDataAnalysis();
			try {
				JPanel p4 = data.go(name, temp);
				HomePage.screen.get(HomePage.count-1).setVisible(false);
				HomePage.screen.add(p4);
				HomePage.count++;
				HomePage.first.add(p4);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
		
	public JPanel go(){
		new team();
		return p3;
	}
}
