package com.lsjgitrading.mapper;

import com.lsjgitrading.entity.Posts;
import org.apache.ibatis.annotations.Update;

import java.util.List;
public interface PostsMapper {

    int insert(Posts posts);

    int deleteById(Integer id);

    int updateById(Posts posts);

    Posts selectById(Integer id);

    List<Posts> selectAll(Posts posts);

    List<Posts> selectFrontAll(Posts posts);

    @Update("update posts set read_count = read_count + 1 where id = #{id}")
    void updateCount(Integer id);

}