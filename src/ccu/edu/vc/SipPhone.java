package ccu.edu.vc;

//import gov.nist.javax.sip.header.CSeq;
//import gov.nist.javax.sip.header.ContentLength;
//import gov.nist.javax.sip.header.ContentType;
//import gov.nist.javax.sip.header.From;
//import gov.nist.javax.sip.header.Via;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import javax.sip.*;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.address.URI;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
//import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

public class SipPhone implements SipListener {
	public void processDialogTerminated(DialogTerminatedEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("processDialogTerminated " + arg0.toString());
	}

	public void processIOException(IOExceptionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("processIOException " + arg0.toString());
	}

	public void processTimeout(TimeoutEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(" processTimeout " + arg0.toString());
	}

	public void processTransactionTerminated(TransactionTerminatedEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(" processTransactionTerminated "
				+ arg0.getClientTransaction().getBranchId() + " "
				+ arg0.getServerTransaction().getBranchId());
	}

	/*
	 */
	public void processRequest(RequestEvent arg0) {
		Request request = arg0.getRequest();
		if (null == request) {
			System.out.println("processRequest request is null.");
			return;
		}
		System.out.println("processRequest:" + request.toString());
		if (Request.REGISTER.equals(request.getMethod())) {
			registerRequest(request, arg0);
		} else if (Request.MESSAGE.equalsIgnoreCase(request.getMethod())) {
			messageRequest(request, arg0);
		} else if (Request.NOTIFY.equalsIgnoreCase(request.getMethod())) {
			notifyRequest(request, arg0);
		} else {
			System.out.println("no support the method!");
		}
	}

	/*
	 */
	public void processResponse(ResponseEvent arg0) {
		Response response = arg0.getResponse();

		System.out.println("recv the response :" + response.toString());

		System.out.println();

		if (response.getStatusCode() == Response.TRYING) {
			System.out.println("The response is 100 response.");
			System.out.println();
			return;
		} else if(response.getStatusCode() == Response.OK){
			// ?????????????
			if(null == callIdHeader) {
				return;
			}
			ackAndByeResponse((ToHeader) response.getHeader(ToHeader.NAME));
		} else {
			System.out.println();
			System.out.println(response.getStatusCode());
			System.out.println();			
		}
	}

