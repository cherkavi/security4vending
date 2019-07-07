package fenomen.server.controller.client.view.settings.module.sensor_window;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SensorManagerAsync {
	/** ������� �� ���� ������ �� ��������� �� ���������� ������, ����� �� ������ �� �����, ����������� ���������� ��� ���� ��������/�������� � �������
	 * @param moduleId - ���������� ������������� ������ �� ���� ������ (module.id)  
	 * */
	void setTaskGetAllSensor(int moduleId, AsyncCallback<Boolean> callback);

	/** �������� ��� ������� � ������� �� ��������� ����������� ������ ������ 
	 * @param moduleId - ���������� ������������� ������ �� ���� ������ (module.id)
	 * */
	void getAllSensorsIntoModule(int moduleId,
			AsyncCallback<Device[]> callback);

	/** �������� ������ �������� ������� 
	 * @param moduleId - ��� ������ � ���� ������  (module.id)
	 * @param addressModbus - ���������� ����� � ���� Modbus ��� �������/�������
	 * @return {@link Device} ������-������� ��� ������ �� ������� ���� ������ 
	 */
	void getSensorIntoModule(int moduleId, int addressModbus,
			AsyncCallback<Device> callback);

	/** ������� ������ �� ��������� ������ ��� ��������� �������� �� ���� ��������� �� �������  
	 * @param moduleId - ��� ������ � ���� ������ (module.id)
	 * @param addressModbus - ���������� ����� � ���� Modbus ��� ������� �������/������� 
	 * @return 
	 * <li> <b>true</b> task ������� ���������� </li> 
	 * <li> <b>false</b> ������ ��������� Task </li> 
	 * */
	void setTaskGetValueFromModule(int moduleId, int addressModbus,
			AsyncCallback<Boolean> callback);

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
	void setTaskPutValueToModuleToRegister(int moduleId, int addressModbus,
			int registerAddress, int value, Boolean isEnabled,
			AsyncCallback<Boolean> callback);

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
	void setTaskPutValueToModuleToRegister(int moduleId, int addressModbus,
			int[] registers, int[] values, Boolean isEnabled,
			AsyncCallback<Boolean> callback);

}
