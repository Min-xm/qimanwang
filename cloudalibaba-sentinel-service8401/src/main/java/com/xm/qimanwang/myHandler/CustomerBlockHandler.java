package com.xm.qimanwang.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public static String handlerException(BlockException exception){
        return "自定义兜底方法1";
    }
    public static String handlerException2(BlockException exception){
        return "自定义兜底方法2";
    }
}
