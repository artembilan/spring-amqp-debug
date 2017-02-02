package edu.umich.med.cci.crap.mq.service;

import java.util.HashMap;
import java.util.List;

public interface QueryMessageDispatcher {
	boolean processQuery(HashMap<String, List<String>> concepts);
}
