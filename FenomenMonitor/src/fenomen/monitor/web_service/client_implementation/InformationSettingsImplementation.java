package fenomen.monitor.web_service.client_implementation;

import fenomen.monitor.web_service.common.InformationSettingsElement;

import fenomen.monitor.web_service.common.MonitorIdentifier;
import fenomen.monitor.web_service.interf.IInformationSettings;

/** ��������� ����� ������� WebService ��� InformationSettings */
public class InformationSettingsImplementation extends XFireImplementation<IInformationSettings> implements IInformationSettings{

	/** ��������� ����� ������� WebService ��� InformationSettings
	 * @param url - ������ ���� � ������� ( ��� ���������� FenomenServer )
	 *  */
	public InformationSettingsImplementation(String url) {
		super(url, "services/InformationSettings");
	}

	@Override
	protected Class<IInformationSettings> getGenericClass() {
		return IInformationSettings.class;
	}

	@Override
	public InformationSettingsElement[] getList(MonitorIdentifier monitorIdentifier) throws Exception{
        try{
        	// ������ �������
        	return this.getProxy().getList(monitorIdentifier); 
        }catch(Exception ex){
        	// ������ ������� 
        	this.reconnectToService();
        	return this.getProxy().getList(monitorIdentifier);
        }
	}

	@Override
	public boolean updateList(MonitorIdentifier monitorIdentifier, InformationSettingsElement[] list) throws Exception{
        try{
        	// ������ �������
        	return this.getProxy().updateList(monitorIdentifier, list); 
        }catch(Exception ex){
        	// ������ ������� 
        	this.reconnectToService();
        	return this.getProxy().updateList(monitorIdentifier, list);
        }
	}

}
