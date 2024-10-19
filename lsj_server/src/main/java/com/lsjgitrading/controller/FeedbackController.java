package com.lsjgitrading.controller;

import com.lsjgitrading.common.Result;
import com.lsjgitrading.entity.Feedback;
import com.lsjgitrading.service.FeedbackService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public Result add(@RequestBody Feedback feedback) {
        feedbackService.add(feedback);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        feedbackService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        feedbackService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Feedback feedback) {
        feedbackService.updateById(feedback);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Feedback feedback = feedbackService.selectById(id);
        return Result.success(feedback);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Feedback feedback) {
        List<Feedback> list = feedbackService.selectAll(feedback);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Feedback feedback,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Feedback> page = feedbackService.selectPage(feedback, pageNum, pageSize);
        return Result.success(page);
    }

}