package ccu.edu.vc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;




/**
 * Servlet implementation class Video
 */
@WebServlet("/VideoServlet")
public class VideoServlet extends HttpServlet {
	
	private SipPhone sp = null;
	private static final long serialVersionUID = 1L;
	
	public void  init() throws ServletException {
        sp = new SipPhone();
        sp.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = Common.getParamsMap(request.getQueryString(), "utf-8");
		String comm = map.get("command")[0];
		String code = map.get("code")[0];

		//String me = "sip:22030100000000000001@122.141.94.91:5060";
	    String to = "sip:"+ code +"@36.48.166.6:7100";  
	    //String ms_xml = "<?xml version='1.0'?>\n <Query>\r\n <CmdType>Catalog</CmdType>\r\n <SN>471</SN>\r\n <DeviceID>22030100000000000002</DeviceID>\r\n </Query>\r\n";
	    String message = "v=0\r\no=" + code +" 0 0 IN IP4 122.141.94.91\r\ns=Play\r\nc=IN IP4 122.141.94.91\r\nt=0 0\r\nm=video 5494 RTP/AVP 96 97 98\r\na=rtpmap:96 PS/90000\r\na=rtpmap:97 MPEG4/90000\r\na=rtpmap:98 H264/90000\r\na=recvonly\r\ny=0100000001";
		
		if(comm.equals("Play"))  {
			//
			sp.sendInvite(to, message);
			//
		}
		else if(comm.equals("Playback")) {
			//
			sp.sendInvite(to, message);
			//			
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String jsonStr = "{\"result\":\"ok\"}";
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(jsonStr);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
