package cn.auchp.gmall.service;

import cn.auchp.gmall.bean.PmsProductImage;
import cn.auchp.gmall.bean.PmsProductInfo;
import cn.auchp.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);
}
