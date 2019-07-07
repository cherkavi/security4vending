package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� ��� AlarmChecker-� �� ���������� ������� */
public class TaskDataAlarmCheckerGetList extends TaskData{

	/** �������� ��� AlarmChecker-� �� ���������� ������� 
	 * @param addressModbus - ����� � ���� ModBus 
	 * */
	public TaskDataAlarmCheckerGetList(int addressModbus) {
		super(true, "�������� AlarmChecker-� �� ������� �"+addressModbus);

		Element alarm_checker=this.getDocument().createElement("alarm_checker");
		this.getTaskElement().appendChild(alarm_checker);
		
			Element id_modbus=this.createElementWithText("id_modbus", addressModbus);
			alarm_checker.appendChild(id_modbus);
		
			Element get_list=this.getDocument().createElement("get_list");
			alarm_checker.appendChild(get_list);
	}

}
