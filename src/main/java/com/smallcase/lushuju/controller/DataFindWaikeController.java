package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.service.PersonInfoService;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/18 21:37
 */

@Slf4j
@RestController
@RequestMapping(path = "/waike")
public class DataFindWaikeController {
    @Autowired
    private PersonInfoService personInfoService;


    @GetMapping(value = "/search/{id}/personInfo")
    public ResultVO<PersonInfo> findPersonInfo(@PathVariable String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("personId", id);
        PersonInfo personInfo = personInfoService.findOne(id);
        return ResultVOUtil.success(personInfo);
    }

    @PatchMapping(value = "edit/{id}/personInfo")
    public ResponseEntity editPersonInfo(@PathVariable("id") String personId, @RequestBody PersonInfoForm form){
//        HttpSession session = request.getSession();
//        String personId = (String) session.getAttribute("personId");
        try {
            personInfoService.edit(form, personId);

        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }

        return RestfulResult.createSuccess();
    }

}
