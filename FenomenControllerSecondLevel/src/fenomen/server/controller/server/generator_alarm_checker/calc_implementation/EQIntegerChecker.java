package fenomen.server.controller.server.generator_alarm_checker.calc_implementation;

import java.util.Date;

import fenomen.server.controller.server.generator_alarm_checker.calc.AIntegerChecker;
import fenomen.server.controller.server.generator_alarm_checker.message.AlarmMessage;

/** �������� �� ��������� ������� */
public class EQIntegerChecker extends AIntegerChecker<AlarmMessage>{
	private final static long serialVersionUID=1L;
	
	/**  �������� �� ��������� �������
	 * @param registerNumber - ���������� ����� ��������  
	 * @param delayMs - �������� ����� ��������� �������������� ������� 
	 * @param message - ���������, ������� ������� �������� 
	 * @param controlValue - ����������� �������� (�������� ��� ��������� )
	 */
	public EQIntegerChecker(int registerNumber, int delayMs, String message, int controlValue) {
		super(registerNumber, delayMs, message,controlValue);
	}

	@Override
	protected boolean isAlarm(int value) {
		if(value==controlValue){
			return true;
		}else return false;
	}

	@Override
	protected AlarmMessage getMessage(String value) {
		return new AlarmMessage(this.getRegisterAddress(), new Date(),this.getTextMessage(), value);
	}

}
