package fenomen.monitor.web_service.interf;

import fenomen.monitor.web_service.common.AlarmSettingsElement;

import fenomen.monitor.web_service.common.MonitorIdentifier;

/** ���������� ������� AlarmSettings  */
public interface IAlarmSettings {
	/** �������� ������ 
	 * @param moduleIdentifier - ���������� ������������� �������� 
	 * */
	public AlarmSettingsElement[] getList(MonitorIdentifier moduleIdentifier) throws Exception;
	/** �������� ������  
	 * @param moduleIdentifier - ���������� ������������� ��������
	 * @param list - ������ ���������, ������� ����� �������� � ���� ������ (�� ����������� ������ ������ ���� ����������, ���������� {@link #getList(MonitorIdentifier)}) 
	 * */
	public boolean updateList(MonitorIdentifier moduleIdentifier, AlarmSettingsElement[] list) throws Exception;
}
