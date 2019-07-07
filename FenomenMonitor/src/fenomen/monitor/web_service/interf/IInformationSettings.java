package fenomen.monitor.web_service.interf;

import fenomen.monitor.web_service.common.InformationSettingsElement;

import fenomen.monitor.web_service.common.MonitorIdentifier;

/** ���������� ������� InformationSettings  */
public interface IInformationSettings {
	/** �������� ������ 
	 * @param moduleIdentifier - ���������� ������������� �������� 
	 * */
	public InformationSettingsElement[] getList(MonitorIdentifier moduleIdentifier) throws Exception;
	/** �������� ������  
	 * @param moduleIdentifier - ���������� ������������� ��������
	 * @param list - ������ ���������, ������� ����� �������� � ���� ������ (�� ����������� ������ ������ ���� ����������, ���������� {@link #getList(MonitorIdentifier)}) 
	 * */
	public boolean updateList(MonitorIdentifier moduleIdentifier, InformationSettingsElement[] list) throws Exception;
}
