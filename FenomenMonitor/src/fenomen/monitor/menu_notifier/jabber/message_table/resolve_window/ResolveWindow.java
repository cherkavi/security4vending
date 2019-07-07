package fenomen.monitor.menu_notifier.jabber.message_table.resolve_window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fenomen.monitor.menu_notifier.jabber.message_table.MessageElement;
import fenomen.monitor.menu_notifier.jabber.message_table.TableOfMessage;
import swing_framework.AbstractInternalFrame;
import swing_framework.messages.IMessageSender;

/** ����, ��������������� ��������� �� �������� �� ������ ������ ������� */
public class ResolveWindow extends AbstractInternalFrame {
	private final static long serialVersionUID=1L;
	/** ������� � ���������, ���� �� ������� ���������� � ������ ������� */
	private TableOfMessage table;
	/** ������ � ������� */
	private MessageElement data;
	
	/** ����, ��������������� ��������� �� �������� �� ������ ������ �������
	 * @param table - �������, � ������� ����� ������� ��������� � ������ ������ �������� �� ������ ������� ������  
	 * @param messageSender - ������������ ����, �� ��������� �������� ������� "���������" ������ ����
	 * @param messageElement - ������� � ������� 
	 * @param caption - ��������� ( ���������� ������������� ) �� ������� ����  
	 */
	public ResolveWindow(TableOfMessage table, IMessageSender messageSender, MessageElement messageElement, String caption) {
		super(caption, messageSender, 300, 200, false, true);
		this.table=table;
		this.data=messageElement;
		initComponents();
	}
	
	/** �������� ������ ������ */
	private String[] resolve=new String[]{"�� ���������","������ �� ����� ������������","������� �� �������"};
	private PanelChoice panelChoice;
	
	/** ������������� ����������� */
	private void initComponents(){
		panelChoice=new PanelChoice(resolve);
		
		JPanel panelManager=new JPanel();
		panelManager.setLayout(new GridLayout(1,2,10,5));
		JButton buttonSend=new JButton("Save");
		buttonSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				onButtonSend();
			}
		});
		panelManager.add(buttonSend);
		JButton buttonCancel=new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				onButtonCancel();
			}
		});
		panelManager.add(buttonCancel);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panelChoice, BorderLayout.CENTER);
		this.getContentPane().add(panelManager,BorderLayout.SOUTH);
	}
	
	/** ������� �� ������ "�������" */
	private void onButtonSend(){
		Integer index=this.panelChoice.getSelectedIndex();
		if(index==null){
			JOptionPane.showMessageDialog(this, "�������� ���� �����");
		}else{
			if(this.panelChoice.isUserValue()){
				String value=this.panelChoice.getSelectedValue();
				if((value==null)||(value.trim().equals(""))){
					JOptionPane.showMessageDialog(this, "������� ����� ����������������� ������");
				}else{
					this.table.resolveEvent(this.data, value);
					this.closeCurrentWindow();
				}
			}else{
				String value=this.panelChoice.getSelectedValue();
				this.table.resolveEvent(this.data, value);
				this.closeCurrentWindow();
			}
		}
	}
	
	/** ������� �� ������ "��������" */
	private void onButtonCancel(){
		this.closeCurrentWindow();
	}
}

/** ������, ������� ��������� �������� �� ������ ������� ������� */
class PanelChoice extends JPanel{
	private final static long serialVersionUID=1L;
	private String[] variants=null;
	private JRadioButton[] buttons; 
	private JTextField userValue=new JTextField();
	/** ������, ������� ��������� �������� �� ������ ������� ������� */
	public PanelChoice(String[] variants){
		this.variants=variants;
		this.buttons=new JRadioButton[variants.length+1];
		ButtonGroup buttonGroup=new ButtonGroup();
		this.setLayout(new GridLayout(this.buttons.length,1));
		// �������� ��� ������ � ������ 
		for(int counter=0;counter<variants.length;counter++){
			this.buttons[counter]=new JRadioButton(variants[counter]);
			this.buttons[counter].setHorizontalAlignment(SwingConstants.LEFT);
			buttonGroup.add(this.buttons[counter]);
			this.add(this.buttons[counter]);
		}
		/** ������ ������  */
		this.buttons[variants.length]=new JRadioButton("Other:");
		buttonGroup.add(this.buttons[variants.length]);
		JPanel panelEdit=new JPanel(new BorderLayout());
		panelEdit.add(this.buttons[variants.length], BorderLayout.WEST);
		panelEdit.add(userValue);
		this.add(panelEdit);
	}
	
	/** �������� ���������� � ������ ������ ������ �� ����������� � ������������ ������� 
	 * @return null - ��� ����������� ��������
	 * */
	public Integer getSelectedIndex(){
		Integer returnValue=null;
		for(int counter=0;counter<this.buttons.length;counter++){
			if(this.buttons[counter].isSelected()){
				returnValue=counter;
				break;
			}
		}
		return returnValue;
	}
	
	/**
	 * <ul>
	 * 	<li><b>true</b> ������������ ���� ���� ������� </li>
	 * 	<li><b>false</b> ������������ ������ ������� �� ������ </li>
	 * </ul> 
	 * 
	 * */
	public boolean isUserValue(){
		Integer index=this.getSelectedIndex();
		if(index!=null){
			return (index==this.variants.length);
		}else{
			return false;
		}
	}
	
	/** �������� ���������� � ������ ������ �������� �� ����������� �������, ���� �� ������� ������������
	 * @return null - ��� ����������� ��������, ���� ���������� ����� �� ���� �����, ���� �� ���������� Caption ������ �� JRadionButton
	 * */
	public String getSelectedValue(){
		if(isUserValue()){
			return this.userValue.getText();
		}else{
			try{
				Integer index=this.getSelectedIndex();
				return this.buttons[index].getText();
			}catch(Exception ex){
				return null;
			}
		}
	}
}
