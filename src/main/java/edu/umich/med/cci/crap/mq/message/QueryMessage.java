package edu.umich.med.cci.crap.mq.message;

import java.util.List;

public class QueryMessage {
	private int taskId;
	private int jobId;
	private int jobCount;
	private List<String> fields;
	private List<String> tables;
	private List<String> conditions;
	
	public QueryMessage() {
		this.taskId = this.jobId = this.jobCount = -1;
		this.fields = this.tables = this.conditions = null;
	}
	public QueryMessage(int taskId, int jobId, int jobCount) {
		this.taskId = taskId;
		this.jobId = jobId;
		this.jobCount = jobCount;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobCount() {
		return jobCount;
	}
	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<String> getTables() {
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (null == other || other.getClass() != this.getClass())
			return false;
		QueryMessage castOther = (QueryMessage)other;
		return	this.taskId == castOther.taskId && this.jobId == castOther.jobId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + Integer.hashCode(this.taskId);
		hash = hash * prime + Integer.hashCode(this.jobId);
		return hash;
	}
}
