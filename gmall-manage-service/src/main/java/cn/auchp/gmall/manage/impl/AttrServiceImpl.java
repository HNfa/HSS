package cn.auchp.gmall.manage.impl;

import cn.auchp.gmall.bean.PmsBaseAttrInfo;
import cn.auchp.gmall.bean.PmsBaseAttrValue;
import cn.auchp.gmall.bean.PmsBaseSaleAttr;
import cn.auchp.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import cn.auchp.gmall.manage.mapper.PmsBaseAttrValueMapper;
import cn.auchp.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import cn.auchp.gmall.service.AttrService;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {

            List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }

        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
       String id  = pmsBaseAttrInfo.getId();
       if (StringUtils.isBlank(id)){
           //id为空 保存
           //保存属性
           pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
           //保存属性值
           List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrInfo.getAttrValueList();
           for (PmsBaseAttrValue pmsBaseAttrValue :pmsBaseAttrValues){
               pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());

               pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
           }
       }else {
           //id不为空  修改
           //属性修改
           Example example = new Example(PmsBaseAttrInfo.class);
           example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
           pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

           //属性值修改
           //按照属性id删除所有属性值
           PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
           pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
           pmsBaseAttrValueMapper.delete(pmsBaseAttrValue);

           //删除后，将新的属性值插入
           List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue1:pmsBaseAttrValueList){
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue1);
            }
       }
        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
       PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
       pmsBaseAttrValue.setAttrId(attrId);
       List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
       return pmsBaseAttrValues;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
