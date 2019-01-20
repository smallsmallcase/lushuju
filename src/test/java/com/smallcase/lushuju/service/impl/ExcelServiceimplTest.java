package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.service.ExcelService;
import com.smallcase.lushuju.utils.excelUtil.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: smallcase
 * @Date: 2019/1/3 15:39
 * @Package com.smallcase.lushuju.service.impl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExcelServiceimplTest {
    @Autowired
    private ExcelService excelService;

    @Test
    public void findDataByPersonIds() {
        List<String> list = new ArrayList<>();
        list.add("2c93439f670c29c401670c2af0560000");
        HSSFWorkbook hssfWorkbook = excelService.findDataByPersonIds(list);
    }
}