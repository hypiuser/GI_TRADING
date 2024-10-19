package com.lsjgitrading.service;

import com.lsjgitrading.entity.Circles;
import com.lsjgitrading.mapper.CirclesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CirclesService {

    @Resource
    private CirclesMapper circlesMapper;

    public void add(Circles circles) {
        circlesMapper.insert(circles);
    }


    public void deleteById(Integer id) {
        circlesMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            circlesMapper.deleteById(id);
        }
    }

    public void updateById(Circles circles) {
        circlesMapper.updateById(circles);
    }

    public Circles selectById(Integer id) {
        return circlesMapper.selectById(id);
    }

    public List<Circles> selectAll(Circles circles) {
        return circlesMapper.selectAll(circles);
    }


    public PageInfo<Circles> selectPage(Circles circles, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Circles> list = circlesMapper.selectAll(circles);
        return PageInfo.of(list);
    }

}