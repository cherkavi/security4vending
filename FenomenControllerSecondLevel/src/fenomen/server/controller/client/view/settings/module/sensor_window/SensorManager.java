package fenomen.server.controller.client.view.settings.module.sensor_window;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sensorManager")
public interface SensorManager extends RemoteService{
	/** ������� �� ���� ������ �� ��������� �� ���������� ������, ����� �� ������ �� �����, ����������� ���������� ��� ���� ��������/�������� � �������
	 * @param moduleId - ���������� ������������� ������ �� ���� ������ (module.id)  
	 * */
	public boolean setTaskGetAllSensor(int moduleId);
	
	/** �������� ��� ������� � ������� �� ��������� ����������� ������ ������ 
	 * @param moduleId - ���������� ������������� ������ �� ���� ������ (module.id)
	 * */
	public Device[] getAllSensorsIntoModule(int moduleId);
	
	/** �������� ������ �������� ������� 
	 * @param moduleId - ��� ������ � ���� ������  (module.id)
	 * @param addressModbus - ���������� ����� � ���� Modbus ��� �������/�������
	 * @return {@link Device} ������-������� ��� ������ �� ������� ���� ������ 
	 */
	public Device getSensorIntoModule(int moduleId, int addressModbus);
	
	
	/** ������� ������ �� ��������� ������ ��� ��������� �������� �� ���� ��������� �� �������  
	 * @param moduleId - ��� ������ � ���� ������ (module.id)
	 * @param addressModbus - ���������� ����� � ���� Modbus ��� ������� �������/������� 
	 * @return 
	 * <li> <b>true</b> task ������� ���������� </li> 
	 * <li> <b>false</b> ������ ��������� Task </li> 
	 * */
	public boolean setTaskGetValueFromModule(int moduleId, int addressModbus);
	
	/** ������� ������ �� ��������� �������� � ������ 
	 * @param moduleId - ��� ������ � ���� ������  (module.id)
	 * @param addressModbus - ���������� ����� �������/������� � ���� ModBus
	 * @param registerAddress - ���������� ����� �������� �� ������  
	 * @param value - ��������, ������� ����� �������� � �������
	 * @param isEnabled  
	 * <ul type="square">
	 * 		<li> <b>true</b>- ���������� �������� ��� ������� Enabled</li>
	 * 		<li> <b>false</b>- ���������� �������� ��� ������� Disabled</li>
	 * 		<li> <b>null</b>- �� �������� �������� �������</li>
	 * </ul>
	 * @return 
	 * <li> <b>true</b> task ������� ���������� </li> 
	 * <li> <b>false</b> ������ ��������� Task </li> 
	 * */
	public boolean setTaskPutValueToModuleToRegister(int moduleId, 
										   			 int addressModbus,
										   			 int registerAddress,
										   			 int value, 
										   			 Boolean isEnabled);

	/** ������� ������ �� ��������� �������� � ������ 
	 * @param moduleId - ��� ������ � ���� ������  (module.id)
	 * @param addressModbus - ���������� ����� �������/������� � ���� ModBus
	 * @param registers - ������ ��������� �� ������ ( � ���� ������� )   
	 * @param values - �������� ��� ��������� (� ���� ������� ������������ ��������� registers )
	 * @param isEnabled  
	 * <ul type="square">
	 * 		<li> <b>true</b>- ���������� �������� ��� ������� Enabled</li>
	 * 		<li> <b>false</b>- ���������� �������� ��� ������� Disabled</li>
	 * 		<li> <b>null</b>- �� �������� �������� �������</li>
	 * </ul>
	 * @return 
	 * <li> <b>true</b> task ������� ���������� </li> 
	 * <li> <b>false</b> ������ ��������� Task </li> 
	 * */
	public boolean setTaskPutValueToModuleToRegister(int moduleId, 
										   			 int addressModbus,
										   			 int[] registers,
										   			 int[] values, 
										   			 Boolean isEnabled);

}
