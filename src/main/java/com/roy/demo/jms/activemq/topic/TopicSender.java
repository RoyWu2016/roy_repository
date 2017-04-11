package com.roy.demo.jms.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageProducer producer;
		connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			destination = session.createTopic("FirstTopic");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 优先级不能影响先进先出。。。那这个用处究竟是什么呢呢呢呢
			MqBean bean = new MqBean();
			bean.setAge(13);
			for (int i = 0; i < 5; i++) {
				Thread.sleep(1000);
				bean.setName("小黄" + i);
				producer.send(session.createObjectMessage(bean));
			}
			producer.close();
			System.out.println("呵呵");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

}
