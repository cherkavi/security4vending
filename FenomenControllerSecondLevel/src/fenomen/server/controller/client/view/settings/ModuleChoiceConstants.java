package fenomen.server.controller.client.view.settings;

import com.google.gwt.i18n.client.Constants;

public interface ModuleChoiceConstants extends Constants{
	/** ��������� ��� ������ */
	@DefaultStringValue("Modules")
	public String panelTitle();
	
	/** ����������� ��������� ��� ������ "�����" � ������� ����� ������ */
	@DefaultStringValue("Modules")
	public String searchButtonTitle();
	
	/** ������ ���������� � �������� */
	@DefaultStringValue("server error")
	public String serverConnectionError();
	
	/** ��������� ��� ������� ID */
	@DefaultStringValue("id")
	public String gridHeaderId();
	
	/** ��������� ��� �������������� ������ */
	@DefaultStringValue("id module")
	public String gridHeaderIdModule();
	
	/** ��������� ��� ������ */
	@DefaultStringValue("Address")
	public String gridHeaderAddress();

	/** ������� ���� � ��������� � ������� ���� */
	@DefaultStringValue("Close")
	public String closeWindow();
}
