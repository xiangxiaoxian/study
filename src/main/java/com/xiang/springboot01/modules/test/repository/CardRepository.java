package com.xiang.springboot01.modules.test.repository;

import com.xiang.springboot01.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CardRepository.java
 * @Description TODO
 * @createTime 2020年08月12日 17:07:00
 */

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
