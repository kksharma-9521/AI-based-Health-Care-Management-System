package com.webhook_receiver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @GetMapping
    public String test() {
        return "working";
    }

    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody Object data) {

        System.out.println("Webhook received: " + data);

        return ResponseEntity.ok("Webhook received successfully");
    }
}