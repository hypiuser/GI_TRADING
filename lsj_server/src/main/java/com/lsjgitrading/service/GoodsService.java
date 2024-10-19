package com.lsjgitrading.service;

import cn.hutool.core.date.DateUtil;
import com.lsjgitrading.common.enums.RoleEnum;
import com.lsjgitrading.common.enums.StatusEnum;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.entity.Collect;
import com.lsjgitrading.entity.Goods;
import com.lsjgitrading.entity.Likes;
import com.lsjgitrading.mapper.CollectMapper;
import com.lsjgitrading.mapper.GoodsMapper;
import com.lsjgitrading.mapper.LikesMapper;
import com.lsjgitrading.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    LikesMapper likesMapper;
    @Resource
    CollectMapper collectMapper;

    public void add(Goods goods) {
        goods.setDate(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        goods.setUserId(currentUser.getId());
        goods.setStatus(StatusEnum.NOT_AUDIT.value);
        goods.setReadCount(0);
        goodsMapper.insert(goods);
    }

    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            goodsMapper.deleteById(id);
        }
    }

    public void updateById(Goods goods) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            goods.setStatus(StatusEnum.NOT_AUDIT.value);
        }
        goodsMapper.updateById(goods);
    }

    public Goods selectById(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        //当前用户父类
        Account currentUser = TokenUtils.getCurrentUser();
        Likes likes = likesMapper.selectByUserIdAndFid(currentUser.getId(), id);
        goods.setUserLikes(likes != null);
        int likesCount = likesMapper.selectCountByFid(id);
        goods.setLikesCount(likesCount);

        Collect collect = collectMapper.selectByUserIdAndFid(currentUser.getId(), id);
        goods.setUserCollect(collect != null);
        int collectCount = collectMapper.selectCountByFid(id);
        goods.setCollectCount(collectCount);

        return goods;
    }


    public List<Goods> selectAll(Goods goods) {
        return goodsMapper.selectAll(goods);
    }


    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            goods.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsMapper.selectAll(goods);
        return PageInfo.of(list);
    }

    public PageInfo<Goods> selectFrontPage(Goods goods, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            goods.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsMapper.selectFrontAll(goods);
        for (Goods g : list) {
            int likesCount = likesMapper.selectCountByFid(g.getId());
            g.setLikesCount(likesCount);
        }
        return PageInfo.of(list);
    }

    //为商品增加浏览量+1
    public void updateReadCount(Integer id) {
        goodsMapper.updateReadCount(id);
    }
}