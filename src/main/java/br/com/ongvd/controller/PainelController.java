package br.com.ongvd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PainelController {

    @RequestMapping(method = RequestMethod.GET, path = "/painel/ong")
    public String painelOng() {
        return "painel/ong";
    }
}
