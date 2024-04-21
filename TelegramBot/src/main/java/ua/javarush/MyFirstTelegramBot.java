package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
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
            sendMessage(chatId, 0, "step_1_pic", STEP_1_TEXT, Map.of(
                    "Злам холодильника", FIRST_BUTTON
            ));
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals(FIRST_BUTTON) && getGlories(chatId) == 0) {
                sendMessage(chatId, 20, "step_2_pic", STEP_2_TEXT, Map.of(
                        "Взяти сосиску! +20 слави", SECOND_BUTTON,
                        "Взяти рибку! +20 слави", SECOND_BUTTON,
                        "Скинути банку з огірками! +20 слави", SECOND_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(SECOND_BUTTON) && getGlories(chatId) == 20) {
                sendMessage(chatId, 20, "step_3_pic", STEP_3_TEXT, Map.of(
                        "Злам робота пилососа", THIRD_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(THIRD_BUTTON) && getGlories(chatId) == 40) {
                sendMessage(chatId, 30, "step_4_pic", STEP_4_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", FOURTH_BUTTON,
                        "Проїхатися на робопилососі! +30 слави", FOURTH_BUTTON,
                        "Тікати від робопилососа! +30 слави", FOURTH_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(FOURTH_BUTTON) && getGlories(chatId) == 70) {
                sendMessage(chatId, 30, "step_5_pic", STEP_5_TEXT, Map.of(
                        "Одягнути та включити GoPro!", FIFTH_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(FIFTH_BUTTON) && getGlories(chatId) == 100) {
                sendMessage(chatId, 40, "step_6_pic", STEP_6_TEXT, Map.of(
                        "Бігати дахами, знімати на GoPro! +40 слави", SIXTH_BUTTON,
                        "З GoPro нападати на інших котів із засідки! +40 слави", SIXTH_BUTTON,
                        "З GoPro нападати на собак із засідки! +40 слави", SIXTH_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(SIXTH_BUTTON) && getGlories(chatId) == 140) {
                sendMessage(chatId, 40, "step_7_pic", STEP_7_TEXT, Map.of(
                        "Злам пароля", SEVENTH_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(SEVENTH_BUTTON) && getGlories(chatId) == 180) {
                sendMessage(chatId, 50, "step_8_pic", STEP_8_TEXT, Map.of(
                        "Вийти на подвір'я", EIGHTH_BUTTON
                ));
            }
            if (update.getCallbackQuery().getData().equals(EIGHTH_BUTTON) && getGlories(chatId) == 230) {
                sendMessage(chatId, 70, "final_pic", FINAL_TEXT, Map.of());
            }
        }
    }

    private void sendMessage(Long chatId, int glories, String picName, String text, Map<String, String> buttons) {
        addGlories(chatId, glories);
        SendPhoto photoMessage = createPhotoMessage(chatId, picName);
        executeAsync(photoMessage);

        SendMessage message = createMessage(chatId, text, buttons);
        sendApiMethodAsync(message);
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}