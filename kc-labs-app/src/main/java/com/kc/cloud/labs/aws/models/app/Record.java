package com.kc.cloud.labs.aws.models.app;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Record {

    public Date date;
    public String description;
    public String type;

    public Record(){}

    public Record(Date date, String description, String type){
        this.date = date;
        this.description = description;
        this.type = type;
    }

}
