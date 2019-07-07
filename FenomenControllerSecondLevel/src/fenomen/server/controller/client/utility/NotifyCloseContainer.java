package fenomen.server.controller.client.utility;

import java.util.ArrayList;

/** ������ ������� ������ ��� ����������, �������� � ���������� ��������, ����������� ����� {@link INotifyClose} */
public class NotifyCloseContainer {
	private ArrayList<INotifyClose> list=new ArrayList<INotifyClose>();
	
	/** �������� ������, ������� ����� ����������� � �������� */
	public void addNotifyClose(INotifyClose notifyClose){
		this.list.add(notifyClose);
	}
	
	/** ������� ������ �� ������ ���������� */
	public void removeNotifyClose(INotifyClose notifyClose){
		this.list.remove(notifyClose);
	}
	
	/** 
	 * <lo>���������� ��� ������� �� ������ ���������� � �������� �������� ��� ������� ������� </lo> 
	 * <lo>������� ��� ������� �� ���������� </lo>*/
	@SuppressWarnings("unchecked")
	public void notifyAllObjectAndRemoveThey(){
		/** ����� ���� �������, ������ ��� ���� �� �������������� ������� ����� ������� �������� ���� �� �� ������������� ����������  */
		ArrayList<INotifyClose> cloneList=(ArrayList<INotifyClose>)this.list.clone();
		for(INotifyClose object:cloneList){
			try{
				object.notifyClose();
			}catch(Exception ex){
				// System.err.println("Notify Close Exception:"+ex.getMessage());
			};
		}
		this.list.clear();
	}
}
