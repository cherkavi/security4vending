package fenomen.server.controller.client.view.settings.module.thread_settings_window;

import com.google.gwt.user.client.rpc.IsSerializable;

/** �������� ��� Settings ������ (��� ������/��������� ��������)
 * <table border=1>
 * 	<tr><th>Parameter Name </th><th>Description</th></tr>
 * 	<tr><td>idSection</td><td>���������� ������������� ������ (module_settings_section.id)</td></tr>
 * 	<tr><td>idParameter</td><td>���������� ������������� ��������� � ���� (module_settings_parameter.id)</td></tr>
 * 	<tr><td>parameterName</td><td>������������ ��������� (module_settings_parameter.parameter_name)</td></tr>
 * 	<tr><td>settingsValue</td><td>�������� ��������� � ��������� ���� (module_settings.settings_value)</td></tr>
 * 	<tr><td>moduleRecieve</td><td>0 - �������� � ����, 1 - ������� �������</td></tr>
 * </table>
 * 
 * */
public class ModuleSettingsWrap implements IsSerializable{
	private int idSection;
	private int idParameter;
	private String parameterName;
	private String settingsValue;
	private Integer moduleRecieve;
	
	/** �������� ��� Settings ������ (��� ������/��������� ��������)*/
	public ModuleSettingsWrap(){
	}

	/** �������� ���������� ������������� ������ ( �� ������� module_settings_section.id )*/
	public int getIdSection() {
		return idSection;
	}

	/** @param idSection ���������� ���������� ������������� ������ ( �� ������� module_settings_section.id )*/
	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}

	/** �������� ���������� ������������� ��������� ( �� ������� module_settings_parameter.id ) */
	public int getIdParameter() {
		return idParameter;
	}

	/** @param idParameter ���������� ���������� ������������� ��������� ( �� ������� module_settings_parameter.id ) */
	public void setIdParameter(int idParameter) {
		this.idParameter = idParameter;
	}

	/** �������� ���������� ( � �������� ������ ) ��� ��������� module_settings_parameter.name */
	public String getParameterName() {
		return parameterName;
	}

	/** @param parameterName ����������  ���������� ( � �������� ������ ) ��� ��������� module_settings_parameter.name */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/** �������� ��������� �������� �������������� ��������� */
	public String getSettingsValue() {
		return settingsValue;
	}

	/** ���������� ��������� �������� ��������� */
	public void setSettingsValue(String settingsValue) {
		this.settingsValue = settingsValue;
	}

	/** ���� �����������/�������������� ���������   
	 * <li> <b>0</b> - �������� � ���� </li> 
	 * <li> <b>1</b> - ������� ������� </li>
	 * */
	public Integer getModuleRecieve() {
		return moduleRecieve;
	}

	/** ���� �����������/�������������� ���������
	 * <li> <b>0</b> - �������� � ���� </li> 
	 * <li> <b>1</b> - ������� ������� </li>
	 * */
	public void setModuleRecieve(Integer moduleRecieve) {
		this.moduleRecieve = moduleRecieve;
	}
	
}
