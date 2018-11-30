package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.repository.PersonInfoRepository;
import com.smallcase.lushuju.service.ExternalApplicaitionServlce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PersonInfoRepository personInfoRepository;

    @Override
    public void runApplication(String appPath, String filePath) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(appPath + " " + filePath);
        } catch (IOException e) {
            throw e;
        }

    }

    /**
     * 读取外部文件的地址
     * @param personId
     * @return
     */
    @Override
    public String readFilePath(String personId) throws Exception {
        String filepath = personInfoRepository.getFilePathByPersonId(personId);
        if (filepath == null) {
            throw new Exception("数据库中文件路径找不到");
        }
        return filepath;
    }


    /**
     * 添加外部文件的地址
     *
     * @param personId
     */
    @Override
    @Transactional
    public void addFilePath(String personId, String filePath) {
        personInfoRepository.insertFilePath(filePath, personId);
    }
}
