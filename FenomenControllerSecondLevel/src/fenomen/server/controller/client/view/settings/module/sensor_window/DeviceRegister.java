package fenomen.server.controller.client.view.settings.module.sensor_window;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/** ������������� ������ �������� �� ���������� {@link Device} */
public class DeviceRegister implements IsSerializable{
	/** ���������� ������������� � ���� */
	private int id;
	/** ���������� ������������� �������/������� � ����  */
	private int idSensor;
	/** ����� ������� �������� �� ������*/
	private int registerAddress;
	/** �������� ������� �������� */
	private int registerValue;
	/** �������� */
	private String description;
	/** ����� ��������� �������� */
	private Date registerValueDateTime;
	
	/** ������������� ������ �������� �� ���������� {@link Device} */
	public DeviceRegister(){
	}

	/** ���������� ������������� � ���� */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/** ���������� ������������� �������/������� � ����  */
	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	/** ����� ������� �������� �� ������*/
	public int getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(int registerAddress) {
		this.registerAddress = registerAddress;
	}

	/** �������� ������� �������� */
	public int getRegisterValue() {
		return registerValue;
	}

	public void setRegisterValue(int registerValue) {
		this.registerValue = registerValue;
	}

	/** �������� */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** ����� ��������� �������� */
	public Date getRegisterValueDateTime() {
		return registerValueDateTime;
	}

	public void setRegisterValueDateTime(Date registerValueDateTime) {
		this.registerValueDateTime = registerValueDateTime;
	}
	
	
}
