package playerui;

import javax.swing.*;

public class SinglePlayer {
	public JWindow go(String[] s){
		JWindow w = new JWindow();
		w.setLayout(null);
		
		ImageIcon icon = new ImageIcon(".\\players\\portrait\\"+s[0]+".png");
		JLabel l = new JLabel(icon);
		JLabel name = new JLabel(s[0]);
		
		JLabel k = new JLabel("Ͷ��������:   "+s[3]);
		JLabel k2 = new JLabel("Ͷ��������:   "+s[4]);
		JLabel k3 = new JLabel("����������:   "+s[5]);
		JLabel k4 = new JLabel("����������:   "+s[6]);
		JLabel k5 = new JLabel("����������:   "+s[7]);
		JLabel k6 = new JLabel("���������:   "+s[8]);
		JLabel k7 = new JLabel("����������:   "+s[9]);
		JLabel k8 = new JLabel("����������:   "+s[10]);
		JLabel k9 = new JLabel("��������:   "+s[11]);
		
		JLabel k10 = new JLabel("������:   "+s[12]);
		JLabel k11 = new JLabel("������:   "+s[13]);
		JLabel k12 = new JLabel("��ñ��:   "+s[14]);
		JLabel k13 = new JLabel("ʧ����:   "+s[15]);
		JLabel k14 = new JLabel("������:   "+s[16]);
		JLabel k15 = new JLabel("���˵÷�:   "+s[17]);
		
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
