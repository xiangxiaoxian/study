package com.xiang.springboot01.modules.test.service;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.test.entity.Card;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CardService.java
 * @Description TODO
 * @createTime 2020年08月12日 17:08:00
 */
public interface CardService {

    Result<Card> insertCard(Card card);
}
