package fenomen.server.controller.client.view.settings;

/** ��������� �� ������ ������ */
public interface IFindModule {
	/** �������� ������ �� ������ 
	 * @param moduleId - ( nullable ) ���������� ������������� ������ 
	 * @param address - ( nullable ) ����� ������ 
	 */
	public void filter(String moduleId, String address);
}
