package com.waes.victor.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Controller to provide a index page for the application
 *
 * @author Victor Wardi - @victorwardi on 2/9/2020
 */
@ApiIgnore
@Controller
public class IndexController {

    /**
     * Gets index page.
     *
     * @return the index page
     */
    @RequestMapping("/")
    public String getIndexPage() {
        return "index.html";
    }

    /**
     * Gets index page.
     *
     * @return the index page
     */
    @RequestMapping("/javadocs")
    public String getJavaDocsPage() {
        return "/javadocs/index.html";
    }
}
