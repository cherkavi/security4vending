package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� ��������� ThreadSensor - ������, ������� �������� � ��������� */
public class TaskDataSensorThreadGet extends TaskData{

	/** �������� ��������� ThreadSensor - ������, ������� �������� � ��������� */
	public TaskDataSensorThreadGet() {
		super(true, "�������� ��������� ��� ThreadSensor");

		Element sensor_thread=this.getDocument().createElement("sensor_thread");
		this.getTaskElement().appendChild(sensor_thread);
		
			Element get=this.getDocument().createElement("get");
			sensor_thread.appendChild(get);
	}

}
