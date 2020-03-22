package pres.hjc.kotlinspringboot.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/14
@time 17:10
@version 1.0
To change this template use File | Settings | File Templates.
 */

@Controller
class InitController {

    val logging: Logger = LoggerFactory.getLogger(InitController::class.java)

    @Autowired
    private lateinit var userServiceImpl: UserServiceImpl

    @GetMapping("/")
    fun index():String{
        logging.info("index path /")
        return "index"
    }

    /*@GetMapping("l")
    fun login(name:String,
              password:String,
              model:Model,
              request:HttpRequest,
              response:HttpResource):String{

        return "html/login"
    }*/

    /*@RequestMapping("add")
    fun insert(userModel: UserModel?):String{
//        var l = userServiceImpl.insert(userModel)
        var user = userServiceImpl.login("111","111")
        logging.info(user?.toString())
        return "index"
    }*/
}