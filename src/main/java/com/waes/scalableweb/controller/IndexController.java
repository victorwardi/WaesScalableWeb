package com.waes.scalableweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Controller to provide a index page for the application
 *
 * @author Victor Wardi - @victorwardi on 2/9/2020
 *
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String getWelcomePage() {
        return "index.html";
    }
}
