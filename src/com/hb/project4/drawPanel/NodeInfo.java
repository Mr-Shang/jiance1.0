package com.hb.project4.drawPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NodeInfo extends JDialog {
	public NodeInfo(JFrame arg0) {
		super(arg0,"节点信息设置",true);
		// TODO Auto-generated constructor stub
		initDialog();	
		this.setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setLocation( (width - WIDTH) / 2, (height - HEIGHT) / 2);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 400;
	static final int HEIGHT= 150;
	private boolean ret = false; 
	
	JTextField sbt = new JTextField(20);	
	JTextField hlt = new JTextField(20);
	JTextField jdt = new JTextField(20);
	
	public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridheight = h;
		constraints.gridwidth = w;
		add(c, constraints);
	}
	
	boolean retState() {
		return ret;
	}
	
	String getSb() {
		return sbt.getText();
	}
	
	String getHl() {
		return hlt.getText();
	}
	
	String getJd() {
		return jdt.getText();
	}

	void setSb(String sb) {
		sbt.setText(sb);
	}
	
	void setHl(String hl) {
		hlt.setText(hl);
	}
	
	void setJd(String jd) {
		jdt.setText(jd);
	}
	
	void initDialog() {
		
		GridBagLayout lay = new GridBagLayout();
		setLayout(lay);		
		
		JButton ok = new JButton("保存");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//
				ret = true;
				dispose();				
			}
		});
		
		JButton cancel = new JButton("放弃");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();			
			}			
		});
		
		JLabel sb = new JLabel("设备编号");
		JLabel hl = new JLabel("回路编号");
		JLabel jd = new JLabel("节点编号");		
		
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.NONE;
		cs.anchor = GridBagConstraints.CENTER;
		cs.weightx = 4;
		cs.weighty = 6;
		JLabel lss = new JLabel("    ");

		add(lss, cs, 0,0,2,1);		
		add(lss, cs, 2,0,2,1);

//		add(lss, cs, 0,1,2,1);		
//		add(lss, cs, 2,1,2,1);
		
		add(sb, cs, 0,2,2,1);		
		add(sbt, cs, 2,2,2,1);
		
		add(hl, cs, 0,3,2,1);
		add(hlt, cs, 2,3,2,1);

		add(jd, cs, 0,4,2,1);
		add(jdt, cs, 2,4,2,1);

		add(lss, cs, 0,5,2,1);		
		add(lss, cs, 2,5,2,1);
		
		add(ok, cs, 0,6,2,1);
		add(cancel, cs, 2,6,2,1);				
	}
	
}
