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
 * ������
 * @author fm
 *
 */
public class SquareShape extends SuperShape{
	

	public SquareShape() {
		this.setShape(new Rectangle2D.Double());
	}

	/**
	 * �õ���ǰ���ȵ����,���򷵻�0
	 */
	@Override
	public int checkOnShape(Point p) {

		// ���Ƶ��Ϸ�����Ӧ�ĵ������
		for (int i = 0; i < this.getHitPoints().length; i++) {
			Ellipse2D tempEllipse2D = new Ellipse2D.Double();
			tempEllipse2D.setFrameFromDiagonal(new Point(this.getHotPoint(i + 1).x - 4, this
					.getHotPoint(i + 1).y - 4),new Point(this.getHotPoint(i + 1).x + 4, this
							.getHotPoint(i + 1).y + 4));
			if (tempEllipse2D.contains(p)) {
				return (i+1);
			}
		}

		// ͼ�α�Ե����-1
		Point o=new Point(this.getBegin());
		double r=Math.sqrt(2)*this.getBegin().distance(this.getEnd());
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
	public void setHotPoint(int index, Point changePoint) {
		int h=Math.abs(changePoint.y-this.getBegin().y);
		int w=Math.abs(changePoint.x-this.getBegin().x);
		switch (index) {
		case 1:
			this.setEnd(changePoint);
			break;
		case 2://
			this.setEnd(new Point(this.getBegin().x-h,this.getBegin().y-h));
			break;
		case 3:
			this.setEnd(changePoint);
			break;
		case 4:
			this.setEnd(new Point(this.getBegin().x-w,this.getBegin().y-w));
			break;
		case 5:
			this.setEnd(changePoint);
			break;
		case 6:
			this.setEnd(new Point(this.getBegin().x-h,this.getBegin().y-h));
			break;
		case 7:
			this.setEnd(changePoint);
			break;
		case 8:
			this.setEnd(new Point(this.getBegin().x-w,this.getBegin().y-w));
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
	 * ��ͼ
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getClor());
		Point o=new Point(this.getBegin());
		double r=Math.sqrt(2)*this.getBegin().distance(this.getEnd());
		g2d.draw(this.newShape(o, r));
		if(this.isSelect()){
//			g2d.setXORMode(Color.WHITE);
			this.htD.setHotPoints(this.getHitPoints());
			this.htD.draw(g);
		}
	}
	/**
	 * �����ȵ�
	 */
	@Override
	public Point[] getHitPoints() {
		int r=(int)(Math.sqrt(2)*this.getBegin().distance(this.getEnd())/2);
		Point[] hP = new Point[8];
		hP[0] = new Point(this.getBegin().x-r, this.getBegin().y-r);
		hP[1] = new Point(this.getBegin().x, this.getBegin().y-r);
		hP[2] = new Point(this.getBegin().x+r, this.getBegin().y-r);
		hP[3] = new Point(this.getBegin().x+r,this.getBegin().y );
		hP[4] = new Point(this.getBegin().x+r, this.getBegin().y+r);
		hP[5] = new Point(this.getBegin().x, this.getBegin().y+r);
		hP[6] = new Point(this.getBegin().x-r, this.getBegin().y+r);
		hP[7] = new Point(this.getBegin().x-r, this.getBegin().y );
		this.setHotPoints(hP);
		return hP;
	}
	/**
	 * �ӿ�ʵ��
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
	 * ������
	 * @param o ����
	 * @param r �߳�
	 * @return ������
	 */
	public Shape newShape(Point o, double r) {
		// TODO Auto-generated method stub
//		o=new Point((this.getBegin().x+this.getEnd().x)/2,(this.getBegin().y+this.getEnd().y)/2);
		Rectangle2D re = new Rectangle2D.Double(o.x-r/2,o.y-r/2,r,r);
		return re;
	}
	/**
	 * ����
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
