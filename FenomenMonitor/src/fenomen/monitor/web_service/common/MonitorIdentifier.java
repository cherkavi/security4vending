package fenomen.monitor.web_service.common;

/** ���������� ������������� ��������, �� �������� ��� ����������� ����  */
public class MonitorIdentifier {
	private int id;
	public MonitorIdentifier(){
	}
	
	public MonitorIdentifier(int id){
		this.id=id;
	}

	/** �������� ��� �� ���� ������ */
	public int getId() {
		return id;
	}

	/** ���������� ��� (�� ���� ������ )*/
	public void setId(int id) {
		this.id = id;
	}
	
}
