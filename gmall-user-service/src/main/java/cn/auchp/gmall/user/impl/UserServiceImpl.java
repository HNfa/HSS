package cn.auchp.gmall.user.impl;

import cn.auchp.gmall.bean.UmsMember;
import cn.auchp.gmall.bean.UmsMemberReceiveAddress;
import cn.auchp.gmall.service.UserService;
import cn.auchp.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import cn.auchp.gmall.user.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {

//        List<UmsMember> umsMemberList = userMapper.selectAllUser();
        List<UmsMember> umsMemberList = userMapper.selectAll();

        return umsMemberList;
    }

    @Override
    public List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressByMemberId(String memberId) {
        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);

        return umsMemberReceiveAddresses;
    }

}
