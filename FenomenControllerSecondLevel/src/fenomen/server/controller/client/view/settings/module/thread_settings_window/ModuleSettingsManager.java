package fenomen.server.controller.client.view.settings.module.thread_settings_window;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("moduleSettingsManager")
public interface ModuleSettingsManager extends RemoteService{
	/** �������� ������ ���������� �� ��������� 
	 * @param idModule - ���������� ����� ������
	 * @param idSection - ���������� ����� ������ 
	 * <table>
	 * 	<tr>
	 * 		<th>id</th><th>name</th>
	 *  </tr>
	 * 	<tr>
	 * 		<th>1</th><th>HeartBeat</th>
	 *  </tr>
	 * 	<tr>
	 * 		<th>2</th><th>Information</th>
	 *  </tr>
	 * 	<tr>
	 * 		<th>3</th><th>Alarm</th>
	 *  </tr>
	 * 	<tr>
	 * 		<th>4</th><th>Sensor</th>
	 *  </tr>
	 * </table>
	 * @return
	 * <li><b> array </b> ���� �������� ������ </li>
	 * <li><b> null </b> ������ ��������� ������ </li>
	 */
	public ModuleSettingsWrap[] getListOfParameters(int idModule, int idSection);

	/** 
	 * ���������� �/��� �������� �������� 
	 * @param idModule - ���������� ������������� ������ 
	 * @param idSection - ���������� ������������� ������
	 * @param idParameter - ���������� ������������� ��������
	 * @param value ��������� �������� ���������
	 * @return 
	 * <li><b> null </b> ������� ��������/������ </li>
	 * <li><b> not null </b> ������ �������� </li>
	 */
	public String saveOrUpdateParameter(int idModule, int idSection, int idParameter, String value);
	
	/** ��������/������� ������ ����������
	 * @param idModule - ���������� ������������� ������  
	 * @param idSection - ���������� ������������� �����
	 * <table border=1>
	 * 	<tr><th>Id</th><th>Description</th></tr>
	 * 	<tr><td>1</td><td>HeartBeat</td></tr>
	 * 	<tr><td>2</td><td>Information</td></tr>
	 * 	<tr><td>3</td><td>Alarm</td></tr>
	 * 	<tr><td>4</td><td>Sensor</td></tr>
	 * </table>
	 * @param parameters - ������ ����������, ������� ����� ��������/�������
	 * @return 
	 * <li><b> null </b> ������� ��������/������ </li>
	 * <li><b> not null </b> ������ �������� (�������� ��� ��������� ���� �������� )</li>
	 */
	public String saveOrUpdateParameter(int idModule, int idSection, ModuleSettingsWrap[] parameters);
	
}
