package fenomen.server.controller.server.generator_alarm_checker.calc;

import java.math.BigDecimal;

/** ����������� ��� Double �������� */
public abstract class ADoubleChecker<T> extends Checker<T>{
	private final static long serialVersionUID=1L;
	/** ����������� �������� */
	private double controlValue;
	/** ����, �� �������� ������� ��������� */
	private int digitForRound;
	/** ����� ��������, ������� �������� �������� */
	private int registerAddress;
	
	/** ����������� ��� Double �������� 
	 * @param delayMs - ����� �������� ��� ��������� �������� 
	 * @param message - ���������, ������� ����� ���� ������
	 * @param controlValue - ����������� �������� ��� ���������
	 * @param digit - ������, �� �������� ������� ��������� �������� (-1 - ���������� �� ���������)
	 * <li><b>-1</b>���������� �� ��������� </li>
	 * <li><b>>=0</b>��������� �� ���������� ����� </li>
	 */
	public ADoubleChecker(int absoluteRegisterNumber, int delayMs, String message,double controlValue, int digit) {
		super(delayMs, message);
		this.registerAddress=absoluteRegisterNumber;
		this.controlValue=controlValue;
		this.digitForRound=digit;
	}

	/** �������� ����������� ��������, � ������� ����� ���������� �������� */
	public double getControlValue(){
		return this.controlValue;
	}
	
	/** �������� ���-�� ������, �� ������� ����� ��������� �������� */
	protected int getDigit(){
		return this.digitForRound;
	}
	
	/** ��������� �������� �� ���������� �����  */
	protected double round(double d, int decimalPlace){
		if(decimalPlace<0){
			return d;
		}else{
		    // see the Javadoc about why we use a String in the constructor
		    // http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
		    BigDecimal bd = new BigDecimal(Double.toString(d));
		    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
		    return bd.doubleValue();
		}
	  }	/**
	 * @param value - ��������, ������� ������ ������
	 * @return 
	 * */
	protected abstract boolean isAlarm(double value); 
	
	/** ������������ ��������� �� ���������� �������� */
	protected abstract T getMessage(String value);
	
	@Override
	protected T calculate(int absoluteNumber, int value) {
		if(this.registerAddress==absoluteNumber){
			try{
				double doubleValue=value;
				if(isAlarm(doubleValue)){
					this.setDateEvent();
					return this.getMessage(Integer.toString(value)); 
				}else{
					return null;
				}
			}catch(Exception ex){
				System.err.println("ADoubleChecker Exception: "+ex.getMessage());
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public int getRegisterAddress() {
		return registerAddress;
	}

}
