package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel.add_checker;

import com.google.gwt.i18n.client.Constants;

public interface AddAlarmCheckerConstants extends Constants{
	/** ����������� ��������� ��� ������ ������� */
	public String buttonCloseHint();
	
	/** ��������� ��� ����� ���������, ������� ����� ��������� �� ������ ��� ������������� ������� */
	public String captionAlertForUserMessage();// ���������, ������� ����� ������������ ��� ���������:
	
	/** ��������� ��� ����� ��������, ������� ����� ������������ � �������� Description ��� AlertChecker */
	public String captionAlertForDescription();// ������� �������� ������� AlertChecker-a
	
	/** caption for Button save */
	public String captionButtonSave();// ���������
	
	/** caption for Button Cancel */
	public String captionButtonCancel();// ��������
	/** ������������ ������ */
	public String lessData(); 
	/** ������ �������������� */
	public String errorConversion(); 
	/** ��������� ��� */
	public String needForType();
	/** ���������� ������ ����� ���������� �� ��������� ��������  */
	public String needForTimeRepeat();
	/** ������� ��������� ��������� */
	public String inputAlarmMessage();
	/** �������� ����� �� �������������� �������� */
	public String choicePresentationValue();
	/** �������� ����� �� ������� �� �������� */
	public String choiceConditionValue();
	/** �������� */
	public String waitMessage();
	/** ����������...  */
	public String saving();
	/** ������ ������ � �������� */
	public String serverExchangeError();
}
