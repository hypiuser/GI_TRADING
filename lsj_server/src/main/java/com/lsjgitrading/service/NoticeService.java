package com.lsjgitrading.service;

import cn.hutool.core.date.DateUtil;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.entity.Notice;
import com.lsjgitrading.mapper.NoticeMapper;
import com.lsjgitrading.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        noticeMapper.insert(notice);
    }

    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            noticeMapper.deleteById(id);
        }
    }

    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapper.selectAll(notice);
        return PageInfo.of(list);
    }

}