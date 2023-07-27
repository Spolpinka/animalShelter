package sky.pro.animalshelter.config;

import org.springframework.beans.factory.annotation.Value;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfiguration {
    TelegramBot bot = new TelegramBot("BOT_TOKEN");

    @Bean
    public TelegramBot telegramBot(@Value("{telegram.bot.token}") String token) {
        return new TelegramBot(token);
    }
}
