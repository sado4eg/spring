package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;

/**
 * Created by alex on 13.03.2018.
 */
public interface EventLogger {
    public void logEvent(Event event);
}
