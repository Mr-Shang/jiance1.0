package com.hb.project4.drawPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.WindowConstants;
import javax.swing.JFrame;

import com.hb.project4.shapes.SuperShape;
import com.hb.project4.tools.SuperTool;

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
public class DrawJPanel extends javax.swing.JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<SuperShape> currentShapes = new ArrayList<SuperShape>(); // save current Shapes for drawing in the panel

	private ArrayList<ArrayList<SuperShape>> historyShapes = new ArrayList<ArrayList<SuperShape>>();// save each step of oprations

	private int historyIndex = 1; // the pointer of undo and redo's step

	private ArrayList<SuperShape> oldShapes;

	private SuperTool currentTool;

	SuperTool sss;

	private Rectangle2D panelBounds;

	private Color linecolor = new java.awt.Color(0, 0, 0);

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DrawJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public DrawJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		oldShapes = new ArrayList<SuperShape>();
		this.saveHistory();
		try {
			setPreferredSize(new Dimension(400, 300));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.setCurrentShapes(new ArrayList<SuperShape>());
		// this.setHistoryShapes(new ArrayList<ArrayList<SuperShape>>());
		// this.getHistoryShapes().add(new ArrayList<SuperShape>());
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent evt) {
				rootMouseMoved(evt);
			}

			public void mouseDragged(MouseEvent evt) {
				rootMouseDragged(evt);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				rootMouseReleased(evt);
			}

			public void mousePressed(MouseEvent evt) {
				rootMousePressed(evt);
			}

			public void mouseClicked(MouseEvent evt) 
			{
				rootMouseClicked(evt);
			}
		});
	}

//	public Rectangle2D[] getRectOfPanel() {
//		int size = 9;
//		Point leftTopPoint = this.getLocation();
//		int height = this.getHeight();
//		int width = this.getWidth();
//		return new Rectangle2D[] {// 返回三个改变画布大小的控制点
//				new Rectangle2D.Double(leftTopPoint.x + width - size,
//						leftTopPoint.y + height / 2 - size, size / 2, size / 2),
//				new Rectangle2D.Double(leftTopPoint.x + width / 2 - size,
//						leftTopPoint.y + height - size, size / 2, size / 2),
//				new Rectangle2D.Double(this.getLocation().x + this.getWidth()
//						- size - 1, this.getLocation().y + this.getHeight()
//						- size - 1, size / 2, size / 2) };
//	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.getCurrentShapes() != null) {
			for (int i = 0; i < this.getCurrentShapes().size(); i++) {
				((SuperShape) this.getCurrentShapes().get(i)).draw(g);
			}
		}
		// Graphics2D g2 = (Graphics2D) g;
		// g.setColor(Color.BLACK);
		// for (Rectangle2D r : getRectOfPanel())
		// g2.fill(r);
	}

	public ArrayList<SuperShape> getCurrentShapes() {
		return currentShapes;
	}

	public void setCurrentShapes(ArrayList<SuperShape> currentShapes) {
		this.currentShapes = currentShapes;
	}

	public SuperTool getCurrentTool() {
		return currentTool;
	}

	public void setCurrentTool(SuperTool currentTool) {
		this.currentTool = currentTool;
	}

	public ArrayList<ArrayList<SuperShape>> getHistoryShapes() {
		return historyShapes;
	}

	public void setHistoryShapes(ArrayList<ArrayList<SuperShape>> historyShapes) {
		this.historyShapes = historyShapes;
	}

	private void rootMouseClicked(MouseEvent evt) {
		if (this.getCurrentTool() != null)
			this.getCurrentTool().mouseClickedAction(evt);
	}

	private void rootMousePressed(MouseEvent evt) {
		if (this.getCurrentTool() != null)
			this.getCurrentTool().mousePressedAction(evt);

	}

	private void rootMouseReleased(MouseEvent evt) {
		if (this.getCurrentTool() != null)
			this.getCurrentTool().mouseReleasedAction(evt);
	}

	private void rootMouseDragged(MouseEvent evt) {	
		if (this.getCurrentTool() != null)
			this.getCurrentTool().mouseDraggedAction(evt);
	}

	private void rootMouseMoved(MouseEvent evt) {
		if (this.getCurrentTool() != null)
			this.getCurrentTool().mouseMovedAction(evt);
	}

	/**
	 * @return the oldShapes
	 */
	public ArrayList<SuperShape> getOldShapes() {
		return oldShapes;
	}

	/**
	 * @param oldShapes
	 *            the oldShapes to set
	 */
	public void setOldShapes(ArrayList<SuperShape> oldShapes) {
		this.oldShapes.clear();// 此句必不可少
		for (SuperShape r : oldShapes) {
			this.oldShapes.add(r);
		}
	}

	public Rectangle2D getPanelBounds() {
		return panelBounds;
	}

	public void setPanelBounds(Rectangle2D panelBounds) {
		this.panelBounds = panelBounds;
	}

	/**
	 * 撤销操作
	 */
	public void undo() {
		// 如果有回退记录并且指针不在第一位进入操作
		if (historyShapes != null && historyIndex < historyShapes.size()) {
			int tempI = historyShapes.size() - 1 - historyIndex;// 指针位置
			
			ArrayList<SuperShape> temp111 = historyShapes.get(tempI);
			ArrayList<SuperShape> temp222 = new ArrayList<SuperShape>(temp111
					.size());
			//　将上次操作数组内容复制到temp222并赋给currentShapes，重新绘制
			for (int i = 0; i < temp111.size(); i++) {
				temp222.add(temp111.get(i));
			}
			this.setCurrentShapes(temp222);
			historyIndex++;
			this.repaint();
		}
	}

	/**
	 * 恢复操作
	 */
	public void redo() {
		// 如果可以重做，即指针位置不在historyShapes最后一位，将上一次操作的记录赋给当前记录，并重新绘制
		if (historyIndex <= historyShapes.size()) {

			int tempI = historyShapes.size() - historyIndex + 1;
			if (tempI == historyShapes.size()) {
				return;
			}
			ArrayList<SuperShape> temp111 = historyShapes.get(tempI);
			ArrayList<SuperShape> temp222 = new ArrayList<SuperShape>(temp111
					.size());
			for (int i = 0; i < temp111.size(); i++) {
				temp222.add(temp111.get(i));
			}
			this.setCurrentShapes(temp222);
			historyIndex--;
			this.repaint();
		}
	}	
	/**
	 * 快照，复制当前图形集合本身到histroyShapes里
	 *
	 */
	public void saveHistory() {
		// 重新绘制新图形，清空history指针下面的记录，即禁止重做操作
		for (int i = 0; i < this.getHistoryIndex() - 1; i++) {
			this.getHistoryShapes().remove(this.getHistoryShapes().size() - 1);
		}
		this.setHistoryIndex(1);
		// 快照
		ArrayList<SuperShape> temp111 = this.getCurrentShapes();
		ArrayList<SuperShape> temp222 = new ArrayList<SuperShape>(temp111
				.size());
		for (int i = 0; i < temp111.size(); i++) {
			temp222.add(temp111.get(i).copySelf());
		}
		this.getHistoryShapes().add(temp222);
	}

	public Color getLinecolor() {
		return linecolor;
	}

	public void setLinecolor(Color linecolor) {
		this.linecolor = linecolor;
	}

	public int getHistoryIndex() {
		return historyIndex;
	}

	public void setHistoryIndex(int historyIndex) {
		this.historyIndex = historyIndex;
	}

	public void shapeArrayClear()
	{
		currentShapes.clear();
		oldShapes.clear();
	}
}
