package fenomen.server.controller.client.view.settings.module.sensor_window.sensor_panel.alarm_checker_panel.add_checker.panel_create;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.form.TextField;

/** ������, ������� ���������� ������� ��� ���������� ����������  */
public class PanelValueCondition extends HorizontalPanel{
	private RadioButton[] listOfButtons=null;
	private TextField controlValue=new TextField();
	private TextField timeValue=new TextField();
	
	/** ������, ������� ���������� ������� ��� ���������� ����������  
	 * @param radioButtonGroupName
	 * @param values
	 * @param captions
	 */
	public PanelValueCondition(String radioButtonGroupName, String[] values){
		this.setWidth("100%");
		this.setHeight("100%");
		
		VerticalPanel panelCondition=new VerticalPanel();
		panelCondition.add(new Label("if value: "));
		
		VerticalPanel panelChoice=new VerticalPanel();
		listOfButtons=new RadioButton[values.length];
		for(int counter=0;counter<values.length;counter++){
			listOfButtons[counter]=new RadioButton(radioButtonGroupName, values[counter]);
			listOfButtons[counter].ensureDebugId(radioButtonGroupName);
			panelChoice.add(listOfButtons[counter]);
		}
		
		String titleControl="Control Value";
		DecoratorPanel panelControlValue=new DecoratorPanel();
		panelControlValue.setTitle(titleControl);
		VerticalPanel decoratorVertical=new VerticalPanel();
		decoratorVertical.add(new Label(titleControl));
		decoratorVertical.add(controlValue);
		panelControlValue.add(decoratorVertical);
		
		String titleDelay="Delay for next Alarm";
		DecoratorPanel panelTimeDelay=new DecoratorPanel();
		panelTimeDelay.setTitle(titleDelay);
		VerticalPanel contentTimeDelay=new VerticalPanel();
		contentTimeDelay.add(new Label(titleDelay));
		this.timeValue=new TextField();
		this.timeValue.setMaskRe("[0,9]*");
		contentTimeDelay.add(this.timeValue);
		panelTimeDelay.add(contentTimeDelay);
		
		this.add(panelCondition);
		this.add(panelChoice);
		VerticalPanel panelValues=new VerticalPanel();
		panelValues.add(panelControlValue);
		panelValues.add(panelTimeDelay);
		this.add(panelValues);
	}
	
	/** ���������� ������ ��� Enabled, �������� ����������� �������� (�������������� ����� ������� {@link #setDisabledAll()})*/
	public void setEnabled(int[] indexes){
		if(indexes==null)return;
		for(int counter=0;counter<this.listOfButtons.length;counter++){
			if(this.positionInArray(indexes, counter)>=0){
				this.listOfButtons[counter].setEnabled(true);
			}
		}
	}
	
	/** �������� ������� � ������� */
	private int positionInArray(int[] array, int value){
		for(int counter=0;counter<array.length;counter++){
			if(array[counter]==value){
				return value;
			}
		}
		return -1;
	}
	
	/** ������� ��� RadionButton Enabled */
	public void setEnabledAll(){
		for(RadioButton button:listOfButtons){
			button.setEnabled(true);
		}
	}

	/** ������� ��� RadioButton Disabled*/
	public void setDisabledAll(){
		for(RadioButton button:listOfButtons){
			button.setEnabled(false);
		}
	}

	/**  �������� ���������� �������� 
	 * @return null - ���� ����� �� ���������� ����� Enabled RadionButton
	 * */
	public String getSelectedCondition(){
		RadioButton buttonSelected=null;
		for(int counter=0;counter<this.listOfButtons.length;counter++){
			if((this.listOfButtons[counter].isEnabled()==true)&&(this.listOfButtons[counter].getValue()==true)){
				buttonSelected=this.listOfButtons[counter];
				break;
			}
		}
		if(buttonSelected!=null){
			return buttonSelected.getText();
		}else{
			return null;
		}
	}
	
	public void setSelectedCondition(String valueCondition) {
		for(int counter=0;counter<this.listOfButtons.length;counter++){
			if(this.listOfButtons[counter].getText().equals(valueCondition)){
				listOfButtons[counter].setValue(false);
				if(listOfButtons[counter].isEnabled()==true){
					listOfButtons[counter].setValue(true);
				}
			}
		}
	}
	
	
	/** �������� �������� �� ���� ����� ������������ �������� */
	public String getControlValue(){
		if(this.controlValue.validate()){
			return this.controlValue.getText();
		}else{
			return null;
		}
	}

	public void setControlValue(String controlValue2) {
		this.controlValue.setValue(controlValue2);
	}
	

	/** ��������� �������� ��� ������� ��������� ���������� �������� */
	public String getTimeDelay(){
		if(this.timeValue.validate()){
			return this.timeValue.getText();
		}else{
			return null;
		}
	}

	public void setTimeDelay(int timeDelay) {
		this.timeValue.setValue(Integer.toString(timeDelay));
	}
	
	public void setFocusToControlValue(){
		this.controlValue.focus();
	}

}
