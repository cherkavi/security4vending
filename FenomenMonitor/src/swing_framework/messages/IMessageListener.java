package swing_framework.messages;

/** ������ - ��������� ������� ��������� */
public interface IMessageListener {
	/** ������ - ��������� ������� ��������� 
	 * @param message - ���������, ������� ����� ������� ������ ����� 
	 */
	public boolean notifyMessage(WindowMessage message);
}
