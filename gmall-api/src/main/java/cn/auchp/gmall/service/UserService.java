package cn.auchp.gmall.service;

import cn.auchp.gmall.bean.UmsMember;
import cn.auchp.gmall.bean.UmsMember;
import cn.auchp.gmall.bean.UmsMemberReceiveAddress;


import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressByMemberId(String menberId);
}
