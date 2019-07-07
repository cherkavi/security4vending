package swing_framework;

import javax.swing.JInternalFrame;

/** ���������, ������� �������� ���������� �� ������������ �� �������� ��������� ���� */
public interface IParent {
	/** �������� ���� ��������� ������������ � ������������� ��������� ������ ������� ��������� */
	public void selfSetVisible(boolean isVisible);
	/** �������� ���� ��������� ������������ � ����� ��������, � �������� � ��������� ���� ���� */
	public void childWindowWasClosed(JInternalFrame source);
}
