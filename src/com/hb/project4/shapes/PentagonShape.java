package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.hb.project4.tools.ShapePropertyTool;
/**
 * �������
 * @author fm
 *
 */
public class PentagonShape extends SuperShape{
	
	private double a;//����ת��˳ʱ����ת�Ƕ�
	public PentagonShape() {
		this.setShape(new GeneralPath());
	}
	/**
	 * �õ���ǰ���ȵ����,���򷵻�0
	 */
	@Override
	public int checkOnShape(Point p) {
		// TODO Auto-generated method stub
//		 ���Ƶ��Ϸ�����Ӧ�ĵ������
		for (int i = 0; i < this.getHitPoints().length; i++) {
			Ellipse2D tempEllipse2D = new Ellipse2D.Double();
			tempEllipse2D.setFrameFromDiagonal(new Point(this.getHotPoint(i + 1).x - 4, this
					.getHotPoint(i + 1).y - 4),new Point(this.getHotPoint(i + 1).x + 4, this
							.getHotPoint(i + 1).y + 4));
			if (tempEllipse2D.contains(p)) {
				return i + 1;
			}
		}
		// ͼ�α�Ե����-1
		Point o=new Point(this.getBegin());
		double r=this.getBegin().distance(this.getEnd());
		if(!this.newShape(o, r-5).contains(p)&&this.newShape(o, r+5).contains(p)){
			return -1;
		}
		return 0;
	}
	/**
	 * �õ���ǰ��ŵ��ȵ�
	 */
	@Override
	public Point getHotPoint(int index) {

		Point[] pO = this.getHotPoints();

		return pO[index - 1];

	}
	/**
	 * �ȵ��϶��ػ�ͼ��
	 */
	@Override
	public void setHotPoint(int index,Point changePoint) {
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
		case 5:
			this.setEnd(changePoint);
			break;
		case 6:
			a=theta(new Point((int)(this.getBegin().x),(int)(this.getBegin().y-this.getBegin().distance(this.getEnd()))),
					this.getBegin(), changePoint);
		case 0:
			break;
		default:
			break;
		}
	}
	/**
	 * ��ͼ
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		Point o=new Point(this.getBegin());
		double r=this.getBegin().distance(this.getEnd());
		g2d.draw(this.newShape(o,r));
		if(this.isSelect()){
			this.htD.setHotPoints(this.getHitPoints());
			this.htD.draw(g);
		}
	}
	/**
	 * �����ȵ�
	 */
	@Override
	public Point[] getHitPoints() {
		double x1=this.getBegin().x;
		double y1=this.getBegin().y;
		double r=this.getBegin().distance(this.getEnd());
		Point[] hP = new Point[6];
		hP[0] = rotatePT(this.getBegin(),new Point((int)(x1),(int)(y1-r)),a);
		hP[1] = rotatePT(this.getBegin(),hP[0],Math.PI/2.5);
		hP[2] = rotatePT(this.getBegin(),hP[1],Math.PI/2.5);
		hP[3] = rotatePT(this.getBegin(),hP[2],Math.PI/2.5);
		hP[4] = rotatePT(this.getBegin(),hP[3],Math.PI/2.5);
		hP[5] =rotatePT(this.getBegin(),new Point((int)(x1),(int)(y1-r)-10),a);//��ת��
		this.setHotPoints(hP);
		return hP;
	}
	/**
	 * ͼ��
	 * @param o ����
	 * @param r �뾶
	 * @return �������
	 */
	public Shape newShape(Point o, double r){
		double x1=o.x;
		double y1=o.y;
		Point2D p1=rotatePT(this.getBegin(),new Point((int)(x1),(int)(y1-r)),a);
		Point2D p2=rotatePT(this.getBegin(),new Point((int)p1.getX(),(int)p1.getY()),Math.PI/2.5);
		Point2D p3=rotatePT(this.getBegin(),new Point((int)p2.getX(),(int)p2.getY()),Math.PI/2.5);
		Point2D p4=rotatePT(this.getBegin(),new Point((int)p3.getX(),(int)p3.getY()),Math.PI/2.5);
		Point2D p5=rotatePT(this.getBegin(),new Point((int)p4.getX(),(int)p4.getY()),Math.PI/2.5);
		Line2D l1=new Line2D.Double(p1,p2);
		Line2D l2=new Line2D.Double(p2,p3);
		Line2D l3=new Line2D.Double(p3,p4);
		Line2D l4=new Line2D.Double(p4,p5);
		Line2D l5=new Line2D.Double(p5,p1);
		GeneralPath pentagonPath=new GeneralPath();
		pentagonPath.append(l1,true);
		pentagonPath.append(l2,true);
		pentagonPath.append(l3,true);
		pentagonPath.append(l4,true);
		pentagonPath.append(l5,true);
		return pentagonPath;
	}
	/**
	 * ʵ�ֽӿ�
	 */
	public SuperShape propertyToShape(ShapePropertyTool shapeProObject) {
		PentagonShape tempPentagonShape =new PentagonShape();
		tempPentagonShape.setBegin(new Point((int)shapeProObject.getBeginPoint()[0],(int)shapeProObject.getBeginPoint()[1]));
		tempPentagonShape.setEnd(new Point((int)shapeProObject.getEndPoint()[0],(int)shapeProObject.getEndPoint()[1]));
		tempPentagonShape.setClor(shapeProObject.getShapeColor());
		
		tempPentagonShape.setSb(shapeProObject.getSb());
		tempPentagonShape.setHl(shapeProObject.getHl());
		tempPentagonShape.setJd(shapeProObject.getJd());

		return tempPentagonShape;
	}
	/**
	 * ˳ʱ��н�
	 * @param begin ��ʼ��
	 * @param o Բ��
	 * @param end ������
	 * @return �н�
	 */
	public static double theta(Point begin,Point o,Point end){
		int x1=begin.x-o.x;
		int y1=begin.y-o.y;
		int x2=end.x-o.x;
		int y2=end.y-o.y;
		return Math.atan2(x1, y1)-Math.atan2(x2, y2);
	}
	/**
	 * ��ת
	 * @param x0y0 Բ��
	 * @param xy ����ת��
	 * @param a ˳ʱ����ת�Ƕ�
	 * @return ��ת��ĵ�
	 */
	public static Point rotatePT(Point x0y0,Point xy,double a)
	{
	int x=(int)(x0y0.x+(xy.x-x0y0.x)*Math.cos(a)-(xy.y-x0y0.y)*Math.sin(a));
	int y=(int)(x0y0.y+(xy.y-x0y0.y)*Math.cos(a)+(xy.x-x0y0.x)*Math.sin(a));
	return new Point(x,y);
	}

	@Override
	public SuperShape copySelf() {
		PentagonShape tempSelf =new PentagonShape();
		tempSelf.setBegin(new Point());
		tempSelf.setBegin(this.getBegin());
		
		tempSelf.setEnd(new Point());
		tempSelf.setEnd(this.getEnd());
		
		
		tempSelf.setClor(Color.white);
		tempSelf.setClor(this.getClor());
		tempSelf.a=this.a;
		
		return tempSelf;
	}

}