package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� InformationChecker ��� ���������� �������  */
public class TaskDataInformationCheckerAdd extends TaskData{

	/** �������� ��� InformationChecker-� �� ���������� ������� 
	 * @param addressModbus - ����� ����������/������� � ���� Modbus
	 * @param register - ����� �������� �� ���������� (���� ������� ����� �����, ����� ���� checker �� ��������� ��������� )
	 * @param idModuleInformationChecker - ���������� ����� ������ � ������� module_information_checker  
	 * */
	public TaskDataInformationCheckerAdd(int addressModbus, int register, int idModuleInformationChecker) {
		super(true, "�������� InformationChecker ��� �������/������ �"+addressModbus+" �������: "+register);

		Element information_checker=this.getDocument().createElement("information_checker");
		this.getTaskElement().appendChild(information_checker);
		
			Element id_modbus=this.createElementWithText("id_modbus", addressModbus);
			information_checker.appendChild(id_modbus);

			Element id_register=this.createElementWithText("register", register);
			information_checker.appendChild(id_register);
			
			Element add=this.getDocument().createElement("add");
			information_checker.appendChild(add);
			
				Element id_file=this.createElementWithText("id_file", idModuleInformationChecker);
				add.appendChild(id_file);
	}

}
