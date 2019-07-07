package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.WrapChecker;

public interface AlarmCheckerManagerAsync {
	public final static String presentationInteger="integer";
	public final static String presentationDouble="double";
	public final static String presentationZeroOne="0 or 1";
	public final static String presentationString="string";
	
	public final static String conditionEQ="==";
	public final static String conditionNE="!=";
	public final static String conditionGT=">";
	public final static String conditionGE=">=";
	public final static String conditionLT="<";
	public final static String conditionLE="<=";

	/**
	 * �������� ��� ������������������ � ���� ������ AlarmChecker-� �� ������.�������
	 * @param idModule - ���������� id ������ (module.id � �������� ���� ������ ) 
	 * @param modbusAddress - ���������� ����� �������-������ 
	 * @return ������ ������������������ �� ������� ������ �������� 
	 */
	void getListOfAlarmChecker(int idModule, int modbusAddress,
			AsyncCallback<AlarmCheckerTableElement[]> callback);

	/** �������� ������ ���� ��������� �� ������.������� 
	 * @param idModule - ���������� ����� ������ � �������� ���� (module.id)
	 * @param modbusAddress - ���������� ������������� ������ � ���� modbus
	 * @return ������ ���� int, ��������� �� ������� ��������� ���������  
	 */
	void getListOfRegisterAddress(int idModule, int modbusAddress,
			AsyncCallback<int[]> callback);

	/**
	 * ������� AlarmChecker
	 * @param idModule - ���������� ����� ������ � ���� ������ (module.id)
	 * @param modbusAddress - ���������� ����� ������ � ���� ModBus ()
	 * @param registerNumber - ����� �������� �� ������� � ���� Modbus 
	 * @param idChecker - ���������� ����� checker-a �� ���� ������
	 * @return
	 * <li><b>null</b> remove was done </li>
	 * <li><b>Test of Error</b> remove error, returned text of error</li>
	 */
	void removeAlarmChecker(int idModule, 
							int modbusAddress, 
							int registerNumber,
							int idChecker, 
							AsyncCallback<String> callback);


	/**
	 * ��������/�������������  AlarmChecker
	 * @param wrapChecker - ������� ��� Checker-a
	 * @return
	 * <li><b>null</b> Checker ������ ��������  </li>
	 * <li><b>Value</b> ����� ������ </li>
	 */
	void addAlarmChecker(WrapChecker wrapChecker, AsyncCallback<String> callback);


	/** �������� Checker �� ����������� ����� 
	 * @param id - ���������� ������������� �� ���� ������: module_alarm_checker.id
	 * */
	void getAlarmChecker(Integer id, AsyncCallback<WrapChecker> callback);

}
