package com.smallcase.lushuju.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.pojo.entity.ClassToPerson;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.repository.ClassToPersonRepository;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 17:04
 */
@Slf4j
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    private final PersonInfoRepository repository;

    private final ClassToPersonRepository classToPersonRepository;

    @Autowired
    public PersonInfoServiceImpl(ClassToPersonRepository classToPersonRepository, PersonInfoRepository repository) {
        this.classToPersonRepository = classToPersonRepository;
        this.repository = repository;
    }

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

    /**
     * 通过personId数组，查询personInfo数组
     * @param array
     * @return
     */
    @Override
    public List<PersonInfo> findListByids(JSONArray array) throws MyException {
        Iterator<Object> iterator = array.iterator();
        List<PersonInfo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            try {
                list.add(repository.findOne((String) iterator.next()));

            } catch (RuntimeException e) {
                throw new MyException("查询personInfo数组出错，可能personId在数据库中没有");
            }
        }
        return list;
    }

    @Override
    public List<PersonInfo> findAll() {
        return repository.findAll();
    }


    /**
     * 根据病人id检查是否已经录入
     * @param patientId
     * @return
     */
    @Override
    @Deprecated
    public boolean checkExisted(String patientId) {
        List<PersonInfo> list = repository.findByPatientId(patientId);

        /*
        如果数量大于等于1，已经录入了，返回false
         */
        if (list.size() != 0) {
            return true;
        } else return false;
    }


    /**
     * 检查该病人是否有疾病大类相关的信息,有就true，没有就false
     * @param personId
     * @return
     */
    @Override
    public boolean checkBigClassExisted(String personId) {
        List<ClassToPerson> classToPersonList = classToPersonRepository.findByPersonId(personId);
        return classToPersonList.size() != 0;
    }

    @Override
    public String findPersonIdByPatientId(String patientId) {
        List<PersonInfo> personInfoList = repository.findByPatientId(patientId);
        if (personInfoList.size() != 1) {
            return null;
        } else {
            //返回personId
            return personInfoList.get(0).getPersonId();
        }
    }

    /**
     * 根据personId，删除一个病人的全部数据
     * @param personId
     * @return
     */
    @Override
    public void deleteOnePersonByPersonId(String personId) {

        try {
            repository.delete(personId);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据personId查询ClassToPerson列表
     * @param personId
     * @return
     */
    @Override
    public List<ClassToPerson> findClassToPersonByPersonId(String personId) {
        return classToPersonRepository.findByPersonId(personId);
    }

    /**
     * 根据classId查询ClassToPerson列表
     *
     * @param classId
     * @return
     */
    @Override
    public List<ClassToPerson> findClassToPersonByClassId(Integer classId, int pageSize, int pageNum) {

        int offsetValue = calculatePage(pageSize, pageNum);
        return classToPersonRepository.findPagesByClassId(classId, pageSize, offsetValue);
    }


    /**
     * 根据classId查询ClassToPerson列表大小
     * @param classId
     * @return
     */
    @Override
    public int findClassToPersonNumByClassId(Integer classId) {
        return classToPersonRepository.countClassToPersonByClassId(classId);
    }




    /**
     * 根据personId,删除一个病人的所有大类信息
     * @param personId
     */
    @Override
    @Transactional
    public void deleteAllBigClassByPersonId(String personId) throws MyException {

        try {
            classToPersonRepository.deleteSomePersonId(personId);
        } catch (RuntimeException e) {
            throw new MyException("删除personId为" + personId + "的疾病大类信息时出错");
        }



    }

    @Override
    public PersonInfo save(PersonInfo personInfo) throws MyException {

        //TODO 录入主表信息需要录入主类信息
        PersonInfo result;
        try {
            result = repository.save(personInfo);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }

    /**
     * 给一个病人添加疾病大类
     * @param classToPersonList
     */
    @Override
    public void insertBigClass(List<ClassToPerson> classToPersonList) throws MyException {
        try {
            classToPersonRepository.save(classToPersonList);
        } catch (IllegalArgumentException e) {
            throw new MyException("添加疾病出错，保存失败，可能传入的参数不对");
        }
    }


    @Override
    @Transactional
    public PersonInfo edit(PersonInfo form, String personId) throws MyException {
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
    public void insertImg(String imgPath,String fileName,String personId) throws RuntimeException {

        int effectedNum = repository.insertImgPath(imgPath,fileName,personId);
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
    @Deprecated
    public String getFileName(String personId) throws RuntimeException {
        String imgName;
        try {
            imgName = repository.getImgNameByPersonId(personId);
        } catch (RuntimeException e) {
            throw new RuntimeException("下载图片时，根据personId找不到对应的图片地址");
        }
        return imgName;
    }

    private int calculatePage(int pageSize, int pageNum) {
        return (pageNum - 1) * pageSize;
    }

}
