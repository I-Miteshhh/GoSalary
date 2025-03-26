package com.gosalary;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class App 
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
	props.put("bootstrap.servers", "localhost:9092");
	props.put("acks","all");
	props.put("retries", 0);
	props.put("batch.size", 16384);
	props.put("linger.ms", 1);
	props.put("buffer.memory", 33554432);
	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

	Producer<String, String> producer = new KafkaProducer<>(props);

	String[] salaryData = {
		"{\"job_title\":\"SDE 2\",\"experience\":3,\"location\":\"Mumbai\",\"company\":\"Google\",\"salary\":2500000}",
		"{\"job_title\":\"SDE 1\",\"experience\":2,\"location\":\"Pune\",\"company\":\"Goldman\",\"salary\":1600000}"
	};

	for(String data : salaryData) {
		System.out.println("Sending data: " + data);
		producer.send(new ProducerRecord<>("salary-data",null,data));
	}

	producer.close();

    }
}

