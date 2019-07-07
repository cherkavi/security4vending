package fenomen.monitor.menu_notifier.jabber.message_table;

import fenomen.monitor.web_service.common.XmlMessage;

/** ������, ������� ���������� �������, ����������� �� �������  */
public class MessageElement{
	private final static long serialVersionUID=1L;
	/** ������, ������� ���� �������� �� �������  */
	private XmlMessage data;
	/** ���� ����������� ����, ��� ������ ������� ��������� � ��������� �������� ������ �� �������  */
	private boolean process;
	
	/** ������, ������� ���������� �������, ����������� �� �������  
	 * @param xmlMessage - �������, ������� ��������� �� ������� 
	 */
	public MessageElement(XmlMessage xmlMessage){
		this.data=xmlMessage;
	}
	
	/** �������� ������, ���������� ����������� ������ */
	public XmlMessage getData(){
		return this.data;
	}

	/** ���� ����������� ����, ��� ������ ������� ��������� � ��������� �������� ������ �� �������  */
	public boolean isProcess() {
		return process;
	}

	/** ���� ����������� ����, ��� ������ ������� ��������� � ��������� �������� ������ �� �������  */
	public void setProcess(boolean process) {
		this.process = process;
	}
	
	
}
