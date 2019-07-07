package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** ��������� ��� ThreadAlarm (����������) */
public class TaskDataAlarmSet extends TaskData{

	/** ��������� ��� ThreadAlarm (����������)
	 * @param timeWait - ����� ���������� ������ �� �����
	 * @param timeError - ����� �������� ����� ���������� ����������
	 * @param maxCount - ������������ ���-�� ����������� ������� Information (�������� ������� ��� ���������� )  
	 */
	public TaskDataAlarmSet(int timeWait, int timeError, int maxCount) {
		super(true, "���������� ��������� ��� ThreadAlarm");

		Element alarm=this.getDocument().createElement("alarm");
		this.getTaskElement().appendChild(alarm);
		
		Element set=this.getDocument().createElement("set");
		alarm.appendChild(set);
		
		Element time_wait=this.getDocument().createElement("time_wait");
		time_wait.setTextContent(Integer.toString(timeWait));
		set.appendChild(time_wait);
		
		Element time_error=this.getDocument().createElement("time_error");
		time_error.setTextContent(Integer.toString(timeError));
		set.appendChild(time_error);

		Element max_count=this.getDocument().createElement("max_count");
		max_count.setTextContent(Integer.toString(maxCount));
		set.appendChild(max_count);
	}

}
