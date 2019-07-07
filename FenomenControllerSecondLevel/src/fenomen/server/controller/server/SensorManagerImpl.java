package fenomen.server.controller.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import database.ConnectWrap;
import database.StaticConnector;
import database.wrap.ModuleTaskWrap;

import fenomen.server.controller.client.view.settings.module.sensor_window.Device;
import fenomen.server.controller.client.view.settings.module.sensor_window.SensorManager;
import fenomen.server.controller.client.view.settings.module.sensor_window.DeviceRegister;
import fenomen.server.controller.server.utility.XmlData;
import fenomen.server.controller.server.utility.xml_data.task.TaskData;
import fenomen.server.controller.server.utility.xml_data.task.TaskDataSensorGet;
import fenomen.server.controller.server.utility.xml_data.task.TaskDataGetSensorList;
import fenomen.server.controller.server.utility.xml_data.task.TaskDataSensorSet;

/** ��������� ������� */
public class SensorManagerImpl extends RemoteServiceServlet implements SensorManager{
	private final static long serialVersionUID=1L;
	private Logger logger=Logger.getLogger(this.getClass());
	
	/** {@inheritDoc} */
	@Override
	public Device[] getAllSensorsIntoModule(int moduleId) {
		Device[] returnValue=null;
		ConnectWrap connector=StaticConnector.getConnectWrap();
		ResultSet rs=null;
		try{
			Connection connection=connector.getConnection();
			rs=connection.createStatement().executeQuery("select * from module_sensor where id_module="+moduleId);
			ArrayList<Device> list=new ArrayList<Device>();
			while(rs.next()){
				Device device=this.getSensorInModule(connection, 
													 rs.getInt("id_module"), 
													 rs.getInt("id_modbus"));
				if(device!=null){
					list.add(device);
				}else{
					logger.error("Module does not finded: IdModule:"+rs.getInt("id_module")+"    IdModbus:"+rs.getInt("id_modbus"));
				}
			}
			returnValue=list.toArray(new Device[]{});
		}catch(Exception ex){
			logger.error(" getAllSensors Exception: "+ex.getMessage());
		}finally{
			try{
				rs.close();
			}catch(Exception ex){};
			connector.close();
		}
		return returnValue;
	}

