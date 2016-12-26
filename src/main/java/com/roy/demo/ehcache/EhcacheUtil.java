package com.roy.demo.ehcache;

import java.net.URL;

import org.ehcache.CacheManager;

public class EhcacheUtil {

	private static final String path = "/ehcache.xml";

	private URL url;

	private CacheManager manager;

	private static EhcacheUtil ehCache;

    private EhcacheUtil(String path) {  
        url = getClass().getResource(path);  
    }
}
