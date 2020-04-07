package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import pres.hjc.kotlinspringboot.entity.MailFromInfoModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/7
@time 20:21
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface MailInfoMapping {

    @Select("select * from mailinfo where mailid = #{mailid}")
    fun queryById(@Param("mailId") mailId:Long):MailFromInfoModel?

    @Update("update mailinfo set from_name = #{fromName},set from_mail = #{fromMail}")
    fun add(mailFromInfoModel: MailFromInfoModel):Unit

}