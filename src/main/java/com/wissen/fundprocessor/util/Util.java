package com.wissen.fundprocessor.util;

import java.io.Closeable;
import java.io.IOException;

public class Util {

	private Util() {}
	
    public static void closeResource(Closeable resource) {
    	if(resource != null) {
    		try {
    			resource.close();
    		} catch(IOException e) {}
    	}
    }
}
