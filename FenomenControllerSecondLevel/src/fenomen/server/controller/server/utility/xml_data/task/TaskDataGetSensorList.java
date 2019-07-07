package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;


/** ������� ������ � ������ �� ��������� ���� ������ � �������: �������� ������ ��������  */
public class TaskDataGetSensorList extends TaskData{

	/** ������� ������ � ������ �� ��������� ���� ������ � �������: �������� ������ ��������  
	 */
	public TaskDataGetSensorList(){
		super(true, "�������� ������ �������� ");

		Element sensorList=this.getDocument().createElement("sensor_list");
		this.getTaskElement().appendChild(sensorList);
		
		Element get=this.getDocument().createElement("get");
		sensorList.appendChild(get);
	}
	
}
