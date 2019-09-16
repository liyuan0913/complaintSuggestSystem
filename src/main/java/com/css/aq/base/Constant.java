package com.css.aq.base;

/**
 * 常用常量类
 */
public interface Constant {
    /** 服务器端500 */
    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**  返回成功状态码200 OK*/
    Integer SC_OK_200 = 200;
    /**排序默认值 */
    Integer OR_NUM = 100;
    /** 删除标记 */
    Integer DELETE = 2;
     /** 未删除标记 */
    Integer NO_DELETE = 1;
    /** 开启标记 */
    Integer OPEN = 1;
    /** 关闭标记 */
    Integer CLOSE = 0;
    /**激活状态 */
    Integer ACT = 1;
    /**未激活状态 */
    Integer NOT_ACT = 1;
    /** true字符串 */
    String TRUE = "true";
    /** false字符串 */
    String FALSE = "false";

    /** 展开树 打开状态 */
    String TREE_OPEN = "open";
    /** 展开树 关闭状态 */
    String TREE_CLOSED = "closed";

    /**  1-导航 */
    Integer FUNCTION_MENU = 1;
    /**  2-功能  */
    Integer FUNCTION_FUNC = 2;

    String DICT_ROOT = "d_root";

    String salt = "YzcmCZNvbXocrsz9dm8e";
}
