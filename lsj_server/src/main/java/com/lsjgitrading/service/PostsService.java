package com.lsjgitrading.service;

import cn.hutool.core.date.DateUtil;
import com.lsjgitrading.common.enums.RoleEnum;
import com.lsjgitrading.common.enums.StatusEnum;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.entity.Posts;
import com.lsjgitrading.mapper.PostsMapper;
import com.lsjgitrading.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PostsService {
    @Resource
    private PostsMapper postsMapper;
    public void add(Posts posts) {
        posts.setTime(DateUtil.now());
        Account currentUser = TokenUtils.getCurrentUser();
        posts.setUserId(currentUser.getId());
        posts.setStatus("待审核");
        postsMapper.insert(posts);
    }

    public void deleteById(Integer id) {
        postsMapper.deleteById(id);
    }


    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            postsMapper.deleteById(id);
        }
    }


    public void updateById(Posts posts) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            posts.setStatus(StatusEnum.NOT_AUDIT.value);
        }
        postsMapper.updateById(posts);
    }


    public Posts selectById(Integer id) {
        return postsMapper.selectById(id);
    }


    public List<Posts> selectAll(Posts posts) {
        return postsMapper.selectAll(posts);
    }

    public PageInfo<Posts> selectPage(Posts posts, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            posts.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Posts> list = postsMapper.selectAll(posts);
        return PageInfo.of(list);
    }

    public PageInfo<Posts> selectFrontPage(Posts posts, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posts> list = postsMapper.selectFrontAll(posts);
        return PageInfo.of(list);
    }

    public void updateCount(Integer id) {
        postsMapper.updateCount(id);
    }
}