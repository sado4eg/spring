package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 13.03.2018.
 */
public class CacheFileLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void init() throws IOException {
        super.init();
        cache = new LinkedList<Event>();
    }


    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() >= cacheSize) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
            cache.stream().forEach(event -> super.logEvent(event));
            cache.clear();
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

}
