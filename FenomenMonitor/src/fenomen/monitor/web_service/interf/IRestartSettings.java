package fenomen.monitor.web_service.interf;

import fenomen.monitor.web_service.common.MonitorIdentifier;
import fenomen.monitor.web_service.common.RestartSettingsElement;

/** ���������� ������� RestartSettings  */
public interface IRestartSettings {
	/** �������� ������ 
	 * @param moduleIdentifier - ���������� ������������� �������� 
	 * */
	public RestartSettingsElement[] getList(MonitorIdentifier moduleIdentifier) throws Exception;
	/** �������� ������  
	 * @param moduleIdentifier - ���������� ������������� ��������
	 * @param list - ������ ���������, ������� ����� �������� � ���� ������ (�� ����������� ������ ������ ���� ����������, ���������� {@link #getList(MonitorIdentifier)}) 
	 * */
	public boolean updateList(MonitorIdentifier moduleIdentifier, RestartSettingsElement[] list) throws Exception;
}
