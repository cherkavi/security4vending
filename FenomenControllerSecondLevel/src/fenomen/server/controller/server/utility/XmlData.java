package fenomen.server.controller.server.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;

import database.ConnectWrap;
import database.StaticConnector;
import database.wrap.ModuleStorage;

/** ������ � ���� XML ����� */
public class XmlData {
	public final static String pathAlarm="Alarm";
	public final static String pathInformation="Information";
	public final static String pathTask="Task";
	public final static String pathAlarmChecker="AlarmChecker";
	public final static String pathInformationChecker="InformationChecker";
	
	private Logger logger=Logger.getLogger(this.getClass());
	/** ��������, ������� ����� ����������� */
	private Document document;
	/** ������� ��� �����, ������� ����� ������������� ��� ���������� */
	private String filePrefix;
	/** ������� ��� �����, ������� ����� ������������� ��� ���������� (���������� ����� )  */
	private String fileSuffix;
	
	/** ������ � ���� XML ����� 
	 * @param filePrefix - ������� ��� ����� (A)
	 * @param fileSuffix - ������� ��� ����� (.axml) 
	 */
	public XmlData(String filePrefix, String fileSuffix){
		document=null;
		this.filePrefix=filePrefix;
		this.fileSuffix=fileSuffix;
		// create empty XML document
		javax.xml.parsers.DocumentBuilderFactory document_builder_factory=javax.xml.parsers.DocumentBuilderFactory.newInstance();
		document_builder_factory.setValidating(false);
		document_builder_factory.setIgnoringComments(true);
		try{
			javax.xml.parsers.DocumentBuilder document_builder=document_builder_factory.newDocumentBuilder();
			document=document_builder.newDocument();
		}catch(Exception ex){
			logger.error("XmlData constructor Exception: "+ex.getMessage());
		}
	}
	
	/** ��������� ������ ������ ��� XML ���� � ������� ��� ��� 
	 * @param pathToDirectory - �������, � ������� ������ ���� �������� ���� <b>���� � �������� ������ ���� � trim() ���� � ������������� �� System.getProperty("file.separator")</b>
	 * @return 
	 * <li><b>��� �����</b> - ���� ��� ������ ������� </li>
	 * <li><b>null </b> - ���� ��������� ������ ���������� ����� </li>
	 */
	public String saveXmlAsFile(String pathToDirectory){
		// ������������� ����� ��� ��� �����
		String fileName=this.getUniqueName(filePrefix, fileSuffix);
		try{
			javax.xml.transform.TransformerFactory transformer_factory = javax.xml.transform.TransformerFactory.newInstance();  
			javax.xml.transform.Transformer transformer = transformer_factory.newTransformer();  
			javax.xml.transform.dom.DOMSource dom_source = new javax.xml.transform.dom.DOMSource(this.document); // Pass in your document object here  
			java.io.FileWriter out=new java.io.FileWriter(pathToDirectory+fileName);
			//string_writer = new Packages.java.io.StringWriter();  
			javax.xml.transform.stream.StreamResult stream_result = new javax.xml.transform.stream.StreamResult(out);  
			transformer.transform(dom_source, stream_result);
			out.flush();
			out.close();
			return fileName;
		}catch(Exception ex){
			logger.error("saveXmlAsFile Exception:"+ex.getMessage());
			return null;
		}
	}
	
	/** ��������� ������ ����� ��� ����� � ���������� ��������� */
	private String getUniqueName(String prefix, String suffix){
		return prefix+this.sdf.format(new Date())+"_"+this.generateUniqueSiquence(4)+suffix;
	}
	
	/** �������, ������� ����� �������������� ��� ����������� ����������� ����� ����� */
	private final static char[] uniqueChar=new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	private Random random=new Random();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
	/** ������������� ���������� ������������������  */
	private String generateUniqueSiquence(int size){
		StringBuffer returnValue=new StringBuffer();
		for(int counter=0;counter<size;counter++){
			returnValue.append(uniqueChar[random.nextInt(uniqueChar.length)]);
		}
		return returnValue.toString();
	}
	
	/** �������� XML �������� */
	protected Document getDocument(){
		return this.document;
	}
	
	/** �������� ���� � ��������
	 * @param synonim - ��������� ���, �� �������� ����� �������� ������� �� ������� <b>module_storage</b>, ��������� ��������:
	 * <li><b>{@link XmlData#pathTask}</b> ������ ���� � Task �������� </li>
	 * <li><b>{@link XmlData#pathAlarm}</b> ������ ���� � Alarm �������� </li>
	 * <li><b>{@link XmlData#pathInformation}</b> ������ ���� � Information </li>
	 * @return ���� � �������� ��� ���������� XML ������ ���������� ����:
	 */
	public String getPathToStorage(String synonim){
		String returnValue=null;
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			returnValue=(String) ((ModuleStorage)connector.getSession().createCriteria(ModuleStorage.class).add(Restrictions.eq("name", synonim)).uniqueResult()).getDirectory();
		}catch(Exception ex){
			logger.warn("ModuleStorage#getPathToStorage Exception: "+ex.getMessage());
		}finally{
			connector.close();
		}
		return returnValue;
	}
	
}
