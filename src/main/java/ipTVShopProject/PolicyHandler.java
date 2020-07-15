package ipTVShopProject;

import ipTVShopProject.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    MarketingRepository marketingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverJoinOrdered_InfoRegister(@Payload JoinOrdered joinOrdered){

        if(joinOrdered.isMe()){
            System.out.println("##### listener InfoRegister : " + joinOrdered.toJson());
            Marketing infoRegister = new Marketing();
            infoRegister.setOrderId(joinOrdered.getId());
            infoRegister.setCustomerId(joinOrdered.getCustomerId());

            marketingRepository.save(infoRegister);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_InfoDelete(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener InfoDelete : " + orderCanceled.toJson());
            Marketing infoDelete = marketingRepository.findByOrderId(orderCanceled.getId());
            marketingRepository.delete(infoDelete);
        }
    }

}
