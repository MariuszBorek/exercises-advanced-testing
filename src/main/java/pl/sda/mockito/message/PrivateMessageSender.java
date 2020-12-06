package pl.sda.mockito.message;

import pl.sda.mockito.exception.SdaException;

import java.time.LocalDateTime;
import java.util.UUID;

public class PrivateMessageSender implements MessageSender {

    private final MessageProvider messageProvider;
    private final MessageValidator messageValidator;

    public PrivateMessageSender(final MessageProvider messageProvider, final MessageValidator messageValidator) {
        this.messageProvider = messageProvider;
        this.messageValidator = messageValidator;
    }

    public void sendPrivateMessage(final String text, final String authorId, final String recipientId) {
        final Message message = new Message(UUID.randomUUID(), text, LocalDateTime.now(), authorId, recipientId);

        if (!messageValidator.isMessageValid(message) || !messageValidator.isMessageRecipientReachable(recipientId)) {
            throw new SdaException("Cannot send private message. Validation failed");
        }

        messageProvider.send(message, MessageType.PRIVATE);
    }
}
