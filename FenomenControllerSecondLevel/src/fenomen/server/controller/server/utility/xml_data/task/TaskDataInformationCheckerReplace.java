package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** �������� InformationChecker ��� ���������� �������  � ���������� InformationChecker-�*/
public class TaskDataInformationCheckerReplace extends TaskData{

	/** �������� ��� InformationChecker-� �� ���������� ������� 
	 * @param addressModbus - ���������� ����� �������/������ � ���� Modbus
	 * @param register - ����� �������� �� ������/�������
	 * @param idCheckerNumber - ���������� ����� Checker-a � ��������� ������� 
	 * @param idModuleInformationChecker - ���������� ����� ������ � ������� module_information_checker  
	 * */
	public TaskDataInformationCheckerReplace(int addressModbus,
											 int register,
											 int idCheckerNumber, 
											 int idModuleInformationChecker) {
		super(true, "�������� InformationChecker ��� ������� �"+addressModbus+"  �������: "+ register+" checkerNumber:"+idCheckerNumber);

		Element information_checker=this.getDocument().createElement("information_checker");
		this.getTaskElement().appendChild(information_checker);
		
			Element id_modbus=this.createElementWithText("id_modbus", addressModbus);
			information_checker.appendChild(id_modbus);
			
			Element id_register=this.createElementWithText("register", register);
			information_checker.appendChild(id_register);

			Element replace=this.getDocument().createElement("replace");
			information_checker.appendChild(replace);
			
				Element id_checker=this.createElementWithText("id_checker", idCheckerNumber);
				replace.appendChild(id_checker);
		
					Element id_file=this.createElementWithText("id_file", idModuleInformationChecker);
					replace.appendChild(id_file);
	}

}
