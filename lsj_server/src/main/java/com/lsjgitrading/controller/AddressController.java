package com.lsjgitrading.controller;
import com.lsjgitrading.common.Result;
import com.lsjgitrading.entity.Address;
import com.lsjgitrading.service.AddressService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        addressService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        addressService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Address address) {
        addressService.updateById(address);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Address address = addressService.selectById(id);
        return Result.success(address);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Address address) {
        List<Address> list = addressService.selectAll(address);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Address address,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Address> page = addressService.selectPage(address, pageNum, pageSize);
        return Result.success(page);
    }

}