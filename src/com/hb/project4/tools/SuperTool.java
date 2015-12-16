package com.hb.project4.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import com.hb.project4.drawPanel.DrawJPanel;
import com.hb.project4.shapes.SuperShape;

public abstract class SuperTool{
	private DrawJPanel jPanel;

	private Point beginPoint;

	private Point oldMovePoint;

	private Point oldDragPoint;

	private Point newMovePoint;

	private Point newDragPoint;

	private Point endPoint;

	private Point clickPoint;

	private SuperShape operateShape;


	public SuperTool(DrawJPanel j) {
		this.setJPanel(j);
	}

	public DrawJPanel getJPanel() {
		return jPanel;
	}

	public void setJPanel(DrawJPanel jP) {
		jPanel = jP;
	}

	public Point getBeginPoint() {
		return beginPoint;
	}

	public void setBeginPoint(Point beginPoint) {
		this.beginPoint = beginPoint;
	}

	public Point getClickPoint() {
		return clickPoint;
	}

	public void setClickPoint(Point clickPoint) {
		this.clickPoint = clickPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public Point getNewDragPoint() {
		return newDragPoint;
	}

	public void setNewDragPoint(Point newDragPoint) {
		this.newDragPoint = newDragPoint;
	}

	public Point getNewMovePoint() {
		return newMovePoint;
	}

	public void setNewMovePoint(Point newMovePoint) {
		this.newMovePoint = newMovePoint;
	}

	public Point getOldDragPoint() {
		return oldDragPoint;
	}

	public void setOldDragPoint(Point oldDragPoint) {
		this.oldDragPoint = oldDragPoint;
	}

	public Point getOldMovePoint() {
		return oldMovePoint;
	}

	public void setOldMovePoint(Point oldMovePoint) {
		this.oldMovePoint = oldMovePoint;
	}

	public SuperShape getOperateShape() {
		return operateShape;
	}

	public void setOperateShape(SuperShape operateShape) {
		this.operateShape = operateShape;
	}

	public abstract void mouseMovedAction(MouseEvent mouseEvent);

	public abstract void mouseClickedAction(MouseEvent mouseEvent);

	public abstract void mousePressedAction(MouseEvent mouseEvent);

	public abstract void mouseReleasedAction(MouseEvent mouseEvent);

	public abstract void mouseDraggedAction(MouseEvent mouseEvent);
	
	public void clear(){
		if(this.getOperateShape()!=null)
			this.getOperateShape().setSelect(false);
		this.beginPoint=null;
		this.clickPoint=null;
		this.endPoint=null;
		this.newDragPoint=null;
		this.newMovePoint=null;
		this.oldDragPoint=null;
		this.oldMovePoint=null;
		this.operateShape=null;
		this.getJPanel().repaint();
	}
}
