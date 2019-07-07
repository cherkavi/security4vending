package fenomen.monitor.menu_notifier.jabber.message_table;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import fenomen.monitor.menu_notifier.MenuNotifier;
import fenomen.monitor.menu_notifier.jabber.message_table.resolve_window.ResolveWindow;
import fenomen.monitor.web_service.common.XmlMessage;

import swing_framework.messages.IMessageSender;

/** �������, ������� �������� ��� ���������, ������� ���� �������� �� ���������� �������  */
public class TableOfMessage extends JTable {
	private final static long serialVersionUID=1L;
	
	private void debug(Object message){
		System.out.println("DEBUG TableOfMessage:"+message);
	}

	private void error(Object message){
		System.err.println("DEBUG TableOfMessage:"+message);
	}
	
	/** ������������ ����, �� ��������� �������� ������� ������� ��� ���� */
	private IMessageSender parentWindow;
	private TableModel tableModel=new TableModel();
	private MenuNotifier menuNotifier;
	/** �������, ������� �������� ��� ���������, ������� ���� �������� �� ���������� �������  
	 * @param menuNotifier - ������������ ��������� 
	 * @param messageSender - ������������ ����, �� ����� �������� "���������" �������� ���� 
	 */
	public TableOfMessage(MenuNotifier menuNotifier, IMessageSender messageSender){
		this.menuNotifier=menuNotifier;
		this.parentWindow=messageSender;
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// this.setCellSelectionEnabled(true);
		this.tableModel=new TableModel();
		this.setModel(tableModel);
		this.setDefaultRenderer(Object.class, new TableRender(this.tableModel));
		this.getColumn(this.getColumnName(0)).setHeaderRenderer(new ColumnCell("Id"));
		this.getColumn(this.getColumnName(0)).setPreferredWidth(70);
		this.getColumn(this.getColumnName(1)).setHeaderRenderer(new ColumnCell("���"));
		this.getColumn(this.getColumnName(1)).setPreferredWidth(70);
		this.getColumn(this.getColumnName(2)).setHeaderRenderer(new ColumnCell("�����"));
		this.getColumn(this.getColumnName(2)).setPreferredWidth(100);
		this.getColumn(this.getColumnName(3)).setHeaderRenderer(new ColumnCell("������.���"));
		this.getColumn(this.getColumnName(3)).setPreferredWidth(100);
		this.getColumn(this.getColumnName(4)).setHeaderRenderer(new ColumnCell("������.�����"));
		this.getColumn(this.getColumnName(4)).setPreferredWidth(200);
		this.getColumn(this.getColumnName(5)).setHeaderRenderer(new ColumnCell("��������"));
		this.getColumn(this.getColumnName(5)).setPreferredWidth(200);

		// ������ ������ ������� � ���� ����������� �������������� 
		this.removeColumn(this.getColumn(this.getColumnName(0)));
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent event) {
				if((event.getButton()==MouseEvent.BUTTON1)&&(event.getClickCount()>1)){
					onDblClick();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
	}

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	/** ������� �� ������� ������� ������ ���� � ���������� ������� */
	private void onDblClick(){
		MessageElement selectedElement=this.tableModel.getValue(this.convertRowIndexToModel(this.getSelectedRow()));
		if(selectedElement!=null){
			String caption=selectedElement.getData().getType()+"  "+sdf.format(selectedElement.getData().getTimeWrite());
			new ResolveWindow(this, this.parentWindow, selectedElement, caption);
		}
	}

	/** �������� ���� �������� ������� ������ �� Event  
	 * @param value - ��������, �� �������� ������� �������  
	 * @param data �������, ������� ����� �������� ��� �������� 
	 */
	public void resolveEvent(MessageElement data, String value){
		XmlMessage message=new XmlMessage(data.getData().getType()){};
		message.setId(data.getData().getId());
		
		message.setFlagResolve(true);
		message.setResolveId(0);
		message.setResolveName(value);
		try{
			String serverExchange=this.menuNotifier.sendToServer(message.convertToXml());
			if(serverExchange==null){
				debug("������ ��������� �� ������ ������ �������, �������� ������ �� '��������� ��������' ");
				data.setProcess(true);
				this.tableModel.fireTableDataChanged();
				this.repaint();
				//this.tableModel.removeElement(data);
				//this.tableModel.fireTableDataChanged();
			}else{
				JOptionPane.showMessageDialog(this, serverExchange,"������ �������� ������", JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "������ ���������","������ �������� ������", JOptionPane.WARNING_MESSAGE);			
		}
	}
	
	/** �������� ��������� �� �������, ���������� ��� ��������� � ������� �����, ������� ������� ��������  */
	public String inputMessageFromServer(String text){
		XmlMessage message=XmlMessage.getInstanceByMessage(text);
		// INFO �������.input message
		if(message!=null){
			String returnValue=null;
			debug("�������� �������� ��������� �� �������: "+text);
			if((message.isFlagNotify())&&(tableModel.checkForExists(message)==false)){
				debug("��������� �������� ������������ � ����� ������� ");
				// ������� ������ � ��������� ��� ������ � �������
				MessageElement messageElement=new MessageElement(message);
				tableModel.addElement(messageElement);
				tableModel.fireTableDataChanged();
				XmlMessage messageConfirm=new XmlMessage(message.getType()){};
				messageConfirm.setId(message.getId());
				messageConfirm.setFlagConfirm(true);
				try{
					returnValue=messageConfirm.convertToXml();
				}catch(Exception ex){
					return null;
				}
			} else if(message.isFlagConfirm()){
				debug("������� �������� �������������� �������� ���������� � ������� �������� ");
				if(tableModel.removeElement(tableModel.getMessageElementByXmlMessage(message))){
					this.tableModel.fireTableDataChanged();
					debug("������� ������� ������:"+message.getId());
				}else{
					error("������ �������� ������ ");
				}
			} else {
				error("����������� ���� �������� ");
			}
			return returnValue;
		}else{
			debug("�� ������� ������������ ���������:"+text);
			return null;
		}
	}
}

/** ������, ������� ���������� ������������ ����� � ��������  */
class TableRender implements TableCellRenderer{
	private TableModel model;
	private SimpleDateFormat sdf=new SimpleDateFormat("MM.dd HH:mm:ss");

	private Color colorAlarmSelected=new Color(255,100,100);
	private Color colorAlarmFocused=new Color(180,0,0);
	private Color colorAlarmNormal=new Color(255,0,0);
	
	private Color colorInformationSelected=new Color(100,255,255);
	private Color colorInformationFocused=new Color(0,150,150);
	private Color colorInformationNormal=new Color(0,255,255);
	
	private Color colorRestartSelected=new Color(100,255,100);
	private Color colorRestartFocused=new Color(0,120,0);
	private Color colorRestartNormal=new Color(0,255,0);
	
	private Color colorHeartBeatSelected=new Color(255,255,100);
	private Color colorHeartBeatFocused=new Color(180,180,0);
	private Color colorHeartBeatNormal=new Color(255,255,0);
	
	private Color colorUnknownSelected=new Color(230,230,230);
	private Color colorUnknownFocused=new Color(170,170,170);
	private Color colorUnknownNormal=new Color(190,190,190);

	private void debug(Object message){
		System.out.println("TableOfMessage.TableRender: "+message);
	}
	
	/** ������, ������� ���������� ������������ ����� � ��������  */
	public TableRender(TableModel model){
		this.model=model;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, 
												   Object value,
												   boolean isSelected, 
												   boolean hasFocus, 
												   int row, 
												   int column) {
		JLabel label=null;
		if(value instanceof String){
			label=new JLabel((String)value);
		}else if (value instanceof Date){
			label=new JLabel(sdf.format((Date)value));
		}else if (value instanceof Integer){
			label=new JLabel(((Integer)value).toString());
		}else{
			if(value!=null){
				debug("����������� ��� ��� �������������� ");
				label=new JLabel(value.toString());
			}else{
				label=new JLabel("");
			}
		}
		JPanel panel=new JPanel(new GridLayout(1,1));
		panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
		panel.add(label);
		MessageElement currentElement=model.getValue(row);
		if(currentElement.isProcess()){
			// ���������� �������, ������� ��������� � �������� �������� ������ �� �������
			if(hasFocus){
				// ������� ����� �����
				panel.setBackground(Color.darkGray);
			}else if(isSelected){
				// ������� �������
				panel.setBackground(Color.gray);
			}else{
				// ������� ���������
				panel.setBackground(Color.gray);
			}
		}else{
			if(currentElement.getData().getType().equals(XmlMessage.typeAlarm)){
				if(hasFocus){
					// ������� ����� �����
					panel.setBackground(colorAlarmFocused);
				}else if(isSelected){
					// ������� �������
					panel.setBackground(colorAlarmSelected);
				}else{
					// ������� ���������
					panel.setBackground(colorAlarmNormal);
				}
				
			}else if (currentElement.getData().getType().equals(XmlMessage.typeInformation)){
				if(hasFocus){
					// ������� ����� �����
					panel.setBackground(colorInformationFocused);
				}else if(isSelected){
					// ������� �������
					panel.setBackground(colorInformationSelected);
				}else{
					// ������� ���������
					panel.setBackground(colorInformationNormal);
				}
				
			}else if (currentElement.getData().getType().equals(XmlMessage.typeRestart)){
				if(hasFocus){
					// ������� ����� �����
					panel.setBackground(colorRestartFocused);
				}else if(isSelected){
					// ������� �������
					panel.setBackground(colorRestartSelected);
				}else{
					// ������� ���������
					panel.setBackground(colorRestartNormal);
				}
				
			}else if (currentElement.getData().getType().equals(XmlMessage.typeHeartBeat)){
				if(hasFocus){
					// ������� ����� �����
					panel.setBackground(colorHeartBeatFocused);
				}else if(isSelected){
					// ������� �������
					panel.setBackground(colorHeartBeatSelected);
				}else{
					// ������� ���������
					panel.setBackground(colorHeartBeatNormal);
				}
				
			}else{
				// ����������� ��� �������� 
				if(hasFocus){
					// ������� ����� �����
					panel.setBackground(colorUnknownFocused);
				}else if(isSelected){
					// ������� �������
					panel.setBackground(colorUnknownSelected);
				}else{
					// ������� ���������
					panel.setBackground(colorUnknownNormal);
				}
			}
			
		}
		return panel;
	}
	
}

/** ������ ������ ��� �������  */
class TableModel extends AbstractTableModel{
	private final static long serialVersionUID=1L;
	private ArrayList<MessageElement> list=new ArrayList<MessageElement>();
	
	/** �������� ������� � ������� */
	public void addElement(MessageElement messageElement){
		this.list.add(0,messageElement);
	}
	
	public MessageElement getMessageElementByXmlMessage(XmlMessage message){
		MessageElement returnValue=null;
		for(int counter=0;counter<list.size();counter++){
			MessageElement currentElement=list.get(counter);
			if ((currentElement.getData().getId()==message.getId())
			  &&(currentElement.getData().getType().equals(message.getType()))
			  ){
				returnValue=list.get(counter);
				break;
			}
		}
		return returnValue;
	}
	
	/** ���������, ���� �� ��������� � ������ ����� ��� � ������� */
	public boolean checkForExists(XmlMessage message) {
		return this.getMessageElementByXmlMessage(message)!=null;
	}

	/** ������� ������� �� ������� */
	public boolean removeElement(MessageElement messageElement){
		return this.list.remove(messageElement);
	}
	
	/** ������� ������� �� ������� */
	public void removeElement(int index){
		this.list.remove(index);
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		if(list!=null){
			return list.size();
		}else{
			return 0;
		}
	}

	/** �������� �������� �������� �� ��������� ������� */
	public MessageElement getValue(int rowIndex){
		return this.list.get(rowIndex);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: {return this.list.get(rowIndex).getData().getId();}
		case 1: {return this.list.get(rowIndex).getData().getType();}
		case 2: {return this.list.get(rowIndex).getData().getTimeWrite();}
		case 3: {return this.list.get(rowIndex).getData().getModuleName();}
		case 4: {return this.list.get(rowIndex).getData().getModuleAddress();}
		case 5: {return this.list.get(rowIndex).getData().getDescription();}
		default:{return this.list.get(rowIndex).getData().getDescription();}
		}
	}
	
}
/** ���������� TableCellRender � ���� �������-��������� */
class ColumnCell implements TableCellRenderer{
	private JLabel label=null;
	/** ���������� TableCellRender � ���� �������-��������� */
	public ColumnCell(String title){
		label=new JLabel(title, SwingConstants.CENTER);
		label.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return label;
	}
}
