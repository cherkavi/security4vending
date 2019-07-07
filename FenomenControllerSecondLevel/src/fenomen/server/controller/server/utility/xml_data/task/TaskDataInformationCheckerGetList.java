package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� ��� InformationChecker-� �� ���������� ������� */
public class TaskDataInformationCheckerGetList extends TaskData{

	/** �������� ��� InformationChecker-� �� ���������� ������� 
	 * @param modbusAddress - ����� �������/������ � ���� ModBus 
	 * */
	public TaskDataInformationCheckerGetList(int addressModbus) {
		super(true, "�������� InformationChecker-� �� ������� �"+addressModbus);

		Element information_checker=this.getDocument().createElement("information_checker");
		this.getTaskElement().appendChild(information_checker);
		
			Element modbus=this.createElementWithText("id_modbus", addressModbus);
			information_checker.appendChild(modbus);
			
			Element get_list=this.getDocument().createElement("get_list");
			information_checker.appendChild(get_list);
	}

}
