package xmlUtility;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"taskID", "clicks"})

public class Task {
	int taskID;
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	List<Click> clicks;

	public List<Click> getClicks() {
		return clicks;
	}

	public void setClicks(List<Click> clicks) {
		this.clicks = clicks;
	}

}
