package edu.umich.med.cci.crap.mq.message;

public class JobMessage {
	private boolean isSentByDispatcher = false;
	private boolean isSentByWorker = false;
	private boolean isDelivered = false;
	private boolean isCompleted = false;
	private String jobId;
	private String taskId;
	private String jobCount;
	private String sql;
	private String fileLocation;
	
	public JobMessage() {
		this.jobId = this.taskId = this.jobCount = this.sql = "";
	}
	public JobMessage(String taskId, String jobId, String jobCount) {
		this.taskId = taskId;
		this.jobId = jobId;
		this.jobCount = jobCount;
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getJobCount() {
		return jobCount;
	}
	public void setJobCount(String jobCount) {
		this.jobCount = jobCount;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public boolean isSentByDispatcher() {
		return isSentByDispatcher;
	}
	public void setSentByDispatcher(boolean isSentByDispatcher) {
		this.isSentByDispatcher = isSentByDispatcher;
	}
	public boolean isSentByWorker() {
		return isSentByWorker;
	}
	public void setSentByWorker(boolean isSentByWorker) {
		this.isSentByWorker = isSentByWorker;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (null == other || other.getClass() != this.getClass())
			return false;
		JobMessage castOther = (JobMessage)other;
		return	this.taskId.equals(castOther.taskId) && this.jobId.equals(castOther.jobId) 
				&& this.isCompleted == castOther.isCompleted && this.isDelivered == castOther.isDelivered;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskId.hashCode();
		hash = hash * prime + this.jobId.hashCode();
		hash = hash * prime + Boolean.hashCode(this.isDelivered);
		hash = hash * prime + Boolean.hashCode(this.isCompleted);
		return hash;
	}
}
