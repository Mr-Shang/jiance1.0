package com.hb.project4.tools;

import com.hb.project4.shapes.SuperShape;

//�˽ӿ��������ļ���ȡ�Ǵ�shapeProperty�������»��shape����
//������μ���ShapePropertyTool����Shape�����࣬���ڱ���Shape������:
//benginPoint,endPoint,color�ȣ�
//
public interface ReMakeTool {
	SuperShape propertyToShape(ShapePropertyTool shapeProObject) ;
}
