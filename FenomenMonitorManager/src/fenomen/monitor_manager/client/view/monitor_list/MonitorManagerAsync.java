package fenomen.monitor_manager.client.view.monitor_list;

import com.google.gwt.user.client.rpc.AsyncCallback;

/** ����������� ����������  (��������� ��� ���������� �������, �����������)*/
public interface MonitorManagerAsync {

	/** �������� ��� ��������� �������� � ������� 
	 * @return ������ ��������� � ������� 
	 * */
	void getMonitors(AsyncCallback<MonitorWrap[]> callback);

	/** �������� ����� ������� � ������� 
	 * @param monitor �������, ������� ������� �������� � ������� ( monitor.getId()==0)
	 * @return - ����� ������ ��� null( ���� ��� ������ ������� )   
	 * */
	void addMonitor(MonitorWrap monitor, AsyncCallback<String> callback);

	/** �������� ������ �� �������� � ������� 
	 * @param monitor �������, ������� ������� �������� � ������� ( monitor.getId()!=0)
	 * @return - ����� ������ ��� null( ���� ��� ������ ������� )   
	 * */
	void editMonitor(MonitorWrap monitor, AsyncCallback<String> callback);

	/**
	 * ������� ��������� ������� �� ������� 
	 * @param monitor �������, ������� ������� ������� 
	 * @return
	 * <ul>
	 * 	<li>null - ������� ������</li>
	 * 	<li>text - ������ ��������, ����� ������ </li>
	 * <ul>
	 */
	void removeMonitor(MonitorWrap monitor, AsyncCallback<String> callback);

}