	/** {@inheritDoc}}*/
	@Override
	public boolean setTaskGetAllSensor(int moduleId) {
		boolean returnValue=false;
		// ������������ XML ������, 
		TaskData taskData=new TaskDataGetSensorList();
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			// ������� ��� ������ �� ���������� �� ���� ������ 
			this.removeDevicesByModuleFromDatabase(connector, moduleId);
			// ��������� ������ � ���� ����� �� �����
				// �������� ���� � �������� �� �����, ������� �������� ���������
			String taskPath=taskData.getPathToStorage(XmlData.pathTask);
			String fileName=taskData.saveXmlAsFile(taskPath);
			if(fileName!=null){
				// � �������� � ���� ������ ������ ��� ������
				ModuleTaskWrap moduleTaskWrap=new ModuleTaskWrap();
				moduleTaskWrap.setIdModule(moduleId);
				moduleTaskWrap.setIdStorage(fileName);
				moduleTaskWrap.setIdResult(0);
				moduleTaskWrap.setIdState(0);
				moduleTaskWrap.setTimeWrite(new java.util.Date());
				Session session=connector.getSession();
				session.beginTransaction();
				session.save(moduleTaskWrap);
				session.getTransaction().commit();
				returnValue=true;
				logger.debug("setTaskGetAllSensor put record in \"module_task\" by IdModule:"+moduleId);
			}else{
				returnValue=false;
			}
		}catch(Exception ex){
			logger.error("setTaskGetAllSensor Exception: "+ex.getMessage());
		}finally{
			connector.close();
		}
		return returnValue;
	}

	
	@Override
	public Device getSensorIntoModule(int moduleId, int addressModbus) {
		Device returnValue=null;
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			returnValue=this.getSensorInModule(connector.getConnection(), moduleId, addressModbus);
		}catch(Exception ex){
			logger.error("getSensorIntoModule Exception: "+ex.getMessage());
		}finally{
			connector.close();
		}
		return returnValue;
	}

	
	/** �������� �� ���� ������ ���� SensorData
	 * @param connection - ���������� � ����� ������ 
	 * @param idModule - id ������ �� ���� 
	 * @param idModbus - ����� ������� �� ������  
	 * @return
	 */
	private Device getSensorInModule(Connection connection, int idModule, int idModbus){
		Device returnValue=null;
		ResultSet rs=null;
		ResultSet registers=null;
		PreparedStatement registerDescription=null;
		try{
			// ������� ���� ���������� �� ��������� ������ ������ � ����, � �� ��������� ������ � ���� Modbus ( ��� �� ����������� � �������� ������ )
			rs=connection.createStatement().executeQuery("select module_sensor.*, sensor_type.name sensor_type_name, sensor_type.description sensor_type_description from module_sensor left join sensor_type on sensor_type.id=module_sensor.id_sensor_type where id_module="+idModule+"  and id_modbus="+idModbus);
			if(rs.next()){
				Device currentDevice=new Device();
				currentDevice.setIdSensor(rs.getInt("id"));
				currentDevice.setIdModule(rs.getInt("id_module"));
				currentDevice.setModbusAddress(rs.getInt("id_modbus"));
				currentDevice.setIdSensorType(rs.getInt("id_sensor_type"));
				currentDevice.setEnabled(rs.getInt("is_enabled")==1);
				currentDevice.setSensorTypeName(rs.getString("sensor_type_name"));
				currentDevice.setSensorTypeDescription(rs.getString("sensor_type_description"));
				
				// �������� ��� �������� �� ������� ������
				registers=connection.createStatement().executeQuery("SELECT * FROM module_sensor_register where id_sensor="+rs.getInt("id"));
				registerDescription=connection.prepareStatement("select * from sensor_register_type where id_sensor_type=? and address_register=?");
				while(registers.next()){
					DeviceRegister register=new DeviceRegister();
					register.setId(registers.getInt("id"));
					register.setIdSensor(registers.getInt("id_sensor"));
					register.setRegisterAddress(registers.getInt("register_address"));
					register.setRegisterValue(registers.getInt("register_value"));
					register.setDescription(registers.getString("description"));
					try{
						register.setRegisterValueDateTime(new Date(registers.getTimestamp("register_value_date_write").getTime()));
					}catch(Exception ex){};
					registerDescription.clearParameters();
					registerDescription.setInt(1, currentDevice.getIdSensorType());
					registerDescription.setInt(2, register.getRegisterAddress());
					ResultSet tempRs=registerDescription.executeQuery();
					if(tempRs.next()){
						register.setDescription(tempRs.getString("description"));
					}
					tempRs.close();
					currentDevice.getListOfRegister().add(register);
				}
				registers.getStatement().close();
				returnValue=currentDevice;
			}else{
				returnValue=null;
			}
		}catch(Exception ex){
			logger.error("#getSensorInModule Exception: "+ex.getMessage());
		}finally{
			try{
				rs.close();
			}catch(Exception ex){};
			try{
				registers.close();
			}catch(Exception ex){};
		}
		return returnValue;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean setTaskGetValueFromModule(int moduleId,
											 int addressModule) {
		boolean returnValue=false;
		// ������� �������
		TaskDataSensorGet getSensor=new TaskDataSensorGet(addressModule);
		// ��������� ��� XML ���� �� �����
		String fileName=getSensor.saveXmlAsFile(getSensor.getPathToStorage(XmlData.pathTask));
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			// ������� Task
			ModuleTaskWrap moduleTask=new ModuleTaskWrap();
			moduleTask.setIdModule(moduleId);
			moduleTask.setIdResult(0);
			moduleTask.setIdState(0);
			moduleTask.setIdStorage(fileName);
			moduleTask.setTimeWrite(new java.util.Date());
			Session session=connector.getSession();
			// ��������� ���� Task � ���� ������  
			session.beginTransaction();
			session.save(moduleTask);
			session.getTransaction().commit();
			returnValue=true;
		}catch(Exception ex){
			logger.error("setTaskGetValueFromModule Exception: ");
		}finally{
			try{
				connector.close();
			}catch(Exception ex){};
		}
		return returnValue;
	}

	/** {@inheritDoc} */
	@Override
	public boolean setTaskPutValueToModuleToRegister(int moduleId,
													 int addressModbus, 
													 int registerAddress, 
													 int value, 
													 Boolean isEnabled) {
		boolean returnValue=false;
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			TaskDataSensorSet taskData=new TaskDataSensorSet(addressModbus, isEnabled, new int[]{registerAddress}, new int[]{value});
			
			String fileName=taskData.saveXmlAsFile(taskData.getPathToStorage(XmlData.pathTask));
			ModuleTaskWrap moduleTask=new ModuleTaskWrap();
			moduleTask.setIdModule(moduleId);
			moduleTask.setIdResult(0);
			moduleTask.setIdState(0);
			moduleTask.setIdStorage(fileName);
			moduleTask.setTimeWrite(new java.util.Date());
			Session session=connector.getSession();
			// ��������� ���� Task � ���� ������  
			session.beginTransaction();
			session.save(moduleTask);
			session.getTransaction().commit();
			returnValue=true;
		}catch(Exception ex){
			logger.error("setTaskSetValueToModule Exception: "+ex.getMessage());
		}finally{
			
		}
		return returnValue;
	}

	/** {@inheritDoc} */
	@Override
	public boolean setTaskPutValueToModuleToRegister(int moduleId,
													 int addressModbus, 
													 int[] registers, 
													 int[] values, 
													 Boolean isEnabled) {
		boolean returnValue=false;
		ConnectWrap connector=StaticConnector.getConnectWrap();
		try{
			TaskDataSensorSet taskData=new TaskDataSensorSet(addressModbus, isEnabled, registers, values);
			
			String fileName=taskData.saveXmlAsFile(taskData.getPathToStorage(XmlData.pathTask));
			ModuleTaskWrap moduleTask=new ModuleTaskWrap();
			moduleTask.setIdModule(moduleId);
			moduleTask.setIdResult(0);
			moduleTask.setIdState(0);
			moduleTask.setIdStorage(fileName);
			moduleTask.setTimeWrite(new java.util.Date());
			Session session=connector.getSession();
			// ��������� ���� Task � ���� ������  
			session.beginTransaction();
			session.save(moduleTask);
			session.getTransaction().commit();
			returnValue=true;
		}catch(Exception ex){
			logger.error("setTaskSetValueToModule Exception: "+ex.getMessage());
		}finally{
			
		}
		return returnValue;
	}

	/** ������� ���������� �� ����������� �� ��������� ������ 
	 * @param connector - ���������� � ����� ������ 
	 * @param moduleId - ���������� ������������� ������ � ���� ������
	 * @return
	 * <ul type="square">
	 * 	<li> <strong>null</strong> - ������ ������� ������� </li>
	 * 	<li> <strong>String</strong> - ����� ������ �������� ������ </li>
	 * </ul>
	 */
	private String removeDevicesByModuleFromDatabase(ConnectWrap connector, int moduleId){
		String returnValue=null;
		try{
			// ������� ��� �������� �� ����������� 
			connector.getConnection().createStatement().executeUpdate("delete from module_sensor_register where id_sensor in (select id from module_sensor where id_module="+moduleId+")");
			// ������� ��� ���������� 
			connector.getConnection().createStatement().executeUpdate("delete from module_sensor where id_module="+moduleId);
			connector.getConnection().commit();
		}catch(Exception ex){
			logger.error("removeDevicesByModuleFromDatabase Exception:"+ex.getMessage());
			returnValue=ex.getMessage();
		}
		return returnValue;
	}
}
