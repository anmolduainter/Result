package com.example.joginderpal.result_btech;

import java.io.Serializable;

/**
 * Created by joginderpal on 16-03-2017.
 */
public class Student implements Serializable {

    String rollnumber;
    int urank;

    Student(String rollnumber,int urank){
        this.rollnumber=rollnumber;
        this.urank=urank;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public int getUrank() {
        return urank;
    }
}
