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
import teamui.team;

public class HomePage implements TreeSelectionListener{
	JFrame f = new JFrame("NBA���ݲ�ѯƽ̨");
	String[] operation = {"��Ӳ�ѯ","��Ա��ѯ","������ѯ","�ȵ�"};
	public static JPanel first = new JPanel();           //��һ��panel
	JPanel subp = new JPanel();			  //�ڶ���panel
	public static int count = 0;          //�����Ӧ����һ��
	JTree tree = null;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("NBA���ݲ�ѯƽ̨");
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
		first.add(subp);
		screen.add(subp);
		count++;
		first.setSize(675,610);
		subp.setSize(675,610);
		
		initTree();
		
		back.setBounds(0,0,20,20);
		front.setBounds(40,0,20,20);
		buttonP.add(back);
		buttonP.add(front);
			
		f.getContentPane().add(BorderLayout.CENTER,first);
		f.getContentPane().add(BorderLayout.NORTH,buttonP);
		f.getContentPane().add(BorderLayout.WEST,tree);
		f.setSize(800,650);
		f.setLocation(300,60);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initTree() {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("������ѯ");
		root.add(node);
		node = new DefaultMutableTreeNode("��Ӳ�ѯ");
		root.add(node);
		node = new DefaultMutableTreeNode("��Ա��ѯ");
		root.add(node);
		node = new DefaultMutableTreeNode("�ȵ��ѯ");
		root.add(node);
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�����ȵ���Ա");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("�����ȵ���Ա");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("�����ȵ����");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("���������Ա");
		node.add(node1);
		
		tree = new JTree(root);
		tree.setOpaque(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
	}

	class myModel extends DefaultListModel{
		public myModel(){
			for(int i=0;i<operation.length;i++){
				this.addElement(operation[i]);
			}
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		JTree temp = (JTree) e.getSource();
		DefaultMutableTreeNode selection = 
			(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		String nodeName = selection.toString();
		if(selection.isLeaf()){
			if(nodeName.equals("������ѯ")){
				match c = new match();
				JPanel result = c.go();
				screen.get(count-1).setVisible(false);
				screen.add(result);
				count++;
				first.add(result);
			}
			else if(nodeName.equals("��Ӳ�ѯ")){
				team c = new team();
				JPanel result = c.go();
				screen.get(count-1).setVisible(false);
				screen.add(result);
				count++;
				first.add(result);
			}
			else if(nodeName.equals("��Ա��ѯ")){
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
			else if(nodeName.equals("�����ȵ���Ա")){
			/*	TodayHotPlayer h = new TodayHotPlayer();
				JPanel result;
				try{
					result = h.go();
					screen.get(count-1).setVisible(false);
					screen.add(result);
					count++;
					first.add(result);
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}*/
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
}
