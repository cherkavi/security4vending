package fenomen.server.controller.client.view.settings.module.sensor_window;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/** ������, ������� �������� �������� ��� ������� - ������� ��� �������/������� � ������� */
public class Device implements IsSerializable{
	private int idSensor;
	private int idModule;
	private int modbusAddress;
	private int idSensorType;
	private String sensorTypeName;
	private String sensorTypeDescription;
	private boolean isEnabled;
	private Date timeWrite;
	private ArrayList<DeviceRegister> listOfRegister=new ArrayList<DeviceRegister>();
	
	/** ������, ������� �������� �������� ��� ������� */
	public Device(){
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public int getIdSensorType() {
		return idSensorType;
	}

	public void setIdSensorType(int idSensorType) {
		this.idSensorType = idSensorType;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public String getSensorTypeName() {
		return sensorTypeName;
	}

	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
	}

	public void setTimeWrite(Date timeWrite) {
		this.timeWrite=timeWrite;
	}

	public Date getTimeWrite(){
		return this.timeWrite;
	}
	
	/** �������� ������ ��������� ({@link DeviceRegister}) ������� ����������  */
	public ArrayList<DeviceRegister> getListOfRegister(){
		return this.listOfRegister;
	}

	public String getSensorTypeDescription() {
		return sensorTypeDescription;
	}

	public void setSensorTypeDescription(String sensorTypeDescription) {
		this.sensorTypeDescription = sensorTypeDescription;
	}

	public int getModbusAddress() {
		return modbusAddress;
	}

	public void setModbusAddress(int modbusAddress) {
		this.modbusAddress = modbusAddress;
	}
	
}
