package sky.pro.animalshelter.util;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageSenderTest {
@Mock
private TelegramBot telegramBot;
@InjectMocks
private MessageSender messageSender;
@Captor
private ArgumentCaptor<SendMessage> captor;

private KeyboardUtil keyboardUtil;
Long id=1L;
String text="Текстовое сообщение пользователю";

    @Test
    @DisplayName("Вывод сообщения")
    void sendMessage() {
        messageSender.sendMessage(id,text);
        verify(telegramBot, times(1).execute(captor.capture()));
        var sendMassage=captor.getValue();
        assertEquals(sendMassage.getParameters().get("text"),text);
        assertEquals(sendMassage.getParameters().get("chat_id"),id);

    }

    @Test
    @DisplayName("Вывод сообщения с клавиатурой")
    void sendMessageWithKeyboard() {
        var keyboard=keyboardUtil.setKeyboard(CallbackDataRequest.SHELTER_CAT,CallbackDataRequest.SHELTER_DOG);
        messageSender.sendMessageWithKeyboard(id,text,keyboard);
        verify(telegramBot,times(1)).execute(captor.capture());

        var sentMessage=captor.getValue();
        assertEquals(sentMessage.getParameters().get("text"),text);
        assertEquals(sentMessage.getParameters().get("chat_id"),id);
        var expectedKeyboard=keyboardUtil.setKeyboard(CallbackDataRequest.SHELTER_CAT,CallbackDataRequest.SHELTER_DOG);
        assertEquals(sentMessage.getParameters().get("reply_markup"),keyboard);
    }
}