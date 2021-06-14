package com.bigFamily.service001.coupon;//in ExcelOperater

import com.bigFamily.service001.redis.RedisServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * add condition
 *
 * tuya
 * catalog：数据类型 0=不限制范围，3=新零售商品，4=新零售类⽬
 * code：商品code|类⽬code
 *
 * itnode
 * scope（0 全部；1 类目；2 商品）
 * product_id 如使用范围是商品，必填
 * category_id 如使用范围是分类，必填
 *
 */
@Component
public class ExcelOperater3 {

    @Autowired
    private RedisServiceImpl redisService;
    private static Map<String,String> scops = new HashMap<>();


    public void addCouponCondition() {
        initScops();
        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/1.fss优惠券初始化.xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            File filewrite = new File("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/3.fss优惠券(补充使用范围).xls");
            filewrite.createNewFile();
            OutputStream os = new FileOutputStream(filewrite);
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);


            //读取第一张工作表
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            for (int i = 0; i < rsRows; i++) {
                if(i==0){
                    for (int j = 0; j < rsColumns; j++) {
                        Cell cell = readsheet.getCell(j, i);
                        String val = cell.getContents();
                        Label label = new Label(j, i, val);
                        ws.addCell(label);
                    }
                    continue;
                }
                for (int j = 0; j < rsColumns; j++) {
                    if(j==4) {
                        Cell cell = readsheet.getCell(0, i);
                        String itnodeCouponId = cell.getContents();
                        String conditionKey = CouponConstant.COUPON_CONDITION+itnodeCouponId;
                        String conditionVal = (String) redisService.get(conditionKey);
                        if(StringUtils.isEmpty(conditionVal)){
                            continue;
                        }
                        String[] conditionVals = conditionVal.split("&");
                        String catalog = conditionVals[0];
                        String scope = scops.get(catalog);

                        Label label = new Label(4, i, scope);
                        ws.addCell(label);


                        if("1".equals(scope)){//类目,添加category_id
                            label = new Label(6, i, conditionVals[1]);
                            ws.addCell(label);
                        }else if("2".equals(scope)){//商品,添加product_id
                            label = new Label(5, i, conditionVals[1]);
                            ws.addCell(label);
                        }
                    }else if(j!=5 && j!=6){
                        Cell cell = readsheet.getCell(j, i);
                        String val = cell.getContents();
                        Label label = new Label(j, i, val);
                        ws.addCell(label);
                    }
                }


            }

            //

            //写入Excel对象

            wwb.write();

            wwb.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }

    public void initScops(){
        scops.put("0","0");
        scops.put("4","1");
        scops.put("3","2");
    }


}