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
	JFrame f = new JFrame("NBA数据查询平台");
	String[] operation = {"球队查询","球员查询","比赛查询","热点"};
	public static JPanel first = new JPanel();           //第一层panel
	JPanel subp = new JPanel();			  //第二层panel
	public static int count = 0;          //获得相应的哪一层
	JTree tree = null;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("NBA数据查询平台");
	public static ArrayList<JPanel> screen = new ArrayList<JPanel>();     //存放所有的JPanel
	JPanel buttonP = new JPanel();
	JButton back = new JButton("返回");
	JButton front = new JButton("前进");
		
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("比赛查询");
		root.add(node);
		node = new DefaultMutableTreeNode("球队查询");
		root.add(node);
		node = new DefaultMutableTreeNode("球员查询");
		root.add(node);
		node = new DefaultMutableTreeNode("热点查询");
		root.add(node);
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("当天热点球员");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("赛季热点球员");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("赛季热点球队");
		node.add(node1);
		node1 = new DefaultMutableTreeNode("进步最快球员");
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
			if(nodeName.equals("比赛查询")){
				match c = new match();
				JPanel result = c.go();
				screen.get(count-1).setVisible(false);
				screen.add(result);
				count++;
				first.add(result);
			}
			else if(nodeName.equals("球队查询")){
				team c = new team();
				JPanel result = c.go();
				screen.get(count-1).setVisible(false);
				screen.add(result);
				count++;
				first.add(result);
			}
			else if(nodeName.equals("球员查询")){
				Member m = new Member();
				JPanel result;
				try {
					result = m.go();
					screen.get(count-1).setVisible(false);
					screen.add(result);
					count++;
					first.add(result);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			else if(nodeName.equals("当天热点球员")){
			/*	TodayHotPlayer h = new TodayHotPlayer();
				JPanel result;
				try{
					result = h.go();
					screen.get(count-1).setVisible(false);
					screen.add(result);
					count++;
					first.add(result);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
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
	        			SubstanceSkin skin  =   new  RavenGraphiteGlassSkin().withWatermark(watermark);   //初始化有水印的皮肤

	        			UIManager.setLookAndFeel(new  SubstanceRavenGraphiteGlassLookAndFeel());
	                 	SubstanceLookAndFeel.setSkin(skin);  //设置皮肤 
	        	}catch (Exception e) {  
	        		JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");  
	        	}
	        	new HomePage();
	        }
	    });
	}
}
