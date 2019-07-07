/*
 * position.java
 * Created on 25 ���� 2008, 10:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package swing_framework;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
/**
 * ����� ��� ��������� ������� ��� JFrame � JInternalFrame (on JDesktopPane)
 * @author Technik
 */
public class Position {
    
    /** Creates a new instance of position */
    public Position() {
    }
    
    /**
     * ��������� JDialog � ��������� ��������� � ������ �������� ��������
     */
    public static void set_dialog_by_dimension(JDialog frame,Dimension dimension){
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int)((screenSize.getWidth()-dimension.getWidth())/2),
                        (int)((screenSize.getHeight()-dimension.getHeight())/2),
                        (int)dimension.getWidth(),
                        (int)dimension.getHeight());
    }
    
    /**
     * ��������� JDialog �� ���� ����� �������� ��������
     */
    public static void set_dialog_to_center(JDialog frame){
        set_dialog_to_center(frame,0,0);
    }
    /**
     * ��������� JDialog � ����� �� ��������� ����� � ������
     */
    public static void set_dialog_to_center(JDialog frame,int offset_left,int offset_top){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(offset_left, offset_top, 
                        screenSize.width - offset_left*2, 
                        screenSize.height-offset_top*2);
    };
    
    
    /**
     * ��������� JFrame �� ���� ����� �������� ��������
     */
    public static void set_frame_to_center(JFrame frame){
        set_frame_to_center(frame,0,0);
    }
    /**
     * ��������� JFrame � ����� �� ��������� ����� � ������
     */
    public static void set_frame_to_center(JFrame frame,int offset_left,int offset_top){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(offset_left, offset_top, 
                        screenSize.width - offset_left*2, 
                        screenSize.height-offset_top*2);
    };
    /**
     * ��������� JFrame � ��������� ��������� � ������ �������� ��������
     */
    public static void set_frame_by_dimension(JFrame frame,Dimension dimension){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int)((screenSize.getWidth()-dimension.getWidth())/2),
                        (int)((screenSize.getHeight()-dimension.getHeight())/2),
                        (int)dimension.getWidth(),
                        (int)dimension.getHeight());
    }
    /**
     * ��������� JFrame � ��������� ��������� � ������ �������� ��������
     */
    public static void set_frame_by_dimension(JFrame frame,int width,int height){
        set_frame_by_dimension(frame,new Dimension(width,height));
    }
    /**
     * ��������� JInternalFrame � ������ �������� ����� �� ��������� ����� � ������
     */
    public static void set_frame_to_center(JInternalFrame internalframe,
                                           JDesktopPane desktop,
                                           int offset_left,
                                           int offset_top){
        Dimension screenSize = desktop.getSize();
        internalframe.setBounds((int)(screenSize.getWidth()/2-offset_left/2-internalframe.getWidth()/2),
                                (int)(screenSize.getHeight()/2-offset_top/2-internalframe.getHeight()/2),
                                (int)internalframe.getWidth(),
                                (int)internalframe.getHeight());
    }
    /**
     * ��������� JInternalFrame �� ���� ������� ����
     */
    public static void set_frame_to_center(JInternalFrame internalframe,
                                           JDesktopPane desktop){
        set_frame_to_center(internalframe,desktop,0,0);
    }                                         
    /**
     * ��������� JInternalFrame � ������ �������� ����� � ��������� ���������
     */
    public static void set_frame_by_dimension(JInternalFrame internalframe,
                                              JDesktopPane desktop,
                                              int width,
                                              int height){
        internalframe.setBounds((int)(((desktop.getWidth()-internalframe.getWidth())/2)-width/2),
                                (int)(((desktop.getHeight()-internalframe.getHeight())/2)-height/2),
                                width,
                                height);
    }
    /**
     * ��������� JInternalFrame � ������ �������� ����� ��� ��������� ��������
     */
    public static void set_frame_by_dimension(JInternalFrame internalframe,
                                              JDesktopPane desktop){
        internalframe.setBounds((int)(((desktop.getWidth()-internalframe.getWidth())/2)-internalframe.getWidth()/2),
                                (int)(((desktop.getHeight()-internalframe.getHeight())/2)-internalframe.getHeight()/2),
                                internalframe.getWidth(),
                                internalframe.getHeight());
    }
    
}



















