package com.homework.myapplication.util;

import java.util.UUID;


public class GuidGeneratorUtil {
  public static String newGuid(){
    return UUID.randomUUID().toString().replaceAll("-","");
  }
}
