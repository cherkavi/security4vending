package fenomen.monitor.web_service.common;

import java.io.ByteArrayInputStream;

import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** ����� ����� ��� ���� ��������� */
public abstract class XmlMessage {
	/** ��������� �� ������� monitor_event_alarm */
	public static String typeAlarm="alarm";
	/** ��������� �� ������� monitor_event_information */
	public static String typeInformation="information";
	/** ��������� �� ������� monitor_event_heart_beat */
	public static String typeHeartBeat="heart_beat";
	/** ��������� �� ������� monitor_event_restart */
	public static String typeRestart="restart";
	
	/** ���������� ����� ������ �� ������� ���� ������ (monitor_event_*) */
	private int id;
	/** ��� ��������� {@link XmlMessage#typeAlarm} {@link XmlMessage#typeInformation} {@link XmlMessage#typeRestart} {@link XmlMessage#typeHeartBeat}*/
	private String type;
	/** ����� �������� � ���� Jabber */
	private String jabberAddress;
	/** ���������� id �� ������� ������� ({@link #getType}) module_event_alarm, module_event_information, module_event_heart_beat, module_event_restart */
	private int eventId;
	/** ���� � ����� ������� */
	private Date timeWrite;
	/** �������� ������� */
	private String description;
	/** �������� ������� */
	private String moduleName;
	/** ����� ������ */
	private String moduleAddress;
	
	/** ���� ���������� */
	private boolean flagNotify=false;
	/** ���� ������������� */
	private boolean flagConfirm=false;
	/** ���� ������� �������� */
	private boolean flagResolve=false;
	
	/** ������� �������� ��� �� ������� monitor_event_resolve */
	private int resolveId;
	/** ������� ��������, ���� ������� */
	private String resolveName;
	
	/** ����� ����� ��� ���� ��������� */
	public XmlMessage(String type){
		this.type=type;
	}

	/** ���������� ����� ������ �� ������� ���� ������ (monitor_event_*) */
	public int getId() {
		return id;
	}

	/** ���������� ����� ������ �� ������� ���� ������ (monitor_event_*) */
	public void setId(int id) {
		this.id = id;
	}

	/** ��� ��������� {@link XmlMessage#typeAlarm} {@link XmlMessage#typeInformation} {@link XmlMessage#typeRestart} {@link XmlMessage#typeHeartBeat}*/
	public String getType() {
		return type;
	}

	/** ��� ��������� {@link XmlMessage#typeAlarm} {@link XmlMessage#typeInformation} {@link XmlMessage#typeRestart} {@link XmlMessage#typeHeartBeat}*/
	public void setType(String type) {
		this.type = type;
	}

	/** ����� �������� � ���� Jabber */
	public String getJabberAddress() {
		return jabberAddress;
	}

	/** ����� �������� � ���� Jabber */
	public void setJabberAddress(String jabberAddress) {
		this.jabberAddress = jabberAddress;
	}

	/** ���������� id �� ������� ������� ({@link #getType}) module_event_alarm, module_event_information, module_event_heart_beat, module_event_restart */
	public int getEventId() {
		return eventId;
	}

	/** ���������� id �� ������� ������� ({@link #getType}) module_event_alarm, module_event_information, module_event_heart_beat, module_event_restart */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/** ���� � ����� ������� */
	public Date getTimeWrite() {
		return timeWrite;
	}

	/** ���� � ����� ������� */
	public void setTimeWrite(Date timeWrite) {
		this.timeWrite = timeWrite;
	}

	/** �������� ������� */
	public String getDescription() {
		return description;
	}

	/** �������� ������� */
	public void setDescription(String description) {
		this.description = description;
	}

	/** ��� ������ */
	public String getModuleName() {
		return moduleName;
	}

	/** ��� ������ */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** ����� ������ */
	public String getModuleAddress() {
		return moduleAddress;
	}

	/** ����� ������ */
	public void setModuleAddress(String moduleAddress) {
		this.moduleAddress = moduleAddress;
	}
	
	
	/** �������� � ������������� �������� ���� XML � ������� ��������� ��������� 
	 * @param document - XML ��������
	 * @param parent - ������������ �������
	 * @param elementName - ��� �������� 
	 * @param elementValue - ��������� �������� ��������
	 */
	private void appendLeafToElement(Document document, Element parent, String elementName, String elementValue){
		Element element=document.createElement(elementName);
		element.setTextContent(elementValue);
		parent.appendChild(element);
	}
	
