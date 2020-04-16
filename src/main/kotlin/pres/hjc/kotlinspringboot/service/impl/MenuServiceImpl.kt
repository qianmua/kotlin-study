package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.MenuModel
import pres.hjc.kotlinspringboot.mapping.MenuMapping
import pres.hjc.kotlinspringboot.service.MenuService

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/16
@time 21:12
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class MenuServiceImpl :MenuService {
    @Autowired
    private lateinit var menu:MenuMapping

    override fun queryById(eid: Long): MenuModel {
        return menu.queryById(eid)
    }

    override fun queryAll(): MutableList<MenuModel> {
        return menu.queryAll()
    }

    override fun add(roles: String): Int {
        return menu.add(roles)
    }
}