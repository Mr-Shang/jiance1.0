package com.hb.project4.tools;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.hb.project4.drawPanel.DrawJPanel;
import com.hb.project4.shapes.SuperShape;
/**
 * 该类用于读取文件中保存的图形
 * @author 杨鹏飞
 * 时间：2008.08.04
 */
public class OpenTool extends SuperTool {
	private ArrayList<SuperShape> shapeArray;
	private ArrayList<ShapePropertyTool> shapePropArray;//保存文件属性的数组
	
	public OpenTool(DrawJPanel j) {
		super(j);
		shapeArray = new ArrayList<SuperShape>();
		shapePropArray = new ArrayList<ShapePropertyTool>();
	}
	/**
	 * 该方法读取文件file中的图形
	 * @param file 保存图形的文件
	 * @return 返回从文件file中返回的图形数组ArrayList<SuperShape> 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SuperShape> openFile(File file){
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			try {
				shapePropArray =(ArrayList<ShapePropertyTool>) in.readObject();
				for(ShapePropertyTool r:shapePropArray){
					String className = r.getClassName();
					try {
						ReMakeTool reMakedShape = (ReMakeTool)Class.forName(className).newInstance();
						shapeArray.add(reMakedShape.propertyToShape(r));
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "文件错误!");
		}
		return shapeArray;
	}

	@Override
	public void mouseClickedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDraggedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMovedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleasedAction(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
	}
}
