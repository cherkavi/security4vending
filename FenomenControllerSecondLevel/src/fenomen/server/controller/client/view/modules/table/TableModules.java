package fenomen.server.controller.client.view.modules.table;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

import fenomen.server.controller.client.view.modules.IUpdateNotify;
import fenomen.server.controller.client.view.modules.table.row.RowData;
import fenomen.server.controller.client.view.modules.table.row.RowElement;

/** �������, ������� ���������� ������-������ */
public class TableModules extends Composite implements IModulesManagerAsyncAware{
	private ModulesManagerAsync modulesManager=GWT.create(ModulesManager.class);
	/** ������ � �������  */
	private ArrayList<RowData> rows=new ArrayList<RowData>();
	/** �������, ������� �������� ��� ������  */
	private FlexTable table;
	/** ������, ������� ����� ��������� � ������������� ���������� ������ */
	private IUpdateNotify updateNotify;
	
	/** �������, ������� ���������� ������-������ */
	public TableModules(IUpdateNotify updateNotify){
		this.updateNotify=updateNotify;
		table=new FlexTable();
		this.initWidget(table);
	}
	
	@Override
	public ModulesManagerAsync getModulesManager(){
		return this.modulesManager;
	}
	
	public void updateTable(){
		// �������� ������� �� �������
		modulesManager.getAllRowElement(new AsyncCallback<RowData[]>(){
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("updateTable Exception: "+caught.getMessage());
			}
			@Override
			public void onSuccess(RowData[] result) {
				TableModules.this.onSuccess(result);
			}
		});
	}

	/** ������ ������ ������������� ��������� � ���� ������� ���������, ������� ���������� ��� ��������� �������� */
	private void onSuccess(RowData[] result){
		// ������� ��� ������
		table.removeAllRows();
		rows.clear();
		for(int counter=0;counter<result.length;counter++){
			RowData rowData=result[counter];
			//System.out.println("RowData:"+rowData);
			rows.add(rowData);
			table.insertRow(counter);
			table.insertCell(counter, 0);
			table.setWidget(counter, 0, new RowElement(TableModules.this.updateNotify, TableModules.this, rowData));
		}
	}
}
