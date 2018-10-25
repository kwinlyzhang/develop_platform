package com.alyer.modules.oss.cloud;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class LocalFileStorageService extends CloudStorageService {

    private static final Logger logger = LoggerFactory.getLogger(LocalFileStorageService.class);

    public LocalFileStorageService(CloudStorageConfig config){
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        final String fileStoreFSPath = config.getFileStorePath();
        final File tmpFile = new File(fileStoreFSPath + "/" + path);

        final File tmpPath = tmpFile.getParentFile();
        if (tmpPath.exists() == false) {
            tmpPath.mkdirs();
        }

        try (OutputStream outputStream = new FileOutputStream(tmpFile);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);) {
            IOUtils.copy(bufferedInputStream, bufferedOutputStream);
        } catch (final IOException ex) {
            logger.error("文件系统存储文件发生错误！", ex);
        }

        return fileStoreFSPath + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getFileStorePrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getFileStorePrefix(), suffix));
    }
}
