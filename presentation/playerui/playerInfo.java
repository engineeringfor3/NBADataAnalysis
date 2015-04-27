package playerui;

import javax.swing.*;

public class playerInfo {
	public JPanel go(String name){
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setSize(650,610);
		
		ImageIcon i = new ImageIcon(".\\players\\action\\"+name+".png");
		ImageIcon i2 = new ImageIcon(".\\players\\portrait\\"+name+".png");
		JLabel l = new JLabel(i);
		JLabel l2 = new JLabel(i2);
		
		l.setBounds(450,0,200,400);
		l2.setBounds(40,40,150,200);
		
		JLabel firstName = new JLabel("姓名:  ");
		JLabel number = new JLabel("球衣号码:   ");
		JLabel position = new JLabel("位置:   ");
		JLabel height = new JLabel("身高:    ");
		JLabel weight = new JLabel("体重:    ");
		JLabel birth = new JLabel("生日:   ");
		JLabel age = new JLabel("年龄:   ");
		JLabel exp = new JLabel("球龄:    ");
		JLabel school = new JLabel("毕业学校:   ");
		JLabel team = new JLabel("队伍:    ");
		
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
		
		return p;
	}
}
