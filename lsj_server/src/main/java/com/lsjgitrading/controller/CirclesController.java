package com.lsjgitrading.controller;

import com.lsjgitrading.common.Result;
import com.lsjgitrading.entity.Circles;
import com.lsjgitrading.service.CirclesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/circles")
public class CirclesController {

    @Resource
    private CirclesService circlesService;

    @PostMapping("/add")
    public Result add(@RequestBody Circles circles) {
        circlesService.add(circles);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        circlesService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        circlesService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Circles circles) {
        circlesService.updateById(circles);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Circles circles = circlesService.selectById(id);
        return Result.success(circles);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Circles circles) {
        List<Circles> list = circlesService.selectAll(circles);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Circles circles,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Circles> page = circlesService.selectPage(circles, pageNum, pageSize);
        return Result.success(page);
    }

}