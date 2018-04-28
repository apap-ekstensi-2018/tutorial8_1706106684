package com.example.tutorial08.service;

import java.util.List;

import com.example.tutorial08.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);

    List<StudentModel> selectAllStudents ();

    void addStudent (StudentModel student);

    void updateStudent (StudentModel student);

    void deleteStudent (String npm);
}