	private void ackAndByeResponse(ToHeader toHeader) {
		// TODO Auto-generated method stub
		String to = "sip:22030100000000000002@36.48.166.6:7100";
		System.out.println("ack reponse....................");
		try {
			SipURI from = addressFactory.createSipURI(userName, ipAddr
					+ ":5060");
			Address fromNameAddress = addressFactory.createAddress(from);
			fromNameAddress.setDisplayName(userName);
			FromHeader fromHeader = headerFactory.createFromHeader(
					fromNameAddress, "sip28181");

			String username = to
					.substring(to.indexOf(":") + 1, to.indexOf("@"));
			String address = to.substring(to.indexOf("@") + 1);

			SipURI toAddress = addressFactory.createSipURI(username, address);
			Address toNameAddress = addressFactory.createAddress(toAddress);
			toNameAddress.setDisplayName(username);

			SipURI requestURI = addressFactory.createSipURI(username, address);
			requestURI.setTransportParam("udp");

			List<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
			ViaHeader viaHeader = headerFactory.createViaHeader(ipAddr, port,
					"udp", "branch1");
			viaHeaders.add(viaHeader);

			MaxForwardsHeader maxForwards = headerFactory
					.createMaxForwardsHeader(70);

			SipURI contactURI = addressFactory.createSipURI(userName, ipAddr);
			contactURI.setPort(port);
			Address contactAddress = addressFactory.createAddress(contactURI);
			contactAddress.setDisplayName(userName);
			ContactHeader contactHeader = headerFactory
					.createContactHeader(contactAddress);
			
			@SuppressWarnings("deprecation")
			CSeqHeader cSeqHeaderAck = headerFactory.createCSeqHeader(2,
					Request.ACK);
			Request requestAck = msgFactory.createRequest(requestURI, Request.ACK,
					callIdHeader, cSeqHeaderAck, fromHeader, toHeader, viaHeaders,
					maxForwards);
			requestAck.addHeader(contactHeader);
			sipProvider.sendRequest(requestAck);
			
			@SuppressWarnings("deprecation")
			CSeqHeader cSeqHeaderBye = headerFactory.createCSeqHeader(3,
					Request.BYE);
			Request requestBye = msgFactory.createRequest(requestURI, Request.BYE,
					callIdHeader, cSeqHeaderBye, fromHeader, toHeader, viaHeaders,
					maxForwards);
			requestBye.addHeader(contactHeader);

			sipProvider.sendRequest(requestBye);
			
			callIdHeader = null;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}


	// ----------------------------------------------------------------------------------------------------------
	/**
	 * 鏈嶅姟鍣ㄤ睛鍚琁P鍦板潃
	 */
	private String ipAddr = "122.141.94.91";
	/**
	 * 鏈嶅姟鍣ㄤ睛鍚鍙�
	 */
	private int port = 5060;
	/**
	 * 涓诲彨瀵硅瘽
	 */
	// private Dialog calleeDialog = null;

	/**
	 * 琚彨瀵硅瘽
	 */
	// private Dialog callerDialog = null;

	/**
	 * 淇濆瓨褰撳墠娉ㄥ唽鐨勭敤鎴�
	 */
	private static Hashtable<URI, URI> currUser = new Hashtable<URI, URI>();

	private static SipStack sipStack = null;
	private static SipProvider sipProvider = null;
	private static AddressFactory addressFactory = null;
	private static MessageFactory msgFactory = null;
	private static HeaderFactory headerFactory = null;
	private CallIdHeader callIdHeader = null;
	// private ServerTransaction serverTransactionId = null;
	// private ClientTransaction clientTransactionId = null;
	private String userName = "22030100000000000001";

	/**
	 * @author software 娉ㄥ唽瀹氭椂鍣�
	 */
	class TimerTask extends Timer {
		/**
		 * default constructor
		 */
		public TimerTask() {

		}

		/**
		 * 濡傛灉瀹氭椂浠诲姟鍒帮紝鍒欏垹闄よ鐢ㄦ埛鐨勬敞鍐屼俊鎭�
		 */
		public void run() {

		}
	}

	// ----------------------------------------------------------------------------------------------------------
	/**
	 * 澶勭悊MESSAGE
	 * 
	 * @param request
	 *            璇锋眰娑堟伅
	 * @throws IOException
	 * @throws JDOMException
	 */
	private void messageRequest(Request request, RequestEvent requestEvent) {

		System.out.println("------processMessage start-----");
		try {
			// Reply with OK
			Response response = msgFactory.createResponse(200, request);
			ToHeader toHeader = (ToHeader) response.getHeader(ToHeader.NAME);
			toHeader.setTag("888");
			ServerTransaction st = sipProvider.getNewServerTransaction(request);
			st.sendResponse(response);
			System.out.println("----------------200 ok ok--------");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] buff = request.getRawContent();

		SAXBuilder sb = new SAXBuilder();
		InputStream is = new ByteArrayInputStream(buff);

		Document doc = null;
		try {
			doc = sb.build(is);// 鏋勯�犳枃妗ｅ璞�
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
		String str = root.getChildText("CmdType").toLowerCase();
		//
		if (str.equals("keepalive")) {
			try {
				//
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else if (str.equals("")) {

		}
		System.out.println("------processMessage end------ ");
	}

	/**
	 * 澶勭悊register璇锋眰
	 * 
	 * @param request
	 *            璇锋眰娑堟伅
	 */
	private void registerRequest(Request request, RequestEvent requestEvent) {
		if (null == request) {
			System.out.println("processRegister request is null.");
			return;
		}
		ServerTransaction serverTransactionId = requestEvent
				.getServerTransaction();

		try {
			ToHeader head = (ToHeader) request.getHeader(ToHeader.NAME);
			Address toAddress = head.getAddress();
			URI toURI = toAddress.getURI();
			ContactHeader contactHeader = (ContactHeader) request
					.getHeader("Contact");
			Address contactAddr = contactHeader.getAddress();
			URI contactURI = contactAddr.getURI();
			System.out.println("processRegister from: " + toURI
					+ " request str: " + contactURI);
			int expires = request.getExpires().getExpires();
			// 濡傛灉expires涓嶇瓑浜�0,鍒欎负娉ㄥ唽锛屽惁鍒欎负娉ㄩ攢銆�
			if (expires != 0 || contactHeader.getExpires() != 0) {
				currUser.put(toURI, contactURI);
				System.out.println("register user " + toURI);
			} else {
				currUser.remove(toURI);
				System.out.println("unregister user " + toURI);
			}

			Response response = msgFactory.createResponse(200, request);
			System.out.println("send register response  : "
					+ response.toString());

			if (serverTransactionId == null) {
				serverTransactionId = sipProvider
						.getNewServerTransaction(request);
				serverTransactionId.sendResponse(response);
				// serverTransactionId.terminate();
				System.out.println("register serverTransaction: "
						+ serverTransactionId);
			} else {
				System.out
						.println("processRequest serverTransactionId is null.");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * */
	private void notifyRequest(Request request, RequestEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("------processNotify start-----");
		try {
			// Reply with OK
			Response response = msgFactory.createResponse(200, request);
			ToHeader toHeader = (ToHeader) response.getHeader(ToHeader.NAME);
			toHeader.setTag("888");
			ServerTransaction st = sipProvider.getNewServerTransaction(request);
			st.sendResponse(response);
			System.out.println("----------------200 ok ok--------");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------processNotify end-----");
	}

	//
	// ----------------------------------------------------------------------------------------------------------
	public void sendMessage(String to, String message) {
		try {
			SipURI from = addressFactory.createSipURI(userName, ipAddr
					+ ":5060");
			Address fromNameAddress = addressFactory.createAddress(from);
			fromNameAddress.setDisplayName(userName);
			FromHeader fromHeader = headerFactory.createFromHeader(
					fromNameAddress, "sip28181");

			String username = to
					.substring(to.indexOf(":") + 1, to.indexOf("@"));
			String address = to.substring(to.indexOf("@") + 1);

			SipURI toAddress = addressFactory.createSipURI(username, address);
			Address toNameAddress = addressFactory.createAddress(toAddress);
			toNameAddress.setDisplayName(username);
			ToHeader toHeader = headerFactory.createToHeader(toNameAddress,
					null);

			SipURI requestURI = addressFactory.createSipURI(username, address);
			requestURI.setTransportParam("udp");

			List<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
			ViaHeader viaHeader = headerFactory.createViaHeader(ipAddr, port,
					"udp", "branch1");
			viaHeaders.add(viaHeader);

			CallIdHeader cih = sipProvider.getNewCallId();

			@SuppressWarnings("deprecation")
			CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(1,
					Request.MESSAGE);

			MaxForwardsHeader maxForwards = headerFactory
					.createMaxForwardsHeader(70);

			Request request = msgFactory.createRequest(requestURI,
					Request.MESSAGE, cih, cSeqHeader, fromHeader,
					toHeader, viaHeaders, maxForwards);

			SipURI contactURI = addressFactory.createSipURI(userName, ipAddr);
			contactURI.setPort(port);
			Address contactAddress = addressFactory.createAddress(contactURI);
			contactAddress.setDisplayName(userName);
			ContactHeader contactHeader = headerFactory
					.createContactHeader(contactAddress);
			request.addHeader(contactHeader);

			ContentTypeHeader contentTypeHeader = headerFactory
					.createContentTypeHeader("Application", "MANSCDP+xml");

			request.setContent(message, contentTypeHeader);

			sipProvider.sendRequest(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	public void sendInvite(String to, String message) {
		try {
			SipURI from = addressFactory.createSipURI(userName, ipAddr
					+ ":5060");
			Address fromNameAddress = addressFactory.createAddress(from);
			fromNameAddress.setDisplayName(userName);
			FromHeader fromHeader = headerFactory.createFromHeader(
					fromNameAddress, "sip28181");

			String username = to
					.substring(to.indexOf(":") + 1, to.indexOf("@"));
			String address = to.substring(to.indexOf("@") + 1);

			SipURI toAddress = addressFactory.createSipURI(username, address);
			Address toNameAddress = addressFactory.createAddress(toAddress);
			toNameAddress.setDisplayName(username);
			ToHeader toHeader = headerFactory.createToHeader(toNameAddress,
					null);

			SipURI requestURI = addressFactory.createSipURI(username, address);
			requestURI.setTransportParam("udp");

			List<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
	
			ViaHeader viaHeader = headerFactory.createViaHeader(ipAddr, port,
					"udp", "branch1");
			viaHeaders.add(viaHeader);

			callIdHeader = sipProvider.getNewCallId();

			@SuppressWarnings("deprecation")
			CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(20,
					Request.INVITE);

			MaxForwardsHeader maxForwards = headerFactory
					.createMaxForwardsHeader(70);

			Request request = msgFactory.createRequest(requestURI,
					Request.INVITE, callIdHeader, cSeqHeader, fromHeader,
					toHeader, viaHeaders, maxForwards);

			SipURI contactURI = addressFactory.createSipURI(userName, ipAddr);
			contactURI.setPort(port);
			Address contactAddress = addressFactory.createAddress(contactURI);
			contactAddress.setDisplayName(userName);
			ContactHeader contactHeader = headerFactory
					.createContactHeader(contactAddress);
			request.addHeader(contactHeader);

			ContentTypeHeader contentTypeHeader = headerFactory
					.createContentTypeHeader("Application", "SDP");

			request.setContent(message, contentTypeHeader);

			sipProvider.sendRequest(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	public void sendSubscribe(String to, String message) {
		try {
			SipURI from = addressFactory.createSipURI(userName, ipAddr
					+ ":5060");
			Address fromNameAddress = addressFactory.createAddress(from);
			fromNameAddress.setDisplayName(userName);
			FromHeader fromHeader = headerFactory.createFromHeader(
					fromNameAddress, "sip28181");

			String username = to
					.substring(to.indexOf(":") + 1, to.indexOf("@"));
			String address = to.substring(to.indexOf("@") + 1);

			SipURI toAddress = addressFactory.createSipURI(username, address);
			Address toNameAddress = addressFactory.createAddress(toAddress);
			toNameAddress.setDisplayName(username);
			ToHeader toHeader = headerFactory.createToHeader(toNameAddress,
					null);

			SipURI requestURI = addressFactory.createSipURI(username, address);
			requestURI.setTransportParam("udp");

			List<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
			ViaHeader viaHeader = headerFactory.createViaHeader(ipAddr, port,
					"udp", "branch1");
			viaHeaders.add(viaHeader);

			CallIdHeader cih = sipProvider.getNewCallId();

			@SuppressWarnings("deprecation")
			CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(1,
					Request.SUBSCRIBE);

			MaxForwardsHeader maxForwards = headerFactory
					.createMaxForwardsHeader(70);

			Request request = msgFactory.createRequest(requestURI,
					Request.SUBSCRIBE, cih, cSeqHeader, fromHeader,
					toHeader, viaHeaders, maxForwards);

			SipURI contactURI = addressFactory.createSipURI(userName, ipAddr);
			contactURI.setPort(port);
			Address contactAddress = addressFactory.createAddress(contactURI);
			contactAddress.setDisplayName(userName);
			ContactHeader contactHeader = headerFactory
					.createContactHeader(contactAddress);
			request.addHeader(contactHeader);

			ContentTypeHeader contentTypeHeader = headerFactory
					.createContentTypeHeader("Application", "MANSCDP+xml");

			request.setContent(message, contentTypeHeader);

			sipProvider.sendRequest(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------------
	public void init() {
		SipFactory sipFactory = null;

		sipFactory = SipFactory.getInstance();
		if (null == sipFactory) {
			System.out.println("init sipFactory is null.");
			return;
		}

		sipFactory.setPathName("gov.nist");
		Properties properties = new Properties();
		properties.setProperty("javax.sip.STACK_NAME", "sipphone");
		properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
		properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
				"sipphonedebug.txt");
		properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
				"sipphonelog.txt");
		try {
			sipStack = sipFactory.createSipStack(properties);
		} catch (PeerUnavailableException e) {
			e.printStackTrace();
			return;
		}

		try {
			headerFactory = sipFactory.createHeaderFactory();
			addressFactory = sipFactory.createAddressFactory();
			msgFactory = sipFactory.createMessageFactory();
			ListeningPoint lp = sipStack.createListeningPoint(ipAddr, port,
					"udp");
			sipProvider = sipStack.createSipProvider(lp);
			System.out.println("udp provider " + sipProvider.toString());
			sipProvider.addSipListener(this);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
}
