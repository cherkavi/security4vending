package fenomen.server.controller.server.generator_alarm_checker.calc_implementation;

import java.util.Date;

import fenomen.server.controller.server.generator_alarm_checker.calc.AStringChecker;
import fenomen.server.controller.server.generator_alarm_checker.message.AlarmMessage;

/** �������� �� �� ��������� ����� */
public class NEStringChecker extends AStringChecker<AlarmMessage>{
	private final static long serialVersionUID=1L;
	
	/** �������� �� �� ��������� �����
	 * @param registerNumber - ����� ��������  
	 * @param delayMiliseconds - �������� � ������������ ����� ��������� ������� ����� ���������� �������
	 * @param alarmDescription - ���������, ������� ����� ������ � ������ ��������� �������
	 * @param controlValue - ��������, � ������� ����� ���������� �������� 
	 * @param ignoreCase - ������������ �� c 
	 */
	public NEStringChecker(int registerNumber, int delayMiliseconds, String alarmDescription, String controlValue, boolean ignoreCase) {
		super(registerNumber, delayMiliseconds, alarmDescription, controlValue, ignoreCase);
	}

	@Override
	protected boolean isAlarm(String value) {
		try{
			if(this.isIgnoreCase()){
				return !this.getControlValue().equalsIgnoreCase(value);
			}else{
				return !this.getControlValue().equals(value);
			}
		}catch(Exception ex){
			System.err.println("EQStringChecker Exception:"+ex.getMessage());
			return false;
		}
	}

	@Override
	protected AlarmMessage getMessage(String value) {
		return new AlarmMessage(this.getRegisterAddress(), new Date(),this.getTextMessage(), value);
	}

}
