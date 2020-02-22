package com.tsw.sign.commons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
    @RequestMapping(value = "permission",method = RequestMethod.GET)
    public String permission(){
        return "permission_detail";
    }
}
