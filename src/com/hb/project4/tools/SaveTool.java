package com.hb.project4.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.hb.project4.drawPanel.DrawJPanel;
import com.hb.project4.shapes.SuperShape;
/**
 * 该类用于保存当前画板中的图形
 * @author Administrator
 *时间：2008.08.04
 */
public class SaveTool extends SuperTool {
	private ArrayList<ShapePropertyTool> pictureTempArray;
	public SaveTool(DrawJPanel j) 
	{
		super(j);	
		pictureTempArray = new ArrayList<ShapePropertyTool>();
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
	/**
	 * 该方法用于保存shapeArray中的图形到文件file中
	 * @param shapeArray要保存的图形的数组
	 * @param file保存到的文件夹
	 */
	public void save(ArrayList<SuperShape> shapeArray, File file) {
		for (SuperShape r : shapeArray)
			pictureTempArray.add(new ShapePropertyTool(r));
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(pictureTempArray);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pictureTempArray.clear();//必须有！
	}
}
