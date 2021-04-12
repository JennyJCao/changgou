package com.changgou.goods.pojo;

import java.io.Serializable;
import java.util.List;

// 商品
// 用于从前端接收参数
// 1 * spu + n * sku
public class Goods implements Serializable {
    private Spu spu;

    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "spu=" + spu +
                ", skuList=" + skuList.toString() +
                '}';
    }
}
