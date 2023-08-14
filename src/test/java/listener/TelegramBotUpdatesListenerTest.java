package listener;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.animalshelter.listener.TelegramBotUpdatesListener;
import sky.pro.animalshelter.service.CatService;
import sky.pro.animalshelter.service.DogService;
import sky.pro.animalshelter.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    private TelegramBot telegramBot;
    @Mock
   private CatService  catService;
    @Mock
    private DogService dogService;
    @Mock
    private UserService userService;
    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;
    @Test
    public void handleStartTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update= BotUtils.fromJson(json.replace("%text","volunteer"),Update.class);
        SendResponse sendResponse= BotUtils.fromJson("""
        {
        "ok":true
        }""",SendResponse.class);

        telegramBotUpdatesListener.process(Collections.singletonList(update));
        when(telegramBot.execute(any())).thenReturn(sendResponse);



        ArgumentCaptor<SendMessage> argumentCaptor=ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual= argumentCaptor.getValue();
        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(update.message("Волонтёёр! Волонтёёооор!!!! Ты тут?"));
    }
    @Test
    public void handleReportFormTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update= BotUtils.fromJson(json.replace("%text","getReportForm"),Update.class);
        SendResponse sendResponse= BotUtils.fromJson("""
        {
        "ok":true
        }""",SendResponse.class);

        telegramBotUpdatesListener.process(Collections.singletonList(update));
        when(telegramBot.execute(any())).thenReturn(sendResponse);



        ArgumentCaptor<SendMessage> argumentCaptor=ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual= argumentCaptor.getValue();
        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(update.message("здесь должна быть форма отчета"));
    }
}
