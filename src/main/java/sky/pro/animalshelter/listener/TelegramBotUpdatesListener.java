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
import org.jetbrains.annotations.NotNull;
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

    private final Replies replies = new Replies();
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
                Message message = update.message();


                //реакции на кнопки в меню
                if (update.callbackQuery() != null) {
                    String callbackData = update.callbackQuery().data();
                    long chatId = update.callbackQuery().message().chat().id();
                    User user = userService.getByChatId(chatId);

                    if ("shelterInfo".equals(callbackData)) {//переход в меню 1
                        sendMessage(chatId, null, update, 1);
                    } else if ("adoptionInfo".equals(callbackData)) { //переход в меню 2
                        sendMessage(chatId, null, update, 2);
                    } else if ("petReport".equals(callbackData)) {//переход в меню 3
                        sendMessage(chatId, null, update, 3);
                    } else if ("volunteer".equals(callbackData)) {//зовем волонтера
                        messageWasRead(update);
                        sendMessage(chatId, "Волонтёёр! Волонтёёооор!!!! Ты тут?");
                    } else if ("needDog".equals(callbackData)) {//выбрали собаку
                        messageWasRead(update);
                        user.setDog(true);
                        userService.save(user);
                        sendMessage(chatId, replies.getChooseDog());
                        setMenuStage0(chatId);
                    } else if ("needCat".equals(callbackData)) {//выбрали кошку
                        messageWasRead(update);
                        user.setDog(false);
                        userService.save(user);
                        sendMessage(chatId, replies.getChooseCat());
                        setMenuStage0(chatId);
                    }
                    //начало меню 1
                    else if ("shelterStory".equals(callbackData) && user.isDog()) {//история приюта для собак
                        sendMessage(chatId, replies.getDogShelterStory(), update, 1);
                    } else if ("shelterStory".equals(callbackData)) { // история приюта для кошек
                        sendMessage(chatId, replies.getCatShelterStory(), update, 1);
                    } else if ("shelterScheduleAndAddress".equals(callbackData) && user.isDog()) {//расписание для собак
                        sendMessage(chatId, replies.getDogShelterScheduleAndAddress(), update, 1);
                    } else if ("shelterScheduleAndAddress".equals(callbackData)) {//расписание для кошек
                        sendMessage(chatId, replies.getCatShelterScheduleAndAddress(), update, 1);
                    } else if ("safetyRules".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getDogSafetyRules(), update, 1);
                    } else if ("safetyRules".equals(callbackData)) {
                        sendMessage(chatId, replies.getCatSafetyRules(), update, 1);
                    } else if ("userContacts".equals(callbackData)) {
                        sendMessage(chatId, replies.getUserContacts(), update, 1);
                        // по хорошему надо переходит в отдельный метод и парсить контакты
                    } else if ("toMainMenu".equals(callbackData)) {
                        sendMessage(chatId, null, update, 0);
                    }
                    //начало меню 2
                    else if ("meetingRules".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getDogMeetingRules(), update, 2);
                    } else if ("meetingRules".equals(callbackData)) {
                        sendMessage(chatId, replies.getCatMeetingRules(), update, 2);
                    } else if ("docsList".equals(callbackData)) {
                        sendMessage(chatId, replies.getDocsList(), update, 2);
                    } else if ("moveRecommendation".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getDogMoveRecommendation(), update, 2);
                    } else if ("moveRecommendation".equals(callbackData)) {
                        sendMessage(chatId, replies.getCatMoveRecommendation(), update, 2);
                    } else if ("youngAnimalHomeRecommendation".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getYoungDogHomeRecommendation(), update, 2);
                    } else if ("youngAnimalHomeRecommendation".equals(callbackData)) {
                        sendMessage(chatId, replies.getYoungCatHomeRecommendation(), update, 2);
                    } else if ("homeRecommendation".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getDogHomeRecommendation(), update, 2);
                    } else if ("homeRecommendationForInvasion".equals(callbackData) && user.isDog()) {
                        sendMessage(chatId, replies.getDogHomeRecommendationForInvasion(), update, 2);
                    } else if ("homeRecommendationForInvasion".equals(callbackData)) {
                        sendMessage(chatId, replies.getCatHomeRecommendationForInvasion(), update, 2);
                    } else if ("cynologistAdvise".equals(callbackData)) {
                        sendMessage(chatId, replies.getCynologistAdvise(), update, 2);
                    } else if ("cynologistList".equals(callbackData)) {
                        sendMessage(chatId, replies.getCynologistList(), update, 2);
                    } else if ("reasonList".equals(callbackData)) {
                        sendMessage(chatId, replies.getReasonForRejectionList(), update, 2);
                    }
                    //начало меню 3
                    else if ("getReportForm".equals(callbackData)) {
                        sendMessage(chatId, "здесь должна быть форма отчета", update, 3);
                    }

                }


                //реакции на сообщения /start и произвольное
                if (message != null) {
                    Long chatId = message.chat().id();
                    String text = message.text();

                    //определяем, зарегистирован ли уже такой пользователь
                    User userTmp = userService.getByChatId(chatId);
                    if ("/start".equals(text) && userTmp != null) {//если старт и нет такого ползователя
                        //устанавливаем имя и время регистрации пользователя
                        userTmp = new User();
                        String userName = message.chat().firstName() + " " + message.chat().lastName();
                        userTmp.setChatId(chatId);
                        userTmp.setName(userName);
                        userTmp.setTimeOfRegistration(LocalDateTime.now());
                        //сохраняем нового пользователя
                        userService.save(userTmp);
                        //посылаем приветствие и выбор собака/кошка
                        sendMessage(chatId, replies.getHello());
                        setDogCatMenu(chatId);
                    } else if ("/start".equals(text)) {//только выбор собака/кошка
                        setDogCatMenu(chatId);
                    } else {//в остальных случаях зовем волонтера, но неплохо бы запилить простейший анализ
                        // вводимого сообщения на знакомые слова и соответствующие реакции
                        sendMessage(chatId, replies.getCallVolunteer());
                    }
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * стандартный метод для отправки сообщения пользователю
     *
     * @param id      чат Id
     * @param message текст сообщения
     */
    private void sendMessage(long id, String message) {
        SendMessage sendMessage = new SendMessage(id, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during message:{}", sendResponse.description());
        }
    }

    /**
     * перегрузка метода для отправки
     *
     * @param id      чат Id
     * @param message сообщение
     * @param update  объект update
     * @param i       уровень меню куда переходить
     */
    private void sendMessage(long id, String message, Update update, int i) {
        messageWasRead(update);
        if (message != null) {
            sendMessage(id, message);
        }
        switch (i) {
            case 0 -> setMenuStage0(id);
            case 1 -> setMenuStage1(id);
            case 2 -> setMenuStage2(id);
            case 3 -> setMenuStage3(id);
        }
    }

    /**
     * меню выбора между собаками и кошками
     *
     * @param chatId чат Id
     */
    private void setDogCatMenu(long chatId) {
        String menu = "Выберите, кого вы хотите приютить:";
        logger.info("Set choose between dogs and cats for chat {}", chatId);

        //кнопки выбора собак или кошек
        InlineKeyboardButton needDog = new InlineKeyboardButton("Собаки верные друзья!").callbackData("needDog");
        InlineKeyboardButton needCat = new InlineKeyboardButton("Милее кошки только 2 кошки!").callbackData("needCat");

        //формируем клавиатуру, каждый массив - новая строка
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                needDog, needCat);
        //отправка
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

    /**
     * вывод главного меню
     *
     * @param chatId чат Id
     */
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

    /**
     * вывод меню 1
     *
     * @param chatId чат Id
     */
    private void setMenuStage1(long chatId) {
        String menu = "Информация о приютах:";
        logger.info("Set menu stage 1 for chat:{}", chatId);
        //формируем кнопки
        InlineKeyboardButton shelterInfo = new InlineKeyboardButton("Рассказ о приюте")
                .callbackData("shelterStory");
        InlineKeyboardButton shelterSchedule = new InlineKeyboardButton("Расписание работы приюта и адрес")//не забыть схему проезда
                .callbackData("shelterScheduleAndAddress");
        InlineKeyboardButton shelterContacts = new InlineKeyboardButton("Контакты охраны для оформления пропуска")
                .callbackData("shelterContacts");
        InlineKeyboardButton safety = new InlineKeyboardButton("Техника безопасности на территории приюта")
                .callbackData("safetyRules");
        InlineKeyboardButton userContacts = new InlineKeyboardButton("Оставить контакты для связи")
                .callbackData("userContacts");
        InlineKeyboardButton volunteer = new InlineKeyboardButton("Позвать волонтера")
                .callbackData("volunteer");
        InlineKeyboardButton returnToMainMenu = new InlineKeyboardButton("Вернутся в главное меню")
                .callbackData("toMainMenu");

        //формируем клавиатуру, каждый массив - новая строка
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{shelterInfo},
                new InlineKeyboardButton[]{shelterSchedule},
                new InlineKeyboardButton[]{shelterContacts},
                new InlineKeyboardButton[]{safety},
                new InlineKeyboardButton[]{userContacts},
                new InlineKeyboardButton[]{volunteer},
                new InlineKeyboardButton[]{returnToMainMenu}
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

    /**
     * вывод меню 2
     *
     * @param chatId чат Id
     */
    private void setMenuStage2(long chatId) {
        String menu = "Консультация с потенциальным новым хозяином:";
        logger.info("Set menu stage 2 for chat:{}", chatId);
        //формируем кнопки
        InlineKeyboardButton meetingRules = new InlineKeyboardButton("Правила знакомства")
                .callbackData("meetingRules");
        InlineKeyboardButton docsList = new InlineKeyboardButton("Список необходимых документов")//не забыть схему проезда
                .callbackData("docsList");
        InlineKeyboardButton moveRecommendation = new InlineKeyboardButton("Список рекомендаций по транспортировке")
                .callbackData("moveRecommendation");
        InlineKeyboardButton youngAnimalHomeRecommendation = new InlineKeyboardButton("Список рекомендаций по обустройству дома " +
                "для щенка/котенка")
                .callbackData("youngAnimalHomeRecommendation");
        InlineKeyboardButton homeRecommendation = new InlineKeyboardButton("Список рекомендаций по обустройству дома " +
                "для взрослого животного")
                .callbackData("homeRecommendation");
        InlineKeyboardButton homeRecommendationForInvasion = new InlineKeyboardButton("список рекомендаций " +
                "по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)")
                .callbackData("homeRecommendationForInvasion");

        //только для собаки
        InlineKeyboardButton cynologistAdvise = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой")
                .callbackData("cynologistAdvise");
        InlineKeyboardButton cynologistList = new InlineKeyboardButton("рекомендации по проверенным кинологам " +
                "для дальнейшего обращения к ним")
                .callbackData("cynologistList");

        //дальше опять общий список

        InlineKeyboardButton reasonList = new InlineKeyboardButton("Список оснований для отказа")
                .callbackData("reasonList");
        InlineKeyboardButton userContacts = new InlineKeyboardButton("Оставить контакты для связи")
                .callbackData("userContacts");

        InlineKeyboardButton volunteer = new InlineKeyboardButton("Позвать волонтера")
                .callbackData("volunteer");
        InlineKeyboardButton returnToMainMenu = new InlineKeyboardButton("Вернутся в главное меню")
                .callbackData("toMainMenu");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        //формируем клавиатуру для собачников
        if (userService.getByChatId(chatId).isDog()) {
            markup = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]{meetingRules},
                    new InlineKeyboardButton[]{docsList},
                    new InlineKeyboardButton[]{moveRecommendation},
                    new InlineKeyboardButton[]{youngAnimalHomeRecommendation},
                    new InlineKeyboardButton[]{homeRecommendation},
                    new InlineKeyboardButton[]{homeRecommendationForInvasion},
                    new InlineKeyboardButton[]{cynologistAdvise},
                    new InlineKeyboardButton[]{cynologistList},
                    new InlineKeyboardButton[]{reasonList},
                    new InlineKeyboardButton[]{userContacts},
                    new InlineKeyboardButton[]{volunteer},
                    new InlineKeyboardButton[]{returnToMainMenu}
            );
        } else {//для кошатников
            markup = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]{meetingRules},
                    new InlineKeyboardButton[]{docsList},
                    new InlineKeyboardButton[]{moveRecommendation},
                    new InlineKeyboardButton[]{youngAnimalHomeRecommendation},
                    new InlineKeyboardButton[]{homeRecommendation},
                    new InlineKeyboardButton[]{homeRecommendationForInvasion},
                    new InlineKeyboardButton[]{reasonList},
                    new InlineKeyboardButton[]{userContacts},
                    new InlineKeyboardButton[]{volunteer},
                    new InlineKeyboardButton[]{returnToMainMenu}
            );
        }
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

    /**
     * вывод меню 3
     *
     * @param chatId чат Id
     */
    private void setMenuStage3(long chatId) {
        String menu = "Ведение питомца:";
        logger.info("Set menu stage 3 for chat:{}", chatId);
        //формируем кнопки
        InlineKeyboardButton getReportForm = new InlineKeyboardButton("Получить форму отчета")
                .callbackData("getReportForm");

        InlineKeyboardButton volunteer = new InlineKeyboardButton("Позвать волонтера")
                .callbackData("volunteer");
        InlineKeyboardButton returnToMainMenu = new InlineKeyboardButton("Вернутся в главное меню")
                .callbackData("toMainMenu");

        //формируем клавиатуру, каждый массив - новая строка
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{getReportForm},

                new InlineKeyboardButton[]{volunteer},
                new InlineKeyboardButton[]{returnToMainMenu}
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

    private void messageWasRead(@NotNull Update update) {
        logger.info("Message was read");
        telegramBot.execute(new AnswerCallbackQuery(update.callbackQuery().id()));
    }
}




