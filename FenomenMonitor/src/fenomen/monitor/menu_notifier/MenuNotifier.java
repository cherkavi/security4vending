package fenomen.monitor.menu_notifier;

import java.awt.BorderLayout;

import java.awt.Cursor;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import fenomen.monitor.Monitor;
import fenomen.monitor.menu_notifier.jabber.message_table.TableOfMessage;
import fenomen.monitor.menu_notifier.jabber.wrap.IMessageListener;
import fenomen.monitor.menu_notifier.jabber.wrap.IPresenceListener;
import fenomen.monitor.menu_notifier.jabber.wrap.JabberWrap;
import fenomen.monitor.web_service.client_implementation.LoginImplementation;
import fenomen.monitor.web_service.common.JabberSettings;

import swing_framework.AbstractInternalFrame;
import swing_framework.messages.IMessageSender;

public class MenuNotifier extends AbstractInternalFrame implements IMessageListener, IPresenceListener{
	private static final long serialVersionUID=1L;
	/** ��������� ��� Jabber-������� */
	private JabberSettings jabberSettings=null;
	/** jabber-������ - ��������� ��� ������ � ��������  */
	private JabberWrap jabber=null;
	/** �������, ������� �������� ��� �������, ������� ������ �� ������� */
	private TableOfMessage table=null;
	
	public MenuNotifier( IMessageSender messageSender) {
		super("������� ����������", messageSender, 750, 400, false, true);
		initComponents();
		this.addInternalFrameListener(new InternalFrameListener(){
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameClosing(InternalFrameEvent arg0) {
				onCloseWindow();
			}
			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
		});
		// �������� ��������� ��� Jabber ���� �� ������� ��������
		if(getJabberSettings()==false){
			JOptionPane.showMessageDialog(this, "������ ��������� �� ������� �������� ", "������ ����������", JOptionPane.ERROR_MESSAGE);
			this.closeCurrentWindow();
		}else{
			// ��������� �������� - ����������� � Jabber-������� 
			jabber=new JabberWrap(jabberSettings.getJabberUrl(), jabberSettings.getJabberPort(), jabberSettings.getJabberProxy());
			try{
				jabber.connect(this.jabberSettings.getJabberLogin(), this.jabberSettings.getJabberPassword(), this,this);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "������ �����������",JOptionPane.ERROR_MESSAGE);
				this.jabberDisconnect();
				this.closeCurrentWindow();
			}
		}
	}
	
	/** ������� �� ������ ��������� � ���������� ���������� Event  
	 * @param data - ������ �� ������� ������� ������� 
	 * @param value - ��������, ������� ���������� ��� ������� 
	 * @return ������, ���� �� ������� ��������� ��������� 
	 */
	public String sendToServer(String text){
		String returnValue=null;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try{
			this.jabber.sendMessage(this.jabberSettings.getServerAddress(), text);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}catch(Exception ex){
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			returnValue=ex.getMessage();
		}
		return returnValue;
	}

	/** �������� Jabber ��������� �� �������  */
	private boolean getJabberSettings(){
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try{
			LoginImplementation loginManager=new LoginImplementation(Monitor.url);
			jabberSettings=loginManager.getJabberSettings(Monitor.monitorIdentifier);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return true;
		}catch(Exception ex){
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return false;
		}
	}
	
	/** �������������� ������������� ����������� */
	private void initComponents(){
		this.table=new TableOfMessage(this, this);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(this.table), BorderLayout.CENTER);
	}

	@Override
	public void messageNotify(String from, String text) {
		// �������� �������� ��������� �� ������������ 
		if(from.equals(this.jabberSettings.getServerAddress())){
			// ������������ ���������
			String messageForSend=this.table.inputMessageFromServer(text);
			if(messageForSend!=null){
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				this.jabber.sendMessage(this.jabberSettings.getServerAddress(), messageForSend);
				this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}else{
				// ��� ������ ��� ��������
			}
		}else{
			// �������� ��������� �� �� ������� - �������� ���� 
		}
	}

	@Override
	public void userEnter(String user) {
		// ���� ������������ � ����
	}

	@Override
	public void userError(String user) {
		// ��������� ���������� ���������
	}

	@Override
	public void userExit(String user) {
		// SwingUtilities.invokeLater(new Runnable(){})
		// ����� ���� ������� ������, ���� ���������� �������� ������ �� ������� Jabber
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// ����� ������������ �� ����
		JOptionPane.showMessageDialog(this, "������ ����� �� ����, ���� ����� �������","��������������", JOptionPane.WARNING_MESSAGE);
		this.jabberDisconnect();
		this.closeCurrentWindow();
	}

	/** ��������� �������� �������� ���� */
	private void onCloseWindow(){
		this.jabberDisconnect();
	}
	
	/** ������������� �� ������� Jabber  */
	private void jabberDisconnect(){
		try{
			this.jabber.disconnect();
		}catch(Exception ex){
		}
	}
}