	private String getStringFromXmlDocument(Document document){
		Writer out=null;
		try{
			javax.xml.transform.TransformerFactory transformer_factory = javax.xml.transform.TransformerFactory.newInstance();  
			javax.xml.transform.Transformer transformer = transformer_factory.newTransformer();  
			javax.xml.transform.dom.DOMSource dom_source = new javax.xml.transform.dom.DOMSource(document); // Pass in your document object here  
			out=new StringWriter();
			//string_writer = new Packages.java.io.StringWriter();  
			javax.xml.transform.stream.StreamResult stream_result = new javax.xml.transform.stream.StreamResult(out);  
			transformer.transform(dom_source, stream_result);  
		}catch(Exception ex){
			System.err.println("getStringFromXmlDocument:"+ex.getMessage());
		}
		return (out==null)?"":out.toString();
	}


	
	/** ���� ���������� */
	public boolean isFlagNotify() {
		return flagNotify;
	}

	/** ���� ���������� */
	public void setFlagNotify(boolean flagNotify) {
		this.flagNotify = flagNotify;
	}

	/** ���� ������������� */
	public boolean isFlagConfirm() {
		return flagConfirm;
	}

	/** ���� ������������� */
	public void setFlagConfirm(boolean flagConfirm) {
		this.flagConfirm = flagConfirm;
	}

	/** ���� ������� �������� */
	public boolean isFlagResolve() {
		return flagResolve;
	}

	/** ���� ������� �������� */
	public void setFlagResolve(boolean flagResolve) {
		this.flagResolve = flagResolve;
	}

	/** ��� ������� �������� �� �������  monitor_event_resolve.id */
	public int getResolveId() {
		return resolveId;
	}

	/** ��� ������� �������� �� �������  monitor_event_resolve.id, ���������� ��������� ����� {@link #setFlagResolve}*/
	public void setResolveId(int resolveId) {
		this.resolveId = resolveId;
	}

	/** ������� ��������, ���� �������  */
	public String getResolveName() {
		return resolveName;
	}

