package com.baiyu.zzg.mapper;

import com.baiyu.zzg.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface Articlemapper {


    @Insert("insert into article(title,content,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    @Select("<script>"
            + "SELECT * FROM article "
            + "WHERE create_user = #{userId} "
            + "<if test='categoryId != null'> "
            + "AND category_id = #{categoryId} "
            + "</if>"
            + "<if test='state != null and state.trim() != \"\"'> "
            + "AND state = #{state} "
            + "</if>"
            + "</script>")
    List<Article> list(@Param("userId") Integer userId,
                       @Param("categoryId") Integer categoryId,
                       @Param("state") String state);

    @Update("update article set title=#{title},content=#{content},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteById(Integer id);
}
