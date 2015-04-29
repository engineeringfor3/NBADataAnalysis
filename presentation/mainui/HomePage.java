package mainui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import matchui.match;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import playerui.Member;
import playerui.TodayHotPlayer;
import teamui.team;

public class HomePage implements ListSelectionListener{
	JFrame f = new JFrame("NBA���ݲ�ѯƽ̨");
	String[] operation = {"��Ӳ�ѯ","��Ա��ѯ","������ѯ","�����ȵ���Ա","�����ȵ���Ա","�����ȵ����","���������Ա"};
	public static JPanel first = new JPanel();           //��һ��panel
	JPanel subp = new JPanel();			  //�ڶ���panel
	public static int count = 0;          //�����Ӧ����һ��
	
	JList list = null;
	DefaultListModel model = null;
	
	public static ArrayList<JPanel> screen = new ArrayList<JPanel>();     //������е�JPanel
	JPanel buttonP = new JPanel();
	JButton back = new JButton("����");
	JButton front = new JButton("ǰ��");
		
	public HomePage(){
		f.setLayout(new BorderLayout());
		first.setLayout(null);
		first.setOpaque(false);
		subp.setLayout(null);
		subp.setOpaque(false);
		buttonP.setOpaque(false);
		buttonP.setLayout(new FlowLayout());
		first.add(subp);
		screen.add(subp);
		count++;
		first.setSize(675,610);
		subp.setSize(675,610);
		
		initList();
		
		buttonP.add(new JLabel(new ImageIcon("NBA.png")));	
		buttonP.add(new JLabel("                                                                                    "));	
		buttonP.add(back);
		buttonP.add(front);
		
		back.setBounds(0, 0, 100, 50);
		
			
		f.getContentPane().add(BorderLayout.CENTER,first);
		f.getContentPane().add(BorderLayout.NORTH,buttonP);
		f.getContentPane().add(BorderLayout.WEST,list);
		f.setSize(800,650);
		f.setLocation(300,20);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initList() {
		list = new JList(operation);
		list.setOpaque(false);
		list.addListSelectionListener(this);
		list.setFont(new Font("����",Font.PLAIN,15));
		list.setFixedCellHeight(70);
		
	}

	class myModel extends DefaultListModel{
		public myModel(){
			for(int i=0;i<operation.length;i++){
				this.addElement(operation[i]);
			}
		}
	}
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);  
	    JDialog.setDefaultLookAndFeelDecorated(true);  
	    
	    SwingUtilities.invokeLater(new Runnable() {  
	        public void run() {  
	        	try {  
	        			SubstanceImageWatermark watermark  =   new  SubstanceImageWatermark("cba.jpg");
	        			watermark.setKind(ImageWatermarkKind.SCREEN_CENTER_SCALE);
	        			SubstanceSkin skin  =   new  RavenGraphiteGlassSkin().withWatermark(watermark);   //��ʼ����ˮӡ��Ƥ��

	        			UIManager.setLookAndFeel(new  SubstanceRavenGraphiteGlassLookAndFeel());
	                 	SubstanceLookAndFeel.setSkin(skin);  //����Ƥ�� 
	        	}catch (Exception e) {  
	        		JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");  
	        	}
	        	new HomePage();
	        }
	    });
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() == 2){
			match c = new match();
			JPanel result = c.go();
			screen.get(count-1).setVisible(false);
			screen.add(result);
			count++;
			first.add(result);
		}
		else if(list.getSelectedIndex() == 0){
			team c = new team();
			JPanel result = c.go();
			screen.get(count-1).setVisible(false);
			screen.add(result);
			count++;
			first.add(result);
		}
		else if(list.getSelectedIndex() == 1){
			Member m = new Member();
			JPanel result;
			try {
				result = m.go();
				screen.get(count-1).setVisible(false);
				screen.add(result);
				count++;
				first.add(result);
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		else if(list.getSelectedIndex() == 3){
			TodayHotPlayer h = new TodayHotPlayer();
			JPanel result;
			result = h.go();
			screen.get(count-1).setVisible(false);
			screen.add(result);
			count++;
			first.add(result);
		}
	}
}
