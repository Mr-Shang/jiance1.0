package com.hb.project4.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.hb.project4.shapes.RectangleShape;
import com.hb.project4.shapes.SuperShape;

public class ShapePropertyTool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3884474213590618585L;

	private int[] beginPoint;

	private int[] endPoint;

	private Color shapeColor;
	//
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

	private String sb;
	private String hl;
	private String jd;
//
	private SuperShape tempShape;

	private String className;

	public ShapePropertyTool(SuperShape myShape) {
		beginPoint = new int[2];
		endPoint = new int[2];
		
		beginPoint[0] = myShape.getBegin().x;
		beginPoint[1] = myShape.getBegin().y;
		
		endPoint[0] = myShape.getEnd().x;
		endPoint[1] = myShape.getEnd().y;
		
		shapeColor = myShape.getClor();
		
		sb = myShape.getSb();
		hl = myShape.getHl();
		jd = myShape.getJd();
		
		className = myShape.getClass().getName();
	}

	public int[] getBeginPoint() {
		return beginPoint;
	}

	public int[] getEndPoint() {
		return endPoint;
	}

	public Color getShapeColor() {
		return shapeColor;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
