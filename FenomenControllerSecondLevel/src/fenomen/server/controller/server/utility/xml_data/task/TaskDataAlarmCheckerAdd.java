package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� AlarmChecker ��� ���������� �������  */
public class TaskDataAlarmCheckerAdd extends TaskData{

	/** �������� ��� AlarmChecker-� �� ���������� ������� 
	 * @param addressModbus - ����� � ���� ModBus
	 * @param addressRegister - ����� �������� �� ������ 
	 * @param idModuleAlarmChecker - ���������� ����� ������ � ������� module_alarm_checker  
	 * */
	public TaskDataAlarmCheckerAdd(int addressModbus, 
								   int addressRegister,
								   int idModuleAlarmChecker) {
		super(true, "�������� AlarmChecker ��� ������� �"+addressModbus);

		Element alarm_checker=this.getDocument().createElement("alarm_checker");
		this.getTaskElement().appendChild(alarm_checker);
		
			Element id_sensor=this.createElementWithText("id_modbus", addressModbus);
			alarm_checker.appendChild(id_sensor);
	
			Element register=this.createElementWithText("register", addressModbus);
			alarm_checker.appendChild(register);
			
			Element add=this.getDocument().createElement("add");
			alarm_checker.appendChild(add);
			
				Element id_file=this.createElementWithText("id_file", idModuleAlarmChecker);
				add.appendChild(id_file);
	}

}
