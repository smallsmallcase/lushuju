package com.smallcase.lushuju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2018/10/26 16:48
 *控制路由转发
 * @author smallcase
 * @since JDK 1.8
 */
@Controller
public class PathController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)

    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/personInfo", method = RequestMethod.GET)
    public String add() {
        return "personInfo";
    }
}
