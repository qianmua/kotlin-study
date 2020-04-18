package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.GroupModel
import pres.hjc.kotlinspringboot.mapping.GroupMapping
import pres.hjc.kotlinspringboot.service.GroupService

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/16
@time 20:46
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class GroupServiceImpl :GroupService{
    @Autowired
    private lateinit var groupMapping: GroupMapping

    override fun queryAll(): MutableList<GroupModel> {
        return groupMapping.queryAll()
    }

    override fun queryById(gid: Int): GroupModel  = groupMapping.queryById(gid)

    override fun queryByName(name: String): GroupModel {
        return groupMapping.queryByName(name)
    }

    override fun add(groupModel: GroupModel) {

        groupMapping.add(groupModel)
    }

    override fun update(groupModel: GroupModel): Int {
        return groupMapping.update(groupModel)
    }
}