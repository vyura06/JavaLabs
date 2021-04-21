package com.MichaelMoroz;

import java.util.Arrays;

public class Teacher {
    String teacherSurname;
    Day[] days = new Day[6];

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherSurname='" + teacherSurname + '\'' +
                ", days=" + Arrays.toString(days) +
                '}';
    }

    public void inizialize() {
        for (int i = 0; i < days.length; i++) {
            days[i] = new Day();
        }
    }
}
