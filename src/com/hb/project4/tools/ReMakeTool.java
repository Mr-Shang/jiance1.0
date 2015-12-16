package com.hb.project4.tools;

import com.hb.project4.shapes.SuperShape;

//此接口用于在文件读取是从shapeProperty对象重新获得shape对象
//详情请参见类ShapePropertyTool（即Shape属性类，用于保存Shape的属性:
//benginPoint,endPoint,color等）
//
public interface ReMakeTool {
	SuperShape propertyToShape(ShapePropertyTool shapeProObject) ;
}
