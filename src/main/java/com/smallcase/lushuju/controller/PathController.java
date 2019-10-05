package com.smallcase.lushuju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2019/1/20 16:18
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Controller
@RequestMapping(path = "thy", method = RequestMethod.GET)

public class PathController {
    @RequestMapping(path = "uploadFile")

    public String upload() {
        return "file";
    }

}
