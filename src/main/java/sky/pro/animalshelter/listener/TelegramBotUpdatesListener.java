package sky.pro.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.service.UserService;
import sky.pro.animalshelter.text.Replies;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private Replies replies = new Replies();
    private final TelegramBot telegramBot;


    public TelegramBotUpdatesListener(UserService userService, TelegramBot telegramBot) {
        this.userService = userService;
        this.telegramBot = telegramBot;


    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Handles update:{}", update);
                Message message=update.message();
                Long chatId=message.chat().id();
                String text= message.text();
                String userName = message.chat().username();
                //создаем нового юзера
                User user = new User();
                user.setChatId(chatId);
                user.setName(userName);
                user.setTimeOfRegistration(LocalDateTime.now());
                //определяем, зарегистирован ли уже такой пользователь
                boolean userExist = userService.getAllUsers().contains(user);
                if("/start".equals(text) && !userExist){//посылаем приветствие и выбор собака/кошка
                    userService.save(user);
                    sendMessage(chatId, replies.getHello());
                } else if ("/start".equals(text) && userExist){//только выбор собака/кошка
                    sendMessage(chatId, replies.getUserAlreadyExist());
                } else if ("/кошка".equals(text)){ //выбрали кошку + переход в основное меню
                    user.setDog(false);
                    userService.save(user);
                    sendMessage(chatId, replies.getChooseCat());
                    sendMessage(chatId, replies.getMainManu());
                } else if ("/собака".equals(text)) {//выбрали собаку + переход в основное меню
                    user.setDog(true);
                    userService.save(user);
                    sendMessage(chatId, replies.getChooseDog());
                    sendMessage(chatId, replies.getMainManu());
                } else {//в остальных случаях зовем волонтера
                    sendMessage(chatId, replies.getCallVolunteer());
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(long id, String message) {
        SendMessage sendMessage = new SendMessage(id, "Привет!");
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during message:{}", sendResponse.description());
        }
    }
}




