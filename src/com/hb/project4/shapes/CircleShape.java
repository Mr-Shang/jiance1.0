package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.hb.project4.tools.ShapePropertyTool;
/**
 * 圆
 * @author fm
 *
 */
public class CircleShape extends SuperShape{

	public CircleShape() {
		this.setShape(new Ellipse2D.Double());
	}
	/**
	 * 得到当前点热点序号,无则返回0
	 */
	@Override
	public int checkOnShape(Point p) {
		// TODO Auto-generated method stub
//		 控制点上返回相应的点的序列
		for (int i = 0; i < this.getHitPoints().length; i++) {
			Ellipse2D tempEllipse2D = new Ellipse2D.Double();
			tempEllipse2D.setFrameFromDiagonal(new Point(this.getHotPoint(i + 1).x - 4, this
					.getHotPoint(i + 1).y - 4),new Point(this.getHotPoint(i + 1).x + 4, this
							.getHotPoint(i + 1).y + 4));
			if (tempEllipse2D.contains(p)) {
				return i + 1;
			}
		}
		// 图形边缘返回-1
		Point o=new Point(this.getBegin());
		double r=this.getBegin().distance(this.getEnd());
		if(!this.newShape(o, r-5).contains(p)&&this.newShape(o, r+5).contains(p)){
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
		switch (index) {
		case 1:
			this.setEnd(changePoint);
			break;
		case 2:
			this.setEnd(changePoint);
			break;
		case 3:
			this.setEnd(changePoint);
			break;
		case 4:
			this.setEnd(changePoint);
			break;
		case 0:
			break;
		default:
			break;
		}
	}
	/**
	 * 图形绘制
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Point o=new Point(this.getBegin());
		double r=this.getBegin().distance(this.getEnd());
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		g2d.draw(this.newShape(o,r));
		if(this.isSelect()){
			this.htD.setHotPoints(this.getHitPoints());
			this.htD.draw(g);
		}
	}
	/**
	 * 设置热点
	 */
	@Override
	public Point[] getHitPoints() {
		int r=(int)this.getBegin().distance(this.getEnd());
		Point[] hP = new Point[4];
		hP[0] = new Point(this.getBegin().x, this.getBegin().y-r);
		hP[1] = new Point(this.getBegin().x+r,this.getBegin().y);
		hP[2] = new Point(this.getBegin().x,this.getBegin().y+r);
		hP[3] = new Point(this.getBegin().x-r,this.getBegin().y);
		this.setHotPoints(hP);
		return hP;
	}

	/**
	 * 绘圆
	 * @param o 中心
	 * @param r 半径
	 * @return 圆
	 */
	public Shape newShape(Point o, double r) {
		// TODO Auto-generated method stub
		Ellipse2D cirl = new Ellipse2D.Double();
		cirl.setFrameFromDiagonal(o.x-r, o.y-r,o.x+r,o.y+r);
		return cirl;
	}
	/**
	 * 实现接口
	 */
	public SuperShape propertyToShape(ShapePropertyTool shapeProObject) {
		CircleShape tempCircleShape =new CircleShape();
		tempCircleShape.setBegin(new Point((int)shapeProObject.getBeginPoint()[0],(int)shapeProObject.getBeginPoint()[1]));
		tempCircleShape.setEnd(new Point((int)shapeProObject.getEndPoint()[0],(int)shapeProObject.getEndPoint()[1]));
		tempCircleShape.setClor(shapeProObject.getShapeColor());
		
		tempCircleShape.setSb(shapeProObject.getSb());
		tempCircleShape.setHl(shapeProObject.getHl());
		tempCircleShape.setJd(shapeProObject.getJd());

		
		return tempCircleShape;
	}
	/**
	 * 快照
	 */
	@Override
	public SuperShape copySelf() {
		CircleShape tempSelf =new CircleShape();
		tempSelf.setBegin(new Point());
		tempSelf.setBegin(this.getBegin());
		
		tempSelf.setEnd(new Point());
		tempSelf.setEnd(this.getEnd());
		
		
		tempSelf.setClor(Color.white);
		tempSelf.setClor(this.getClor());
		
		return tempSelf;
	}

	

}
