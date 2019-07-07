package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.value_panel;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

import fenomen.server.controller.client.utility.INotifyClose;
import fenomen.server.controller.client.view.settings.module.sensor_window.Device;
import fenomen.server.controller.client.view.settings.module.sensor_window.DeviceRegister;
import fenomen.server.controller.client.view.settings.module.sensor_window.SensorManagerAsync;

/** ������, ������������ �������� �������� �� ����������, �� ��������� ������ */
public class RegisterValuePanel extends Panel implements INotifyClose{
	private ValuePanelConstants constant=GWT.create(ValuePanelConstants.class);
	/** ���������� ������������� ������ � �������� ���� ������ */
	private int moduleId;
	/** ���������� ������������� �������-������� �� ������  */
	private int modbusAddress;
	/** ����� �������� �� ������� ������� */
	private int registerAddress;
	/** ���� ��������� ������� */
	private Label dateTime=new Label("");
	/** �������� ������� */
	private TextField value=new TextField();
	/** �������� �� ��������� �������� ��������/�������� */
	private SensorManagerAsync sensorManager;
	/** ������ �� ��������� ���������� �������� �� ������� */
	private Button buttonGet=null;
	/** ������, ������� ���������� ������ �� ������� ������ �������� �� ������.�������*/
	private Timer timer=null;
	/** ��������� �������� ���� ����������� �������� */
	private Date registerDateWrite=null;
	
