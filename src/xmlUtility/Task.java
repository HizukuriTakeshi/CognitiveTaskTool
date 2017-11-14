package xmlUtility;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"taskID", "startPreTime","endPreTime","startPostTime","endPostTime","clicks"})

public class Task {
	int taskID;
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	long startPreTime;
	long endPreTime;
	long startPostTime;
	long endPostTime;



	public long getStartPreTime() {
		return startPreTime;
	}

	public void setStartPreTime(long startPreTime) {
		this.startPreTime = startPreTime;
	}

	public long getEndPreTime() {
		return endPreTime;
	}

	public void setEndPreTime(long endPreTime) {
		this.endPreTime = endPreTime;
	}

	public long getStartPostTime() {
		return startPostTime;
	}

	public void setStartPostTime(long startPostTime) {
		this.startPostTime = startPostTime;
	}

	public long getEndPostTime() {
		return endPostTime;
	}

	public void setEndPostTime(long endPostTime) {
		this.endPostTime = endPostTime;
	}

	List<Click> clicks;

	public List<Click> getClicks() {
		return clicks;
	}

	public void setClicks(List<Click> clicks) {
		this.clicks = clicks;
	}

}
