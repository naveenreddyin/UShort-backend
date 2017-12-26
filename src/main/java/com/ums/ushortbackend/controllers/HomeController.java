package com.ums.ushortbackend.controllers;

import com.ums.ushortbackend.domains.ShortURL;
import com.ums.ushortbackend.repositories.ShortURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Naveen on 15/07/2017.
 */
@Controller
public class HomeController {

    @Autowired
    ShortURLRepository shortURLRepository;

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/goto/{code}")
    public RedirectView destination(@PathVariable String code){
        System.out.println("CODE : "+code);
        ShortURL shortURL = shortURLRepository.findByCode(code);

        int count = shortURL.getCount();
        count += 1;
        shortURL.setCount(count);
        ShortURL updatedShortURL = shortURLRepository.save(shortURL);
        System.out.println(updatedShortURL.getCount());
        return new RedirectView(updatedShortURL.getHttpURL());
    }

}
