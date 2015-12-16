package ccu.edu.vc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hb.project4.shapes.SuperShape;
import com.hb.project4.tools.ReMakeTool;
import com.hb.project4.tools.ShapePropertyTool;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class CrtServlet
 */
@WebServlet("/CrtServlet")
public class CrtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 内容类型
	private final String CONTENT_TYPE = "image/jpeg";

	//doGet
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String[]> map = Common.getParamsMap(request.getQueryString(), "utf-8");
		String crtPath = map.get("file")[0];
		
		String sb = map.get("sb")[0];
		
		String[] hl = map.get("hl");
		String[] jd = map.get("jd");

		ArrayList<SuperShape> shapeArray = new ArrayList<SuperShape>();
		ArrayList<ShapePropertyTool> shapePropArray = new ArrayList<ShapePropertyTool>();

		// 要输出的字符串
		String outputString = "消防CRT图示";

		// 设置输出字符串的属性
		int fontsize = 20; // 字体大小
		String fontname = "宋体"; // 字体名称
		int x = 15;// 横坐标
		int y = 20;// 纵坐标

		int width = 1200;
		int height = 800;

		// 得到图形，用于作图
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		try {
			ObjectInputStream in = new ObjectInputStream(getServletContext().getResourceAsStream(crtPath));
			try {
				shapePropArray = (ArrayList<ShapePropertyTool>)in.readObject();
				for (ShapePropertyTool r : shapePropArray) {
					String className = r.getClassName();
					try {
						ReMakeTool reMakedShape = (ReMakeTool) Class.forName(className).newInstance();
						shapeArray.add(reMakedShape.propertyToShape(r));
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				// 关闭数据流
				in.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 设置颜色
		g.setColor(Color.red);

		for (int i = 0; i < shapeArray.size(); i++) {
			for(String hls: hl) {
				for (String jds : jd) {
					if ((((SuperShape) shapeArray.get(i)).getHl().equals(hls)) &&
						(((SuperShape) shapeArray.get(i)).getJd().equals(jds)) &&
						(((SuperShape) shapeArray.get(i)).getSb().equals(sb))) {
						((SuperShape) shapeArray.get(i)).setClor(Color.red);						
					}
				}
			}			
			((SuperShape) shapeArray.get(i)).draw(g);
		}

		// 设置字体
		g.setFont(new Font(fontname, Font.ITALIC, fontsize));

		// 输出字符串
		g.drawString(outputString, x, y);

		// 设置response对象的ContentType
		response.setContentType(CONTENT_TYPE);

		// 得到servlet输出流
		ServletOutputStream out = response.getOutputStream();

		// 输出数据流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);

		// 关闭servlet输出流
		out.close();
	}
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
