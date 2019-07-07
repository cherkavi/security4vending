package fenomen.monitor.web_service.client_implementation;

import fenomen.monitor.web_service.common.MonitorIdentifier;
import fenomen.monitor.web_service.common.RestartSettingsElement;
import fenomen.monitor.web_service.interf.IRestartSettings;

/** ��������� ����� ������� WebService ��� RestartSettings */
public class RestartSettingsImplementation extends XFireImplementation<IRestartSettings> implements IRestartSettings{

	/** ��������� ����� ������� WebService ��� RestartSettings
	 * @param url - ������ ���� � ������� ( ��� ���������� FenomenServer )
	 *  */
	public RestartSettingsImplementation(String url) {
		super(url, "services/RestartSettings");
	}

	@Override
	protected Class<IRestartSettings> getGenericClass() {
		return IRestartSettings.class;
	}

	@Override
	public RestartSettingsElement[] getList(MonitorIdentifier monitorIdentifier) throws Exception{
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
	public boolean updateList(MonitorIdentifier monitorIdentifier, RestartSettingsElement[] list) throws Exception{
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
