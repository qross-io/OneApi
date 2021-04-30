package io.qross.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DocumentController {

    @RequestMapping("/oneapi/docs")
    Object index(Model root, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/oneapi/doc/detail")
    Object detail(Model root, HttpServletRequest request) {
        return "detail";
    }
}
