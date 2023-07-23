package sky.pro.animalshelter.text;

import lombok.Data;

@Data
public class Replies {
    private String hello = """
            Привет!
            Я - бот приюта для животных из Астаны
            Если тебе нужен надежный и ласковый друг наш меньший - то тебе сюда
            Но сначала выбери нужна ли тебе
            /собака или
            /кошка
            Если в дальнейшем ты захочешь сменить этот выбор, просто еще раз напиши /start
            """;

    private String userAlreadyExist = """
            вы уже зарегистрированы в системе
            выбери нужна ли тебе
            /собака или
            /кошка
            """;

    private String chooseCat = """
            Поздравляю, ты выбрал кошек!
            """;

    private String chooseDog = """
            Поздравляю, ты выбрал собак!
            """;

    private String mainManu = """
            С каким вопросом ты пришел?
            /узнать информацию о приюте
            /как взять животное из приюта
            /прислать отчет о питомце
            /позвать волонтера
            """;

    private String callVolunteer = """
            зову волонтера
            """;
}
