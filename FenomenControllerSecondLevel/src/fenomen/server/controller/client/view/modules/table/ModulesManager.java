package fenomen.server.controller.client.view.modules.table;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fenomen.server.controller.client.view.modules.table.row.RowData;

/** ���������� �������� �� ������� (��������, �������, �������������)*/
@RemoteServiceRelativePath("modulesManager")
public interface ModulesManager extends RemoteService{
	/** �������� ��� �������� �� ������� */
	public RowData[] getAllRowElement();
	
	/** ���������� ������ � ������������� �������� ������ 
	 * @param id - ���������� ������������� ������ 
	 * @return 
	 * <li><b>true</b> - ������ </li>
	 * <li><b>false</b> - ������ �������� ������ </li>
	 */
	public boolean removeRowElement(int id);
	
	/** ���������� ������ � ������������� ���������� ������ 
	 * @param id -���������� ������������� ������ ��� ����������
	 * @param rowData - ������ ��� ����������  
	 * @return
	 * <li><b>null</b> - ���������� ������ ������� </li>
	 * <li><b>not null </b> - ����� ������� �� ���������� ������ </li>
	 */
	public String updateRowElement(int id, RowData rowData);

	/** ���������� ������ � ������������� �������� ������ ��������-������
	 * @param rowData - �������, ������� ������ ���� ������ (��� Id)
	 * @return 
	 * <li><b>null</b> - ���������� ������ ������� </li>
	 * <li><b>not null </b> - ����� ������� �� ���������� ������ </li>
	 * */
	public String createRowElement(RowData rowData);
}
