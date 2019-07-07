package swing_framework;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import swing_framework.messages.IMessageListener;
import swing_framework.messages.IMessageSender;
import swing_framework.messages.WindowMessage;
import swing_framework.messages.WindowMessageType;
import swing_framework.window.RegisteredWindow;
import swing_framework.window.WindowIdentifier;

/** ���� �� ������� ����� */
public abstract class AbstractInternalFrame extends JInternalFrame implements InternalFrameListener, 
																			  IMessageListener,
																			  IMessageSender{
	private static final long serialVersionUID=1L;
	protected IMessageSender messageSender;
	protected int width;
	protected int height;
	/** �������� ���� ������� ���� */
	protected ArrayList<RegisteredWindow> listOfRegisteredWindow=new ArrayList<RegisteredWindow>();

	protected void error(Object object){
		System.err.print("ERROR AbstractinternalFrame ");
		System.err.println(object);
	}
	protected void debug(Object object){
		System.out.print("DEBUG AbstractinternalFrame ");
		System.out.println(object);
	}
	

	/** ���� �� ������� ����� 
	 * @param caption - ��������� ���� 
	 * @param messageSender - ������, �������� ������� ���������� ��� ��������� 
	 * @param width - ������ ���� 
	 * @param height - ������ ����
	 * @param isModal - �������� �� ���� ���������
	 * @param isSingleton - �������� �� ������ ����� ���� ���������� � ��������� �������� 
	 */
	public AbstractInternalFrame(String caption,
								IMessageSender messageSender,
								int width,
								int height,
								boolean isModal,
								boolean isSingleton){
		this(caption,messageSender, width, height, isModal, isSingleton, true,true,true,true);
	}
	/** ���� �� ������� ����� 
	 * @param caption - ��������� ���� 
	 * @param messageSender - ������, �������� ������� ���������� ��� ��������� 
	 * @param width - ������ ���� 
	 * @param height - ������ ����
	 * @param isModal - �������� �� ���� ���������
	 * @param isSingleton - �������� �� ������ ����� ���� ���������� � ��������� ��������
	 * @param closable - ���������� ������ �������� 
	 * @param iconifiable - ����������� ������������ � ������
	 * @param resizable - �������� ����������� 
	 * @param maximizable - ��������������� �� ������������� ������� 
	 */
	public AbstractInternalFrame(String caption,
								IMessageSender messageSender,
								int width,
								int height,
								boolean isModal,
								boolean isSingleton,
								boolean closable,
								boolean iconifiable,
								boolean resizable,
								boolean maximizable){
		super(caption);
		this.singleton=isSingleton;
		this.messageSender=messageSender;
		this.width=width;
		this.height=height;
		this.addInternalFrameListener(this);
		//this.setPreferredSize(new Dimension(this.width,this.height));
		this.setBounds(1,1,this.width,this.height);
		this.setVisible(true);
		this.setClosable(closable);
		this.setIconifiable(iconifiable);
		this.setResizable(resizable);
		this.setMaximizable(maximizable);
		messageSender.sendMessage(this, new WindowMessage(WindowMessageType.REGISTER_WINDOW,
				// �������������� � caption - ����������� ����� ����, ���� isSingleton==true
				new WindowIdentifier(this.getTitle(),false),
				this
				));
		messageSender.sendMessage(this,new WindowMessage(WindowMessageType.MOVE_TO_CENTER,null,this));
		if(isModal==true){
			this.setModal(true);
		}
		this.toFront();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private boolean modal=false;
	
	/** ���������� ������ ���� ��� ��������� */
	public void setModal(boolean value){
		if(value){
			messageSender.sendMessage(this, new WindowMessage(WindowMessageType.CHILD_SET_MODAL));
		}
		this.modal=value;
	}
	
	/** �������� �������� ���������� ���� */
	public boolean isModal(){
		return this.modal;
	}
	
	private boolean singleton;
	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// ��������� "�������� ����" ��� ���� ��������
		WindowMessage closeMessage=new WindowMessage(WindowMessageType.CLOSE_WINDOW);
		while(this.listOfRegisteredWindow.size()>0){
			this.listOfRegisteredWindow.get(this.listOfRegisteredWindow.size()-1).getFrame().notifyMessage(closeMessage);
		}
		// ������� "unregister ������� ����" ������������� ���� 
		messageSender.sendMessage(this, new WindowMessage(WindowMessageType.UNREGISTER_WINDOW,null, this));
	}
	
	/** ��������������� ��������� ������� super().notifyMessage()*/
	@Override
	public boolean notifyMessage(WindowMessage message){
		boolean returnValue=false;
		switch(message.getMessageId()){
			case WindowMessageType.MOVE_ON_TOP:{
				this.moveToFront();
				this.childOnTop();
				returnValue=true;
			};break;
			case WindowMessageType.SELECTED_SET:{
				try{
					this.setSelected(true);
				}catch(Exception ex){};
				returnValue=true;
			};break;
			case WindowMessageType.UNSELECTED_SET:{
				try{
					this.setSelected(false);
				}catch(Exception ex){};
				returnValue=true;
			};break;
			case WindowMessageType.CLOSE_WINDOW:{
				if(this.getModalWindowIndex()<0){
					this.internalFrameClosed(null);
				}
				returnValue=true;
			};break;
			case WindowMessageType.ICONIFY_WINDOW:{
				if(this.getModalWindowIndex()<0){
					try{
						this.setIcon(true);
					}catch(Exception ex){};
				}
				returnValue=true;
			};break;
			
			case WindowMessageType.DEICONIFY_WINDOW:{
				try{
					this.setIcon(false);
				}catch(Exception ex){};
				returnValue=true;
			};break;

			default:;
		}
		return returnValue;
	}
	
	/** ����������� ��� �������� ���� �� ������� ������� */
	private void childOnTop(){
		for(RegisteredWindow window:this.listOfRegisteredWindow){
			window.getFrame().toFront();
		}
		checkModal();
	}

	/** �������� ������ ���������� ���� */
	private int getModalWindowIndex(){
		int returnValue=(-1);
		for(int counter=0;counter<this.listOfRegisteredWindow.size();counter++){
			if(this.listOfRegisteredWindow.get(counter).getFrame().isModal()){
				returnValue=counter;
				break;
			}
		}
		return returnValue;
	}
	
	/** ��������� ������ ���� �� ������� � �������� ����� ����������� , � ���� ���� - �������� ����� ������ �������� ���������� ����*/
	private void checkModal(){
		int modalIndex=this.getModalWindowIndex();
		if(modalIndex>=0){
			this.listOfRegisteredWindow.get(modalIndex).getFrame().notifyMessage(new WindowMessage(WindowMessageType.SELECTED_SET));
		}
	}
	
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		this.messageSender.sendMessage(this, new WindowMessage(WindowMessageType.FOCUS_WAS_GOT));
		this.childOnTop();
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
	}

	private MouseListener[] titleMouseListener;
	private MouseMotionListener[] titleMouseMotionListener;
	private MouseWheelListener[] titleMouseWheelListener;
	private boolean initIconifiable;
	private boolean initClosable;
	private boolean initMaximizable;
	private boolean initResizable;

	/** ������� � ��������� ��� ��������� ������� ���� */
	private void getMouseListenerFromComponent(JComponent component){
		titleMouseListener=component.getMouseListeners();
		titleMouseMotionListener=component.getMouseMotionListeners();
		titleMouseWheelListener=component.getMouseWheelListeners();
		if(titleMouseListener!=null){
			for(int counter=0;counter<titleMouseListener.length;counter++){
				component.removeMouseListener(titleMouseListener[counter]);
			}
		}
		if(titleMouseMotionListener!=null){
			for(int counter=0;counter<titleMouseMotionListener.length;counter++){
				component.removeMouseMotionListener(titleMouseMotionListener[counter]);
			}
		}
		if(titleMouseWheelListener!=null){
			for(int counter=0;counter<titleMouseWheelListener.length;counter++){
				component.removeMouseWheelListener(titleMouseWheelListener[counter]);
			}
		}
		initIconifiable=this.isIconifiable();
		initClosable=this.isClosable();
		initMaximizable=this.isMaximizable();
		initResizable=this.isResizable();
		this.setIconifiable(false);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setResizable(false);
	}
	
	
	/** ������� �� ����� ��� ��������� ������� ���� ��� ���������� */
	private void setMouseListenerToComponent(JComponent component){
		if(titleMouseListener!=null){
			for(int counter=0;counter<titleMouseListener.length;counter++){
				component.addMouseListener(titleMouseListener[counter]);
			}
		}
		if(titleMouseMotionListener!=null){
			for(int counter=0;counter<titleMouseMotionListener.length;counter++){
				component.addMouseMotionListener(titleMouseMotionListener[counter]);
			}
		}
		if(titleMouseWheelListener!=null){
			for(int counter=0;counter<titleMouseWheelListener.length;counter++){
				component.addMouseWheelListener(titleMouseWheelListener[counter]);
			}
		}
		this.setIconifiable(initIconifiable);
		this.setClosable(initClosable);
		this.setMaximizable(initMaximizable);
		this.setResizable(initResizable);
	}
	
	
	/** �������� ��������� �� ������, ������� ��������� ���������� 
	 * @param owner - ����������� ������� ��������� ( ��� null)
	 * @param message - ���������, ������� ����� ������� 
	 * @return
	 */
	@Override
	public boolean sendMessage(AbstractInternalFrame owner, WindowMessage message){
		/** ���������������� ��� ���������, ������� ����� ������� - ��� ��� ������� ������������� ����, ���� �� ����� ������� �������� */
		if(isMessageForMainWindow(message.getMessageId())){
			// ��������� ��� ������������� ���� 
			if(message.getMessageId()==WindowMessageType.UNREGISTER_CHILD_WINDOW){
				// ������� ��������� ������������� ����, � �������� ������ �� �������� ����� ����������� ����
				messageSender.sendMessage(this, message);
			}
			// ��������� ��� ������������� ���� 
			if(message.getMessageId()==WindowMessageType.UNREGISTER_WINDOW){
				// � ������ ��������� ���������������� ��� �������� ������������������ ��������� ����, � ��������� ���� � �������� ����� 
				// ������� ��������� ������������� ����, � �������� ������ �� �������� ����� ����������� ���� 
				messageSender.sendMessage(this, new WindowMessage(WindowMessageType.UNREGISTER_CHILD_WINDOW,null,message.getArgument()));
				// ������� ���� ������������������ � ������ ����
				int removeIndex=this.getIndexOfRegisterWindow((AbstractInternalFrame)message.getArgument());
				if(removeIndex>=0){
					if(this.listOfRegisteredWindow.get(removeIndex).getFrame().isModal()){
						this.repaint();
						JComponent component=((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).getNorthPane();
						this.setMouseListenerToComponent(component);
					}else{
						debug("is not modal");
					}
					
					this.listOfRegisteredWindow.remove(removeIndex);
				}else{
					error("#sendMessage index of Child window was not found ");
				}
				
			}
			if(message.getMessageId()==WindowMessageType.CHILD_SET_MODAL){
				JComponent component=((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).getNorthPane();
				this.getMouseListenerFromComponent(component);

				//this.titleComponentDimension=((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).getNorthPane().getPreferredSize();
				//((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).getNorthPane().setPreferredSize(new Dimension(0,0));
				//this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
			}
			if(message.getMessageId()==WindowMessageType.REGISTER_CHILD_WINDOW){
				messageSender.sendMessage(this, message);
			}
			if(message.getMessageId()==WindowMessageType.REGISTER_WINDOW){
				// � ������ ��������� ���������������� ��� ���������� ��������� ���� � ������� ����, � ���������� ���� �� ������� ����
					// ����� � ������ �������� ���� ������ �� �������������� 
				int index=this.getExistingIdentifierIndex((WindowIdentifier)message.getAdditionalIdentifier());
				if((index<0)||(this.listOfRegisteredWindow.get(index).getFrame().isSingleton()==false)){
					// �������� ���� �� ������� ����
					messageSender.sendMessage(this, new WindowMessage(WindowMessageType.REGISTER_CHILD_WINDOW,message.getAdditionalIdentifier(),message.getArgument()));
					 	// ���������������� ���� � ������ ���� 
					this.listOfRegisteredWindow.add(new RegisteredWindow((AbstractInternalFrame)message.getArgument(),(WindowIdentifier)message.getAdditionalIdentifier()));
				}
			}
			if(message.getMessageId()==WindowMessageType.MOVE_TO_CENTER){
				AbstractInternalFrame source=(AbstractInternalFrame)message.getArgument();
				source.setBounds(
						(this.getWidth()-source.getWidth())/2+this.getX(),
						(this.getHeight()-source.getHeight())/2+this.getY(),
						source.getWidth(),
						source.getHeight()
						);
				// ����������� ������ ���� � �����, ������������ ��������
					// �������� ���������� ������� ����, ������������ �������� �����
			}
			if(message.getMessageId()==WindowMessageType.FOCUS_WAS_GOT){
				// ��������� ����� � ������������ ������ 
				this.messageSender.sendMessage(this, new WindowMessage(WindowMessageType.FOCUS_WAS_GOT));
			}
		}else{
			// ��������� ��� ������������� ������ 
			this.messageSender.sendMessage(this,message);
		}
		return false;
	}
	
	/** ����������� � �������� ����� ���� � ����� �� ��������������� 
	 * @return (-1) ���� �� ������ 
	 * */
	private int getExistingIdentifierIndex(WindowIdentifier additionalIdentifier) {
		int returnValue=(-1);
		for(int counter=0;counter<this.listOfRegisteredWindow.size();counter++){
			try{
				if(this.listOfRegisteredWindow.get(counter).getWindowIdentifiers().equals(additionalIdentifier)){
					returnValue=counter;
					break;
				}
			}catch(NullPointerException ex){};
		}
		return returnValue;
	}
	/** �������� ������ ���� �� ������������������� Frame*/
	private int getIndexOfRegisterWindow(AbstractInternalFrame frame){
		int returnValue=(-1);
		for(int counter=0;counter<this.listOfRegisteredWindow.size();counter++){
			try{
				if(this.listOfRegisteredWindow.get(counter).getFrame().equals(frame)){
					returnValue=counter;
					break;
				}
			}catch(Exception ex){
				System.err.println("FrameMain#countRegisterWindow Exception: "+ex.getMessage());
			}
		}
		return returnValue;
	}

	
	/** �������� �� ������ ��������� ����� ��� ������� ����, � �� ��� ��������� */
	private boolean isMessageForMainWindow(int messageId){
		if(
			 (messageId==WindowMessageType.UNREGISTER_WINDOW)
		   ||(messageId==WindowMessageType.UNREGISTER_CHILD_WINDOW)
		   ||(messageId==WindowMessageType.REGISTER_WINDOW)
		   ||(messageId==WindowMessageType.REGISTER_CHILD_WINDOW)
		   ||(messageId==WindowMessageType.MOVE_TO_CENTER)
		   ||(messageId==WindowMessageType.FOCUS_WAS_GOT)
		   ||(messageId==WindowMessageType.CHILD_SET_MODAL)
				){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/** ���������������� ���� � ������� */
	@Override
	public boolean addWindowListener(AbstractInternalFrame listener,WindowIdentifier identifier){
		
		return false;
	}
	
	/** ������� ������������������ ���� �� ������� */
	@Override
	public boolean removeWindowListener(AbstractInternalFrame listener){
		
		return false;
	}

	/** ������� ������� ���� */
	public void closeCurrentWindow(){
		this.internalFrameClosed(null);
	}
}
