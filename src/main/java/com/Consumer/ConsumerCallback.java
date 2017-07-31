package com.Consumer;

import java.util.Map;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

public class ConsumerCallback implements OffsetCommitCallback{

	public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
		if(exception!=null)
		{
			System.out.println("Exception Occured");
			exception.printStackTrace();
		}
		else
		{
			System.out.println("Commit Success!!");
		}
		
	}



}
