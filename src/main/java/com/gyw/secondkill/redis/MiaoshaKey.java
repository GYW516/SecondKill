package com.gyw.secondkill.redis;

/**
 * @author Dell
 * @create 2019-07-28 21:03
 */
public class MiaoshaKey extends BasePrefix {
    private MiaoshaKey( int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey(0, "go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60, "mp");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300, "vc");
}
