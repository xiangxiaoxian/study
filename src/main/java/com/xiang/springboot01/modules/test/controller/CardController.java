package com.xiang.springboot01.modules.test.controller;

import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.test.entity.Card;
import com.xiang.springboot01.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CardController.java
 * @Description TODO
 * @createTime 2020年08月12日 17:12:00
 */
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /*
    * 127.0.0.1/card/card  ---post
    * {"cardNo":"cdascd"}
    * */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insert(@RequestBody Card card){
        return cardService.insertCard(card);
    }
}
