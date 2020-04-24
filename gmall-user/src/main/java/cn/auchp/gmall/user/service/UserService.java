package cn.auchp.gmall.user.service;

import cn.auchp.gmall.user.bean.UmsMember;
import cn.auchp.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressByMemberId(String menberId);
}
