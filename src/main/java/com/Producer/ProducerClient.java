package com.Producer;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerClient {
    public static void main(String[] argv) throws Exception {
        String topicName = "testTopicJuly30";

        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"tuwizui-dt-1d.ula.comcast.net:9092,epcui-dt-1q.ula.comcast.net:9092,epcuiapp-dt-1q.downingtown.pa.ula.comcast.net:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        Producer<Integer	, Integer> producer = new KafkaProducer<Integer, Integer>(configProperties);
        ProducerCallback callback = new ProducerCallback();
        
        int loop = 0;
        while(loop < 5)
        {
        	System.out.println("In loop");
        	producer.send(new ProducerRecord<Integer, Integer>(topicName, ThreadLocalRandom.current().nextInt(1, 1000 + 1), loop), callback);
        	loop++;
        }
        producer.close();
    }
    
}
