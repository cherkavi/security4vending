package fenomen.monitor.web_service.common;

public class JabberSettings {
	/** URL jabber-������� */
	private String jabberUrl;
	/** ���� jabber */
	private int jabberPort;
	/** jabber proxy */
	private String jabberProxy;
	/** ����� */
	private String jabberLogin;
	/** ������ */
	private String jabberPassword;
	
	/** ����� �������, �� ������� ����� �������� ����������� */
	private String serverAddress;
	
	public JabberSettings(){
	}

	/** URL jabber-������� */
	public String getJabberUrl() {
		return jabberUrl;
	}

	/** URL jabber-������� */
	public void setJabberUrl(String jabberUrl) {
		this.jabberUrl = jabberUrl;
	}

	/** ���� jabber */
	public int getJabberPort() {
		return jabberPort;
	}

	/** ���� jabber */
	public void setJabberPort(int jabberPort) {
		this.jabberPort = jabberPort;
	}

	/** jabber proxy */
	public String getJabberProxy() {
		return jabberProxy;
	}

	/** jabber proxy */
	public void setJabberProxy(String jabberProxy) {
		this.jabberProxy = jabberProxy;
	}

	/** ����� */
	public String getJabberLogin() {
		return jabberLogin;
	}

	/** ����� */
	public void setJabberLogin(String jabberLogin) {
		this.jabberLogin = jabberLogin;
	}


	
	/** ������ */
	public String getJabberPassword() {
		return jabberPassword;
	}

	/** ������ */
	public void setJabberPassword(String jabberPassword) {
		this.jabberPassword = jabberPassword;
	}


	
	/** ����� �������, �� ������� ����� �������� ����������� */
	public String getServerAddress() {
		return serverAddress;
	}

	/** ����� �������, �� ������� ����� �������� ����������� */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
}
