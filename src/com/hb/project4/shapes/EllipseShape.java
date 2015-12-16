package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import com.hb.project4.tools.ShapePropertyTool;
/**
 * ��Բ
 * @author fm
 *
 */
public class EllipseShape extends SuperShape{
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
		Point o=new Point((this.getBegin().x+this.getEnd().x)/2,(this.getBegin().y+this.getEnd().y)/2);
		double w=Math.abs(this.getBegin().x-this.getEnd().x);
		double h=Math.abs(this.getBegin().y-this.getEnd().y);
		if(!this.newShape(o,w-5,h-5).contains(p)&&this.newShape(o,w+5,h+5).contains(p)){
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
	public void setHotPoint(int index, Point changePoint) {
		switch (index) {
		case 1:
			this.setBegin(new Point(this.getBegin().x,changePoint.y));
			break;
		case 2:
			this.setEnd(new Point(changePoint.x,this.getEnd().y));
			break;
		case 3:
			this.setEnd(new Point(this.getEnd().x,changePoint.y));
			break;
		case 4:
			this.setBegin(new Point(changePoint.x,this.getBegin().y));
			break;
		case 0:
			break;
		default:
			break;
		}

	}
	/**
	 * ͼ�λ���
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		Point o=new Point((this.getBegin().x+this.getEnd().x)/2,(this.getBegin().y+this.getEnd().y)/2);
		double w=Math.abs(this.getBegin().x-this.getEnd().x);
		double h=Math.abs(this.getBegin().y-this.getEnd().y);
		g2d.draw(this.newShape(o,w,h));
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
		Point[] hP = new Point[4];
		hP[0] = new Point((this.getBegin().x + this.getEnd().x) / 2, this
				.getBegin().y);
		hP[1] = new Point(this.getEnd().x,
				(this.getBegin().y + this.getEnd().y) / 2);
		hP[2] = new Point((this.getBegin().x + this.getEnd().x) / 2, this
				.getEnd().y);
		hP[3] = new Point(this.getBegin().x,
				(this.getBegin().y + this.getEnd().y) / 2);
		this.setHotPoints(hP);
		return hP;
	}
	
	/**
	 * ��ͼ
	 * @param o ����
	 * @param w ��
	 * @param h ��
	 * @return ��Բ
	 */
	public Shape newShape(Point o, double w,double h) {
//		o=new Point((this.getBegin().x+this.getEnd().x)/2,(this.getBegin().x+this.getEnd().x)/2);
//		w=Math.abs(this.getBegin().x-this.getEnd().x);
//		h=w=Math.abs(this.getBegin().y-this.getEnd().y);
		Ellipse2D re = new Ellipse2D.Double(o.x-w/2,o.y-h/2,w,h);
		return re;
	}
	/**
	 * �ӿ�ʵ��
	 */
	public SuperShape propertyToShape(ShapePropertyTool shapeProObject) {
		EllipseShape tempEllipseShape =new EllipseShape();
		tempEllipseShape.setBegin(new Point((int)shapeProObject.getBeginPoint()[0],(int)shapeProObject.getBeginPoint()[1]));
		tempEllipseShape.setEnd(new Point((int)shapeProObject.getEndPoint()[0],(int)shapeProObject.getEndPoint()[1]));
		tempEllipseShape.setClor(shapeProObject.getShapeColor());
		
		tempEllipseShape.setSb(shapeProObject.getSb());
		tempEllipseShape.setHl(shapeProObject.getHl());
		tempEllipseShape.setJd(shapeProObject.getJd());

		
		return tempEllipseShape;
	}
	/**
	 * ����
	 */
	@Override
	public SuperShape copySelf() {
		EllipseShape tempSelf =new EllipseShape();
		tempSelf.setBegin(new Point());
		tempSelf.setBegin(this.getBegin());
		
		tempSelf.setEnd(new Point());
		tempSelf.setEnd(this.getEnd());
		
		
		tempSelf.setClor(Color.white);
		tempSelf.setClor(this.getClor());
		
		return tempSelf;
	}

}
