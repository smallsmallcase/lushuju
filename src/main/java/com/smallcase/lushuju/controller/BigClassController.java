package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.BigClass;
import com.smallcase.lushuju.service.BigClassService;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2018/11/27 20:24
 *
 * @author smallcase
 * @since JDK 1.8
 */
@RestController
@RequestMapping(path = "/bigclass")
public class BigClassController {
    @Autowired
    private BigClassService bigClassService;


    @PostMapping(value = "/addOne")
    public ResponseEntity addBigClass(@RequestBody BigClass bigClass) {
        if (bigClass.getClassName() == null) {
            return RestfulResult.serviceErr(ResultVOUtil.error("传入的类别名字为空"));
        }
        boolean existed = bigClassService.checkExist(bigClass.getClassName());

        BigClass result;
        //如果存在，只能覆盖
        if (existed) {
            try {
                result = bigClassService.edit(bigClass);
            } catch (MyException e) {
                return RestfulResult.serviceErr(ResultVOUtil.success(e.getMessage()));
            }
        } else {
            try {
                result = bigClassService.insertBigClass(bigClass);
            } catch (Exception e) {
                return RestfulResult.serviceErr(ResultVOUtil.success(e.getMessage()));
            }
        }
        return RestfulResult.ok(ResultVOUtil.success(result));

    }

    @GetMapping(value = "/searchAll")
    public ResponseEntity findAll() {
        List<BigClass> list;
        try {
            list = bigClassService.findAll();
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(list));
    }

}
