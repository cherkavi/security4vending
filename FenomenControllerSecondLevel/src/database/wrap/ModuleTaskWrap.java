package database.wrap;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.persistence.*;

/** 
 * <table border=1>
 * 	<tr>
 * 		<th> Database</th>  <th> POJO </th> <th> Description </th>d
 * 	</tr>
 * 	<tr>
 * 		<td> id </td> <td>id </td> <td> ������� ��� ������ Task ������� ������ </td>
 * 	</tr>
 * 	<tr>
 * 		<td>id_module </td> <td> idModule</td> <td>���������� ������������� ������  </td>
 * 	</tr>
 * 	<tr>
 * 		<td> id_storage </td> <td> idStorage</td> <td>���������� ������������� � ��������� ����� ��� ������ </td>
 * 	</tr>
 * 	<tr>
 * 		<td> id_state </td> <td> idState</td> <td> ��������� ������:
											<li>0 - ����� ������ </li>
											<li>1 - ������ ���� ������ ������ </li>	
											<li>2 - ������ ���� ���������� �������</li> </td>	
 * 	</tr>
 * 	<tr>
 * 		<td> time_write </td> <td> timeWrite</td> <td> ����� ������/��������</td>
 * 	</tr>
 * 	<tr>
 * 		<td> id_result </td> <td> idResult</td> <td> ��������� ���������� ������:
											<li>0 - ������ � �������������� ���������</li>
											<li>1 - ������ ���������</li>
											<li>2 - ������ �� ��������� - ������ �� �������</li>
											<li>3 - ������ �� ��������� - ������ ����������</li></td>
 * 	</tr>
 * </table>
 * 
 */
@Entity
@PersistenceCapable(detachable="false")
@Table(name="module_task")
public class ModuleTaskWrap {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="id_module")
	private int idModule;
	@Column(name="id_storage",length=255)
	private String idStorage;
	@Column(name="id_state")
	private int idState;
	@Column(name="id_result")
	private int idResult;
	@Column(name="time_write")
	private Date timeWrite;
	
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
	public String getIdStorage() {
		return idStorage;
	}
	public void setIdStorage(String idStorage) {
		this.idStorage = idStorage;
	}
	public int getIdState() {
		return idState;
	}
	public void setIdState(int idState) {
		this.idState = idState;
	}
	public int getIdResult() {
		return idResult;
	}
	public void setIdResult(int idResult) {
		this.idResult = idResult;
	}
	public Date getTimeWrite() {
		return timeWrite;
	}
	public void setTimeWrite(Date timeWrite) {
		this.timeWrite = timeWrite;
	}
}
