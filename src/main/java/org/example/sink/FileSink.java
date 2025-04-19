package org.example.sink;

import org.example.LogLevel;
import org.example.LogMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public class FileSink implements Sink {
    private final Set<LogLevel> supportedLevels;
    private final String filePath;
    private final long maxSizeInBytes = 1024; // 1KB for demo
    private final String timestampFormat;

    public FileSink(Set<LogLevel> levels, String filePath, String timestampFormat) {
        this.supportedLevels = levels;
        this.filePath = filePath;
        this.timestampFormat = timestampFormat;
        new File(filePath).getParentFile().mkdirs();
    }

    @Override
    public void publish(LogMessage message) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.length() >= maxSizeInBytes) {
                rotateLogs(file);
            }
            try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(message.format());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rotateLogs(File file) throws IOException {
        for (int i = 5; i >= 1; i--) {
            File oldFile = new File(filePath + "." + i + ".gz");
            if (oldFile.exists()) {
                if (i == 5) {
                    oldFile.delete();
                } else {
                    File newFile = new File(filePath + "." + (i + 1) + ".gz");
                    oldFile.renameTo(newFile);
                }
            }
        }

        File rotated = new File(filePath + ".1");
        Files.move(file.toPath(), rotated.toPath(), StandardCopyOption.REPLACE_EXISTING);

        try (FileInputStream fis = new FileInputStream(rotated);
             FileOutputStream fos = new FileOutputStream(rotated.getPath() + ".gz");
             GZIPOutputStream gzos = new GZIPOutputStream(fos)) {
            fis.transferTo(gzos);
        }

        rotated.delete();
    }

    @Override
    public boolean supports(LogLevel level) {
        return supportedLevels.contains(level);
    }

    @Override
    public String getTimestampFormat() {
        return timestampFormat;
    }
}