	/** ������, ������������ �������� ������� ������� 
	 * @param moduleId - ���������� ������������� ������ � �������� ���� ������
	 * @param modbusAddress - ���������� ����� ���������� � ���� Modbus �� ������
	 * @param sensorManager - ����������� ������������ �� ������  
	 * */
	public RegisterValuePanel(int moduleId, 
					  		  int modbusAddress,
					  		  DeviceRegister register,
					  		  SensorManagerAsync sensorManager){
		this.sensorManager=sensorManager;
		this.moduleId=moduleId;
		this.modbusAddress=modbusAddress;
		this.registerAddress=register.getRegisterAddress();
		this.setLayout(new BorderLayout());
		this.setCollapsible(false);
		this.setTitle(constant.title());

		Label labelSensor=new Label(constant.labelSensorValue());
		Label labelDateTime=new Label(constant.labelDateValue());
		Button buttonSet=new Button(constant.labelButtonSet());
		buttonSet.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				onButtonSet();
			}
		});
		buttonGet=new Button(constant.labelButtonGet());
		buttonGet.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				onButtonGet();
			}
		});

		FlexTable layout=new FlexTable();
		FlexCellFormatter cellFormatter=layout.getFlexCellFormatter();

		layout.setWidget(0, 0, labelSensor);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		layout.setWidget(0, 1,labelDateTime);
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		layout.setWidget(1,0,value);
		cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		layout.setWidget(1,1,dateTime);
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setColSpan(2, 0, 3);
		layout.setWidget(2,0, buttonSet);
		cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		layout.setWidget(0, 2, buttonGet);
		cellFormatter.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
		cellFormatter.setRowSpan(0, 2, 3);

		layout.setWidth("300px");
		layout.setHeight("120px");
		
		HorizontalPanel panelMain=new HorizontalPanel();
		panelMain.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panelMain.add(layout);
		this.add(panelMain, new BorderLayoutData(RegionPosition.CENTER));

		// ���������� ������� �������� � ���� 
		this.registerDateWrite=register.getRegisterValueDateTime();
		try{
			dateTime.setText(RegisterValuePanel.this.registerDateWrite.toString());
		}catch(Exception ex){};
		value.setValue(Integer.toString(register.getRegisterValue()));
	}
	
	/** �������� ��������� �������� �� ������  */
	private AsyncCallback<Device> getSensorDataFromServer=new AsyncCallback<Device>(){
		@Override
		public void onFailure(Throwable caught) {
			// ������ ��� ��������� ������� ��������� �������� �� ������� 
			timer.cancel();
			buttonGet.setEnabled(true);
			MessageBox.alert(RegisterValuePanel.this.constant.serverError());
		}

		@Override
		public void onSuccess(Device result) {
			if(result==null){
				timer.cancel();
				buttonGet.setEnabled(true);
				MessageBox.alert(RegisterValuePanel.this.constant.serverError());
			}else{
				for(int counter=0;counter<result.getListOfRegister().size();counter++){
					if(result.getListOfRegister().get(counter).getRegisterAddress()==RegisterValuePanel.this.registerAddress){
						// ������ ������, �������� ����
						if(result.getListOfRegister().get(counter).getRegisterValueDateTime().after(RegisterValuePanel.this.registerDateWrite)){
							// ������ �� ������ ������� ��������
							timer.cancel();
							buttonGet.setEnabled(true);
							RegisterValuePanel.this.value.setValue(Integer.toString(result.getListOfRegister().get(counter).getRegisterValue()));
							RegisterValuePanel.this.registerDateWrite=result.getListOfRegister().get(counter).getRegisterValueDateTime();
							RegisterValuePanel.this.dateTime.setText(result.getListOfRegister().get(counter).getRegisterValueDateTime().toString());
						}else{
							// ��������� ������ �� �������
						}
						break;
					}
				}
			}
		}
	};
	
	private void onButtonGet(){
		this.buttonGet.setEnabled(false);
		// ������� Task �� ������ � ������������� ��������� ���������� �������� �� ������.�������
		this.sensorManager.setTaskGetValueFromModule(this.moduleId, 
													 this.modbusAddress, 
													 new AsyncCallback<Boolean>(){
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.hide();
				MessageBox.alert(constant.serverError());
				RegisterValuePanel.this.buttonGet.setEnabled(true);
			}

			@Override
			public void onSuccess(Boolean result) {
				MessageBox.hide();
				if(result==true){
					// ������ ������� �����������
					if(timer!=null){
						timer.cancel();
						timer=null;
					}
					// ������� ������, ������� ����� ��������� ���������� ��������� ������ �� ������� ������ �������� �� ������ 
					timer=new Timer(){
						@Override
						public void run() {
							RegisterValuePanel.this.sensorManager.getSensorIntoModule(RegisterValuePanel.this.moduleId, 
																					  RegisterValuePanel.this.modbusAddress, 
																					  getSensorDataFromServer);
						}
					};
					MessageBox.alert(constant.taskWasSended());
					timer.scheduleRepeating(5000);
				}else{
					MessageBox.alert("Server Database Error", constant.serverError());
				}
			}
		});
		MessageBox.show(new MessageBoxConfig(){
			{
				this.setWait(true);
				this.setWidth(200);
				this.setProgressText(RegisterValuePanel.this.constant.serverExchange());
				this.setWaitConfig(new WaitConfig(){
					{
						this.setInterval(200);
					}
				});
			}
		});
	}
	
	/** ������� �������� �� ��������� ������ ( ���������� ������ )
	 * @param register - ���������� ����� ��������, � ������� ����� ���������� �������� 
	 * @param value - �������� ��������, ������� ����� ���������� � ������� 
	 */
	private void sendValueToServer(int register, int value){
		this.sensorManager.setTaskPutValueToModuleToRegister(this.moduleId, 
												   			 this.modbusAddress,
												   			 register,
												   			 value, 
												   			 null, // INFO Controller2 ����������� Device Enabled/Disabled
												   			 new AsyncCallback<Boolean>(){
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.alert(constant.serverError());
			}

			@Override
			public void onSuccess(Boolean result) {
				if(result==true){
					// �������� ����� ��������� ����� ������ ������� �� ��������� �������� �� �������
					onButtonGet();
				}else{
					MessageBox.alert("Server Database Error", constant.serverError());
				}
			}
		});
	}
	
	private void onButtonSet(){
		MessageBox.confirm(constant.confirmSetValueTitle(), 
						   this.value.getText(),
						   new MessageBox.ConfirmCallback() {
								@Override
								public void execute(String btnID) {
									if(btnID.equalsIgnoreCase("yes")){
										sendValueToServer(RegisterValuePanel.this.registerAddress,Integer.parseInt(RegisterValuePanel.this.value.getText()));
										value.setValue("");
									}
								}
							});
	}

	@Override
	public void notifyClose() {
		this.timer.cancel();
	}
}
