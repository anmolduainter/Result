package com.example.joginderpal.result_btech;

import java.util.Comparator;

/**
 * Created by joginderpal on 19-03-2017.
 */
public class cmpRank implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {

        if (o1.getUrank()>o2.getUrank()){
            return 1;
        }
        else if(o1.getUrank()<o2.getUrank()){
            return -1;
        }
        return 0;
    }
}
