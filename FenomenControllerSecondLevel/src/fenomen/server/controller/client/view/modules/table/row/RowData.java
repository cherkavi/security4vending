package fenomen.server.controller.client.view.modules.table.row;

import com.google.gwt.user.client.rpc.IsSerializable;

/** �������, ������� ������������ ������-������� ��� ������ */
public class RowData implements IsSerializable{
	/** ���������� ������������� */
	private int id;
	/** ������������� */
	private String idModule;
	/** ����� */
	private String address;
	
	/** �������, ������� ������������ ������-������� ��� ������ */
	public RowData(){
	}
	
	/** �������, ������� ������������ ������-������� ��� ������ 
	 * @param id - ���������� �������������
	 * @param idModule - ���������� ������������� ������ 
	 * @param address - ����� 
	 */
	public RowData(int id, String idModule, String address){
		this.id=id;
		this.idModule=idModule;
		this.address=address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	@Override
	public String toString(){
		return "Id:"+id+"  Module: "+idModule+"   Address:"+address;
	}

	public String getIdModule() {
		return idModule;
	}

	public void setIdModule(String idModule) {
		this.idModule = idModule;
	}
	
	
}
