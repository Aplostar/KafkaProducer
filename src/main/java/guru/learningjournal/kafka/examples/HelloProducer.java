package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HelloProducer {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("Creating Kafka Producer ...");
        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer = new KafkaProducer<Integer, String>(properties);

        logger.info("Start Sending Messages ... ");
        for(int i = 0;i<AppConfigs.numEvents;i++)
        {
            producer.send(new ProducerRecord<>(AppConfigs.topicName,i,"Simple Message "+i));
        }
        logger.info("Finished sending messages. Closing producer... ");
        producer.close();
    }
}
