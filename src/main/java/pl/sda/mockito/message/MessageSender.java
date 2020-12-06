package pl.sda.mockito.message;

public interface MessageSender {

    void sendPrivateMessage(final String text, final String authorId, final String recipientId);
}
