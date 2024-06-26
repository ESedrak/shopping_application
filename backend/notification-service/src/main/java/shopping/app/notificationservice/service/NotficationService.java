package shopping.app.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import shopping.app.orderservice.event.OrderPlacedEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotficationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent){
        log.info("Got Message from order-placed topic {}", orderPlacedEvent);
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("springshop@gmail.com");
            mimeMessageHelper.setTo(orderPlacedEvent.getEmail());
            mimeMessageHelper.setSubject(String.format("Order %s has been placed", orderPlacedEvent.getOrderNumber()));
            mimeMessageHelper.setText(String.format("Your order has been placed successfully: %s",  orderPlacedEvent.getOrderNumber()));
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Order notification email sent successfully");
        }catch (MailException e){
            log.error("Error while sending email notification", e);
            throw new RuntimeException("Exception while sending email notification");
        }

    }
}
