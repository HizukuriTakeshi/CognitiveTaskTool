package xmlUtility;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"subjectID", "tasks"})
public class Result {

	int subjectID;
	List<Task> tasks;


	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
}
