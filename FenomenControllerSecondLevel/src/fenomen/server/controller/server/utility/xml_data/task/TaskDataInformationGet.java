package fenomen.server.controller.server.utility.xml_data.task;

import org.w3c.dom.Element;

public class TaskDataInformationGet extends TaskData{

	/** �������� ��������� ��� ThreadInformation (�� ������)*/
	public TaskDataInformationGet() {
		super(true, "�������� ��������� ��� ThreadInformation");

		Element heart_beat=this.getDocument().createElement("information");
		this.getTaskElement().appendChild(heart_beat);
		
			Element get=this.getDocument().createElement("get");
			heart_beat.appendChild(get);
	}

}
