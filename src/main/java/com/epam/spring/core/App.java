package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.enums.EventType;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by alex on 13.03.2018.
 */
public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext ctx;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String args[]) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent(EventType.INFO, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(null, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 1");

        ctx.close();
    }

    public void logEvent(EventType type, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }


}
