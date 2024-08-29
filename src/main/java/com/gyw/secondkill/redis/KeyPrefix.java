package com.gyw.secondkill.redis;

/**
 * @author Dell
 * @create 2019-07-17 19:45
 */
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
