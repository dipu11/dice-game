package com.game.dice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Dipu on 11/21/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto implements Serializable {
    private Long age;
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int score;
    public PlayerDto(Long age, String name) {
        this.age = age;
        this.name= name;
    }
}
