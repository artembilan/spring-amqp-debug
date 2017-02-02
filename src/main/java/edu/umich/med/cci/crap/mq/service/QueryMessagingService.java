package edu.umich.med.cci.crap.mq.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.umich.med.cci.crap.mq.gateway.QueryMessageGateway;
import edu.umich.med.cci.crap.mq.message.QueryMessage;

@Service
public class QueryMessagingService implements QueryMessageDispatcher {
	private QueryMessageGateway gateway;
	@Autowired
	public QueryMessagingService(QueryMessageGateway gateway) {
		this.gateway = gateway;
	}
	@Override
	public boolean processQuery(HashMap<String, List<String>> concepts) {
		int taskId = 1;
		int jobCount = 10;
		QueryMessage qm = null;
		for(int i = 0; i < jobCount - 1; ++i) {
			qm = new QueryMessage(taskId, i, jobCount);
			this.gateway.sendQuery("popcore", qm);
		}
		return false;
	}

}
