package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

import fenomen.server.controller.server.utility.XmlData;

/** ������� ��� ���� Task � ������� */
public class TaskData extends XmlData{
	/** �������� ������ ������ */
	private String description;
	/** �������� ������� ������ ������ */
	private Element rootElement;
	private Element taskElement;
	/** �������� �� ������ ������ �� ������� �� ������ (true) ��� �� ������ �� ������ (false)*/
	private boolean isRequestTask;
	
	/** ������� ��� ���� Task � ������� 
	 * @param isRequestTask
	 * <table border=1>
	 * 	<tr>
	 * 		<th>������</th><th>��������</th> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_request = true</td><td> - �� ������� � ������ </td> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_response = false</td><td> - �� ������ � ������� </td> 
	 * 	</tr>
	 * </table>
	 */
	public TaskData(boolean isRequestTask){
		this(isRequestTask, "");
	}
	
	/** ������� ��� ���� Task � ������� 
	 * @param isRequestTask
	 * <table border=1>
	 * 	<tr>
	 * 		<th>������</th><th>��������</th> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_request = true</td><td> - �� ������� � ������ </td> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_response = false</td><td> - �� ������ � ������� </td> 
	 * 	</tr>
	 * </table>
	 * @param description - �������� ������, ����������� ��� ���� ������ 
	 */
	public TaskData(boolean isRequestTask, String description){
		super("t_",".txml");
		this.isRequestTask=isRequestTask;
		if(isRequestTask==true){
			this.rootElement=this.getDocument().createElement("task_request");
			this.getDocument().appendChild(this.rootElement);
		}else{
			this.rootElement=this.getDocument().createElement("task_response");
			this.getDocument().appendChild(this.rootElement);
		}
		taskElement=this.getDocument().createElement("task");
		this.rootElement.appendChild(taskElement);
		this.description=description;
	}
	
	/** �������� �������� ������� Task */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * @return 
	 * <table border=1>
	 * 	<tr>
	 * 		<th>������</th><th>��������</th> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_request = true</td><td> - �� ������� � ������ </td> 
	 * 	</tr>
	 * 	<tr>
	 * 		<td>task_response = false</td><td> - �� ������ � ������� </td> 
	 * 	</tr>
	 * </table>
	 */
	public boolean isRequestTask(){
		return this.isRequestTask;
	}
	
	/** �������� task ������� */
	protected Element getTaskElement(){
		return this.taskElement;
	}

	/** ������� ������� � ��������� ���� 
	 * @param elementName - ���
	 * @return - �������
	 */
	protected Element createElement(String elementName){
		return this.getDocument().createElement(elementName);
	}
	
	/** ������� ������� � ��������� ���� � �������  
	 * @param elementName - ���
	 * @param text - �����, ������� ������ ���� ������ �������� 
	 * @return - �������
	 */
	protected Element createElementWithText(String elementName,String text){
		Element element=this.getDocument().createElement(elementName);
		element.setTextContent(text);
		return element;
	}

	/** ������� ������� � ��������� ���� � �������  
	 * @param elementName - ���
	 * @param value - �������� ��������  
	 * @return - �������
	 */
	protected Element createElementWithText(String elementName,int value){
		Element element=this.getDocument().createElement(elementName);
		element.setTextContent(Integer.toString(value));
		return element;
	}
	
}

