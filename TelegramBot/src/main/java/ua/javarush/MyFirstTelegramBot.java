package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ua.javarush.TelegramBotContent.*;
import static ua.javarush.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    private static final String FIRST_BUTTON = "step_1_btn";
    private static final String SECOND_BUTTON = "step_2_btn";
    private static final String THIRD_BUTTON = "step_3_btn";
    private static final String FOURTH_BUTTON = "step_4_btn";
    private static final String FIFTH_BUTTON = "step_5_btn";
    private static final String SIXTH_BUTTON = "step_6_btn";
    private static final String SEVENTH_BUTTON = "step_7_btn";
    private static final String EIGHTH_BUTTON = "step_8_btn";

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
            addGlories(chatId, 0);
            SendMessage message = createMessage(chatId, STEP_1_TEXT, Map.of(
                    "Злам холодильника", FIRST_BUTTON
            ));
            sendApiMethodAsync(message);
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals(FIRST_BUTTON) && getGlories(chatId) == 0) {
                addGlories(chatId, 20);
                SendMessage message = createMessage(chatId, STEP_2_TEXT, Map.of(
                        "Взяти сосиску! +20 слави", SECOND_BUTTON,
                        "Взяти рибку! +20 слави", SECOND_BUTTON,
                        "Скинути банку з огірками!", SECOND_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(SECOND_BUTTON) && getGlories(chatId) == 20) {
                addGlories(chatId, 20);
                SendMessage message = createMessage(chatId, STEP_3_TEXT, Map.of(
                        "Злам робота пилососа", THIRD_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(THIRD_BUTTON) && getGlories(chatId) == 40) {
                addGlories(chatId, 30);
                SendMessage message = createMessage(chatId, STEP_4_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", FOURTH_BUTTON,
                        "Проїхатися на робопилососі! +30 слави", FOURTH_BUTTON,
                        "Тікати від робопилососа! +30 слави", FOURTH_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(FOURTH_BUTTON) && getGlories(chatId) == 70) {
                addGlories(chatId, 30);
                SendMessage message = createMessage(chatId, STEP_5_TEXT, Map.of(
                        "Одягнути та включити GoPro!", FIFTH_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(FIFTH_BUTTON) && getGlories(chatId) == 100) {
                addGlories(chatId, 40);
                SendMessage message = createMessage(chatId, STEP_6_TEXT, Map.of(
                        "Бігати дахами, знімати на GoPro! +40 слави", SIXTH_BUTTON,
                        "З GoPro нападати на інших котів із засідки! +40 слави", SIXTH_BUTTON,
                        "З GoPro нападати на собак із засідки! +40 слави", SIXTH_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(SIXTH_BUTTON) && getGlories(chatId) == 140) {
                addGlories(chatId, 40);
                SendMessage message = createMessage(chatId, STEP_7_TEXT, Map.of(
                        "Злам пароля", SEVENTH_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(SEVENTH_BUTTON) && getGlories(chatId) == 180) {
                addGlories(chatId, 50);
                SendMessage message = createMessage(chatId, STEP_8_TEXT, Map.of(
                        "Вийти на подвір'я", EIGHTH_BUTTON
                ));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals(EIGHTH_BUTTON) && getGlories(chatId) == 230) {
                addGlories(chatId, 70);
                SendMessage message = createMessage(chatId, FINAL_TEXT);
                sendApiMethodAsync(message);
            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}