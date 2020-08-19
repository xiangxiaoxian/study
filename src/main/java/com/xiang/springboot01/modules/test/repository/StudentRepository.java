package com.xiang.springboot01.modules.test.repository;

import com.xiang.springboot01.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudentRepository.java
 * @Description TODO
 * @createTime 2020年08月12日 18:28:00
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop2ByStudentNameLike(String studentName);


   /* @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")*/
   /* @Query(value = "select s from Student s where s.studentName = :studentName and s.studentCard.cardId =:cardId")*/
    @Query(nativeQuery = true,value = "select  * from h_student where student_name = :studentName and card_id = :cardId")
    List<Student> selectStudentByParams(@Param("studentName") String studentName ,@Param("cardId") int cardId);
}
