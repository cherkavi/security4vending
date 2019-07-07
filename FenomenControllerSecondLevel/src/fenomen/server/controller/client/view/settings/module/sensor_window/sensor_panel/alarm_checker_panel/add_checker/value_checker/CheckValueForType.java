package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel.add_checker.value_checker;

/** �������� ��������� �������� �� ���������� ���������� ���� ( �������� �� ������������� �� String � �����-���� �������� )*/
public abstract class CheckValueForType {
	private String description;
	
	public CheckValueForType(String description){
		this.description=description;
	}
	
	/** �������� �� ���������� �������� ������������� */
	public abstract boolean isType(String value);
	
	public String getDescription(){
		return description;
	}
}
