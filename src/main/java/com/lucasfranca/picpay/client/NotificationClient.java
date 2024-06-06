package com.lucasfranca.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lucasfranca.picpay.entity.Transfer;

@FeignClient(url = "${client.notification-service.url}")
public interface NotificationClient {
	
	@PostMapping
	ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);
}
