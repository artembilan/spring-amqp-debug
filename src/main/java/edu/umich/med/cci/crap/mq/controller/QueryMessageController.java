package edu.umich.med.cci.crap.mq.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.umich.med.cci.crap.mq.service.QueryMessageDispatcher;

@RestController
@RequestMapping("/rest/*")
public class QueryMessageController {
	@Autowired
	private QueryMessageDispatcher dispatcher;
	 
	@GetMapping("/")
	public void getNothing() {
		this.dispatcher.processQuery(new HashMap<String, List<String>>());
	}
}
