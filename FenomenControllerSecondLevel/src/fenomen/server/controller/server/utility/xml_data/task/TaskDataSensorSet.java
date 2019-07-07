package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;


/** ������� ������ � ������ �� ��������� ���������� ��� ������ ������� */
public class TaskDataSensorSet extends TaskData{

	/** ������ � ������ �� ��������� ���������� ��� ������ ������� 
	 * @param modbusAddress - ����� � ���� Modbus 
	 * @param sensorEnabled - <li><b>true</b> Enabled </li> <li><b>false</b> Disabled </li> <li><b>null</b> �� ��������  </li>
	 * @param registerNumbers - ������ ��������� [0..(n-1)]   
	 * @param registerValues - �������� ��������� [0..(n-1)]
	 */
	public TaskDataSensorSet(int modbusAddress, 
							 Boolean sensorEnabled,
							 int[] registerNumbers,
							 int[] registerValues){
		super(true, "���������� ��������� ������� �"+modbusAddress);

		Element sensor_set=this.getDocument().createElement("sensor_set");
		this.getTaskElement().appendChild(sensor_set);
		
			Element modbus_id=this.createElementWithText("id_modbus", modbusAddress);
			sensor_set.appendChild(modbus_id);
			
			Element enabled=this.createElementWithText("enabled",(sensorEnabled==null)?"":Boolean.toString(sensorEnabled));
			sensor_set.appendChild(enabled);
			
			Element register_list=this.getDocument().createElement("register_list");
			sensor_set.appendChild(register_list);

			for(int counter=0;counter<=registerNumbers.length;counter++){
				Element register=this.getDocument().createElement("register");
				sensor_set.appendChild(register);
					Element number=this.getDocument().createElement("number");
					number.setTextContent(Integer.toString(registerNumbers[counter]));
					register.appendChild(number);
					
					Element value=this.getDocument().createElement("value");
					value.setTextContent(Integer.toString(registerValues[counter]));
					register.appendChild(value);
			}
	}
	
}
