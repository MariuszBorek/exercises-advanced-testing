package pl.sda.mockito.message;

public interface MessageProvider {

    void send(final Message message, final MessageType messageType);
}
