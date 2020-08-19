package com.xiang.springboot01.modules.test.service;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudentService.java
 * @Description TODO
 * @createTime 2020年08月12日 18:28:00
 */
public interface StudentService {
    Result<Student> insertStudent(Student student);

    Student selectStudentById(int studentId);

    Page<Student> selectStudentBySearchVo(SearchVo searchVo);

    List<Student> selectStudentByStudentName(String studentName,int cardId);
}
