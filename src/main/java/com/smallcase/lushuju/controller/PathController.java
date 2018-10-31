package com.smallcase.lushuju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2018/10/31 20:23
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Controller
public class PathController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)

    public String index() {
        return "index";
    }


}
