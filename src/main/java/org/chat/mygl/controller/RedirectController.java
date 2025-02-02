package org.chat.mygl.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {
    @GetMapping("/")
    @Hidden
    public RedirectView redirectViewSwagger(){
        return new RedirectView("/swagger-ui/index.html");
    }

}
