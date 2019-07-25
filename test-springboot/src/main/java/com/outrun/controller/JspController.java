package com.outrun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by outrun on 7/24/19.
 */
@Controller
public class JspController {
    @RequestMapping("/jsp")
    public String jsp(Model m) {
        m.addAttribute("a", 1);
        return "hello";
    }
}
