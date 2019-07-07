package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� AlarmChecker ��� ���������� �������  � ���������� AlarmChecker-�*/
public class TaskDataAlarmCheckerReplace extends TaskData{

	/** �������� ��� AlarmChecker-� �� ���������� ������� 
	 * @param addressModbus - ����� � ���� ModBus
	 * @param addressRegister - ����� �������� �� ������ 
	 * @param idCheckerNumber - ���������� ����� Checker-a � ��������� ������� 
	 * @param idModuleAlarmChecker - ���������� ����� ������ � ������� module_alarm_checker  
	 * */
	public TaskDataAlarmCheckerReplace(int addressModbus, 
			   						   int addressRegister, 
			   						   int idCheckerNumber, 
			   						   int idModuleAlarmChecker) {
		super(true, "�������� AlarmChecker ��� ������� �"+addressModbus);

		Element alarm_checker=this.getDocument().createElement("alarm_checker");
		this.getTaskElement().appendChild(alarm_checker);
		
			Element id_modbus=this.createElementWithText("id_modbus", addressModbus);
			alarm_checker.appendChild(id_modbus);
	
			Element register=this.createElementWithText("register", addressRegister);
			alarm_checker.appendChild(register);
			
			Element replace=this.getDocument().createElement("replace");
			alarm_checker.appendChild(replace);
			
				Element id_checker=this.createElementWithText("id_checker", idCheckerNumber);
				replace.appendChild(id_checker);
	
				Element id_file=this.createElementWithText("id_file", idModuleAlarmChecker);
				replace.appendChild(id_file);
	}

}
