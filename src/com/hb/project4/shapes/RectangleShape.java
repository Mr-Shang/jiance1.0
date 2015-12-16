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

import com.hb.project4.tools.ShapePropertyTool;
/**
 * 矩形
 * @author fm
 *
 */
public class RectangleShape extends SuperShape {

	public RectangleShape() {
		this.setShape(new Rectangle2D.Double());
	}

	/**
	 * 得到当前点热点序号,无则返回0
	 */
	@Override
	public int checkOnShape(Point p) {

		// 控制点上返回相应的点的序列
		for (int i = 0; i < this.getHitPoints().length; i++) {
			Ellipse2D tempEllipse2D = new Ellipse2D.Double();
			tempEllipse2D.setFrameFromDiagonal(new Point(this.getHotPoint(i + 1).x - 4, this
					.getHotPoint(i + 1).y - 4),new Point(this.getHotPoint(i + 1).x + 4, this
							.getHotPoint(i + 1).y + 4));
			if (tempEllipse2D.contains(p)) {
				return (i+1);
			}
		}

		// 图形边缘返回-1
		Line2D.Double tempLine = new Line2D.Double();
		tempLine.setLine(this.getHotPoint(1), this.getHotPoint(3));
		if (tempLine.ptSegDist(this.getHotPoint(1).getX(), this.getHotPoint(1)
				.getY(), this.getHotPoint(3).getX(),
				this.getHotPoint(3).getY(), p.getX(), p.getY()) < 5) {
			return -1;
		}
		tempLine.setLine(this.getHotPoint(3), this.getHotPoint(5));
		if (tempLine.ptSegDist(this.getHotPoint(3).getX(), this.getHotPoint(3)
				.getY(), this.getHotPoint(5).getX(),
				this.getHotPoint(5).getY(), p.getX(), p.getY()) < 5) {
			return -1;
		}
		tempLine.setLine(this.getHotPoint(5), this.getHotPoint(7));
		if (tempLine.ptSegDist(this.getHotPoint(5).getX(), this.getHotPoint(5)
				.getY(), this.getHotPoint(7).getX(),
				this.getHotPoint(7).getY(), p.getX(), p.getY()) < 5) {
			return -1;
		}
		tempLine.setLine(this.getHotPoint(1), this.getHotPoint(7));
		if (tempLine.ptSegDist(this.getHotPoint(1).getX(), this.getHotPoint(1)
				.getY(), this.getHotPoint(7).getX(),
				this.getHotPoint(7).getY(), p.getX(), p.getY()) < 5) {
			return -1;
		}

		return 0;
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
//		Point p1=null;
//		Point p2=null;
		switch (index) {
		case 1:
			this.setBegin(changePoint);
			break;
		case 2:
			this.setBegin(new Point(this.getBegin().x,changePoint.y));
			break;
		case 3:
			this.getHitPoints();
			this.setBegin(new Point(this.getBegin().x,(this.getBegin().y+changePoint.y-this.getHotPoint(3).y)));
			this.setEnd(new Point(this.getEnd().x+changePoint.x - this.getHotPoint(3).x,this.getEnd().y));
			break;
		case 4:
			this.setEnd(new Point(changePoint.x,this.getEnd().y));
			break;
		case 5:
			this.setEnd(changePoint);
			break;
		case 6:
			this.setEnd(new Point(this.getEnd().x,changePoint.y));
			break;
		case 7:
			this.getHitPoints();
			this.setBegin(new Point(this.getBegin().x+changePoint.x-this.getHotPoint(7).x,this.getBegin().y));
			this.setEnd(new Point(this.getEnd().x,changePoint.y -this.getHotPoint(7).y+this.getEnd().y));
			break;
		case 8:
			this.setBegin(new Point(changePoint.x,this.getBegin().y));
			break;
		case 0:
			break;
		case -1:
			Point tempP=new Point();
			tempP.setLocation(this.getBegin().x+changePoint.x,this.getBegin().y+changePoint.y);
			this.setBegin(tempP);
			tempP=new Point();
			tempP.setLocation(this.getEnd().x+changePoint.x,this.getEnd().y+changePoint.y);
			this.setEnd(tempP);
		default:
//			this.setBegin(begin)
			break;
		}

	}
	/**
	 * 绘图
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		g2d.draw(this.newShape());
		if(this.isSelect()){
//			g2d.setXORMode(Color.WHITE);
			this.htD.setHotPoints(this.getHitPoints());
			this.htD.draw(g);
		}
	}
	/**
	 * 设置热点
	 */
	@Override
	public Point[] getHitPoints() {
		Point[] hP = new Point[8];
		hP[0] = new Point(this.getBegin().x, this.getBegin().y);
		hP[1] = new Point((this.getBegin().x + this.getEnd().x) / 2, this
				.getBegin().y);
		hP[2] = new Point(this.getEnd().x, this.getBegin().y);
		hP[3] = new Point(this.getEnd().x,
				(this.getBegin().y + this.getEnd().y) / 2);
		hP[4] = new Point(this.getEnd().x, this.getEnd().y);
		hP[5] = new Point((this.getBegin().x + this.getEnd().x) / 2, this
				.getEnd().y);
		hP[6] = new Point(this.getBegin().x, this.getEnd().y);
		hP[7] = new Point(this.getBegin().x,
				(this.getBegin().y + this.getEnd().y) / 2);
		this.setHotPoints(hP);
		return hP;
	}
	/**
	 * 接口实现
	 */
	public SuperShape propertyToShape(ShapePropertyTool shapeProObject) {
		RectangleShape tempRectangleShape =new RectangleShape();
		tempRectangleShape.setBegin(new Point((int)shapeProObject.getBeginPoint()[0],(int)shapeProObject.getBeginPoint()[1]));
		tempRectangleShape.setEnd(new Point((int)shapeProObject.getEndPoint()[0],(int)shapeProObject.getEndPoint()[1]));
		tempRectangleShape.setClor(shapeProObject.getShapeColor());
		
		tempRectangleShape.setSb(shapeProObject.getSb());
		tempRectangleShape.setHl(shapeProObject.getHl());
		tempRectangleShape.setJd(shapeProObject.getJd());
		
		return tempRectangleShape;
	}

	/**
	 * 矩形
	 */
	public Shape newShape() {
		// TODO Auto-generated method stub
//		o=new Point((this.getBegin().x+this.getEnd().x)/2,(this.getBegin().y+this.getEnd().y)/2);
		Rectangle2D re = new Rectangle2D.Double();
		re.setFrameFromDiagonal(this.getBegin(), this.getEnd());
		return re;
	}
	/**
	 * 快照
	 */
	@Override
	public SuperShape copySelf() {
		RectangleShape tempSelf =new RectangleShape();
		tempSelf.setBegin(new Point());
		tempSelf.setBegin(this.getBegin());
		
		tempSelf.setEnd(new Point());
		tempSelf.setEnd(this.getEnd());
		
		
		tempSelf.setClor(Color.white);
		tempSelf.setClor(this.getClor());
		
		return tempSelf;
	}
	
	

}
