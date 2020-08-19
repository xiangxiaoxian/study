package com.xiang.springboot01.modules.test.service.impl;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.modules.test.entity.Student;
import com.xiang.springboot01.modules.test.repository.StudentRepository;
import com.xiang.springboot01.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudentServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月12日 18:28:00
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"insert success",student);
    }

    @Override
    @Transactional
    public Student selectStudentById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> selectStudentBySearchVo(SearchVo searchVo) {
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
                "studentId" : searchVo.getOrderBy();
        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = new Sort(direction, orderBy);
        // 当前页起始为 0
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1, searchVo.getPageSize(), sort);

        // build Example 对象
        // 如果 keyWord 为 null，则设置的 studentName 不参与查询条件
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher = ExampleMatcher.matching()
                // 全部模糊查询，即 %{studentName} %
                //.withMatcher("studentName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("studentName", match -> match.contains())
                // 忽略字段，即不管id是什么值都不加入查询条件
                .withIgnorePaths("studentId");
        Example<Student> example = Example.of(student, matcher);

        return studentRepository.findAll(example, pageable);
    }

    @Override
    public List<Student> selectStudentByStudentName(String studentName,int cardId) {
        if (cardId>0){
            return studentRepository.selectStudentByParams(studentName,cardId);
        }else {
      /*  return Optional.ofNullable(studentRepository.findByStudentName(studentName))
                .orElse(Collections.emptyList());*/
        return Optional.ofNullable(studentRepository.findByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());
        /*return Optional.ofNullable(studentRepository.findTop2ByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());*/
        }
    }
}
