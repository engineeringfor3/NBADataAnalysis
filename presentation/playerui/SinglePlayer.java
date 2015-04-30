package playerui;

import javax.swing.*;

public class SinglePlayer {
	public JWindow go(String[] s){
		JWindow w = new JWindow();
		w.setLayout(null);
		
		ImageIcon icon = new ImageIcon(".\\players\\portrait\\"+s[0]+".png");
		JLabel l = new JLabel(icon);
		JLabel name = new JLabel(s[0]);
		
		JLabel k = new JLabel("投篮命中数:   "+s[3]);
		JLabel k2 = new JLabel("投篮出手数:   "+s[4]);
		JLabel k3 = new JLabel("三分命中数:   "+s[5]);
		JLabel k4 = new JLabel("三分命中数:   "+s[6]);
		JLabel k5 = new JLabel("罚球命中数:   "+s[7]);
		JLabel k6 = new JLabel("罚球出手数:   "+s[8]);
		JLabel k7 = new JLabel("进攻篮板数:   "+s[9]);
		JLabel k8 = new JLabel("防守篮板数:   "+s[10]);
		JLabel k9 = new JLabel("总篮板数:   "+s[11]);
		
		JLabel k10 = new JLabel("助攻数:   "+s[12]);
		JLabel k11 = new JLabel("抢断数:   "+s[13]);
		JLabel k12 = new JLabel("盖帽数:   "+s[14]);
		JLabel k13 = new JLabel("失误数:   "+s[15]);
		JLabel k14 = new JLabel("犯规数:   "+s[16]);
		JLabel k15 = new JLabel("个人得分:   "+s[17]);
		
		l.setBounds(0, 0, 150, 200);
		k.setBounds(160, 0, 100, 10);
		k2.setBounds(160, 0, 100, 50);
		k3.setBounds(160, 0, 100, 90);
		k4.setBounds(160, 0, 100, 130);
		k5.setBounds(160, 0, 100, 170);
		k6.setBounds(160, 0, 100, 210);
		k7.setBounds(160, 0, 100, 250);
		k8.setBounds(160, 0, 100, 290);
		k9.setBounds(160, 0, 100, 330);
		k10.setBounds(160, 0, 100, 370);
		k11.setBounds(160, 0, 100, 410);
		k12.setBounds(160, 0, 100, 450);
		k13.setBounds(160, 0, 100, 490);
		k14.setBounds(160, 0, 100, 530);
		k15.setBounds(160,0,100,570);
		name.setBounds(40, 210, 200, 50);
		
		w.add(l);
		w.add(k);
		w.add(k2);
		w.add(k3);
		w.add(k4);
		w.add(k5);
		w.add(k6);
		w.add(k7);
		w.add(k8);
		w.add(k9);
		w.add(k10);
		w.add(k11);
		w.add(k12);
		w.add(k13);
		w.add(k14);
		w.add(k15);
		w.add(name);
		
		w.setSize(300,300);
		
		return w;
	}
}
