package com.pori.www.commons;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

/**
 * @author libo
 * @date 2018/4/8 下午5:08
 */
public class CommonsTest {
    @Test
    public void testMd5() {
        String md5Str = DigestUtils.md5Hex("123456");
        System.out.println("32result: " + md5Str);
    }

}
