package ru.javakira.rucscheduleapi.parser;

public class ServerNotRespondingException extends ScheduleParserException {
    public ServerNotRespondingException() {
        super();
    }

    public ServerNotRespondingException(Throwable cause) {
        super(cause);
    }
}
