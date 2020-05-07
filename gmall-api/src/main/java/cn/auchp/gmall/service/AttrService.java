package cn.auchp.gmall.service;

import cn.auchp.gmall.bean.PmsBaseAttrInfo;
import cn.auchp.gmall.bean.PmsBaseAttrValue;
import cn.auchp.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
