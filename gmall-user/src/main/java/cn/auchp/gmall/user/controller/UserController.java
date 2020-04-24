package cn.auchp.gmall.user.controller;

import cn.auchp.gmall.bean.UmsMember;
import cn.auchp.gmall.bean.UmsMemberReceiveAddress;
import cn.auchp.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("getUmsMemberReceiveAddress")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressByMemberId(String menberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddress =  userService.getUmsMemberReceiveAddressByMemberId(menberId);
        return umsMemberReceiveAddress;
    }

    @RequestMapping("index")
    @ResponseBody
    public  String index(){

        return "hello index!";
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers = userService.getAllUser();
        return umsMembers;
    }


}
