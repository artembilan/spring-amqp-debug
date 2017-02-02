package edu.umich.med.cci.crap.mq.gateway;

import edu.umich.med.cci.crap.mq.message.QueryMessage;

public interface QueryMessageGateway {
	void sendQuery(String routingKey, QueryMessage query);
}
