package fenomen.server.controller.server.generator_alarm_checker.calc;

import java.io.Serializable;
import java.util.Date;
import fenomen.server.controller.server.generator_alarm_checker.message.AlarmMessage;

/** ����������� ����� ��� ���������� (��� �������� ��������� ��������)*/
public abstract class Checker<T> implements Serializable{
	private final static long serialVersionUID=1L;
	
	/** �����, ����� ����������� �������� ����� ��������� � ������ ����������� �� ������� */
	private Date nextEnabledTime=null;
	/** ����� �������� � ������������ */
	private int delayMiliseconds=0;
	/** ���������, ������� ����� ������ */
	private String message;
	/** ���������� ������������� �����  */
	private int idFile;
	/** �������� */
	private String description;
	
	/** ����������� ����� ��� ���������� 
	 * @param delayMiliseconds - ����� ��������, ����� ����������� ������� �� ������� �� ������� "������" Checker � ����� ������������ ������� ������ ����� ��������� ������� ������� 
	 * @param message - ������, ������� ����� ������ � {@link AlarmMessage#getMessage()}
	 */
	public Checker(int delayMiliseconds, String message){
		this.delayMiliseconds=delayMiliseconds;
		this.message=message;
	}

	/**  �������� �� ��������� ����������� �������� ��������� (AlarmMessage ��� InormationMessage)
	 * @param number - ���������� ����� �������� �� ����������
	 * @param value - �������� ��������   
	 * @param currentDate ���� ���������� ������� �������� � �������
	 * @return <li> <b>null</b> ��� �� ������� �������� ������������ ������� </li> <li> <b>value</b> ���� �� ������� �������� �������  </li> 
	 */
	public T checkForMessage(int number, int value, Date currentDate){
		// �������� �� ��������� ������� �������� ��� ������� 
		if(nextEnabledTime!=null){
			if(currentDate.after(this.nextEnabledTime)){
				// �������� ����� ��������
				this.clearDateEvent();
				// �������� ��������� �������� 
				return calculate(number, value);
			}else{
				// ����� �������� ��� �� �����  
				return null;
			}
		}else{
			return calculate(number, value);
		}
	}
	
	/** �� ��������� ����������� ��������� ������� AlarmMessage, ���� �� ������� null 
	 * @param number - ���������� ����� ��������
	 * @param value - ���������� �������� ��������
	 * */
	protected abstract T calculate(int number, int value);
	
	
	/** �������� ������, ������� ��������� �� ����������� ������� */
	protected void clearDateEvent(){
		this.nextEnabledTime=null;
	}

	/** �������� ������� */
	public String getTextMessage(){
		return this.message;
	}
	
	/** ����� � ��. �� ���������� �������� ��������� ��������� ����� ���� ��������� */
	public int getTimeDelay(){
		return this.delayMiliseconds;
	}
	
	
	/** ���������� ��������� ������� �� ������� ����� ����������� �������, ���������� � {@link #delayMiliseconds}*/
	protected void setDateEvent(){
		this.nextEnabledTime=new Date((new Date()).getTime()+delayMiliseconds);
	}

	/** �������� ���������� ������������� ������ module_alarm_checker.id ��� �� module_information_checker.id, � ����������� �� �������������������� ��������� */
	public int getIdFile() {
		return idFile;
	}

	/** ���������� ���������� ������������� ������ module_alarm_checker.id ��� �� module_information_checker.id, � ����������� �� �������������������� ��������� */
	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	/** �������� �������� ������� */
	public String getDescription() {
		return description;
	}

	/** ���������� �������� */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return this.idFile;
	} 

	/** �������� ����������� ����� ��������, ���� ������� ����� ����� 
	 * (������� ���������� ����� ��������, ���� Checker ����� ���� "��������" � �������� ������ )*/
	public abstract int getRegisterAddress();
}
