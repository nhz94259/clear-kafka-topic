package com.ant.clearkafkatopic.commonsAndUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by wolf   2018/11/30
 */
@Data
public class Message implements Serializable {


    private final Integer id;

    private final String message;

    @JsonCreator
    public Message(@JsonProperty("id") Integer id,
                   @JsonProperty("message") String message)
    {
        this.id = id;
        this.message = message;
    }


}
