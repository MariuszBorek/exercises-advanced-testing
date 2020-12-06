package pl.sda.mockito.message;

public interface MessageValidator {

    boolean isMessageRecipientReachable(final String recipientId);

    boolean isMessageValid(final Message message);
}
