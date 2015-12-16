package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import com.hb.project4.tools.ReMakeTool;
import com.hb.project4.tools.ShapePropertyTool;
/**
 * ͼ�γ�����
 * @author fm
 *
 */
public abstract class SuperShape implements ReMakeTool {
	/**
	 * �Ƿ�ѡȡ
	 */
	private boolean isSelect = false;
	/**
	 * ��ʼ��
	 */
	private Point begin;
	/**
	 * ������
	 */
	private Point end;
	/**
	 * ��ɫ
	 */
	private Color clor;
	/**
	 * ͼ��
	 */
	private Shape shape;
	/**
	 * �ȵ�
	 */
	private Point[] hotPoints;
	/**
	 * �ȵ�ͼ��
	 */
	HotPointShape htD =new HotPointShape();
	/**
	 * �����ȵ�
	 * @param hotPoints
	 */
	public void setHotPoints(Point[] hotPoints) {
		this.hotPoints = hotPoints;
	}
	/**
	 * �õ��ȵ�
	 * @return
	 */
	public Point[] getHotPoints() {
		return hotPoints;
	}
	/**
	 * �õ���ʼ��
	 * @return
	 */
	public Point getBegin() {
		return begin;
	}
	/**
	 * ������ʼ��
	 * @param begin
	 */
	public void setBegin(Point begin) {
		this.begin = begin;
	}
	/**
	 * �õ���ɫ
	 * @return
	 */
	public Color getClor() {
		return clor;
	}
	/**
	 * ������ɫ
	 * @param clor
	 */
	public void setClor(Color clor) {
		this.clor = clor;
	}
	/**
	 * �õ�������
	 * @return
	 */
	public Point getEnd() {
		return end;
	}
	/**
	 * ���ý�����
	 * @param end
	 */
	public void setEnd(Point end) {
		this.end = end;
	}
	/**
	 * �õ�ͼ��
	 * @return
	 */
	public Shape getShape() {
		return shape;
	}
	/**
	 * ����ͼ��
	 * @param shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	/**
	 * �Ƿ�ѡȡ
	 * @return
	 */
	public boolean isSelect() {
		return isSelect;
	}
	/**
	 * ����ѡȡ״̬
	 * @param isSelect
	 */
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * ����ͼ�εĻ���
	 * @param g
	 */
	public abstract void draw(Graphics g);

	/**
	 * �õ���Ӧλ�õ��ȵ�
	 * @param index
	 * @return
	 */
	public abstract Point getHotPoint(int index);

	/**
	 * ���ݸı���ȵ�������begin��end��
	 * @param index
	 * @param changePoint
	 */
	public abstract void setHotPoint(int index, Point changePoint);

	/**
	 * �жϵ���ͼ�ε�λ��
	 * @param p
	 * @return -1Ϊ��Ե��0Ϊ����ͼ���ϣ�����Ϊ��Ӧ���ȵ�
	 */
	public abstract int checkOnShape(Point p);

	/**
	 * �õ������ȵ��ֵ
	 * @return
	 */
	public abstract Point[] getHitPoints();

	/**
	 * ����ͼ�α���
	 * @return
	 */
	public abstract SuperShape copySelf();
	
	/*
	 *�����豸���,��·,�ڵ���Ϣ  
	 * */
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = sb;
	}
	public String getHl() {
		return hl;
	}
	public void setHl(String hl) {
		this.hl = hl;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}

	private String sb = "";
	private String hl = "";
	private String jd = "";
}
