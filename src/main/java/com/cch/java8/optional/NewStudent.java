package com.cch.java8.optional;

import java.util.Optional;

/**
 * Created by cch
 * 2018-05-01 20:55.
 */

public class NewStudent {
    private Optional<Teacher> teacherOpt;

    public Optional<Teacher> getTeacherOpt() {
        return teacherOpt;
    }

    public void setTeacherOpt(Optional<Teacher> teacherOpt) {
        this.teacherOpt = teacherOpt;
    }

    public NewStudent(Optional<Teacher> teacherOpt) {
        this.teacherOpt = teacherOpt;
    }

    public NewStudent() {
    }
}
