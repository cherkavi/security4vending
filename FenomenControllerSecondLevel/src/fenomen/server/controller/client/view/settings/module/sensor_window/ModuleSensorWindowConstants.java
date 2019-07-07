package fenomen.server.controller.client.view.settings.module.sensor_window;

import com.google.gwt.i18n.client.Constants;

public interface ModuleSensorWindowConstants extends Constants{
	/** ��������� ��� ���� */
	public String windowTitle();
	/** ����������� ��������� ��� ������ ������� */
	public String buttonCloseTooltip();
	/** ������ �������� ��� ������� */
	public String buttonReadAllSensors();
	/** ������ ������� � �������� */
	public String serverExchangeError();
	
	/** ��������� �������������� ������ ������ ���� �������� */
	public String titleConfirmReadAllSensor();
	/** ����� �������������� ������ ���� �������� */
	public String textConfirmReadAllSensor();
	/** ������ �� ���������� ���� �������� �� ������� ������ � �������� �� ��������� ������ */
	public String taskRefreshAllSensorSetOk();
	/** �������������� ����� � �������� */
	public String serverExchange();
	
}
