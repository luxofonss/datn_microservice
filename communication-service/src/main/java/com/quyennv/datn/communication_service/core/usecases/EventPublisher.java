package com.quyennv.datn.communication_service.core.usecases;


public interface EventPublisher {

    <T> void publish(T feedback, String topic);

}
