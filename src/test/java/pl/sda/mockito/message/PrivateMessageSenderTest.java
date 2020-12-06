package pl.sda.mockito.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrivateMessageSenderTest {

    private static final String TEXT = "hello";
    private static final String AUTHOR_ID = "Andrzej";
    private static final String RECIPIENT_ID = "Ania";

    @Mock
    private MessageProvider messageProvider;
    @Mock
    private MessageValidator messageValidator;
    @InjectMocks
    private PrivateMessageSender privateMessageSender;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;

    @Test
    public void shouldSendPrivateMessage() {
        // given
        when(messageValidator.isMessageValid(any())).thenReturn(true);
        when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);

        doNothing().when(messageProvider).send(any(), eq(MessageType.PRIVATE));

        // when
        privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        // then
        verify(messageValidator).isMessageValid(any());
        verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        verifyNoMoreInteractions(messageValidator);
        verify(messageProvider).send(any(), eq(MessageType.PRIVATE));
        verifyNoMoreInteractions(messageProvider);

    }

    @Test
    public void shouldSendPrivateMessageWithCaptor() {
        // given
        when(messageValidator.isMessageValid(any())).thenReturn(true);
        when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);

        doNothing().when(messageProvider).send(any(), eq(MessageType.PRIVATE));

        // when
        privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        // then
        verify(messageValidator).isMessageValid(messageCaptor.capture());
        verify(messageProvider).send(messageCaptor.capture(), eq(MessageType.PRIVATE));

        verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        verifyNoMoreInteractions(messageValidator);
        verifyNoMoreInteractions(messageProvider);

        List<Message> messageCaptorAllValues = messageCaptor.getAllValues();
        org.junit.jupiter.api.Assertions.assertEquals(2, messageCaptorAllValues.size());

        for (Message message : messageCaptorAllValues) {
            org.assertj.core.api.Assertions.assertThat(message.getAuthor()).isEqualTo(AUTHOR_ID);
            org.assertj.core.api.Assertions.assertThat(message.getRecipient()).isEqualTo(RECIPIENT_ID);
            org.assertj.core.api.Assertions.assertThat(message.getValue()).isEqualTo(TEXT);
            org.assertj.core.api.Assertions.assertThat(message.getSendAt()).isNotNull();
            org.assertj.core.api.Assertions.assertThat(message.getId()).isNotNull();
        }


    }

}