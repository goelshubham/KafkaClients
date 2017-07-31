package com.Consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

public class ConsumerThread implements Runnable {
	
	private String topicName = "testTopicJuly30";
	private String groupName = "testTopicJuly30ConsumerGroup";
	private KafkaConsumer<Integer, Integer> kafkaConsumer;
	ConsumerRecords<Integer, Integer> records = null;
	
	/*
	 * Setting Kafka Consumer Properties
	 */
	public Properties createConsumerProperties()
	{
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "tuwizui-dt-1d.ula.comcast.net:9092,epcui-dt-1q.ula.comcast.net:9092,epcuiapp-dt-1q.downingtown.pa.ula.comcast.net:9092");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
        return properties;
	}
	
	
	public void run()
	{
		System.out.println("Inside Run method of Thread");
		kafkaConsumer = new KafkaConsumer<Integer, Integer>(createConsumerProperties());
		kafkaConsumer.subscribe(Arrays.asList(topicName));
		
		try
		{
			while(true)
			{
				records = kafkaConsumer.poll(Long.MAX_VALUE);
				
				if(records!=null)
				{
					System.out.println("Records fetched - count -->" + records.count());
					for(ConsumerRecord<Integer, Integer> record: records)
					{
						System.out.println("Record Value --> " + record.value());
						kafkaConsumer.commitAsync(new ConsumerCallback());
					}
				}
				else if(records == null)
				{
					System.out.println("Records fetched is null " + records);
				}
				
			}
		}
		catch(WakeupException e)
		{
			e.printStackTrace();
		}
		catch(CommitFailedException commitFailedException)
		{
			System.out.println("CommitFailedException");
			commitFailedException.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			kafkaConsumer.close();
			System.out.println("Consumer closed");
		}
	}
}
