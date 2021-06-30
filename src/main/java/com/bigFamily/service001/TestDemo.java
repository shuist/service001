package com.bigFamily.service001;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestDemo {

    public static void main(String[] args) {
        double amountDouble  = 3.14;

        BigDecimal amountBigDecimal  = BigDecimal.valueOf(amountDouble);
        amountBigDecimal.setScale(3, RoundingMode.HALF_UP);

        java.text.DecimalFormat   df    =new   java.text.DecimalFormat("0.00");

        System.out.println(df.format(amountDouble));



    /**
        new_sku_id, product_id, product_sn, external_product_sn,
                sku_code, external_sku_code, pics, exchange_mode, price, point,
                buy_limit, stock, low_stock, lock_stock, sale, spec_json,
                spec_value_indexes, pc_publish_status, mobile_publish_status,
                pc_recommend_status, mobile_recommend_status, version, create_time,
                update_time, create_by, updated_by, deleted, point_repurchase,
                price_repurchase, virtual_stock, name, min_price_type, min_price,
                min_price_ratio, skuid_under_virtual_product, specs_id, ty_extend_sku_code,
                ty_sku_code, ty_commodity_cod
     **/
    /**
     old_sku_row_id  = old_porduct_sku[0]
     product_id = old_porduct_sku[1]
     product_sn = old_porduct_sku[2]
     external_product_sn = old_porduct_sku[3]
     sku_code = old_porduct_sku[4]
     external_sku_code = old_porduct_sku[5]
     pics = old_porduct_sku[6]
     exchange_mode = old_porduct_sku[7]
     price = old_porduct_sku[8]
     point = old_porduct_sku[9]
     buy_limit = old_porduct_sku[10]
     stock = old_porduct_sku[11]
     low_stock = old_porduct_sku[12]
     lock_stock = old_porduct_sku[13]
     sale = old_porduct_sku[14]
     spec_json = old_porduct_sku[15]
     spec_value_indexes = old_porduct_sku[16]
     pc_publish_status = old_porduct_sku[17]
     mobile_publish_status = old_porduct_sku[18]
     pc_recommend_status  = old_porduct_sku[19]
     mobile_recommend_status = old_porduct_sku[20]
     version = old_porduct_sku[21]
     create_time = old_porduct_sku[22]
     update_time = old_porduct_sku[23]
     create_by = old_porduct_sku[24]
     updated_by = old_porduct_sku[25]
     deleted = old_porduct_sku[26]
     point_repurchase = old_porduct_sku[27]
     virtual_stock = old_porduct_sku[28]
     name = old_porduct_sku[29]
     min_price_type = old_porduct_sku[30]
     min_price = old_porduct_sku[31]
     min_price_ratio = old_porduct_sku[32]
     skuid_under_virtual_product = old_porduct_sku[33]
     specs_id = old_porduct_sku[34]
     ty_extend_sku_code = old_porduct_sku[35]
     ty_sku_code = old_porduct_sku[36]
     ty_commodity_cod = old_porduct_sku[37]

                
        **/
    }
}
