package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class FastDFSClient {
    // 静态类中读取fastdfs的配置：fdfs_client.conf
    static {
        try {
            // 获取配置文件
            ClassPathResource file = new ClassPathResource("fdfs_client.conf");
            // 加载配置文件 tracker的信息：ip和端口号
            ClientGlobal.init(file.getPath());
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    // 文件上传
    public static String[] upload(FastDFSFile file) throws IOException, MyException {
        StorageClient storageClient = getStorageClient();
        return storageClient.upload_file(file.getContent(), file.getExt(), null);
    }

    /***
     * 获取文件信息
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        StorageClient storageClient = getStorageClient();
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /***
     * 文件下载
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) throws IOException, MyException {
        StorageClient storageClient = getStorageClient();
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }

    /***
     * 文件删除实现
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static void deleteFile(String groupName,String remoteFileName) throws IOException, MyException {
        StorageClient storageClient = getStorageClient();
        storageClient.delete_file(groupName, remoteFileName);
    }

    /***
     * 获取组信息 第几组
     * @param groupName :组名
     */
    public static StorageServer getStorages(String groupName) throws IOException {
        // 通过trackerServer就可以得到storage的信息
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer, groupName);
    }

    /***
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        // 通过trackerServer就可以得到storage的信息
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /***
     * 获取Tracker服务地址
     */
    public static String getTrackerUrl() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        return trackerServer.getInetSocketAddress().getHostName() + ":" + ClientGlobal.getG_tracker_http_port();
    }

    // 进一步封装
    public static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getConnection();
    }

    public static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        return new StorageClient(trackerServer, null);
    }

// test
    public static void main(String[] args) throws IOException, MyException {
        // 172.16.235.2:8080/group1/M00/00/00/rBDrAmBA3M2AectpAAO1WLzWet8743.jpg

//        // 获取文件信息
//        FileInfo fileInfo = getFile("group1", "M00/00/00/rBDrAmBA3M2AectpAAO1WLzWet8743.jpg");
//        System.out.println("fileInfo: " + fileInfo);

//        // 下载文件
//        InputStream is = downFile("group1", "M00/00/00/rBDrAmBA3M2AectpAAO1WLzWet8743.jpg");
//        FileOutputStream fos = new FileOutputStream("/Users/JC/Desktop/test/3.jpg");
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = is.read(buffer)) != -1) {
//            fos.write(buffer, 0, len);
//        }
//        // 将缓冲区buffer中的数据都写入到目的地
//        fos.flush();
//        fos.close();
//        is.close();

//        // 删除文件
//        // 172.16.235.2:8080/group1/M00/00/00/rBDrAmBA3amAWSy3AAC76iwXAHU331.jpg
//        deleteFile("group1", "M00/00/00/rBDrAmBA3amAWSy3AAC76iwXAHU331.jpg");

//        // 通过组名获取组信息  0
//        StorageServer group1 = getStorages("group1");
//        System.out.println(group1.getStorePathIndex());

//        // 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
//        ServerInfo[] serverInfo = getServerInfo("group1", "M00/00/00/rBDrAmBA3M2AectpAAO1WLzWet8743.jpg");
//        for (ServerInfo info : serverInfo) {
//            System.out.println(info.getIpAddr() + ": " + info.getPort()); // 172.16.235.2: 23000
//        }

//        // 获取Tracker服务地址
//        System.out.println(getTrackerUrl()); // 172.16.235.2:8080
    }
}
