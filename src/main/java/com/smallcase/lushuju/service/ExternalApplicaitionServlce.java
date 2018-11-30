package com.smallcase.lushuju.service;

import java.io.IOException;

/**
 * package: com.smallcase.lushuju.service
 * date: 2018/11/18 21:24
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface ExternalApplicaitionServlce {

    void runApplication(String appPath, String filePath) throws IOException;

    String readFilePath(String personId) throws Exception;

    void addFilePath(String personId, String filePath);
}
