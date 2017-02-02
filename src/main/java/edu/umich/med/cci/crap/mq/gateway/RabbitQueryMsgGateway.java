package edu.umich.med.cci.crap.mq.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.umich.med.cci.crap.mq.message.JobMessage;
import edu.umich.med.cci.crap.mq.message.PendingMessage;
import edu.umich.med.cci.crap.mq.message.QueryMessage;

@Component
public class RabbitQueryMsgGateway implements QueryMessageGateway {
	private static final Logger logger = LoggerFactory.getLogger(RabbitQueryMsgGateway.class);
	@Value("${crap.mq.exchange}")
	private String exchangeName;
	@Value("${crap.mq.exchange4notice}")
	private String exchangeName4Notice;
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public RabbitQueryMsgGateway(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		this.rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
			if(null != correlationData && correlationData instanceof PendingMessage) {
				@SuppressWarnings("unchecked")
				PendingMessage<QueryMessage> pm = (PendingMessage<QueryMessage>)correlationData;
				if(null == pm.getPayload()) {
					logger.error("RabbitQueryMsgGateway can't recognize this query message, payload is null: " + correlationData.getId());
					return;
				}
				if(true == ack) {
					JobMessage jm = new JobMessage(String.format("%d", pm.getPayload().getTaskId()),
							String.format("%d", pm.getPayload().getJobId()),
							String.format("%d", pm.getPayload().getJobCount()));
					jm.setCompleted(false);
					jm.setSentByWorker(false);
					jm.setSentByDispatcher(true);
					jm.setDelivered(true);
					this.rabbitTemplate.convertAndSend(this.exchangeName4Notice, "notice", jm);
					logger.debug("Message sent to notification queue: " + correlationData.toString());
				}
				else {
					//https://git.umms.med.umich.edu/CCI/fusion/issues/20
					logger.error("RabbitQueryMsgGateway failed to send message to job queue, retry: " + cause);
					this.rabbitTemplate.convertAndSend(this.exchangeName, pm.getRoutingKey(), pm.getPayload(), pm);
				}
			}
		});
	}
	@Override
	public void sendQuery(String routingKey, QueryMessage query) {
		//https://git.umms.med.umich.edu/CCI/fusion/issues/20
		this.rabbitTemplate.convertAndSend(this.exchangeName, routingKey, query, new PendingMessage<QueryMessage>(String.format("%d:%d:%d", query.getTaskId(), query.getJobId(), query.getJobCount()), routingKey, query));
	}

}