	/** ������� ��������, ���� �������, ���������� ��������� ����� {@link #setFlagResolve} */
	public void setResolveName(String resolveName) {
		this.resolveName = resolveName;
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	/** ������������� ������ ������ � XML � ���� ������, ��� �������� �� ������� �����  */
	public String convertToXml() throws Exception{
		// create empty XML document
		javax.xml.parsers.DocumentBuilderFactory document_builder_factory=javax.xml.parsers.DocumentBuilderFactory.newInstance();
		document_builder_factory.setValidating(false);
		document_builder_factory.setIgnoringComments(true);
		javax.xml.parsers.DocumentBuilder document_builder=document_builder_factory.newDocumentBuilder();
		Document document=document_builder.newDocument();

		Element root=document.createElement("message");
		document.appendChild(root);
		
		this.appendLeafToElement(document, root, "id", Integer.toString(this.getId()));
		this.appendLeafToElement(document, root, "type", this.getType());
		try{
			this.appendLeafToElement(document, root, "time_write", sdf.format(this.getTimeWrite()));
		}catch(Exception ex){
			this.appendLeafToElement(document, root, "time_write", "");
		}
		this.appendLeafToElement(document, root, "description", this.getDescription());
		this.appendLeafToElement(document, root, "module_name", this.getModuleName());
		this.appendLeafToElement(document, root, "module_address", this.getModuleAddress());
		if(this.isFlagNotify()){
			Element notify=document.createElement("notify");
			root.appendChild(notify);
		} else
		if(this.isFlagConfirm()){
			Element confirm=document.createElement("confirm");
			root.appendChild(confirm);
		}else 
		if(this.isFlagResolve()){
			Element resolve=document.createElement("resolve");
			root.appendChild(resolve);
			this.appendLeafToElement(document, resolve, "id", Integer.toString(this.getResolveId()));
			if(this.resolveName!=null){
				this.appendLeafToElement(document, resolve, "name", this.getResolveName());
			}else{
				this.appendLeafToElement(document, resolve, "name", "");
			}
			
		}
		return this.getStringFromXmlDocument(document);
	}

	/** �������� �������� �� ��������� ������ */
	private static Document getXmlFromString(String value){
		Document return_value=null;
		javax.xml.parsers.DocumentBuilderFactory document_builder_factory=javax.xml.parsers.DocumentBuilderFactory.newInstance();
        // ���������� ������������� ���������� Parser-��
        document_builder_factory.setValidating(false);
        try {
            // ��������� �����������
            javax.xml.parsers.DocumentBuilder parser=document_builder_factory.newDocumentBuilder();
            // Parse ��������
            return_value=parser.parse(new ByteArrayInputStream(value.getBytes("UTF-8")));
            //return_value=parser.parse(new StringBufferInputStream(value));
        }catch(Exception ex){
        	System.err.println("XmlExchange#getXmlFromString ERROR: "+ex.getMessage());
        }
		return return_value;
	}
	
	
	/** �������� ���������� �� ��������� �������� �� ��������� XPath ����
	 * @param parentElement - ������������ ������� 
	 * @param path - ���� XPath
	 * @return - ��������� Element ��� null
	 */
	private static Element getSubElement(XPath xpath, Element parentElement, String path){
		try{
			Object returnValue=xpath.evaluate(path, parentElement,XPathConstants.NODE);
			if(returnValue instanceof Element){
				return (Element)returnValue;
			}else{
				return null;
			}
		}catch(Exception ex){
			return null;
		}
	}
	
	/** �������� ��������� ���������� ��������, �� ��������� ������������� �������� � ����  */
	private static String getTextContent(XPath xpath, Element parentElement, String path){
		Element element=getSubElement(xpath, parentElement,path);
		if(element!=null){
			return element.getTextContent();
		}else{
			return null;
		}
	}
	
	private static void debug(Object message){
		System.out.println("XmlMessage DEBUG: "+message);
	}

	private static void error(Object message){
		System.out.println("XmlMessage ERROR: "+message);
	}
	
	/** �������� {@link XmlMessage} �� ��������� ������ ������ ( ���������� �������� �� ������� ��������� ) 
	 * @param text - ���������, ���������� �� ������� � ��������� ������� {@link #convertToXml()}
	 * @return
	 */
	public static XmlMessage getInstanceByMessage(String text){
		XPath xpath=XPathFactory.newInstance().newXPath();
		debug("�������������� � XML: "+text);
		Document document=getXmlFromString(text);
		if(document!=null){
			NodeList list=document.getElementsByTagName("message");
			debug("�������� ������� message ������ ");
			if(list.getLength()>0){
				Element root=(Element)list.item(0);
				String type=getTextContent(xpath, root, "type");
				XmlMessage message=new XmlMessage(type){};
				Element notify=getSubElement(xpath, root, "notify");
				Element confirm=getSubElement(xpath, root, "confirm");
				Element resolve=getSubElement(xpath, root,"resolve");
				if(notify!=null){
					message.setFlagNotify(true);
					debug("���������� � ����� �������");
					try{
						debug("�������� id");
						message.setId(Integer.parseInt(getTextContent(xpath, root,"id")));
						debug("�������� time_write");
						try{
							message.setTimeWrite(sdf.parse(getTextContent(xpath, root,"time_write")));
						}catch(Exception ex){};
						debug("�������� module_name");
						message.setModuleName(getTextContent(xpath, root,"module_name"));
						debug("�������� module_address");
						message.setModuleAddress(getTextContent(xpath, root,"module_address"));
						debug("�������� description");
						message.setDescription(getTextContent(xpath, root,"description"));
						return message;
					}catch(Exception ex){
						error("������ �� ����� ������� ������ ���������� ");
						return null;
					}
				}else if(confirm!=null){
					message.setFlagConfirm(true);
					try{
						debug("�������� id");
						message.setId(Integer.parseInt(getTextContent(xpath, root,"id")));
					}catch(Exception ex){};
				}else if(resolve!=null){
					message.setFlagResolve(true);
					try{
						debug("�������� id");
						message.setId(Integer.parseInt(getTextContent(xpath, root,"id")));
					}catch(Exception ex){};
					try{
						debug("�������� resolveId");
						message.setResolveId(Integer.parseInt(getTextContent(xpath, resolve,"id")));
					}catch(Exception ex){};
					message.setResolveName(getTextContent(xpath, resolve,"name"));
				}else{
					debug("��� ��������� ��������� �� �������");
				}
				return message;
			}else{
				debug("�� ������ �������� ������� message");
				return null;
			}
		}else{
			debug("������ �������������� ������ � XML ����"); 
			return null;
		}
	}
}


