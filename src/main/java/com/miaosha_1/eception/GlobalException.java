package com.miaosha_1.eception;

import com.miaosha_1.result.CodeMsg;

/**
 * @author qqtang
 * @Date 2020/11/18 14:07
 * @Desc 全局异常
 */
public class GlobalException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public CodeMsg getCm() {
        return cm;
    }

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }
}
