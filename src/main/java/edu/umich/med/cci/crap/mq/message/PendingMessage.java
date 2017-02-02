package edu.umich.med.cci.crap.mq.message;

import org.springframework.amqp.rabbit.support.CorrelationData;

public class PendingMessage<T> extends CorrelationData {
	private T payload;
	private String routingKey;
	public PendingMessage(String id, String rk, T p) {
		super(id);
		this.payload = p;
		this.routingKey = rk;
	}
	public T getPayload() {
		return payload;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (null == other || other.getClass() != this.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		PendingMessage castOther = (PendingMessage)other;
		boolean ret = false;
		if(null == this.getId())
			ret = null == castOther.getId();
		else
			ret = this.getId().equals(castOther.getId());
		if(ret) {
			if(null == this.routingKey)
				ret = null == castOther.routingKey;
			else
				ret = this.routingKey.equals(castOther.routingKey);
		}
		if(ret) {
			if(null == this.payload)
				ret = null == castOther.payload;
			else
				ret = this.payload.equals(castOther.payload);
		}
		return ret;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		if(null != this.getId())
			hash = hash * prime + this.getId().hashCode();
		if(null != this.routingKey)
			hash = hash * prime + this.routingKey.hashCode();
		if(null != this.payload)
			hash = hash * prime + payload.hashCode();
		return hash;
	}
}
