package fenomen.monitor.menu_notifier.jabber.wrap;

import java.util.Collection;

import java.util.HashSet;
import java.util.Iterator;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
/*
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
*/

/** ������-������� ��� ����������� Jabber */
public class JabberWrap implements PacketListener{
	private HashSet<IMessageListener> listOfListener=new HashSet<IMessageListener>();
	private HashSet<IPresenceListener> listOfPresence=new HashSet<IPresenceListener>();
	private ConnectionConfiguration connConfig ;
    private XMPPConnection connection ;
	
	/** ������-������� ��� ����������� Jabber
     * @param url - ������ ���� � Jabber ������� ("talk.google.com")
     * @param port ���� ������� (5222)
     * @param proxy - proxy of Jabber ("gmail.com")
     * @param listeners - ��������� �������� ���������
     */
    public JabberWrap(String url, int port, String proxy){
    	if(proxy==null){
    		connConfig = new ConnectionConfiguration(url, port);
    	}else{
    		connConfig = new ConnectionConfiguration(url, port, proxy);
    	}
    	connection=new XMPPConnection(connConfig);
    	// connection.DEBUG_ENABLED=true;
	}

    private void debug(Object message){
    	System.out.println("DEBUG JabberWrap: "+message);
    }
    
    /** ���������� ���� ���������� � �������� ������  */
    private void notifyAllMessageListeners(Message message){
    	if(message!=null){
    		String from=this.getUserAddress(message.getFrom());
    		String text=message.getBody();
    		debug("�������� �������� ��������� "+from+"     "+text);
        	Iterator<IMessageListener> iterator=this.listOfListener.iterator();
        	while(iterator.hasNext()){
        		iterator.next().messageNotify(from, text);
        	}
    	}
    }
    
    /** �������� ��������� �������� ��������� 
     * @param listener - ��������� �������� ���������
     * */
    public void addMessageListener(IMessageListener listener){
    	if(listener!=null){
    		this.listOfListener.add(listener);
    	}
    }
    
    /** ������� ��������� �������� ��������� 
     * @param listener - ��������� �������� ���������
     */
    public void removeMessageListener(IMessageListener listener){
    	this.listOfListener.remove(listener);
    }
    
    /** ������� ���� ���������� �������� ��������� */
    public void removeAllMessageListener(){
    	this.listOfListener.clear();
    }

    /** �������� ��������� ��������� ��������� 
     * @param presenceListener - ��������� ��������� ���������
     * */
    public void addPresenceListener(IPresenceListener presenceListener){
    	if(presenceListener!=null){
    		this.listOfPresence.add(presenceListener);
    	}
    }
    
    /** ������� ��������� ��������� ��������� 
     * @param presenceListener - ��������� ��������� ���������
     */
    public void removePresenceListener(IPresenceListener presenceListener){
    	this.listOfPresence.remove(presenceListener);
    }
    
    /** ������� ���� ���������� ��������� ��������� */
    public void removeAllPresenceListener(){
    	this.listOfPresence.clear();
    }
    
    /** ��������� ���������� �� ���������� ������ �� ���� ���������
     * @param from - ����� ����������, �������� ���� ���������� ���������  
     * */
    private boolean isRecipientExists(String from){
    	// TODO ��������� ���������� �� ������, ������� ��������� � �������-����� (��������� )
    	// String user=this.getUserAddress(from);
    	return true;
    }

    /** ����������� � Jabber ��������  
     * @param login - ����� ����������
     * @param password - ������ ����������
     * */
    public void connect(String login, String password) throws XMPPException{
    	this.connect(login, password, null, null);
    }
    
    /** ����������� � Jabber ��������  
     * @param login - ����� ����������
     * @param password - ������ ����������
     * @param messageListener - ��������� �������� ���������
     * @param presenceListener - ��������� ��������� ���������
     * @throws XMPPException - ������ ��� ���������� � �������� 
     */
    public void connect(String login, String password, IMessageListener messageListener, IPresenceListener presenceListener) throws XMPPException{
    	// ��������� ����������, ���� ���� - ���������
    	if(connection.isConnected()){
    		connection.disconnect();
    	}
    	// �����������
    	connection.connect();
    	// ������������
    	connection.login(login, password);

    	// ������� ����� ���������� � ��������
    	/*Presence presence = new Presence(Presence.Type.available);
        connection.sendPacket(presence);*/
        
        connection.removePacketListener(this);
        connection.addPacketListener(this, new PacketFilter(){
			@Override
			public boolean accept(Packet packet) {
				return isRecipientExists(packet.getFrom());
			}
    	});
        this.addMessageListener(messageListener);
        this.addPresenceListener(presenceListener);
    }
    
    /** ������������� �� ������� Jabber � ������� ���� ���������� */
    public void disconnect(){
    	Presence presence=new Presence(Presence.Type.unavailable);
    	this.listOfListener.clear();
    	this.listOfPresence.clear();
    	connection.sendPacket(presence);
    	connection.disconnect();
    }
    
