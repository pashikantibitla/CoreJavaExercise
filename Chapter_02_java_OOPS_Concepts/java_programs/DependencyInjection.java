interface MessageService {
    void sendMessage(String message, String recipient);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Email sent to " + recipient + ": " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + ": " + message);
    }
}

class MessageProcessor {
    private MessageService messageService;
    
    public MessageProcessor(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public void processMessage(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}

public class DependencyInjection {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        MessageProcessor processor = new MessageProcessor(emailService);
        processor.processMessage("Hello!", "user@example.com");
        
        MessageService smsService = new SMSService();
        processor = new MessageProcessor(smsService);
        processor.processMessage("Hello!", "+1234567890");
    }
}
