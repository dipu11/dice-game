package com.game.dice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Dipu on 11/24/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements Serializable {

    private String errMessage;
    private String description;
}
