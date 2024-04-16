package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static ua.javarush.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "GameWrittenByAnnaBot";
    }

    @Override
    public String getBotToken() {
        return "6060195892:AAEnwVakPwTVAPPDSTuP9JvN33625qTbvP4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            String welcomeText = "Привіт, майбутній програміст!";
            SendMessage message = createMessage(chatId, welcomeText);
            sendApiMethodAsync(message);
        }

        if (update.hasMessage() && update.getMessage().getText().toLowerCase().contains("привіт")) {
            SendMessage message = createMessage(chatId, "Привіт, як тебе звуть?");
            sendApiMethodAsync(message);
        }

        if (update.hasMessage() && update.getMessage().getText().toLowerCase().contains("мене звуть")) {
            SendMessage message = createMessage(chatId, "Радий знайомству, я — *Кіт*");
            sendApiMethodAsync(message);
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}