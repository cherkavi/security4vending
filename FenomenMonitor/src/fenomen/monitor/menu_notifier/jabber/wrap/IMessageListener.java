package fenomen.monitor.menu_notifier.jabber.wrap;

/** ����������� � �������� ���������� */
public interface IMessageListener {
	/** ����������� � �������� ���������� 
	 * @param from - �� ����
	 * @param text - ����� ��������� ���������
	 */
	public void messageNotify(String from, String text);
}
