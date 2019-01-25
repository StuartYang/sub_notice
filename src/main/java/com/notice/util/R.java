package com.notice.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 操作码
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("mesg", "操作成功");
    }

    public static R error() {
        return error(1, "操作失败");
    }

    public static R error(String mesg) {
        return error(1, mesg);
    }

    public static R error(int code, String mesg) {
        R r = new R();
        r.put("code", code);
        r.put("mesg", mesg);
        return r;
    }

    public static R ok(String mesg) {
        R r = new R();
        r.put("mesg", mesg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

