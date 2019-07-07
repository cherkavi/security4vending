package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/** ������� �� �������-������, ������� ���������� {@link RegisterAlarmCheckerPanel} ��� �������� ������� ������� �� ��������� ������� ������ 
 * <table border=1>
 * 	<tr>
 * 		<th>���� �������</th> <th>��������</th>
 * 	</tr>
 * 	<tr>
 * 		<td>id</td>	<td> ���������� �������������</td> 
 * 	</tr>
 * 	<tr>
 * 		<td>idModule</td>	<td> ����� ������ </td> 
 * 	</tr>
 * 	<tr>
 * 		<td>modbusAddress</td>	<td> ���������� ����� ���������� (�������� ����������� ������ Checker )</td> 
 * 	</tr>
 * 	<tr>
 * 		<td>regiserAddress</td>	<td> ����� �������� �� ���������� ( ����� ���� �������������� )</td> 
 * 	</tr>
 * 	<tr>
 * 		<td>idOnDevice</td>	<td> ���������� ����� �� ����������</td> 
 * 	</tr>
 * 	<tr>
 * 		<td>description</td>	<td> �������� </td> 
 * 	</tr>
 * 	<tr>
 * 		<td>idState</td>	<td> <li>0 - ����� ������� </li><li> 1 - ������� ������� </li><li> 2 - ������� �������� � ������ </li><li> -1 - ���� �� ������, ��� �� ������� </li> </td> 
 * 	</tr>
 * 	<tr>
 * 		<td>timeWrite</td>	<td> ����� ���������� ������ � ���� ������ </td> 
 * 	</tr>
 * </table>
 * 
 * */
public class AlarmCheckerTableElement implements IsSerializable{
	/** ���������� ������������� module_alarm_checker.id*/
	private int id;
	/** ���������� ������������� ������ module.id */
	private int idModule;
	/** ����� ����������, � �������� ���������� ������ Checker */
	private int modbusAddress;
	/** ����� ��������, � �������� ���������� ������ �����  */
	private Integer registerAddress;
	/** ���������� ����� ������� checker-a �� ��������� ������ */
	private Integer idOnDevice;
	/** ��������, ������� ������� ���������� ��� ������� */
	private String description;
	/** ��������� ������� checker-�
	 * ������������� ��������� <li>0 - ����� ������� </li><li> 1 - ������� ������� </li><li> 2 - ������� �������� � ������ </li><li> -1 - ���� �� ������, ��� �� ������� </li>
	 */
	private int state;
	/** ���� ������ ������� Checker-a */
	private Date date;
	
	/* 
	 * ������� �� �������-������, ������� ���������� {@link AlarmChecker} ��� �������� ������� ������� �� ��������� ������� ������
	 */
	public AlarmCheckerTableElement(){
	}
	
	/** ������� �� �������-������, ������� ���������� {@link RegisterAlarmCheckerPanel} ��� �������� ������� ������� �� ��������� ������� ������ 
	 * @param id - ���������� ������������� ������ � �������  
	 * @param idModule - ���������� ������������� ������
	 * @param modbusAddress ����� ����������, � �������� ���������� ������ Checker
	 * @param registerAddress ����� ��������, � �������� ���������� ������ �����  
	 * @param inOnDevice ���������� ����� ������� checker-a �� ��������� ������ 
	 * @param description - �������� ������� AlarmChecker-a
	 */
	public AlarmCheckerTableElement(int id, 
									int idModule, 
									int modbusAddress,
									Integer registerAddress,
									Integer idOnDevice,
									String description, 
									int state, 
									Date date){
		this.id=id;
		this.idModule=idModule;
		this.modbusAddress=modbusAddress;
		this.registerAddress=registerAddress;
		this.idOnDevice=idOnDevice;
		this.description=description;
		this.state=state;
		this.date=date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** ��������� ������� checker-�
	 * ������������� ��������� <li> 0 - ����� ������� </li> <li> 1 - ������� ������� </li> <li> 2 - ������� �������� � ������ </li> <li> -1 - ���� �� ������, ��� �� ������� </li>
	 */
	public int getState() {
		return state;
	}

	/** ��������� ������� checker-�
	 * ������������� ��������� <li> 0 - ����� ������� </li> <li> 1 - ������� ������� </li> <li> 2 - ������� �������� � ������ </li> <li> -1 - ���� �� ������, ��� �� ������� </li>
	 */
	public void setState(int state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getModbusAddress() {
		return modbusAddress;
	}

	public void setModbusAddress(int modbusAddress) {
		this.modbusAddress = modbusAddress;
	}

	public Integer getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(Integer registerAddress) {
		this.registerAddress = registerAddress;
	}

	public Integer getIdOnDevice() {
		return idOnDevice;
	}

	public void setIdOnDevice(Integer idOnDevice) {
		this.idOnDevice = idOnDevice;
	}

}
