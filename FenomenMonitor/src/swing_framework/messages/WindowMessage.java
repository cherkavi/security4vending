package swing_framework.messages;

/** ���������, ������� ������������ �����, ������� ���������������� ��� ��������� */
public class WindowMessage {
	private int messageId;
	private Object additionalIdentifier;
	private Object argument;
	
	/** ���������, ������� ������������ �����, ������� ���������������� ��� ��������� 
	 * @param messageid {@link WindowMessageType}
	 * */
	public WindowMessage(int messageId){
		this.messageId=messageId;
	}

	/** ���������, ������� ������������ �����, ������� ���������������� ��� ��������� 
	 * @param messageid {@link WindowMessageType}
	 * @param additionalIdentifier - �������������� ������, ������� ��������� ������
	 * */
	public WindowMessage(int messageId, String additionalIdentifier){
		this.messageId=messageId;
		this.additionalIdentifier=additionalIdentifier;
	}
	
	/** ���������, ������� ������������ �����, ������� ���������������� ��� ��������� 
	 * @param messageid {@link WindowMessageType}
	 * @param additionalIdentifier - �������������� ������, ������� ��������� ������
	 * @param argument - ������, ������� ���������� � �������� ���������
	 * */
	public WindowMessage(int messageId, Object additionalIdentifier, Object argument){
		this.messageId=messageId;
		this.additionalIdentifier=additionalIdentifier;
		this.argument=argument;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public Object getAdditionalIdentifier() {
		return additionalIdentifier;
	}

	public void setAdditionalIdentifier(Object additionalIdentifier) {
		this.additionalIdentifier = additionalIdentifier;
	}

	public Object getArgument() {
		return argument;
	}

	public void setArgument(Object argument) {
		this.argument = argument;
	}
	
	
	
}
