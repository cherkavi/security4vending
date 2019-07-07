package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;


/** ������� ������ � ������ �� ��������� ������ ������� */
public class TaskDataSensorGet extends TaskData{

	/** ������� ������ � ������ �� ��������� ������ �������
	 * @param number - ���������� ����� ������� � ������   
	 */
	public TaskDataSensorGet(int number ){
		super(true, "�������� ������ �"+number);

		Element sensor=this.getDocument().createElement("sensor_get");
		this.getTaskElement().appendChild(sensor);
		
			Element id_modbus=this.createElementWithText("id_modbus", number);
			sensor.appendChild(id_modbus);
	}
	
}
