package edu.umich.med.cci.crap.mq;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.umich.med.cci.crap.mq.service.QueryMessageDispatcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrapbootMqDispatcherApplicationTests {
	@Autowired
	QueryMessageDispatcher dispatcher;
	@Test
	public void contextLoads() {
	}
	@Test
	public void TestMessageDispatcher() {
		//dispatcher.processQuery(new ArrayList<String>());
	}
}
