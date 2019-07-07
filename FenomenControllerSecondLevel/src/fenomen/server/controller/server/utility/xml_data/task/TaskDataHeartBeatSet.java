package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

/** ��������� ��� ThreadHeartBeat (����������) */
public class TaskDataHeartBeatSet extends TaskData{

	/** ��������� ��� ThreadHeartBeat (����������)
	 * @param timeWait - ����� ���������� ������ �� ����� <b>/set/time_wait</b>
	 * @param timeError - ����� �������� ����� ���������� ���������� <b>/set/time_error</b>
	 */
	public TaskDataHeartBeatSet(int timeWait, int timeError) {
		super(true, "���������� ��������� ��� ThreadHeartBeat");

		Element heart_beat=this.getDocument().createElement("heart_beat");
		this.getTaskElement().appendChild(heart_beat);
		
			Element set=this.getDocument().createElement("set");
			heart_beat.appendChild(set);
		
				Element time_wait=this.getDocument().createElement("time_wait");
				time_wait.setTextContent(Integer.toString(timeWait));
				set.appendChild(time_wait);
				
				Element time_error=this.getDocument().createElement("time_error");
				time_error.setTextContent(Integer.toString(timeError));
				set.appendChild(time_error);
	}

}
