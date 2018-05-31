package com.autotrading.base.util;

/**
 * Created by IBM on 2016/8/2.
 * uri路径解析器
 */
public class UriPathResolver {

    /**
     * 从uri中获取domain
     * @param uri       路径
     * @return          domain
     */
    public static String  getDomain(String uri){
        String domain=uri;
        domain=domain.substring(domain.indexOf("//")+2);
        domain=domain.substring(0,domain.indexOf("/"));

        return domain;
    }

    /**
     * 从uri路径中获取scheme
     * @param uri       路径
     * @return          scheme
     */
    public static String getScheme(String uri){
        String scheme=uri;
        scheme=scheme.substring(0,scheme.indexOf("://"));
        return scheme;
    }

    /**
     * 获取端口号
     * @param uri       路径
     * @return          端口号
     */
    public static String getPort(String uri){
        String port=uri;
        port=port.replace("://","#");
        int start=port.indexOf("#");
        int end=port.indexOf("/");
        int i=port.indexOf(":");

        if(i>start&&i<end){
            port=port.substring(i+1,end);
            return port;
        }else{
            return null;
        }
    }

    /**
     * 返回基础路径
     * @param uri       路径
     * @return          基础路径
     */
    public static String getBasePath(String uri){
        String basePath=uri;
        basePath=basePath.replace("://","#");
        basePath=basePath.substring(basePath.indexOf("#")+1);

        return basePath;
    }

    private UriPathResolver(){
        //私有化构造函数，防止继承。
    }
}
