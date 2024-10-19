package com.lsjgitrading.service;
import com.lsjgitrading.common.enums.RoleEnum;
import com.lsjgitrading.entity.Account;
import com.lsjgitrading.entity.Address;
import com.lsjgitrading.mapper.AddressMapper;
import com.lsjgitrading.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    public void add(Address address) {
        Account currentUser = TokenUtils.getCurrentUser();
        address.setUserId(currentUser.getId());
        addressMapper.insert(address);
    }

    public void deleteById(Integer id) {
        addressMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            addressMapper.deleteById(id);
        }
    }
    public void updateById(Address address) {
        addressMapper.updateById(address);
    }


    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

    public List<Address> selectAll(Address address) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }
        return addressMapper.selectAll(address);
    }


    public PageInfo<Address> selectPage(Address address, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Address> list = addressMapper.selectAll(address);
        return PageInfo.of(list);
    }

}