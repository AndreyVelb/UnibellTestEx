package ru.unibell.unibelltestex.util;

public class SwaggerMessage {

    public final static String CLIENT_CONTROLLER_NAME = "Клиент";

    public final static String CLIENT_CONTROLLER_DESCRIPTION = "API для работы с информацией клиентов";

    public final static String GET_ALL_CLIENTS_SUMMARY = "Получить информацию о всех клиентах";

    public final static String GET_ALL_CLIENTS_DESCRIPTION = "Возвращает информацию о всех клиентах которые есть в базе";

    public final static String GET_CLIENT_SUMMARY = "Получить информацию о клиенте по id";

    public final static String GET_CLIENT_DESCRIPTION = "Возвращает информацию о клиенте по id";

    public final static String CREATE_CLIENT_SUMMARY = "Создать нового клиента";

    public final static String CREATE_CLIENT_DESCRIPTION = "Создает нового клиента с уникальным именем";

    public final static String CONTACT_CONTROLLER_NAME = "Контакты";

    public final static String CONTACT_CONTROLLER_DESCRIPTION = "API для работы с контактами клиентов";

    public final static String CREATE_CONTACT_SUMMARY = "Создать контакт для клиента";

    public final static String CREATE_CONTACT_DESCRIPTION = "Создает, если контакта с таким именем еще не было, или же обновляет телефон и/или адрес эл. почты, если был";

    public final static String GET_CONTACTS_SUMMARY = "Получить информацию о контактах клиента";

    public final static String GET_CONTACTS_DESCRIPTION = "Возвращает информацию о контактах клиента, чей id указан в пути запроса, " +
            "и в зависимости от того указан ли тип контакта возвращает: " +
            "параметр запроса не указан - все контакты данного клиента; " +
            "параметра запроса PHONE - только телефонные контакты; " +
            "параметр запроса EMAIL - только контакты с эл.почтой; ";


}
