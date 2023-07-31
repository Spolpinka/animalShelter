package sky.pro.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
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

                if (update.callbackQuery() != null) {
                    String callbackData = update.callbackQuery().data();
                    long chatId = update.callbackQuery().message().chat().id();
                    if ("shelterInfo".equals(callbackData)) {

                        sendMessage(chatId, "Много разнооборазной и интересной информации о приюте! Stage 1 в общем-то");
                        messageWasRead(update);//чтобы кнопка не висла
                    } else if ("adoptionInfo".equals(callbackData)) {

                        sendMessage(chatId, "Тут переход в меню stage 2");
                        messageWasRead(update);
                    } else if ("petReport".equals(callbackData)) {
                        sendMessage(chatId, "Тут, понятное дело, в stage 3");
                        messageWasRead(update);
                    } else if ("volunteer".equals(callbackData)) {
                        sendMessage(chatId, "Волонтёёр! Волонтёёёёёр!!!! Ты тут?");
                        messageWasRead(update);
                    }
                }

                Message message = update.message();
                Long chatId = message.chat().id();
                String text = message.text();
                String userName = message.chat().username();
                //создаем нового юзера
                User user = new User();
                user.setChatId(chatId);
                user.setName(userName);
                user.setTimeOfRegistration(LocalDateTime.now());
                //определяем, зарегистирован ли уже такой пользователь
                boolean userExist = userService.getAllUsers().contains(user);
                if ("/start".equals(text) && !userExist) {//посылаем приветствие и выбор собака/кошка
                    userService.save(user);
                    sendMessage(chatId, replies.getHello());
                } else if ("/start".equals(text)) {//только выбор собака/кошка
                    sendMessage(chatId, replies.getUserAlreadyExist());
                } else if ("/кошка".equals(text)) { //выбрали кошку + переход в основное меню
                    user.setDog(false);
                    userService.save(user);
                    sendMessage(chatId, replies.getChooseCat());
                    setMenuStage0(chatId);
                } else if ("/собака".equals(text)) {//выбрали собаку + переход в основное меню
                    user.setDog(true);
                    userService.save(user);
                    sendMessage(chatId, replies.getChooseDog());
                    setMenuStage0(chatId);
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
        SendMessage sendMessage = new SendMessage(id, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during message:{}", sendResponse.description());
        }
    }

    private void setMenuStage0(long chatId) {
        String menu = "Главное меню";
        logger.info("Set menu stage 0 for chat:{}", chatId);
        //формируем кнопки
        InlineKeyboardButton shelterInfo = new InlineKeyboardButton("Узнать информацию о приюте").callbackData("shelterInfo");
        InlineKeyboardButton adoptionInfo = new InlineKeyboardButton("Как взять животное из приюта").callbackData("adoptionInfo");
        InlineKeyboardButton petReport = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("petReport");
        InlineKeyboardButton volunteer = new InlineKeyboardButton("Позвать волонтера").callbackData("volunteer");

        //формируем клавиатуру, каждый массив - новая строка
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{shelterInfo},
                new InlineKeyboardButton[]{adoptionInfo},
                new InlineKeyboardButton[]{petReport},
                new InlineKeyboardButton[]{volunteer}
        );
        //посылаем сообщение
        SendMessage sendMessage = new SendMessage(chatId, menu)
                .parseMode(ParseMode.MarkdownV2)
                .replyMarkup(markup);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error sending message to user with id: {}, error: {}",
                    chatId,
                    sendResponse.errorCode());
        }

    }

    private void messageWasRead(Update update) {
        logger.info("Message was read");
        telegramBot.execute(new AnswerCallbackQuery(update.callbackQuery().id()));
    }
}




