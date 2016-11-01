package com.postnov.android.tfnews.data.source.local;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.util.FileManager;

import java.io.File;

import timber.log.Timber;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsCache implements ICache<News> {

    private final File cachedFile;
    private final ISerializer<News> serializer;
    private final FileManager fileManager;

    public NewsCache(File cacheDir, ISerializer<News> serializer) {

        Timber.tag("NewsCache");
        cachedFile = new File(cacheDir, "news.json");
        this.serializer = serializer;
        fileManager = new FileManager();
    }

    @Override
    public News get() {
        String json = fileManager.readFileContent(cachedFile);
        return serializer.deserialize(json);
    }

    @Override
    public void put(News object) {
        fileManager.writeToFile(cachedFile, serializer.serialize(object));
    }

    @Override
    public boolean delete() {
        return cachedFile.delete();
    }

    @Override
    public boolean isEmpty() {
        return !cachedFile.exists();
    }
}
