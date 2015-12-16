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

	private SuperShape newShape = null; // 探测的新图形

	private SuperShape opShape; // 操作图形

	private SuperShape oldShape = null;// 探测同一图形

	boolean dragged = false; // 是否在拖动状态

	boolean isChangedShape = false; // 形状是否改变了

	HotPointShape hotShape = new HotPointShape();

	Point bg = new Point(0, 0); // 操作图形的原开始点

	Point ed = new Point(0, 0); // 操作图形的原结束点

	int position = 0; // 探测图形的位置

	public CatchTool(DrawJPanel j) {

		super(j);
		newShape = null;
		opShape = null;
		oldShape = null;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据移动点，来判断是否抓到了图形
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
	 * 根据鼠标的拖动，来重画相应的图形
	 */
	@Override
	public void mouseDraggedAction(MouseEvent mouseEvent) {
		Graphics g = this.getJPanel().getGraphics();
//		g.setColor(this.getJPanel().getLinecolor());
		g.setXORMode(Color.WHITE);
		if (opShape != null) {
//			g.setColor(this.opShape.getClor());
			this.setNewDragPoint(mouseEvent.getPoint());
			if (this.position == -1) {// 点在边缘平移

				opShape.draw(g);// 擦去原图形和热点

				int wide = this.getNewDragPoint().x - this.getOldDragPoint().x;
				int lengh = this.getNewDragPoint().y - this.getOldDragPoint().y;
				Point tuo = new Point(wide, lengh);
				this.opShape.setBegin(new Point(wide + bg.x, lengh + bg.y));
				this.opShape.setEnd(new Point(wide + ed.x, lengh + ed.y));

				opShape.draw(g);// 画出现有图形和热点

				isChangedShape = true;

			} else if (this.position == 0) {
			} else {
				opShape.draw(g);// 擦去原图形和热点

				this.opShape.setHotPoint(position, this.getNewDragPoint());

				opShape.draw(g);// 画出现有图形和热点

				isChangedShape = true;
			}

		}
	}

	/**
	 * 鼠标探测图形，显示相应的热点
	 */
	@Override
	public void mouseMovedAction(MouseEvent mouseEvent) {
		if (!this.dragged) {
			Graphics g = this.getJPanel().getGraphics();
//			g.setColor(Color.BLACK);
			g.setXORMode(Color.WHITE);

			Point movePoint = mouseEvent.getPoint();

			this.newShape = this.catchShape(movePoint);
			if (newShape != null) { // 鼠标监测到图形时变换
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

				if (oldShape == null) { // 触到第一个图形，画出其热点
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
				if (newShape != oldShape && oldShape != null) { // 当原图形和现图形不同时，再画一次老图形热点
					this.canselectFlag = true;
					if (oldShape != opShape) {
						hotShape.setHotPoints(this.oldShape.getHotPoints());
						hotShape.draw(g);
					}
					if (newShape != opShape) {
						oldShape = newShape;
						hotShape.setHotPoints(this.oldShape.getHotPoints()); // 显示新图形热点，
						hotShape.draw(g);
					}
				}
			} else {
				this.canselectFlag = false;
				if (oldShape != null && oldShape != opShape) {
					hotShape.setHotPoints(this.oldShape.getHotPoints());// 当原图形不空时，再画一次老图形热点
					hotShape.draw(g);
					oldShape = null;
				}
			}
		}
	}

	/**
	 * 鼠标按下，拖动修改图形的前置条件，或者选择要操作的图形
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

			if (opShape == null) { // 操作图形为空时把触到的图形赋给操作图形

				this.opShape = this.oldShape;

				this.setOperateShape(opShape);

				this.opShape.setSelect(true);
				position = this.opShape.checkOnShape(pressPoint);
			}
			if (opShape != oldShape) {
				if (opShape != null) { // 再画一次原操作图形的热点
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
