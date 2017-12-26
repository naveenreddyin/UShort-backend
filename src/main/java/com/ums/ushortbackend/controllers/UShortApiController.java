package com.ums.ushortbackend.controllers;

import com.google.common.hash.Hashing;
import com.ums.ushortbackend.domains.ShortURL;
import com.ums.ushortbackend.repositories.ShortURLRepository;
import com.ums.ushortbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Naveen on 19/07/2017.
 */
@RestController
@RequestMapping("/api")
public class UShortApiController {


    @Autowired
    ShortURLRepository shortURLRepository;



    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    public ResponseEntity<ShortURL> shortenURL(HttpServletRequest httpRequest, @RequestParam("url") String url){

        System.out.println(url);
        if(!Utils.isUrlValid(url)){
            return new ResponseEntity<ShortURL>(new ShortURL(), HttpStatus.BAD_REQUEST);
        }

        final String code = Hashing.murmur3_32()
                .hashString(url, StandardCharsets.UTF_8).toString();

        String requestUrl = httpRequest.getRequestURL().toString();
        String prefix = requestUrl.substring(0, requestUrl.indexOf(httpRequest.getRequestURI(),
                "http://".length()));

        final ShortURL shortURL = new ShortURL(LocalDateTime.now(),
                code, url, prefix);

        ShortURL newShortUrl = shortURLRepository.save(shortURL);



        System.out.println(prefix);
        return new ResponseEntity<ShortURL>(newShortUrl, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/fetchAllShortened", method = RequestMethod.GET)
    public ResponseEntity<Collection<ShortURL>> fetchAllshorten(){

        return new ResponseEntity<>(shortURLRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Collection<ShortURL>> deleteURL(@PathVariable String code){

        ShortURL shortURL = shortURLRepository.findByCode(code);
        shortURLRepository.delete(shortURL.getId());
        return new ResponseEntity<>(shortURLRepository.findAll(), HttpStatus.OK);
    }



}
