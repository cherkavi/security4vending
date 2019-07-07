package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** ���������� ��������� ThreadSensor - ������, ������� �������� � ��������� */
public class TaskDataSensorThreadSet extends TaskData{

	/** ���������� ��������� ThreadSensor - ������, ������� �������� � ��������� */
	public TaskDataSensorThreadSet(int timeWait) {
		super(true, "���������� ��������� ��� ThreadSensor");

		Element sensor_thread=this.getDocument().createElement("sensor_thread");
		this.getTaskElement().appendChild(sensor_thread);
			
			Element set=this.getDocument().createElement("set");
			sensor_thread.appendChild(set);
			
				Element time_wait=this.createElementWithText("time_wait", timeWait);
				set.appendChild(time_wait);
	}

}
