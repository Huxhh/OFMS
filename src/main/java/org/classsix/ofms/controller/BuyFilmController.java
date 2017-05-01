package org.classsix.ofms.controller;

import org.classsix.ofms.service.BuyFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huxh on 2017/5/1.
 */
@RestController
public class BuyFilmController {
    
    @Autowired
    BuyFilmService buyFilmService;
    
    
}
