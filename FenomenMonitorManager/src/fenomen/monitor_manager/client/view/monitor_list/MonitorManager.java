package fenomen.monitor_manager.client.view.monitor_list;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/** ����������� ����������  (���������)*/
@RemoteServiceRelativePath(value="monitor_manager")
public interface MonitorManager extends RemoteService{

	/** �������� ��� ��������� �������� � ������� 
	 * @return ������ ��������� � ������� 
	 * */
	public MonitorWrap[] getMonitors();
	
	/** �������� ����� ������� � ������� 
	 * @param monitor �������, ������� ������� �������� � ������� ( monitor.getId()==0)
	 * @return - ����� ������ ��� null( ���� ��� ������ ������� )   
	 * */
	public String addMonitor(MonitorWrap monitor);
	
	/** �������� ������ �� �������� � ������� 
	 * @param monitor �������, ������� ������� �������� � ������� ( monitor.getId()!=0)
	 * @return - ����� ������ ��� null( ���� ��� ������ ������� )   
	 * */
	public String editMonitor(MonitorWrap monitor);
	
	/**
	 * ������� ��������� ������� �� ������� 
	 * @param monitor �������, ������� ������� ������� 
	 * @return
	 * <ul>
	 * 	<li>null - ������� ������</li>
	 * 	<li>text - ������ ��������, ����� ������ </li>
	 * <ul>
	 */
	public String removeMonitor(MonitorWrap monitor);
}
