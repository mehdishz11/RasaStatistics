
package com.rasa.statistics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable
{

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("description")
    @Expose
    private String description;
    private final static long serialVersionUID = 8621171298283344434L;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
