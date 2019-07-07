package fenomen.monitor.web_service.interf;

import fenomen.monitor.web_service.common.JabberSettings;
import fenomen.monitor.web_service.common.MonitorIdentifier;

/** ������������� ������������ � ����  */
public interface ILogin {
	/** �������� ������������� �������� �� ��������� ������ � ������
	 * @param login - ����� 
	 * @param password - ������
	 * @return - 
	 * <ul>
	 * 	<li>���������� ������������� ��������, ��� ����������� ������ � ��������</li>
	 * 	<li>null - ������������ ��� ������ �� ������� </li>
	 * </ul>
	 * @throws � ������ ������ ������ � ���������
	 */
	public MonitorIdentifier enter(String login, String password) throws Exception;
	
	/** �������� jabber ��������� �� ������� ������� ��� ����� � �������� ����� XMPP �������� */
	public JabberSettings getJabberSettings(MonitorIdentifier monitorIdentifier) throws Exception ;
}
