package swing_framework.messages;

/** ������, ������� �������� ��� ���������, �������� ����� ������������ ���� */
public class WindowMessageType {
	/** ���������������� ���������, � �������������� ���������� � �������� */
	public static final int NOTIFY_USER=0;
	/** ����������� JInternalFrame � ������� ���� new MessageWindow(REGISTER_WINDOW, additinalIdentifier:WindowIdentifier, argument: AbstractInternalFrame)*/
	public static final int REGISTER_WINDOW=1;
	/** ����������� ������ ���� � ����� ������� ��������� */
	public static final int MOVE_ON_TOP=2;
	/** ���� �������� � ����� ��������  - ��������� ��� �������� ���� (<b>owner</b> - ����, ������� ����� ��������� ���������, <b>Argument</b> - ���� ������� ����� �������)*/
	public static final int UNREGISTER_WINDOW=3;
	/** ����������� ���� � �������� �������� ����� new MessageWindow(MOVE_TO_CENTER, additinalIdentifier:null, argument: AbstractInternalFrame)*/
	public static final int MOVE_TO_CENTER=4;
	/** ���������� ����� �� ������ ����  */
	public static final int SELECTED_SET=5;
	/** ���� ���� ������������ - ���������� �������� ���� �� ���������  */
	public static final int FOCUS_WAS_GOT=6;
	/** ����� ����� � ��������� ���� */
	public static final int UNSELECTED_SET=7;
	/** �������� ���� �� ������� ���� <b>new WindowMessage(REGISTER_CHILD_WINDOW, additinalIdentifier:WindowIdentifier, argument: AbstractInternalFrame)</b>*/
	public static final int REGISTER_CHILD_WINDOW=8;
	/** ������ ���� � �������� ����� <b>new WindowMessage(UNREGISTER_CHILD_WINDOW, additionalIdentifier:WindowIdentifier, argument: AbstractInternalFrame)</b>*/
	public static final int UNREGISTER_CHILD_WINDOW=9;
	/** �������� ����������� �������� ����� - ����� ����� ������� � �������� Dimension � Argument */
	public static final int GET_DESKTOP_DIMENSION=10;
	/** ��������� � ��� ��� ������ ���� ������ ���� ������� new WindowMessage(CLOSE_WINDOW, additionalIdentifier:null, argument: AbstractIternalFrame - ������� ������ ���� ������� ) */
	public static final int CLOSE_WINDOW=11;
	/** ��������� � ��� ��� ������ ���� ������ ���� ������� � ������ new WindowMessage(ICONIFY_WINDOW, additionalIdentifier:null, argument: AbstractInternalFrame - ������� ������ ���� �������� )*/
	public static final int ICONIFY_WINDOW = 12;
	/** ��������� � ��� ��� ������ ���� ������ ���� ���������� �� ������ new WindowMessage(DEICONIFY_WINDOW, additionalIdentifier:null, argument: AbstractInternalFrame - ������� ������ ���� ���������� )*/
	public static final int DEICONIFY_WINDOW = 13;
	/** ��������� ��� ������������� ���� ��� �����-�� �������� ���� ���������� ���� ����������� - ��������� ��� ����������� ���������� ��� ����*/
	public static final int CHILD_SET_MODAL = 14;
}
