package com.game.dice.controller;

import com.game.dice.dto.PlayerDto;
import com.game.dice.dto.PlayerRequest;
import com.game.dice.dto.Response;
import com.game.dice.exception.GameExceptionHandler;
import com.game.dice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Dipu on 11/21/22.
 */

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    PlayerController(PlayerService playerService){
        this.playerService= playerService;
    }

    @GetMapping("/test")
    public String testGetAPI(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "id", required = false)Long id
    ) {

        return "id:"+id+", name:"+ name;
    }

    @PostMapping
    public Response<Object> addPlayer(@Valid @RequestBody PlayerRequest playerRequest) throws Exception {

        PlayerDto playerDto= playerService.addPlayer(playerRequest);

        return new Response<>(playerDto);
        //return new ResponseEntity("Player id: "+playerRequest.getId()+", name:"+ playerRequest.getName()+" added", HttpStatus.CREATED);
    }
}
