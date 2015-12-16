package com.hb.project4.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import com.hb.project4.tools.ReMakeTool;
import com.hb.project4.tools.ShapePropertyTool;
/**
 * 图形抽象类
 * @author fm
 *
 */
public abstract class SuperShape implements ReMakeTool {
	/**
	 * 是否选取
	 */
	private boolean isSelect = false;
	/**
	 * 起始点
	 */
	private Point begin;
	/**
	 * 结束点
	 */
	private Point end;
	/**
	 * 颜色
	 */
	private Color clor;
	/**
	 * 图形
	 */
	private Shape shape;
	/**
	 * 热点
	 */
	private Point[] hotPoints;
	/**
	 * 热点图形
	 */
	HotPointShape htD =new HotPointShape();
	/**
	 * 设置热点
	 * @param hotPoints
	 */
	public void setHotPoints(Point[] hotPoints) {
		this.hotPoints = hotPoints;
	}
	/**
	 * 得到热点
	 * @return
	 */
	public Point[] getHotPoints() {
		return hotPoints;
	}
	/**
	 * 得到起始点
	 * @return
	 */
	public Point getBegin() {
		return begin;
	}
	/**
	 * 设置起始点
	 * @param begin
	 */
	public void setBegin(Point begin) {
		this.begin = begin;
	}
	/**
	 * 得到颜色
	 * @return
	 */
	public Color getClor() {
		return clor;
	}
	/**
	 * 设置颜色
	 * @param clor
	 */
	public void setClor(Color clor) {
		this.clor = clor;
	}
	/**
	 * 得到结束点
	 * @return
	 */
	public Point getEnd() {
		return end;
	}
	/**
	 * 设置结束点
	 * @param end
	 */
	public void setEnd(Point end) {
		this.end = end;
	}
	/**
	 * 得到图形
	 * @return
	 */
	public Shape getShape() {
		return shape;
	}
	/**
	 * 设置图形
	 * @param shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	/**
	 * 是否被选取
	 * @return
	 */
	public boolean isSelect() {
		return isSelect;
	}
	/**
	 * 设置选取状态
	 * @param isSelect
	 */
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * 各个图形的画法
	 * @param g
	 */
	public abstract void draw(Graphics g);

	/**
	 * 得到相应位置的热点
	 * @param index
	 * @return
	 */
	public abstract Point getHotPoint(int index);

	/**
	 * 根据改变的热点来重置begin和end点
	 * @param index
	 * @param changePoint
	 */
	public abstract void setHotPoint(int index, Point changePoint);

	/**
	 * 判断点在图形的位置
	 * @param p
	 * @return -1为边缘，0为不在图形上，其他为相应的热点
	 */
	public abstract int checkOnShape(Point p);

	/**
	 * 得到所有热点的值
	 * @return
	 */
	public abstract Point[] getHitPoints();

	/**
	 * 复制图形本身
	 * @return
	 */
	public abstract SuperShape copySelf();
	
	/*
	 *处理设备编号,回路,节点信息  
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
