package com.xiang.springboot01.modules.test.service.impl;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.test.entity.Card;
import com.xiang.springboot01.modules.test.repository.CardRepository;
import com.xiang.springboot01.modules.test.service.CardService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CardServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月12日 17:08:00
 */
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status,"insert success",card);
    }
}
