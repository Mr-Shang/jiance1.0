package com.hb.project4.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.hb.project4.drawPanel.DrawJPanel;
import com.hb.project4.shapes.RectangleShape;
import com.hb.project4.shapes.SquareShape;

public class SquareTool extends SuperTool{


	public SquareTool(DrawJPanel j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClickedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDraggedAction(MouseEvent mouseEvent) {
		this.setNewDragPoint(mouseEvent.getPoint());
		this.setOldDragPoint(this.getNewDragPoint());
		// this.setNewDragPoint(null);

		Graphics g = this.getJPanel().getGraphics();
		g.setColor(this.getJPanel().getLinecolor());
		g.setXORMode(Color.WHITE);

		if (this.getOperateShape().getEnd() != null) {
			this.getOperateShape().draw(g);
		}
		this.getOperateShape().setEnd(this.getNewDragPoint());
		this.getOperateShape().draw(g);
	}

	@Override
	public void mouseMovedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressedAction(MouseEvent mouseEvent) {
		this.setBeginPoint(mouseEvent.getPoint());

		this.setOldDragPoint(this.getBeginPoint());
		this.setOldMovePoint(this.getBeginPoint());

		this.setOperateShape(new SquareShape());
		this.getOperateShape().setClor(this.getJPanel().getLinecolor());
		this.getOperateShape().setBegin(this.getBeginPoint());

	}

	@Override
	public void mouseReleasedAction(MouseEvent mouseEvent) {
		Graphics g = this.getJPanel().getGraphics();
		g.setColor(this.getJPanel().getLinecolor());
//		g.setXORMode(Color.WHITE);
		this.setEndPoint(mouseEvent.getPoint());
		if (this.getOldMovePoint() != null) {
			this.getOperateShape().setEnd(this.getOldMovePoint());
			this.getOperateShape().draw(g);
		}
		this.getOperateShape().setEnd(this.getEndPoint());
		this.getOperateShape().draw(g);
		this.getJPanel().getCurrentShapes().add(this.getOperateShape());
		this.getJPanel().saveHistory();
		this.getJPanel().repaint();
		
		this.setBeginPoint(null);
		this.setEndPoint(null);
	}
}
