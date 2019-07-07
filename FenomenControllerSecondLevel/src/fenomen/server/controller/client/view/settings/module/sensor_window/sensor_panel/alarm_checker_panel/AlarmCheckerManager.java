package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.WrapChecker;

/** ��������� �� ��������, �������������� � �������� AlarmChecker-�� �� ������� ������� */
@RemoteServiceRelativePath(value="alarmCheckerManager")
public interface AlarmCheckerManager extends RemoteService{

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
	
	// this.textForClient.getText();
	// this.textForDescription.getText();
	// this.panelValuePresentation.getSelectedName();
	// this.panelValueCondition.getSelected();
	// ����������� �������� 
	// ��������� ��������
	
	/**
	 * ��������/�������������  AlarmChecker
	 * @param wrapChecker - ������� ��� Checker-a
	 * @return
	 * <li><b>null</b> Checker ������ ��������  </li>
	 * <li><b>Value</b> ����� ������ </li>
	 */
	public String addAlarmChecker(WrapChecker wrapChecker);

	/** �������� Checker �� ����������� ����� 
	 * @param id - ���������� ������������� �� ���� ������: module_alarm_checker.id
	 * */
	public WrapChecker getAlarmChecker(Integer id);
	
	/**
	 * �������� ��� ������������������ � ���� ������ AlarmChecker-� �� ������.�������
	 * @param idModule - ���������� id ������ (module.id � �������� ���� ������ ) 
	 * @param modbusAddress - ���������� ����� �������-������ 
	 * @return ������ ������������������ �� ������� ������ �������� 
	 */
	public AlarmCheckerTableElement[] getListOfAlarmChecker(int idModule, int modbusAddress);

	/** �������� ������ ���� ��������� �� ������.������� 
	 * @param idModule - ���������� ����� ������ � �������� ���� (module.id)
	 * @param modbusAddress - ���������� ������������� ������ � ���� modbus
	 * @return ������ ���� int, ��������� �� ������� ��������� ���������  
	 */
	public int[] getListOfRegisterAddress(int idModule, int modbusAddress);

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
	public String removeAlarmChecker(int idModule, 
									 int modbusAddress, 
									 int registerNumber, 
									 int idChecker);
}
