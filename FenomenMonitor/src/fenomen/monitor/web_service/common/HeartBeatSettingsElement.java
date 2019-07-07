package fenomen.monitor.web_service.common;

/** ������, ������� ������������ ���� ������� �� ������� monitor_settings_heart_beat */
public class HeartBeatSettingsElement {
	/** ���������� ������������� � ���� ������ */
	private int id;
	/** ��� ������ ��� �������������, ��� ������� �����  */
	private String moduleId;
	/** ����� ������ */
	private String moduleAddress;
	/** ����� �� ��������� �� ������� ������  */
	private boolean enabled;
	/** ���-�� ������ ����� ������� ������� ���������� ������ ������������ ��� �� ��������*/
	private int timeWait;
	/** �������� � ��������, ������� ����������� ��� ������ � �������� ������������, � timeWait ������ ���� ������ */
	private int settingValue;
	
	/** ������, ������� ������������ ���� ������� �� ������� Restart */
	public HeartBeatSettingsElement(){
	}

	/** ���������� ������������� �� ���� ������ (monitor_settings_restart.id)*/
	public int getId() {
		return id;
	}

	/** ���������� ������������� �� ���� ������ (monitor_settings_restart.id)*/
	public void setId(int id) {
		this.id = id;
	}


	/** ���� ���������� */
	public boolean isEnabled() {
		return enabled;
	}

	/** ���� ���������� */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/** ����� ������  */
	public String getModuleAddress() {
		return moduleAddress;
	}

	/** ����� ������  */
	public void setModuleAddress(String moduleAddress) {
		this.moduleAddress = moduleAddress;
	}

	/** ������������ ������ */
	public String getModuleId() {
		return moduleId;
	}

	/** ������������ ������ */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/** ����� �������� ������� heartBeat, ����� �������� ����� ������� ������� HeartBeat */
	public int getTimeWait() {
		return timeWait;
	}

	/** ����� �������� ������� heartBeat, ����� �������� ����� ������� ������� HeartBeat */
	public void setTimeWait(int timeWait) {
		this.timeWait = timeWait;
	}

	/** �������� �� ��������� ������, ����� ����� ����� ������ ������ �������� ������� �� ������ */
	public int getSettingsValue() {
		return this.settingValue;
	}
	
	/** �������� �� ��������� ������, ����� ����� ����� ������ ������ �������� ������� �� ������ */
	public void setSettingsValue(int value) {
		this.settingValue=value;
	}
	
}
