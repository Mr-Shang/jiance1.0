package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import com.hb.project4.tools.ReMakeTool;
import com.hb.project4.tools.ShapePropertyTool;
/**
 * 直线
 * @author fm
 *
 */
public class LineShape extends SuperShape{

	public LineShape() {
		this.setShape(new Line2D.Double());
	}
	/**
	 * 得到当前序号的热点
	 */
	@Override
	public Point getHotPoint(int index) {
		Point[] pO = this.getHotPoints();
		return pO[index - 1];
	}
	/**
	 * 热点拖动重绘图形
	 */
	@Override
	public void setHotPoint(int index, Point changePoint) {
		switch (index) {
		case 1:
			this.setBegin(changePoint);
			break;
		case 2:
			this.setEnd(changePoint);
			break;
		default:
//			this.setBegin(begin)
			break;
		}

	}
	/**
	 * 图形绘制
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		g2d.draw(this.newShape());
		if(this.isSelect()){
			this.htD.setHotPoints(this.getHitPoints());
			this.htD.draw(g);
		}
	}
	/**
	 * 得到当前点热点序号,无则返回0
	 */
	@Override
	public int checkOnShape(Point p) {
		for (int i = 0; i < this.getHitPoints().length; i++) {
			Ellipse2D tempEllipse2D = new Ellipse2D.Double();
			tempEllipse2D.setFrameFromDiagonal(new Point(this.getHotPoint(i + 1).x - 4, this
					.getHotPoint(i + 1).y - 4),new Point(this.getHotPoint(i + 1).x + 4, this
							.getHotPoint(i + 1).y + 4));
			if (tempEllipse2D.contains(p)) {
				return i+1;
			}
		}
		Line2D.Double tempLine = new Line2D.Double();
		tempLine.setLine(this.getHotPoint(1), this.getHotPoint(2));
		if (tempLine.ptSegDist(this.getHotPoint(1).getX(), this.getHotPoint(1)
				.getY(), this.getHotPoint(2).getX(),
				this.getHotPoint(2).getY(), p.getX(), p.getY()) < 5) {
			return -1;
		}
		return 0;
	}
	/**
	 * 设置热点
	 */
	@Override
	public Point[] getHitPoints() {
		Point[] hP = new Point[2];
		hP[0] = new Point(this.getBegin().x, this.getBegin().y);
		hP[1] = new Point(this.getEnd().x, this.getEnd().y);

		this.setHotPoints(hP);
		return hP;
	}
	/**
	 * 绘图
	 * @return 直线
	 */
	public Shape newShape(){
		return new Line2D.Double(this.getBegin().x, this.getBegin().y, this.getEnd().x,
		this.getEnd().y);
	}
	/**
	 * 接口实现
	 */
	public SuperShape propertyToShape(ShapePropertyTool shapeProObject) {
		LineShape tempLineShape =new LineShape();
		tempLineShape.setBegin(new Point((int)shapeProObject.getBeginPoint()[0],(int)shapeProObject.getBeginPoint()[1]));
		tempLineShape.setEnd(new Point((int)shapeProObject.getEndPoint()[0],(int)shapeProObject.getEndPoint()[1]));
		tempLineShape.setClor(shapeProObject.getShapeColor());
		
		tempLineShape.setSb(shapeProObject.getSb());
		tempLineShape.setHl(shapeProObject.getHl());
		tempLineShape.setJd(shapeProObject.getJd());

		
		return tempLineShape;
	}
	/**
	 * 快照
	 */
	@Override
	public SuperShape copySelf() {
		LineShape tempSelf =new LineShape();
		tempSelf.setBegin(new Point());
		tempSelf.setBegin(this.getBegin());
		
		tempSelf.setEnd(new Point());
		tempSelf.setEnd(this.getEnd());
		
		
		tempSelf.setClor(Color.white);
		tempSelf.setClor(this.getClor());
		
		return tempSelf;
	}

}
