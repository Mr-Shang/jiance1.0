package com.hb.project4.drawPanel;

//import com.cloudgarden.layout.AnchorConstraint;
//import com.cloudgarden.layout.AnchorLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileFilter;

import com.hb.project4.shapes.SuperShape;
import com.hb.project4.tools.CatchTool;
import com.hb.project4.tools.CircleTool;
import com.hb.project4.tools.EllipseTool;
import com.hb.project4.tools.LineTool;
import com.hb.project4.tools.OpenTool;
import com.hb.project4.tools.PentacleTool;
import com.hb.project4.tools.PentagonTool;
import com.hb.project4.tools.RectangleTool;
import com.hb.project4.tools.SaveTool;
import com.hb.project4.tools.SquareTool;
import com.hb.project4.tools.TriangleTool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Vector;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class DrawJFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;

	private JToggleButton jToggleButton9;

	private JButton jButton2;

	private JPanel buttonContainerPanel;

	private JButton jButton3;

	private JButton jButton1;

	private ButtonGroup buttonGroup1;

	private JToggleButton jToggleButton8;

	private JToggleButton jToggleButton7;

	private JToggleButton jToggleButton6;

	private JToggleButton jToggleButton5;

	private JToggleButton jToggleButton4;

	private JLabel jLabel4;

	private JSeparator jSeparator1;

	private JLabel jLabel26;

	private JLabel jLabel25;

	private JLabel jLabel24;

	private JLabel jLabel23;

	private JMenu jMenu3;

	private JMenu jMenu2;

	private JLabel jLabel29;

	private JLabel jLabel28;

	private JLabel jLabel27;

	private JLabel jLabel22;

	private JLabel jLabel21;

	private JLabel jLabel20;

	private JLabel jLabel19;

	private JLabel jLabel18;

	private JLabel jLabel17;

	private JLabel jLabel16;

	private JLabel jLabel15;

	private JLabel jLabel14;

	private JLabel jLabel13;

	private JLabel jLabel12;

	private JLabel jLabel11;

	private JLabel jLabel10;

	private JLabel jLabel9;

	private JLabel jLabel8;

	private JLabel jLabel7;

	private JLabel jLabel6;

	private JLabel jLabel5;

	private JLabel jLabel3;

	private JLabel jLabel2;

	private JLabel jLabel1;

	private DrawJPanel drawPanel = new DrawJPanel();

	private JPanel jPanel2;

	private JMenu jMenu1;

	private JMenuBar jMenuBar1;

	private JToggleButton jToggleButton14;

	private JToggleButton jToggleButton3;

	private JToggleButton jToggleButton2;

	private Rectangle2D panelBounds;

	private int saveAgainFlag = 0;

	public static DrawJFrame inst;
	
	Vector<JLabel> allColor = new Vector<JLabel>();

	// 工具初始化
	LineTool lineTool = new LineTool(drawPanel);

	RectangleTool rectangleTool = new RectangleTool(drawPanel);

	TriangleTool triangleTool = new TriangleTool(drawPanel);

	EllipseTool ellipseTool = new EllipseTool(drawPanel);

	CircleTool circleTool = new CircleTool(drawPanel);

	PentagonTool pentagonTool = new PentagonTool(drawPanel);

	PentacleTool pentangleTool = new PentacleTool(drawPanel);

	SquareTool squareTool = new SquareTool(drawPanel);

	CatchTool catchTool = new CatchTool(drawPanel);

	OpenTool openTool = new OpenTool(drawPanel);

	SaveTool saveTool = new SaveTool(drawPanel);

	JFileChooser chooser = new JFileChooser();//文件路径选择器

	//文件过滤器
	FileFilter ff = new FileFilter() {
		public boolean accept(File pathname) {
			String tmp = pathname.getName().toLowerCase();
			if (tmp.endsWith(".crt")) {
				return true;
			}
			return false;
		}

		public String getDescription() {
			return ".crt";
		}
	};

	private File tempFile;
	
	private static final String title ="--消防CRT编辑器";
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 808;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		inst = new DrawJFrame();
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = kit.getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		inst.setLocation((int) ((screenWidth - WIDTH) / 2),
				(int) ((screenHeight - HEIGHT) / 2));// Frame居中显示
		inst.setTitle(title);
		inst.setVisible(true);
	}

	public DrawJFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setIconImage(new ImageIcon(getClass().getClassLoader()
					.getResource("icon/paintbox.JPG")).getImage());
			this.setTitle(title);
			this.setFocusTraversalKeysEnabled(false);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					rootWindowClosing(evt);
				}
			});
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();

					jMenuBar1.add(jMenu1);
					jMenu1.setText("文件(F)");
					jMenu1.setMnemonic('F');
					//jMenu1.setAccelerator(KeyStroke.getKeyStroke('F',java.awt.Event.CTRL_MASK));

					jMenu1.setHorizontalAlignment(SwingConstants.CENTER);
					jMenu1.setPreferredSize(new java.awt.Dimension(70, 20));
					JMenuItem createItem = new JMenuItem("新建(N)");
					createItem
							.setPreferredSize(new java.awt.Dimension(100, 20));
					jMenu1.add(createItem);
					createItem.setMnemonic('N');
					createItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							createItemActionPerformed(evt);
						}
					});
					JMenuItem openItem = new JMenuItem("打开(O)");
					openItem.setPreferredSize(new java.awt.Dimension(100, 20));
					jMenu1.add(openItem);
					openItem.setMnemonic('O');
					openItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							openItemActionPerformed(evt);
						}
					});
					jMenu1.addSeparator();
					JMenuItem saveItem = new JMenuItem("保存(S)");
					saveItem.setPreferredSize(new java.awt.Dimension(100, 20));
					jMenu1.add(saveItem);
					saveItem.setMnemonic('S');
					saveItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveItemActionPerformed(evt);
						}
					});
					JMenuItem saveOtherItem = new JMenuItem("另存为...");
					saveOtherItem.setPreferredSize(new java.awt.Dimension(100,
							20));
					jMenu1.add(saveOtherItem);
					saveOtherItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveOtherItemActionPerformed(evt);
						}
					});
					jMenu1.addSeparator();
					JMenuItem exitItem = new JMenuItem("退出");
					exitItem.setMnemonic('X');
					exitItem.setAccelerator(KeyStroke.getKeyStroke('X',java.awt.Event.CTRL_MASK));
					exitItem.setPreferredSize(new java.awt.Dimension(100,
							20));
					jMenu1.add(exitItem);
					exitItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							exitItemActionPerformed(evt);
						}
					});
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("操作(P)");
					jMenu2.setMnemonic('p');
					jMenu2.setHorizontalAlignment(SwingConstants.CENTER);
					jMenu2.setPreferredSize(new java.awt.Dimension(70, 20));
					JMenuItem undoItem = new JMenuItem("撤销(U)");
					undoItem.setPreferredSize(new java.awt.Dimension(100, 20));
					undoItem.setMnemonic('U');
					jMenu2.add(undoItem);
					undoItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
					JMenuItem redoItem = new JMenuItem("重复(R)");
					redoItem.setPreferredSize(new java.awt.Dimension(100, 20));
					jMenu2.add(redoItem);
					redoItem.setMnemonic('R');
					redoItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("节点(E)");
					jMenu3.setMnemonic('E');
					jMenu3.setHorizontalAlignment(SwingConstants.CENTER);
					jMenu3.setPreferredSize(new java.awt.Dimension(70, 20));
					JMenuItem setItem = new JMenuItem("设置(S)");
					setItem.setPreferredSize(new java.awt.Dimension(100, 20));
					setItem.setMnemonic('S');
					jMenu3.add(setItem);
					setItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							setItemActionPerformed(evt);
						}
					});
					JMenuItem clrItem = new JMenuItem("清除(C)");
					clrItem.setPreferredSize(new java.awt.Dimension(100, 20));
					jMenu3.add(clrItem);
					clrItem.setMnemonic('C');
					clrItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							clrItemActionPerformed(evt);
						}
					});
					
				}
			}
			{
				{
					buttonGroup1 = new ButtonGroup();
				}
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(0, -3, 791, 553);
				jPanel1.setLayout(null);
				jPanel1
						.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
							public void ancestorResized(HierarchyEvent evt) {
								jPanel1AncestorResized(evt);
							}
						});
				{
					jToggleButton2 = new JToggleButton();
					jPanel1.add(jToggleButton2);
					jToggleButton2.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/抓取.JPG")));
					buttonGroup1.add(jToggleButton2);
					jToggleButton2.setSelected(true);
					jToggleButton2.setPreferredSize(new java.awt.Dimension(34,
							35));
					jToggleButton2.setBounds(7, 42, 34, 35);
					jToggleButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton2ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton3 = new JToggleButton();
					jPanel1.add(jToggleButton3);
					jToggleButton3.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/直线.JPG")));
					buttonGroup1.add(jToggleButton3);
					jToggleButton3.setPreferredSize(new java.awt.Dimension(34,
							35));
					jToggleButton3.setBounds(7, 77, 34, 35);
					jToggleButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton3ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton4 = new JToggleButton();
					jPanel1.add(jToggleButton4);
					jToggleButton4.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/矩形.JPG")));
					buttonGroup1.add(jToggleButton4);
					jToggleButton4.setBounds(7, 147, 35, 35);
					jToggleButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton4ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton5 = new JToggleButton();
					jPanel1.add(jToggleButton5);
					jToggleButton5.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/椭圆.JPG")));
					buttonGroup1.add(jToggleButton5);
					jToggleButton5.setPreferredSize(new java.awt.Dimension(34,
							35));
					jToggleButton5.setBounds(7, 112, 34, 35);
					jToggleButton5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton5ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton6 = new JToggleButton();
					jPanel1.add(jToggleButton6);
					jToggleButton6.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/圆.JPG")));
					buttonGroup1.add(jToggleButton6);
					jToggleButton6.setPreferredSize(new java.awt.Dimension(35,
							35));
					jToggleButton6.setBounds(41, 112, 34, 35);
					jToggleButton6.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton6ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton7 = new JToggleButton();
					jPanel1.add(jToggleButton7);
					jToggleButton7.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/正三角形.JPG")));
					buttonGroup1.add(jToggleButton7);
					jToggleButton7.setPreferredSize(new java.awt.Dimension(35,
							35));
					jToggleButton7.setBounds(41, 77, 34, 35);
					jToggleButton7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton7ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton8 = new JToggleButton();
					jPanel1.add(jToggleButton8);
					jToggleButton8.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/五边形.JPG")));
					buttonGroup1.add(jToggleButton8);
					jToggleButton8.setPreferredSize(new java.awt.Dimension(35,
							35));
					jToggleButton8.setBounds(41, 182, 34, 35);
					jToggleButton8.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton8ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton9 = new JToggleButton();
					jPanel1.add(jToggleButton9);
					jToggleButton9.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/五角星.JPG")));
					buttonGroup1.add(jToggleButton9);
					jToggleButton9.setPreferredSize(new java.awt.Dimension(34,
							35));
					jToggleButton9.setBounds(7, 182, 34, 35);
					jToggleButton9.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton9ActionPerformed(evt);
						}
					});
				}
				{
					jToggleButton14 = new JToggleButton();
					jPanel1.add(jToggleButton14);
					jToggleButton14.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("icon/正方形.JPG")));
					buttonGroup1.add(jToggleButton14);
					jToggleButton14.setBounds(42, 147, 35, 35);
					jToggleButton14.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jToggleButton14ActionPerformed(evt);
						}
					});
				}
				{
					jPanel2 = new JPanel();// 画布
					jPanel1.add(jPanel2);
					jPanel2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
							null, null, null, null));
					jPanel2.setBackground(new java.awt.Color(145, 145, 145));
					jPanel2.setLayout(null);
					jPanel2.setPreferredSize(new java.awt.Dimension(700, 427));
					jPanel2.setBounds(83, 7, 700, 427);
					{
						jPanel2.add(drawPanel);
						this.drawPanel.setCurrentTool(catchTool);
						this.drawPanel.sss = catchTool;
						drawPanel.setBounds(7, 7, 686, 413);
						drawPanel.setBackground(new java.awt.Color(255, 255,
								255));
						drawPanel.setPanelBounds(drawPanel.getBounds());
					}
				}
				{
					jSeparator1 = new JSeparator();
					jPanel1.add(jSeparator1);
					jSeparator1
							.setPreferredSize(new java.awt.Dimension(776, 28));
					jSeparator1.setBounds(7, 441, 776, 28);
				}
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("icon/撒消.JPG")));
					jButton1.setPreferredSize(new java.awt.Dimension(34, 35));
					jButton1.setBounds(7, 7, 34, 35);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					jPanel1.add(jButton2);
					jButton2.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("icon/重做.JPG")));
					jButton2.setPreferredSize(new java.awt.Dimension(35, 35));
					jButton2.setBounds(41, 7, 34, 35);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
				{
					jButton3 = new JButton();
					jPanel1.add(jButton3);
					jButton3.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("icon/删除.JPG")));
					jButton3.setPreferredSize(new java.awt.Dimension(35, 35));
					jButton3.setBounds(41, 42, 34, 35);
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton3ActionPerformed(evt);
						}
					});
				}
				{
					buttonContainerPanel = new JPanel();
					jPanel1.add(buttonContainerPanel);
					buttonContainerPanel.setBounds(0, 462, 539, 77);
					buttonContainerPanel.setLayout(null);
					buttonContainerPanel.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							buttonContainerPanelMouseClicked(evt);
						}
					});
					{
						jLabel1 = new JLabel();
						buttonContainerPanel.add(jLabel1);
						jLabel1.setBackground(new java.awt.Color(64, 0, 64));
						jLabel1.setOpaque(true);
						jLabel1.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel1.setBounds(84, 7, 28, 28);
						allColor.add(jLabel1);
					}
					{
						jLabel2 = new JLabel();
						buttonContainerPanel.add(jLabel2);
						jLabel2
								.setBackground(new java.awt.Color(128, 128, 128));
						jLabel2.setOpaque(true);
						jLabel2.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel2.setBounds(119, 7, 28, 28);
						allColor.add(jLabel2);
					}
					{
						jLabel3 = new JLabel();
						buttonContainerPanel.add(jLabel3);
						jLabel3.setBackground(new java.awt.Color(128, 0, 0));
						jLabel3.setOpaque(true);
						jLabel3.setBounds(154, 7, 28, 28);
						allColor.add(jLabel3);
					}
					{
						jLabel4 = new JLabel();
						buttonContainerPanel.add(jLabel4);
						jLabel4.setBackground(new java.awt.Color(128, 128, 64));
						jLabel4.setOpaque(true);
						jLabel4.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel4.setBounds(189, 7, 28, 28);
						allColor.add(jLabel4);
					}
					{
						jLabel5 = new JLabel();
						buttonContainerPanel.add(jLabel5);
						jLabel5.setBackground(new java.awt.Color(0, 128, 64));
						jLabel5.setOpaque(true);
						jLabel5.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel5.setBounds(224, 7, 28, 28);
						allColor.add(jLabel5);
					}
					{
						jLabel6 = new JLabel();
						buttonContainerPanel.add(jLabel6);
						jLabel6.setBackground(new java.awt.Color(0, 128, 128));
						jLabel6.setOpaque(true);
						jLabel6.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel6.setBounds(259, 7, 28, 28);
						allColor.add(jLabel6);
					}
					{
						jLabel7 = new JLabel();
						buttonContainerPanel.add(jLabel7);
						jLabel7.setBackground(new java.awt.Color(0, 64, 128));
						jLabel7.setOpaque(true);
						jLabel7.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel7.setBounds(294, 7, 28, 28);
						allColor.add(jLabel7);
					}
					{
						jLabel8 = new JLabel();
						buttonContainerPanel.add(jLabel8);
						jLabel8.setBackground(new java.awt.Color(128, 0, 128));
						jLabel8.setOpaque(true);
						jLabel8.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel8.setBounds(329, 7, 28, 28);
						allColor.add(jLabel8);
					}
					{
						jLabel9 = new JLabel();
						buttonContainerPanel.add(jLabel9);
						jLabel9.setBackground(new java.awt.Color(64, 128, 128));
						jLabel9.setOpaque(true);
						jLabel9.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel9.setBounds(364, 7, 28, 28);
						allColor.add(jLabel9);
					}
					{
						jLabel10 = new JLabel();
						buttonContainerPanel.add(jLabel10);
						jLabel10.setBackground(new java.awt.Color(0, 0, 255));
						jLabel10.setOpaque(true);
						jLabel10.setEnabled(false);
						jLabel10.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel10.setBounds(399, 7, 28, 28);
						allColor.add(jLabel10);
					}
					{
						jLabel11 = new JLabel();
						buttonContainerPanel.add(jLabel11);
						jLabel11.setBackground(new java.awt.Color(0, 64, 128));
						jLabel11.setOpaque(true);
						jLabel11.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel11.setBounds(434, 7, 28, 28);
						allColor.add(jLabel11);
					}
					{
						jLabel12 = new JLabel();
						buttonContainerPanel.add(jLabel12);
						jLabel12
								.setBackground(new java.awt.Color(128, 128, 255));
						jLabel12.setOpaque(true);
						jLabel12.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel12.setBounds(469, 7, 28, 28);
						allColor.add(jLabel12);
					}
					{
						jLabel13 = new JLabel();
						buttonContainerPanel.add(jLabel13);
						jLabel13.setBackground(new java.awt.Color(128, 64, 64));
						jLabel13.setOpaque(true);
						jLabel13.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel13.setBounds(504, 7, 28, 28);
						allColor.add(jLabel13);
					}
					{
						jLabel14 = new JLabel();
						buttonContainerPanel.add(jLabel14);
						jLabel14.setBackground(new java.awt.Color(0, 0, 0));
						jLabel14.setOpaque(true);
						jLabel14.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel14.setBounds(84, 42, 28, 28);
						allColor.add(jLabel14);
					}
					{
						jLabel15 = new JLabel();
						buttonContainerPanel.add(jLabel15);
						jLabel15
								.setBackground(new java.awt.Color(192, 192, 192));
						jLabel15.setOpaque(true);
						jLabel15.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel15.setBounds(119, 42, 28, 28);
						allColor.add(jLabel15);
					}
					{
						jLabel16 = new JLabel();
						buttonContainerPanel.add(jLabel16);
						jLabel16.setBackground(new java.awt.Color(255, 0, 0));
						jLabel16.setOpaque(true);
						jLabel16.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel16.setBounds(154, 42, 28, 28);
						allColor.add(jLabel16);
					}
					{
						jLabel17 = new JLabel();
						buttonContainerPanel.add(jLabel17);
						jLabel17.setBackground(new java.awt.Color(255, 255, 0));
						jLabel17.setOpaque(true);
						jLabel17.setEnabled(false);
						jLabel17.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel17.setBounds(189, 42, 28, 28);
						allColor.add(jLabel17);
					}
					{
						jLabel18 = new JLabel();
						buttonContainerPanel.add(jLabel18);
						jLabel18.setBackground(new java.awt.Color(0, 255, 0));
						jLabel18.setOpaque(true);
						jLabel18.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel18.setBounds(224, 42, 28, 28);
						allColor.add(jLabel18);
					}
					{
						jLabel19 = new JLabel();
						buttonContainerPanel.add(jLabel19);
						jLabel19.setBackground(new java.awt.Color(0, 255, 255));
						jLabel19.setOpaque(true);
						jLabel19.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel19.setBounds(259, 42, 28, 28);
						allColor.add(jLabel19);
					}
					{
						jLabel20 = new JLabel();
						buttonContainerPanel.add(jLabel20);
						jLabel20.setBackground(new java.awt.Color(0, 0, 255));
						jLabel20.setOpaque(true);
						jLabel20.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel20.setBounds(294, 42, 28, 28);
						allColor.add(jLabel20);
					}
					{
						jLabel21 = new JLabel();
						buttonContainerPanel.add(jLabel21);
						jLabel21.setBackground(new java.awt.Color(255, 0, 255));
						jLabel21.setOpaque(true);
						jLabel21.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel21.setBounds(329, 42, 28, 28);
						allColor.add(jLabel21);
					}
					{
						jLabel22 = new JLabel();
						buttonContainerPanel.add(jLabel22);
						jLabel22
								.setBackground(new java.awt.Color(255, 255, 128));
						jLabel22.setOpaque(true);
						jLabel22.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel22.setBounds(364, 42, 28, 28);
						allColor.add(jLabel22);
					}
					{
						jLabel23 = new JLabel();
						buttonContainerPanel.add(jLabel23);
						jLabel23.setBackground(new java.awt.Color(0, 255, 128));
						jLabel23.setOpaque(true);
						jLabel23.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel23.setBounds(399, 42, 28, 28);
						allColor.add(jLabel23);
					}
					{
						jLabel24 = new JLabel();
						buttonContainerPanel.add(jLabel24);
						jLabel24
								.setBackground(new java.awt.Color(128, 255, 255));
						jLabel24.setOpaque(true);
						jLabel24.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel24.setBounds(434, 42, 28, 28);
						allColor.add(jLabel24);
					}
					{
						jLabel25 = new JLabel();
						buttonContainerPanel.add(jLabel25);
						jLabel25
								.setBackground(new java.awt.Color(128, 128, 255));
						jLabel25.setOpaque(true);
						jLabel25.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel25.setBounds(469, 42, 28, 28);
						allColor.add(jLabel25);
					}
					{
						jLabel26 = new JLabel();
						buttonContainerPanel.add(jLabel26);
						jLabel26
								.setBackground(new java.awt.Color(255, 128, 128));
						jLabel26.setOpaque(true);
						jLabel26.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel26.setBounds(504, 42, 28, 28);
						allColor.add(jLabel26);
					}
					{
						jLabel27 = new JLabel();
						buttonContainerPanel.add(jLabel27);
						jLabel27.setOpaque(true);
						jLabel27
								.setBackground(new java.awt.Color(235, 235, 235));
						jLabel27.setBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, null, null, null, null));
						jLabel27.setBounds(7, 7, 70, 63);
						{
							jLabel28 = new JLabel();
							jLabel27.add(jLabel28);
							jLabel28.setBounds(14, 14, 28, 28);
							jLabel28.setOpaque(true);
							jLabel28.setBackground(new java.awt.Color(0, 0, 0));
							jLabel28.setBorder(new LineBorder(
									new java.awt.Color(0, 0, 0), 1, false));
						}
						{
							jLabel29 = new JLabel();
							jLabel27.add(jLabel29);
							jLabel29.setBackground(new java.awt.Color(255, 255,
									255));
							jLabel29.setBorder(new LineBorder(
									new java.awt.Color(0, 0, 0), 1, false));
							jLabel29.setOpaque(true);
							jLabel29.setBounds(28, 21, 28, 28);
						}
					}
				}
			}
			pack();
			this.setSize(WIDTH, HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void clrItemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	protected void setItemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		SuperShape ss = null;
		for(int i=0; i<drawPanel.getCurrentShapes().size(); i++) {
			ss = drawPanel.getCurrentShapes().get(i);
			if(ss.isSelect()) break;			
		}
		if(null != ss) {
			NodeInfo ni = new NodeInfo(this);
			ni.setSb(ss.getSb().equals("") ? "" : ss.getSb());			
			ni.setHl(ss.getHl().equals("") ? "": ss.getHl());			
			ni.setJd(ss.getJd().equals("") ? "": ss.getJd());	
			ni.setVisible(true);
			if (ni.retState()) {
				ss.setSb(ni.getSb());
				ss.setHl(ni.getHl());
				ss.setJd(ni.getJd());
			}
		}
		

	}

	private void exitItemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));		
	}

	private void saveItemActionPerformed(WindowEvent evt) {//  保存文件
		if (!drawPanel.getCurrentShapes().equals(drawPanel.getOldShapes())) {// 判断文件
			chooser.setFileFilter(ff);
			if (saveAgainFlag == 0) {
				int newSaveFlag = JOptionPane.showConfirmDialog(null,
						"是否先保存文件?", "是否先保存文件?",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (newSaveFlag == 0) {
					int returnVal = chooser.showSaveDialog(DrawJFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						tempFile = chooser.getSelectedFile();
						String filePath = "";
						if (!tempFile.getAbsolutePath().endsWith(".crt")) {
							filePath = tempFile.getAbsolutePath()
									.concat(".crt");
							tempFile = new File(filePath);
						}
						if (tempFile.exists()) {
							int coverOrNotFlag = JOptionPane.showConfirmDialog(
									null, "是否覆盖已有文件?", "是否覆盖已有文件?",
									JOptionPane.YES_NO_OPTION);
							if (coverOrNotFlag == 1)
								;
							if (coverOrNotFlag == 0) {// 覆盖已有文件
								String absolutPath = tempFile.getAbsolutePath();
								tempFile.delete();
								tempFile = new File(absolutPath);// 将原有文件清空
							}
						}
						drawPanel.setOldShapes(drawPanel.getCurrentShapes());
						saveTool.save(drawPanel.getCurrentShapes(), tempFile);
					}
					this.dispose();
				}
				if (newSaveFlag == 1) {
					drawPanel.getCurrentShapes().clear();
					drawPanel.getOldShapes().clear();
					drawPanel.repaint();
					saveAgainFlag = 0;
					this.dispose();
				}
				if (newSaveFlag == 2) {
					drawPanel.repaint();
					return;
				}

			} else if (saveAgainFlag == 1) {// 将改动保存到?
				int changeSaveToFlag = JOptionPane.showConfirmDialog(null,
						"将改动保存到" + tempFile.toString(), "将改动保存到"
								+ tempFile.toString(),
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (changeSaveToFlag == 0) {// yes
					drawPanel.setOldShapes(drawPanel.getCurrentShapes());
					saveTool.save(drawPanel.getCurrentShapes(), tempFile);
					drawPanel.repaint();
					saveAgainFlag = 0;
					this.dispose();
				}
				if (changeSaveToFlag == 1) {// no
					saveTool.save(drawPanel.getOldShapes(), tempFile);
					saveAgainFlag = 0;
					this.dispose();
				}
				if (changeSaveToFlag == 2) {// cancel
					drawPanel.repaint();
					return;
				}
			}
		} else
			this.dispose();

	}

	private void saveItemActionPerformed(ActionEvent evt) {//保存文件
		if (!drawPanel.getCurrentShapes().equals(drawPanel.getOldShapes())) {// 判断文件
			chooser.setFileFilter(ff);
			if (saveAgainFlag == 0) {// 新建的文件第一次保存
				int returnVal = chooser.showSaveDialog(DrawJFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					tempFile = chooser.getSelectedFile();
					String filePath = "";
					if (!tempFile.getAbsolutePath().endsWith(".crt")) {
						filePath = tempFile.getAbsolutePath().concat(".crt");
						tempFile = new File(filePath);
					}
					if (tempFile.exists()) {
						int coverOrNotFlag = JOptionPane.showConfirmDialog(
								null, "是否覆盖已有文件?", "是否覆盖已有文件?",
								JOptionPane.YES_NO_OPTION);
						if (coverOrNotFlag == 1)
							return;// 否
						if (coverOrNotFlag == 0) {// 覆盖已有文件
							String absolutPath = tempFile.getAbsolutePath();
							tempFile.delete();
							tempFile = new File(absolutPath);// 将原有文件清空
						}
					}
					drawPanel.setOldShapes(drawPanel.getCurrentShapes());
					saveTool.save(drawPanel.getCurrentShapes(), tempFile);
					inst.setTitle(tempFile.toString() + title);
					drawPanel.repaint();
					saveAgainFlag = 1;
				}
			} else if (saveAgainFlag == 1) {// 已经保存过至少一次，再进行保存	
				drawPanel.setOldShapes(drawPanel.getCurrentShapes());
				saveTool.save(drawPanel.getCurrentShapes(), tempFile);
				inst.setTitle(tempFile.toString() + title);
				drawPanel.repaint();
				return;
			}
		}
	}

	private void openItemActionPerformed(ActionEvent evt) {// ypf 打开文件
		File tempFile2 = null;
		chooser.setFileFilter(ff);
		int returnVal = chooser.showOpenDialog(DrawJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			tempFile2 = chooser.getSelectedFile();
			if (!drawPanel.getCurrentShapes().equals(drawPanel.getOldShapes())) {// 文件未保存
				if (saveAgainFlag == 0) {
					int openFlag = JOptionPane.showConfirmDialog(null,
							"是否先保存文件?", "是否先保存文件?",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (openFlag == 0) {
						int returnVal4 = chooser
								.showSaveDialog(DrawJFrame.this);
						if (returnVal4 == JFileChooser.APPROVE_OPTION) {
							tempFile = chooser.getSelectedFile();
							String filePath = "";
							if (!tempFile.getAbsolutePath().endsWith(".crt")) {
								filePath = tempFile.getAbsolutePath().concat(
										".crt");
								tempFile = new File(filePath);
							}
							if (tempFile.exists()) {
								int coverOrNotFlag = JOptionPane
										.showConfirmDialog(null, "是否覆盖已有文件?",
												"是否覆盖已有文件?",
												JOptionPane.YES_NO_OPTION);
								if (coverOrNotFlag == 1)
									return;// 否
								if (coverOrNotFlag == 0) {// 覆盖已有文件
									String absolutPath = tempFile
											.getAbsolutePath();
									tempFile.delete();
									tempFile = new File(absolutPath);// 将原有文件清空
								}
							}
							drawPanel.shapeArrayClear();
							drawPanel
									.setOldShapes(drawPanel.getCurrentShapes());
							saveTool.save(drawPanel.getCurrentShapes(),
									tempFile);
							saveAgainFlag = 0;
						}
						tempFile = tempFile2;
						drawPanel.setCurrentShapes(openTool.openFile(tempFile));
						drawPanel.setOldShapes(drawPanel.getCurrentShapes());
						inst.setTitle(tempFile.toString() + title);
						drawPanel.repaint();
					}
					if (openFlag == 1) {
						tempFile = tempFile2;
						drawPanel.shapeArrayClear();
						drawPanel.setCurrentShapes(openTool.openFile(tempFile));
						drawPanel.setOldShapes(drawPanel.getCurrentShapes());
						inst.setTitle(tempFile.toString() + title);
						drawPanel.repaint();
					}
					if (openFlag == 2) {
						return;
					}
				}
				if (saveAgainFlag == 1) {// 将改动保存到?
					int changeSaveToFlag = JOptionPane.showConfirmDialog(null,
							"将改动保存到" + tempFile.toString(), "将改动保存到"
									+ tempFile.toString(),
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (changeSaveToFlag == 0) {// yes
						drawPanel.setOldShapes(drawPanel.getCurrentShapes());
						saveTool.save(drawPanel.getCurrentShapes(), tempFile);
						saveAgainFlag = 0;
					}
					if (changeSaveToFlag == 1) {// no
						saveTool.save(drawPanel.getOldShapes(), tempFile);
						saveAgainFlag = 0;
					}
					if (changeSaveToFlag == 2) {// cancel
						return;
					}
					tempFile = tempFile = tempFile2;
					drawPanel.setCurrentShapes(openTool.openFile(tempFile));
					drawPanel.setOldShapes(drawPanel.getCurrentShapes());
					drawPanel.repaint();
				}
			}

			if (!tempFile2.getAbsolutePath().endsWith(".crt")) {// 文件格式不对
				JOptionPane.showMessageDialog(this, "只能打开.crt格式!");
				return;
			}
			tempFile = tempFile = tempFile2;
			saveAgainFlag = 1;
			drawPanel.shapeArrayClear();
			drawPanel.getCurrentShapes().clear();
			drawPanel.getOldShapes().clear();
			drawPanel.setCurrentShapes(openTool.openFile(tempFile));
			drawPanel.setOldShapes(drawPanel.getCurrentShapes());
			inst.setTitle(tempFile.toString() + title);
			drawPanel.repaint();
		}

	}

	private void createItemActionPerformed(ActionEvent evt) {// ypf 新建文件
		int saveOrNotFlag = Integer.MAX_VALUE;
		if (!drawPanel.getCurrentShapes().equals(drawPanel.getOldShapes())) {// 文件未保存,先去保存文件
			chooser.setFileFilter(ff);
			if (saveAgainFlag == 0) {
				int newSaveFlag = JOptionPane.showConfirmDialog(null,
						"是否先保存文件?", "是否先保存文件?",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (newSaveFlag == 0) {
					int returnVal = chooser.showSaveDialog(DrawJFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						tempFile = chooser.getSelectedFile();
						String filePath = "";
						if (!tempFile.getAbsolutePath().endsWith(".crt")) {
							filePath = tempFile.getAbsolutePath()
									.concat(".crt");
							tempFile = new File(filePath);
						}
						if (tempFile.exists()) {
							int coverOrNotFlag = JOptionPane.showConfirmDialog(
									null, "是否覆盖已有文件?", "是否覆盖已有文件?",
									JOptionPane.YES_NO_OPTION);
							if (coverOrNotFlag == 1)
								return;// 否
							if (coverOrNotFlag == 0) {// 覆盖已有文件
								String absolutPath = tempFile.getAbsolutePath();
								tempFile.delete();
								tempFile = new File(absolutPath);// 将原有文件清空
								saveTool.save(drawPanel.getCurrentShapes(),
										tempFile);
								saveAgainFlag = 0;
								drawPanel.getHistoryShapes().clear();
								drawPanel.shapeArrayClear();
								inst.setTitle("未命名");
								drawPanel.repaint();
								return;
							}
						}
						drawPanel.shapeArrayClear();
						drawPanel.getHistoryShapes().clear();
						drawPanel.setOldShapes(drawPanel.getCurrentShapes());
						saveTool.save(drawPanel.getCurrentShapes(), tempFile);
						inst.setTitle("未命名" + title);
						drawPanel.repaint();
						saveAgainFlag = 0;
					}
					return;
				}
				if (newSaveFlag == 1) {
					drawPanel.getHistoryShapes().clear();
					drawPanel.shapeArrayClear();
					drawPanel.getCurrentShapes().clear();
					drawPanel.getOldShapes().clear();
					inst.setTitle("未命名" + title);
					drawPanel.repaint();
					saveAgainFlag = 0;
					return;
				}
				if (newSaveFlag == 2) {
					drawPanel.repaint();
					return;
				}
				return;
			} else if (saveAgainFlag == 1) {// 将改动保存到?
				int changeSaveToFlag = JOptionPane.showConfirmDialog(null,
						"将改动保存到" + tempFile.toString(), "将改动保存到"
								+ tempFile.toString(),
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (changeSaveToFlag == 0) {// yes
					drawPanel.setOldShapes(drawPanel.getCurrentShapes());
					saveTool.save(drawPanel.getCurrentShapes(), tempFile);
					drawPanel.repaint();
					saveAgainFlag = 0;
				}
				if (changeSaveToFlag == 1) {// no
					saveTool.save(drawPanel.getOldShapes(), tempFile);
					saveAgainFlag = 0;
				}
				if (changeSaveToFlag == 2) {// cancel
					return;
				}
			}
		}
		drawPanel.getHistoryShapes().clear();
		drawPanel.shapeArrayClear();
		saveAgainFlag = 0;
		drawPanel.getOldShapes().clear();
		drawPanel.getCurrentShapes().clear();	
		inst.setTitle("未命名" + title);
		drawPanel.repaint();
	}

	private void saveOtherItemActionPerformed(ActionEvent evt) {// ypf 另存为
		chooser.setFileFilter(ff);
		int returnVal = chooser.showSaveDialog(DrawJFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			tempFile = chooser.getSelectedFile();
			String filePath = "";
			if (!tempFile.getAbsolutePath().endsWith(".crt")) {
				filePath = tempFile.getAbsolutePath().concat(".crt");
				tempFile = new File(filePath);
			}
			if (tempFile.exists()) {
				int coverOrNotFlag = JOptionPane.showConfirmDialog(null,
						"是否覆盖已有文件?", "是否覆盖已有文件?", JOptionPane.YES_NO_OPTION);
				if (coverOrNotFlag == 1)
					return;// 否
				if (coverOrNotFlag == 0) {// 覆盖已有文件
					String absolutPath = tempFile.getAbsolutePath();
					tempFile.delete();
					tempFile = new File(absolutPath);// 将原有文件清空
				}
			}
			drawPanel.setOldShapes(drawPanel.getCurrentShapes());
			saveTool.save(drawPanel.getCurrentShapes(), tempFile);
			inst.setTitle(tempFile.toString() + title);
			drawPanel.repaint();
			saveAgainFlag = 1;
		}

	}

	private void rootWindowClosing(WindowEvent evt) {// window关闭事件
		this.saveItemActionPerformed(evt);// 先去保存文件
	}

	private void jPanel1AncestorResized(HierarchyEvent evt) {// 当JFrame窗口最大化时，实现另外三个jPanel的随之放大
		jPanel1.setBounds(0, 0, this.getBounds().width - 10,
				this.getBounds().height - 47);
		jPanel2.setBounds(83, 7, this.getBounds().width - 100,
				this.getBounds().height - 173);
		buttonContainerPanel.setBounds(0, 462 + this.getBounds().height - 600,
				539, 77);
		jSeparator1.setBounds(7, 441 + this.getBounds().height - 600,
				776 + this.getBounds().width - 800, 28);
		drawPanel.setBounds(7, 7, this.getBounds().width - 113, this
				.getBounds().height - 188);
	}

	private void jToggleButton3ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(lineTool);
	}

	private void jToggleButton4ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(rectangleTool);
	}

	private void jToggleButton2ActionPerformed(ActionEvent evt) {
		if (this.catchTool.getOpShape() != null) {
			this.catchTool.getOpShape().setSelect(false);
		}
		this.drawPanel.setCurrentTool(catchTool);
		this.catchTool.setOpShape(null);

		this.catchTool.setOldShape(null);
		this.catchTool.setNewShape(null);
		this.drawPanel.repaint();
	}

	private void jToggleButton7ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(triangleTool);

	}

	private void jToggleButton5ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(ellipseTool);
	}

	private void jToggleButton9ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(pentangleTool);
	}

	private void jToggleButton6ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(circleTool);
	}

	private void jToggleButton14ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(squareTool);
	}

	private void jToggleButton8ActionPerformed(ActionEvent evt) {
		this.drawPanel.getCurrentTool().clear();
		this.drawPanel.setCurrentTool(pentagonTool);
	}

	/**
	 * @return the bounds
	 */
	public Rectangle2D getPanelBounds() {
		return panelBounds;
	}

	/**
	 * @param bounds
	 *            the bounds to set
	 */
	public void setBounds(Rectangle2D bounds) {
		this.panelBounds = bounds;
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		this.drawPanel.undo();
		this.drawPanel.repaint();
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.drawPanel.redo();
		this.drawPanel.repaint();
	}

	private void buttonContainerPanelMouseClicked(MouseEvent evt) {
		for (int i = 0; i < allColor.size(); i++) {
			JLabel tempJlabel = allColor.get(i);
			if (tempJlabel.getBounds().contains(evt.getPoint())) {
				jLabel28.setBackground(tempJlabel.getBackground());
				if (drawPanel.getCurrentTool() == catchTool) {
					if (this.catchTool.getOpShape() != null) {
						this.catchTool.changeColor(tempJlabel.getBackground());
					}
				}
				drawPanel.setLinecolor(tempJlabel.getBackground());
				drawPanel.repaint();
				return;
			}
		}
	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		if (this.drawPanel.getCurrentTool() == catchTool) {
			if (this.catchTool.getOpShape() != null) {
				this.catchTool.removeCatchShape();
			}
		}
	}
}
