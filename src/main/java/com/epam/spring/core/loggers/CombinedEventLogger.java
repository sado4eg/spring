package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;

import java.util.Collection;

/**
 * Created by alex on 15.03.2018.
 */
public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(final Event event) {
        loggers.stream().forEach(eventLogger -> eventLogger.logEvent(event));
    }
}
