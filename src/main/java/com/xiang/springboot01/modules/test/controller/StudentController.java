package com.xiang.springboot01.modules.test.controller;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.modules.test.entity.Student;
import com.xiang.springboot01.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudentController.java
 * @Description TODO
 * @createTime 2020年08月12日 18:32:00
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
    * 127.0.0.1/student/student  ---post
    * {"studentName":"xiangrui","studentCard":{"cardId":"1"}}
    * */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }


    /*
     * 127.0.0.1/student/student/1  ---get
     *
     * */
    @GetMapping("/student/{studentId}")
    public Student selectStudentById(@PathVariable int studentId) {
        return studentService.selectStudentById(studentId);
    }


    /*
     * 127.0.0.1/student/students  ---post
     *  {"currentPage":"1","pageSize":"5","orderBy":"studentName","sort":"desc"}
     * */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> selectStudentBySearchVo(@RequestBody SearchVo searchVo) {
        return studentService.selectStudentBySearchVo(searchVo);
    }

    /*
     * 127.0.0.1/student/students?studentName=xiangrui  ---get
     *
     * */
    @GetMapping("/students")
    public List<Student> selectStudentByParams(@RequestParam String studentName ,
                                               @RequestParam(required = false,defaultValue = "0") Integer cardId) {
        return studentService.selectStudentByStudentName(studentName,cardId);
    }
}
