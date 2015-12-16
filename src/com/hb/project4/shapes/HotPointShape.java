package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import com.hb.project4.tools.ShapePropertyTool;
/**
 * 热点
 * @author fm
 *
 */
public class HotPointShape{

	Point [] hotPoints;
	/**
	 * 绘制热点
	 * @param g
	 */
	public void draw(Graphics g) {

		Point[] points = this.getHotPoints();
		if (points == null)
			return;
		g.setColor(Color.black);
//		g.setXORMode(Color.white);
		Ellipse2D tempEllipse2D = new Ellipse2D.Double();
		Point tempPoint = null;
		for (int i = 0; i < points.length; i++) {
			tempPoint = points[i];
			if (tempPoint == null)
				continue;
			tempEllipse2D.setFrameFromDiagonal(new Point(tempPoint.x - 4, tempPoint.y - 4),new Point(tempPoint.x + 4, tempPoint.y + 4));
			((Graphics2D) g).draw(tempEllipse2D);
		}

	}
	public Point[] getHotPoints() {
		// TODO Auto-generated method stub
		return hotPoints;
	}
	public void setHotPoints(Point[] p){
		this.hotPoints=p;
	}

}
