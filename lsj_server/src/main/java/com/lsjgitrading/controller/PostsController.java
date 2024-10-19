package com.lsjgitrading.controller;

import com.lsjgitrading.common.Result;
import com.lsjgitrading.entity.Posts;
import com.lsjgitrading.service.PostsService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostsController {

    @Resource
    private PostsService postsService;

    @PostMapping("/add")
    public Result add(@RequestBody Posts posts) {
        postsService.add(posts);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        postsService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        postsService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Posts posts) {
        postsService.updateById(posts);
        return Result.success();
    }

    @PutMapping("/updateCount/{id}")
    public Result updateCount(@PathVariable Integer id) {
        postsService.updateCount(id);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Posts posts = postsService.selectById(id);
        return Result.success(posts);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Posts posts) {
        List<Posts> list = postsService.selectAll(posts);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Posts posts,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Posts> page = postsService.selectPage(posts, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectFrontPage")
    public Result selectFrontPage(Posts posts,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Posts> page = postsService.selectFrontPage(posts, pageNum, pageSize);
        return Result.success(page);
    }
}