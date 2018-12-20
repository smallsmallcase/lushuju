package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import com.smallcase.lushuju.utils.excelUtil.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: smallcase
 * @Date: 2018/12/20 17:37
 * @Package com.smallcase.lushuju.controller
 */

@Controller
public class ExcelControler {

    @GetMapping(value = "/aa")
    public String f() {
        return "aaaaa";
    }
    @GetMapping(value = "/mmmm")
    @ResponseBody
    public ResponseEntity fff(HttpServletResponse response) {

        String[] title = {"名字", "性别"};
        String[][] values = {{"赵四", "男"}, {"张三", "女"}};

        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook("ssst", values, title);

        try {
            ExcelUtil.setResponseHeader(response, "2222");
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
