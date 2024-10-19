package com.lsjgitrading.controller;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lsjgitrading.common.Result;
import com.lsjgitrading.common.enums.ResultCodeEnum;
import com.lsjgitrading.common.enums.RoleEnum;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.exception.CustomException;
import com.lsjgitrading.service.AdminService;
import com.lsjgitrading.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
@RestController
public class WebController {
    @Resource
    private AdminService adminService;
    @Resource
    UserService userService;
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            account = userService.login(account);
        } else {
            throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        return Result.success(account);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.register(account);
        } else {
            throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        return Result.success();
    }


    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.updatePassword(account);
        }
        return Result.success();
    }

}
