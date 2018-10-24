package com.smallcase.lushuju.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.repository.PersonInfoRepository;
import com.smallcase.lushuju.service.PersonInfoService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 17:04
 */
@Slf4j
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoRepository repository;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public PersonInfo findOne(String id) throws Exception {
        PersonInfo personInfo;
        try {
            personInfo = repository.findOne(id);

        } catch (Exception e) {
            throw new MyException(e.getMessage() + "个人信息找不到");
        }
        if (personInfo == null) {
            throw new NoDataException("个人信息找不到");
        }
        return personInfo;

    }

    @Override
    public List<PersonInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonInfo save(PersonInfo personInfo) throws MyException {
        PersonInfo result;
        try {
             result= repository.save(personInfo);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }


    @Override
    @Transactional
    public PersonInfo edit(PersonInfoForm form, String personId) throws MyException {
        PersonInfo personInfo;
        try {
            personInfo = repository.findOne(personId);
            BeanUtil.copyPropertiesIgnoreNull(form, personInfo);
            personInfo = repository.save(personInfo);
            if (personInfo == null) {
                log.error("【修改数据】:PersonInfo，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        return personInfo;
    }

    @Override
    @Transactional
    public void insertImg(String imgPath, String fileName,String personId) throws RuntimeException {

        int effectedNum = repository.insertImgPath(imgPath, fileName,personId);
        if (effectedNum != 1) {
            throw new RuntimeException("插入图片失败");
        }
    }

    @Override
    public String getImgPath(String personId) throws RuntimeException {

        String imgPath;
        try {
            imgPath = repository.getImgPathByPersonId(personId);
        } catch (RuntimeException e) {
            throw new RuntimeException("下载图片时，根据personId找不到对应的图片地址");
        }
        return imgPath;
    }

    @Override
    public String getFileName(String personId) throws RuntimeException {
        String imgName;
        try {
            imgName = repository.getImgNameByPersonId(personId);
        } catch (RuntimeException e) {
            throw new RuntimeException("下载图片时，根据personId找不到对应的图片地址");
        }
        return imgName;
    }

}
