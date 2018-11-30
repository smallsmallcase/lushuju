package com.smallcase.lushuju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2018/11/30 19:05
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Controller
public class PathController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
