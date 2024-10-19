package com.lsjgitrading.mapper;

import com.lsjgitrading.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface CommentMapper {

    int insert(Comment comment);

    int deleteById(Integer id);

    int updateById(Comment comment);

    Comment selectById(Integer id);

    List<Comment> selectAll(Comment comment);

    List<Comment> selectRoot(@Param("fid") Integer fid, @Param("module") String module);

    List<Comment> selectByRootId(Integer rootId);

    @Select("select count(*) from comment where fid = #{fid} and module = #{module}")
    Integer selectCount(@Param("fid") Integer fid, @Param("module") String module);

    @Select("select * from comment where pid = #{pid}")
    List<Comment> selectByPid(Integer pid);
}