    /** ������� ��������� ���������� � ��������� �������  
     * @param recipient - ����� ����������
     * @param text - ����� ��� ����������
     */
    public void sendMessage(String recipient, String text){
    	Message msg=new Message(recipient,Message.Type.chat);
    	msg.setBody(text);
    	this.connection.sendPacket(msg);
    }

	@Override
	public void processPacket(Packet packet) {
		/** ������� �����, ������� �������������� ������������ ��� ���������������-�������������� */
		if(packet instanceof Presence){
			Presence presence=(Presence)packet;
			//System.out.println("������� ����� �����������: ");
			/*
			presence.getMode(); // ��������� �������� ��������� ������� (� ����, ������, �� ����������, �� �������� )
			Presence.Mode.available - 
			*/
			// System.out.println("Type:"+presence.getType().toString());
			if(presence.getType().equals(Presence.Type.available)){
				this.notifyEventUserEnter(presence);
			}
			if(presence.getType().equals(Presence.Type.unavailable)){
				this.notifyEventUserExit(presence);
			}
			if(presence.getType().equals(Presence.Type.error)){
				this.notifyEventUserError(presence);
			}
		}else 
		// this.printPacketInfo(packet);
		if(packet instanceof Message){
			notifyAllMessageListeners((Message)packet);
		}else {
			debug("������� ����������� ��� ������: "+packet.getClass().getName());
		}
	}
	
	/** ���������� ���� ���������� � ����� ������������ �� �������-����� � ����  */
	private void notifyEventUserEnter(Presence presence){
		if(presence!=null){
			String user=this.getUserAddress(presence.getFrom());
			Iterator<IPresenceListener> iterator=this.listOfPresence.iterator();
			while(iterator.hasNext()){
				IPresenceListener listener=iterator.next();
				if(listener!=null){
					listener.userEnter(user);
				}
			}
		}
	}
	/** ���������� ���� ���������� � ������ ������������ �� �������-����� �� ���� */
	private void notifyEventUserExit(Presence presence){
		if(presence!=null){
			String user=this.getUserAddress(presence.getFrom());
			Iterator<IPresenceListener> iterator=this.listOfPresence.iterator();
			while(iterator.hasNext()){
				IPresenceListener listener=iterator.next();
				if(listener!=null){
					listener.userExit(user);
				}
			}
		}
	}
	
	/** ���������� ���� ���������� � ������ ������������ �� �������-����� �� ���� */
	private void notifyEventUserError(Presence presence){
		if(presence!=null){
			String user=this.getUserAddress(presence.getFrom());
			Iterator<IPresenceListener> iterator=this.listOfPresence.iterator();
			while(iterator.hasNext()){
				IPresenceListener listener=iterator.next();
				if(listener!=null){
					listener.userError(user);
				}
			}
		}
	}

	/** �������� �� ������ From ����� ������������, ������� ������� ������ ���������  
	 * @param ������ �� ��������� ������ IQ ������� �������� ����� � ������ �� 
	 * */
	private String getUserAddress(String from ){
		if(from!=null){
			int slashPosition=from.indexOf("/");
			if(slashPosition>0){
				return from.substring(0,slashPosition);
			}else{
				return from;
			}
		}else{
			return "";
		}
	}
	
	@SuppressWarnings("unused")
	private void printPacketInfo(Packet packet){
		System.out.println("--------------------------------------------");
		System.out.println("Packet Class: "+packet.getClass().getName());
		System.out.println("From: "+packet.getFrom());
		System.out.println("To: "+packet.getTo());
		System.out.println("Xmlns: "+packet.getXmlns());
		System.out.println("Error: "+packet.getError());
		//System.out.println("      Extensions: ");
		//printExtensions(packet);
		//System.out.println("      Property: ");
		//printProperties(packet); - ������
		System.out.println("============================================");
	}
	
	@SuppressWarnings("unused")
	private void printExtensions(Packet packet){
		Collection<PacketExtension> collection=packet.getExtensions();
		Iterator<PacketExtension> iterator=collection.iterator();
		while(iterator.hasNext()){
			PacketExtension extension=iterator.next();
			System.out.println("          NameSpace: "+extension.getNamespace()+"    ElementName: "+extension.getElementName()+"    "+packet.getExtension(extension.getNamespace(), extension.getElementName()).toXML());
		}
	}
	
	@SuppressWarnings("unused")
	private void printProperties(Packet packet){
		Collection<String> collection=packet.getPropertyNames();
		Iterator<String> iterator=collection.iterator();
		while(iterator.hasNext()){
			String property=iterator.next();
			System.out.println("          PropertyName:"+property+"    PropertyValue:"+packet.getProperty(property));
		}
	}
}
