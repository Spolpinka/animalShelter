package sky.pro.animalshelter.text;

import lombok.Getter;

@Getter
public class Replies {
    public String getHello() {
        return hello;
    }

    private final String hello = """
            Привет!
            Я - бот приюта для животных из Астаны
            Если тебе нужен надежный и ласковый друг наш меньший - то тебе сюда!
            """;

    private final String chooseCat = """
            Поздравляем, ты выбрал кошек!
            """;

    private final String chooseDog = """
            Поздравляем, ты выбрал собак!
            """;

    public String getCallVolunteer() {
        return callVolunteer;
    }

    private final String callVolunteer = """
            зову волонтера
            """;

    private final String dogShelterStory = """
            История приюта для собак
            """;

    private final String catShelterStory = """
            История приюта для кошек
            """;

    private final String catShelterScheduleAndAddress = """
            Расписание и адрес приюта для кошек
            """;

    private final String dogShelterScheduleAndAddress = """
            Расписание и адрес приюта для собак
            """;

    private final String dogSafetyRules = """
            Правила безопасности для собак
            """;

    private final String catSafetyRules = """
            Правила безопасности для кошек
            """;

    private final String userContacts = """
            Введите свои контакты после слова "Контакты"
            """;

    private final String dogMeetingRules = """
            правила знакомства с собакой до того, как забрать его из приюта
            """;

    private final String catMeetingRules = """
            правила знакомства с кошаком до того, как забрать его из приюта
            """;

    private final String docsList = """
            список документов, необходимых для того, чтобы взять животное из приюта.
            """;

    private final String dogMoveRecommendation = """
            список рекомендаций по транспортировке собак
            """;

    private final String catMoveRecommendation = """
            список рекомендаций по транспортировке кошек
            """;

    private final String youngDogHomeRecommendation = """
            список рекомендаций по обустройству дома для щенка
            """;
    private final String youngCatHomeRecommendation = """
            список рекомендаций по обустройству дома для котенка
            """;

    private final String dogHomeRecommendation = """
            список рекомендаций по обустройству дома для взрослой собаки
            """;

    private final String catHomeRecommendation = """
            список рекомендаций по обустройству дома для взрослой кошки
            """;

    private final String dogHomeRecommendationForInvasion = """
            список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение)
            """;

    private final String catHomeRecommendationForInvasion = """
            список рекомендаций по обустройству дома для кошки с ограниченными возможностями (зрение, передвижение)
            """;
    private final String cynologistAdvise = """
            советы кинолога по первичному общению с собакой
            """;

    private final String cynologistList = """
            рекомендации по проверенным кинологам для дальнейшего обращения к ним
            """;

    private final String reasonForRejectionList = """
            список причин, почему могут отказать и не дать забрать собаку из приюта
            """;

    private final String getDogShelterStory= """
            История приюта собак""";


    private final String getCatShelterStory= """
            История приюта кошек""";


    private final String getDogShelterScheduleAndAddress= """
            Адрес приюта собак""";


    private final String getCatShelterScheduleAndAddress= """
            Адрес приюта собак""";


    public String getChooseDog() {
        return chooseDog;
    }

    public String getChooseCat() {
        return chooseCat;
    }

    public String getDogSafetyRules() {
        return dogSafetyRules;
    }

    public String getCatSafetyRules() {
        return catSafetyRules;
    }

    public String getUserContacts() {
        return userContacts;
    }

    public String getDogMeetingRules() {
        return dogMeetingRules;
    }

    public String getCatMeetingRules() {
        return catMeetingRules;
    }

    public String getDocsList() {
        return docsList;
    }

    public String getDogMoveRecommendation() {
        return dogMoveRecommendation;
    }

    public String getCatMoveRecommendation() {
        return catMoveRecommendation;
    }


    public String getYoungDogHomeRecommendation() {
        return youngDogHomeRecommendation;
    }

    public String getYoungCatHomeRecommendation() {
        return youngCatHomeRecommendation;
    }

    public String getDogHomeRecommendation() {
        return dogHomeRecommendation;
    }

    public String getDogHomeRecommendationForInvasion() {
        return dogHomeRecommendationForInvasion;
    }

    public String getCatHomeRecommendationForInvasion() {
        return catHomeRecommendationForInvasion;
    }

    public String getCynologistAdvise() {
        return cynologistAdvise;
    }

    public String getCynologistList() {
        return cynologistList;
    }

    public String getReasonForRejectionList() {
        return reasonForRejectionList;
    }

    public String getDogShelterStory() {
        return dogShelterStory;
    }

    private final String  setDogShelterStory = """
            рекомендации по проверенным кинологам для дальнейшего обращения к ним
            """;

    public String getCatShelterStory() {return getCatShelterStory;
    }

    public String getDogShelterScheduleAndAddress() { return getDogShelterScheduleAndAddress;
    }

    public String getCatShelterScheduleAndAddress() { return getCatShelterScheduleAndAddress;
    }
}
