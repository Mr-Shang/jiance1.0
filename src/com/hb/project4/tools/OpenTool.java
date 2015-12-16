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
 * �������ڶ�ȡ�ļ��б����ͼ��
 * @author ������
 * ʱ�䣺2008.08.04
 */
public class OpenTool extends SuperTool {
	private ArrayList<SuperShape> shapeArray;
	private ArrayList<ShapePropertyTool> shapePropArray;//�����ļ����Ե�����
	
	public OpenTool(DrawJPanel j) {
		super(j);
		shapeArray = new ArrayList<SuperShape>();
		shapePropArray = new ArrayList<ShapePropertyTool>();
	}
	/**
	 * �÷�����ȡ�ļ�file�е�ͼ��
	 * @param file ����ͼ�ε��ļ�
	 * @return ���ش��ļ�file�з��ص�ͼ������ArrayList<SuperShape> 
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
			JOptionPane.showMessageDialog(null, "�ļ�����!");
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
