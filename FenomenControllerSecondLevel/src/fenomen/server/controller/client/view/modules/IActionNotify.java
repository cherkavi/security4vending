package fenomen.server.controller.client.view.modules;

/** ������ ��������� ������� �� ������ �������  */
public interface IActionNotify {
	/** ���������� � ���������� �������� 
	 * @param actionName - ���������� ��� �������� (������������� �������� )
	 * @param parameter - �������� ������� �������� (nullable)
	 */
	public void actionNotify(String actionName, Object parameter);
}
