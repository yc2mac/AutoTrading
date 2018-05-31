package com.autotrading.base.util;




import java.net.URLEncoder;

public class Test {

    public  static final void  main(String[]   args) throws  Exception {




        String x="12345#a#678#b#99#c#";

        String[] str=x.split("@");
          for (int i = 1; i < str.length; i=i+1) {

                String[] str2=str[i].split(" ");
                for (int j=0;j<str2.length;j=j+2)
                {
                    System.out.println(str2[j]);
                }
          }

    }


}
