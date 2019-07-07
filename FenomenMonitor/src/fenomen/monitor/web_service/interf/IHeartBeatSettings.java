package fenomen.monitor.web_service.interf;

import fenomen.monitor.web_service.common.HeartBeatSettingsElement;

import fenomen.monitor.web_service.common.MonitorIdentifier;

/** ���������� ������� HeartBeatSettings  */
public interface IHeartBeatSettings {
	/** �������� ������ 
	 * @param moduleIdentifier - ���������� ������������� �������� 
	 * */
	public HeartBeatSettingsElement[] getList(MonitorIdentifier moduleIdentifier) throws Exception;
	/** �������� ������  
	 * @param moduleIdentifier - ���������� ������������� ��������
	 * @param list - ������ ���������, ������� ����� �������� � ���� ������ (�� ����������� ������ ������ ���� ����������, ���������� {@link #getList(MonitorIdentifier)}) 
	 * */
	public boolean updateList(MonitorIdentifier moduleIdentifier, HeartBeatSettingsElement[] list) throws Exception;
}
