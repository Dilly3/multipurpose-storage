package com.dilly3.multipurposedrive.services;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

 @Service  public class ColorConstants {

    public static final String LOG_INFO = "\u001B[32m %s \u001B[0m" + "";
 public static final String LOG_ERROR = "\u001B[31m %s \u001B[0m" + "";
public static final String LOG_DEBUG = "\u001B[34m %s \u001B[0m" + "";


   public ColorConstants() {

    }

}
