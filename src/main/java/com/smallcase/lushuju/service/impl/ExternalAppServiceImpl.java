package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.service.ExternalApplicaitionServlce;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/11/18 21:26
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Service
public class ExternalAppServiceImpl implements ExternalApplicaitionServlce {
    @Override
    public void runApplication(String appPath, String filePath) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(appPath + " " + filePath);
        } catch (IOException e) {
            throw e;
        }

    }
}
