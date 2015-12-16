package com.hb.project4.tools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.undo.UndoableEdit;

import com.hb.project4.drawPanel.DrawJPanel;
import com.hb.project4.shapes.HotPointShape;
import com.hb.project4.shapes.SuperShape;

public class CatchTool extends SuperTool {

	private boolean canselectFlag = false;

	private Point[] hotP;

	private int index = 0;

	private SuperShape newShape = null; // ̽�����ͼ��

	private SuperShape opShape; // ����ͼ��

	private SuperShape oldShape = null;// ̽��ͬһͼ��

	boolean dragged = false; // �Ƿ����϶�״̬

	boolean isChangedShape = false; // ��״�Ƿ�ı���

	HotPointShape hotShape = new HotPointShape();

	Point bg = new Point(0, 0); // ����ͼ�ε�ԭ��ʼ��

	Point ed = new Point(0, 0); // ����ͼ�ε�ԭ������

	int position = 0; // ̽��ͼ�ε�λ��

	public CatchTool(DrawJPanel j) {

		super(j);
		newShape = null;
		opShape = null;
		oldShape = null;
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����ƶ��㣬���ж��Ƿ�ץ����ͼ��
	 * 
	 * @param p
	 * @return
	 */
	public SuperShape catchShape(Point p) {
		this.setNewShape(null);
		ArrayList shapeList = this.getJPanel().getCurrentShapes();
		SuperShape tempShape = null;
		SuperShape returnShape = null;
		for (int i = shapeList.size() - 1; i > -1; i--) {
			tempShape = (SuperShape) shapeList.get(i);
			if (tempShape.checkOnShape(p) != 0) {
				returnShape = tempShape;
				return returnShape;
			}
		}
		return returnShape;
	}

	@Override
	public void mouseClickedAction(MouseEvent mouseEvent) {

	}

	/**
	 * ���������϶������ػ���Ӧ��ͼ��
	 */
	@Override
	public void mouseDraggedAction(MouseEvent mouseEvent) {
		Graphics g = this.getJPanel().getGraphics();
//		g.setColor(this.getJPanel().getLinecolor());
		g.setXORMode(Color.WHITE);
		if (opShape != null) {
//			g.setColor(this.opShape.getClor());
			this.setNewDragPoint(mouseEvent.getPoint());
			if (this.position == -1) {// ���ڱ�Եƽ��

				opShape.draw(g);// ��ȥԭͼ�κ��ȵ�

				int wide = this.getNewDragPoint().x - this.getOldDragPoint().x;
				int lengh = this.getNewDragPoint().y - this.getOldDragPoint().y;
				Point tuo = new Point(wide, lengh);
				this.opShape.setBegin(new Point(wide + bg.x, lengh + bg.y));
				this.opShape.setEnd(new Point(wide + ed.x, lengh + ed.y));

				opShape.draw(g);// ��������ͼ�κ��ȵ�

				isChangedShape = true;

			} else if (this.position == 0) {
			} else {
				opShape.draw(g);// ��ȥԭͼ�κ��ȵ�

				this.opShape.setHotPoint(position, this.getNewDragPoint());

				opShape.draw(g);// ��������ͼ�κ��ȵ�

				isChangedShape = true;
			}

		}
	}

	/**
	 * ���̽��ͼ�Σ���ʾ��Ӧ���ȵ�
	 */
	@Override
	public void mouseMovedAction(MouseEvent mouseEvent) {
		if (!this.dragged) {
			Graphics g = this.getJPanel().getGraphics();
//			g.setColor(Color.BLACK);
			g.setXORMode(Color.WHITE);

			Point movePoint = mouseEvent.getPoint();

			this.newShape = this.catchShape(movePoint);
			if (newShape != null) { // ����⵽ͼ��ʱ�任
				if (newShape.checkOnShape(movePoint) != -1
						&& newShape.checkOnShape(movePoint) != 0) {
					this
							.getJPanel()
							.setCursor(
									Cursor
											.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				} else if (newShape.checkOnShape(movePoint) == -1) {
					this.getJPanel().setCursor(
							Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			} else {
				this.getJPanel().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			if (newShape != null) {

				if (oldShape == null) { // ������һ��ͼ�Σ��������ȵ�
					if (opShape == null) {
						this.canselectFlag = true;
						oldShape = newShape;
						hotShape.setHotPoints(this.oldShape.getHotPoints());
						hotShape.draw(g);
					} else {
						if (opShape != newShape) {
							this.canselectFlag = true;
							oldShape = newShape;
							hotShape.setHotPoints(this.oldShape.getHotPoints());
							hotShape.draw(g);
						}
					}
				}
				if (newShape != oldShape && oldShape != null) { // ��ԭͼ�κ���ͼ�β�ͬʱ���ٻ�һ����ͼ���ȵ�
					this.canselectFlag = true;
					if (oldShape != opShape) {
						hotShape.setHotPoints(this.oldShape.getHotPoints());
						hotShape.draw(g);
					}
					if (newShape != opShape) {
						oldShape = newShape;
						hotShape.setHotPoints(this.oldShape.getHotPoints()); // ��ʾ��ͼ���ȵ㣬
						hotShape.draw(g);
					}
				}
			} else {
				this.canselectFlag = false;
				if (oldShape != null && oldShape != opShape) {
					hotShape.setHotPoints(this.oldShape.getHotPoints());// ��ԭͼ�β���ʱ���ٻ�һ����ͼ���ȵ�
					hotShape.draw(g);
					oldShape = null;
				}
			}
		}
	}

	/**
	 * ��갴�£��϶��޸�ͼ�ε�ǰ������������ѡ��Ҫ������ͼ��
	 */
	@Override
	public void mousePressedAction(MouseEvent mouseEvent) {
		Point pressPoint = mouseEvent.getPoint();
		this.setOldDragPoint(pressPoint);
		this.setBeginPoint(pressPoint);
		if (opShape != null && opShape.checkOnShape(pressPoint) != 0) {
			this.dragged = true;
		}
		Graphics g = this.getJPanel().getGraphics();
//		g.setColor(this.getJPanel().getLinecolor());
		if (opShape != null && opShape.checkOnShape(pressPoint) == 0 && canselectFlag==false) {
			this.opShape.setSelect(false);
			this.opShape = null;
			this.oldShape=null;
		}
		if (opShape != null) {
			position = this.opShape.checkOnShape(pressPoint);
			// System.out.println("position"+position);
		}
		if (canselectFlag) {

			if (opShape == null) { // ����ͼ��Ϊ��ʱ�Ѵ�����ͼ�θ�������ͼ��

				this.opShape = this.oldShape;

				this.setOperateShape(opShape);

				this.opShape.setSelect(true);
				position = this.opShape.checkOnShape(pressPoint);
			}
			if (opShape != oldShape) {
				if (opShape != null) { // �ٻ�һ��ԭ����ͼ�ε��ȵ�
					this.opShape.setSelect(false);
				}
				this.opShape = this.oldShape;
				this.setOperateShape(opShape);
				this.opShape.setSelect(true);
				position = this.opShape.checkOnShape(pressPoint);
			}
		}

		if (opShape != null) {
			bg = this.opShape.getBegin();
			ed = this.opShape.getEnd();
		}
		this.getJPanel().repaint();

	}

	@Override
	public void mouseReleasedAction(MouseEvent mouseEvent) {
		// px
		if (isChangedShape) {
			this.opShape.setSelect(false);
			this.getJPanel().saveHistory();
			this.opShape.setSelect(true);
			isChangedShape = false;
		}
		// px
		this.dragged = false;
		this.canselectFlag = false;
		this.bg = null;
		this.ed = null;
		position = 0;
		this.getJPanel().repaint();
		this.setOldDragPoint(null);

	}
	
	public void removeCatchShape(){
		this.getJPanel().getCurrentShapes().remove(opShape);
		this.getJPanel().saveHistory();
		this.getJPanel().repaint();
	}
	
	public void changeColor(Color co){
		this.opShape.setClor(co);
		this.getJPanel().saveHistory();
		this.getJPanel().repaint();
	}

	public Point[] getHotP() {
		return hotP;
	}

	public void setHotP(Point[] hotP) {
		this.hotP = hotP;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isCanSelectFlag() {
		return canselectFlag;
	}

	public void setCanSelectFlag(boolean selectFlag) {
		this.canselectFlag = selectFlag;
	}

	public HotPointShape getHotShape() {
		return hotShape;
	}

	public void setHotShape(HotPointShape hotShape) {
		this.hotShape = hotShape;
	}

	public SuperShape getOldShape() {
		return oldShape;
	}

	public void setOldShape(SuperShape oldShape) {
		this.oldShape = oldShape;
	}

	public SuperShape getOpShape() {
		return opShape;
	}

	public void setOpShape(SuperShape opShape) {
		this.opShape = opShape;
	}

	public SuperShape getNewShape() {
		return newShape;
	}

	public void setNewShape(SuperShape newShape) {
		this.newShape = newShape;
	}

}
