package com.sui.haedal.curator.common;

public class CoinUtils {

    public static String coinTokenTypeVal(String coinType){
        String[] collateralTokens = coinType.split("::");
        return collateralTokens[collateralTokens.length - 1]; // 取最后一个元素
    }

    public static int getCoinDecimal(String typeVal) {
        if("SUI".equals(typeVal)){
            return 9;
        }else if("USDC".equals(typeVal)){
            return 6;
        }else if("ETH".equals(typeVal)){
            return 18;
        }else if("CETUS".equals(typeVal)){
            return 9;
        }else if("HASUI".equals(typeVal)){
            return 9;
        }else if("USDT".equals(typeVal)){
            return 6;
        }
        return 0;
    }
}
