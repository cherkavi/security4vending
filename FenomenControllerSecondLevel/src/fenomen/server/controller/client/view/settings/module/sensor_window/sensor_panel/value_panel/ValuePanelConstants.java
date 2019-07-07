package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.value_panel;

import com.google.gwt.i18n.client.Constants;

public interface ValuePanelConstants extends Constants{
	/** ��������� ��� ������ */
	public String title();
	/** �������� ������� */
	public String labelSensorValue();
	/** ���� ��������� ������� � ��������� �������� */
	public String labelDateValue();
	/** ��������� ��� ������ "���������� ��������"*/
	public String labelButtonSet();
	/** ��������� ��� ������ "�������� ��������"*/
	public String labelButtonGet();
	
	/** ������������� ��������� �������� ��������� */
	public String confirmSetValueTitle();
	
	/** �������������� � ������������� ����� � �������� */
	public String serverError();
	
	/** ���������� ����� � �������� */
	public String serverExchange();
	/** ������ ������� ��������� � �������, �������� ������ �� ������ */
	public String taskWasSended();
}
