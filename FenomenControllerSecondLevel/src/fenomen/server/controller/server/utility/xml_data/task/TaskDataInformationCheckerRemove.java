package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** ������� InformationChecker ��� ���������� �������  � ���������� InformationChecker-�*/
public class TaskDataInformationCheckerRemove extends TaskData{

	/** �������� ��� InformationChecker-� �� ���������� ������� 
	 * @param addressModbus - ���������� ����� � ���� Modbus
	 * @param register - ����� �������� � ���� Modbus
	 * @param idCheckerNumber - ���������� ����� Checker-a � ��������� �������, ������� ����� ������� 
	 * */
	public TaskDataInformationCheckerRemove(int addressModbus,
											int register, 
											int idCheckerNumber) {
		super(true, "������� InformationChecker ��� ������� �"+addressModbus+"  Register:"+register+"   idChecker:"+idCheckerNumber);

		Element information_checker=this.getDocument().createElement("information_checker");
		this.getTaskElement().appendChild(information_checker);
		
			Element id_modbus=this.createElementWithText("id_modbus", addressModbus);
			information_checker.appendChild(id_modbus);
	
			Element id_register=this.createElementWithText("register", register);
			information_checker.appendChild(id_register);
			
			Element remove=this.getDocument().createElement("remove");
			information_checker.appendChild(remove);
			
				Element id_checker=this.createElementWithText("id_checker", idCheckerNumber);
				remove.appendChild(id_checker);

	}

}
