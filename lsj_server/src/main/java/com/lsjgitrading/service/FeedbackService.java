package com.lsjgitrading.service;

import cn.hutool.core.date.DateUtil;
import com.lsjgitrading.common.enums.RoleEnum;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.entity.Feedback;
import com.lsjgitrading.mapper.FeedbackMapper;
import com.lsjgitrading.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;


    public void add(Feedback feedback) {
        Account currentUser = TokenUtils.getCurrentUser();
        feedback.setUserId(currentUser.getId());
        feedback.setCreatetime(DateUtil.now());
        feedbackMapper.insert(feedback);
    }

    public void deleteById(Integer id) {
        feedbackMapper.deleteById(id);
    }


    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            feedbackMapper.deleteById(id);
        }
    }


    public void updateById(Feedback feedback) {
        feedbackMapper.updateById(feedback);
    }


    public Feedback selectById(Integer id) {
        return feedbackMapper.selectById(id);
    }


    public List<Feedback> selectAll(Feedback feedback) {
        return feedbackMapper.selectAll(feedback);
    }

    public PageInfo<Feedback> selectPage(Feedback feedback, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            feedback.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Feedback> list = feedbackMapper.selectAll(feedback);
        return PageInfo.of(list);
    }

}