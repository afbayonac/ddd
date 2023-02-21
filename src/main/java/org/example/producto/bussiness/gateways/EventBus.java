package org.example.producto.bussiness.gateways;


import org.example.producto.generic.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);
    void publishError(Throwable errorEvent);
}
