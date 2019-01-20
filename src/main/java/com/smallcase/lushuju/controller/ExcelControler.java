package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.service.ExcelService;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import com.smallcase.lushuju.utils.excelUtil.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: smallcase
 * @Date: 2018/12/20 17:37
 * @Package com.smallcase.lushuju.controller
 */

@Controller
public class ExcelControler {
    @Autowired
    private ExcelService excelService;

    @GetMapping(value = "/aa")
    public String f() {
        return "aaaaa";
    }

    @GetMapping(value = "/mmmm")
    @ResponseBody
    public ResponseEntity fff(HttpServletResponse response, @RequestParam(required = false) String sex,
                              @RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge, @RequestParam(required = false) String patientId) {

//        String[] title = {"名字", "性别"};
//        String[][] values = {{"赵四", "男"}, {"张三", "女"}};
//
//        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook("ssst", values, title);


        List<String> list;
        if (patientId != null) {
            list = excelService.findPersonIdsByPatientId(patientId);
        } else {
            if (sex == null) {
                if (minAge == null && maxAge == null) {
                    list = excelService.findPersonIds();

                } else if (minAge != null && maxAge == null) {
                    list = excelService.findPersonIdsByAge1(minAge);

                    //minage空，maxage不空
                } else if (minAge == null) {
                    list = excelService.findPersonIdsByAge2(maxAge);
                } else {//都不是空
                    list = excelService.findPersonIdsByAge3(minAge, maxAge);
                }
            } else {//sex !=null
                list = excelService.findPersonIdsBySex(sex);
            }
        }


        HSSFWorkbook workbook = excelService.findDataByPersonIds(list);

        try {
            ExcelUtil.setResponseHeader(response, System.currentTimeMillis() + ".xls");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return RestfulResult.ok(ResultVOUtil.success());
    }
}
