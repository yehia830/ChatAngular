package com.tiy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yehia830 on 9/23/16.
 */
@RestController
public class ChatJSONController {
    @RequestMapping(path = "/chat.json", method = RequestMethod.GET)

}
