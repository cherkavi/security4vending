package swing_framework.messages;

import swing_framework.AbstractInternalFrame;
import swing_framework.window.WindowIdentifier;

/** ������, ������� ��������� ��������� ��������� ������ ����� � ������� */
public interface IMessageSender {
	/** �������� ��������� �� ������, ������� ��������� ���������� 
	 * @param owner - ����������� ������� ��������� ( ��� null)
	 * @param message - ���������, ������� ����� ������� 
	 * @return
	 */
	public boolean sendMessage(AbstractInternalFrame owner, WindowMessage message);
	
	/** ���������������� ���� � ������� */
	public boolean addWindowListener(AbstractInternalFrame listener,WindowIdentifier identifier);
	
	/** ������� ������������������ ���� �� ������� */
	public boolean removeWindowListener(AbstractInternalFrame listener); 
}
