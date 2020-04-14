package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.SendMailModel
import pres.hjc.kotlinspringboot.mapping.SendMailMapping
import pres.hjc.kotlinspringboot.service.SendMailService

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/14
@time 20:53
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class SendMailServiceImpl:SendMailService{
    @Autowired
    private lateinit var sendMail:SendMailMapping
    override fun queryAll(): MutableList<SendMailModel> {
        return sendMail.queryAll()
    }

    override fun queryById(mid: Int): SendMailModel {
        return sendMail.queryById(mid)
    }

    override fun add(title: String, title2: String, formuser: String, touser: String, sendname: String, send: String, imgurl: String, createdate: String, status: Int): Int {
        return sendMail.add(title, title2, formuser, touser, sendname, send, imgurl, createdate, status)
    }
}