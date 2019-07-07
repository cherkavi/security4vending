package fenomen.server.controller.server.generator_alarm_checker.calc;

/** ����������� ��� String �������� */
public abstract class AStringChecker<T> extends Checker<T>{
	private final static long serialVersionUID=1L;
	
	/** �������� � ������� ����� ��������� ��������� */
	private String controlValue;
	/** ����, ������� ������� � ������������� ������������� Case � �������  */
	private boolean ignoreCase;
	/** ����� ��������, ������� �������� �������� */
	private int registerAddress;
	
	/** ����������� ��� String �������� 
	 * @param delayMs - ����� �������� ��� ��������� �������� 
	 * @param message - ���������, ������� ����� ���� ������
	 * @param controlValue - ����������� ��������, � ������� ���������� ��������� ��������� 
	 * @param ignoreCase - ����� �� ������������ Case ("one"=="OnE")
	 */
	public AStringChecker(int absoluteRegisterNumber, int delayMs, String message, String controlValue, boolean ignoreCase) {
		super(delayMs, message);
		this.registerAddress=absoluteRegisterNumber;
		this.controlValue=controlValue;
		this.ignoreCase=ignoreCase;
	}

	/** �������� ����������� �������� */
	public String getControlValue(){
		return this.controlValue;
	}
	
	/** ����� �� ������������ case � ������� */
	protected boolean isIgnoreCase(){
		return this.ignoreCase;
	}
	
	/**
	 * @param value - ��������, ������� ������ ������
	 * @return 
	 * */
	protected abstract boolean isAlarm(String value); 
	
	/** �������� ��������� �� ��������� �������� */
	protected abstract T getMessage(String value);
	
	@Override
	protected T calculate(int registerNumber, int value) {
		if(this.registerAddress==registerNumber){
			try{
				if(isAlarm(Integer.toString(value))){
					this.setDateEvent();
					return this.getMessage(Integer.toString(value)); 
				}else{
					return null;
				}
			}catch(Exception ex){
				System.err.println("AStringChecker Exception: "+ex.getMessage());
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
