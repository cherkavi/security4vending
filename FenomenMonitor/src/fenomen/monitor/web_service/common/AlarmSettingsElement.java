package fenomen.monitor.web_service.common;

/** ������, ������� ������������ ���� ������� �� ������� monitor_settings_alarm */
public class AlarmSettingsElement {
	/** ���������� ������������� � ���� ������ */
	private int id;
	/** ��� ������ ��� �������������, ��� ������� �����  */
	private String moduleId;
	/** ����� ������ */
	private String moduleAddress;
	/** ����� �� ��������� �� ������� ������  */
	private boolean enabled;
	
	/** ������, ������� ������������ ���� ������� �� ������� Restart */
	public AlarmSettingsElement(){
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

	public String getModuleAddress() {
		return moduleAddress;
	}

	public void setModuleAddress(String moduleAddress) {
		this.moduleAddress = moduleAddress;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
