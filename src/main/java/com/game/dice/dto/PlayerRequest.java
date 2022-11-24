package com.game.dice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Dipu on 11/24/22.
 */
@Data
public class PlayerRequest implements Serializable {
    @NotNull
    @Min(0)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 64)
    private String name;

}
