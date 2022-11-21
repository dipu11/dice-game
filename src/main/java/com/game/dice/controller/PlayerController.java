package com.game.dice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dipu on 11/21/22.
 */

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @GetMapping("/test")
    public String testGetAPI(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "id", required = false)Long id
    ) {

        return "id:"+id+", name:"+ name;
    }

   // public ResponseEntity addPlayer()
}
