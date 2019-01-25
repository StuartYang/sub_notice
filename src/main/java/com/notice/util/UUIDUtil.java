package com.notice.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString().substring(0, 7);
		return uuid;
	}
}
