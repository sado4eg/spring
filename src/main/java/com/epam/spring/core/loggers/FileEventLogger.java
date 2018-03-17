package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by alex on 13.03.2018.
 */
public class FileEventLogger implements EventLogger{

    private String filename;
    protected File file;

    public FileEventLogger(String filename) {
        this.filename = filename;

    }

    public void init() throws IOException{
        this.file = new File(filename);
        file.canWrite();
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file,event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
