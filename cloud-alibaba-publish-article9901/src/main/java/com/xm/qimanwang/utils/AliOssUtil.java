package com.xm.qimanwang.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;

public class AliOssUtil {
    public enum Bucket{
        BUCKET1("tianxiaoming");

        private String bucketName;
        Bucket(String bucketName){
            this.bucketName = bucketName;
        }

        public String getBucket(){
            return this.bucketName;
        }
    }


    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private final static String endpoint="https://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private final static String accessKeyId="TanLTAI5tN1XHoGXbGDpQTDyoru";
    private final static String accessKeySecret="XaordXcQFXuEJelbtZjiiSSVu9Wt8Mdqu";


    /**
     * @param bucketName
     * @param objectName <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     */
    /*---------------文件管理-----------------*/
    //1.删除文件
    public static void isFile(String bucketName,String objectName){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //1.上传文件
    public static void uploadFile(String bucketName,String objectName) throws IOException{
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("C:\\MyFile\\picture\\test.jpeg");
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void main(String[] args) throws IOException{
        AliOssUtil.uploadFile(Bucket.BUCKET1.bucketName, "images/myImg/test2.jpeg");
    }


}
