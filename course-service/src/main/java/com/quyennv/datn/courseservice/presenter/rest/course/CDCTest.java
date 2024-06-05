package com.quyennv.datn.courseservice.presenter.rest.course;

import com.quyennv.datn.courseservice.presenter.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CDCTest {
    @KafkaListener(id="cdcGroup", topics = "product.public.product")
    public void listen(ProductDto in) {
        log.info("Received: {}", in);
    }
}
