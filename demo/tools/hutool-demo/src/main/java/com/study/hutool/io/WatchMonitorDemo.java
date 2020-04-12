package com.study.hutool.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

public class WatchMonitorDemo {
    public static void main(String[] args) {
        String watchDirStr = "watch-dir";
        Path watchPath = null;
        try {
            watchPath = Paths.get(new ClassPathResource(watchDirStr).getUrl().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        WatchMonitor watchMonitor = WatchMonitor.create(watchPath, WatchMonitor.EVENTS_ALL);
        watchMonitor.setWatcher(new DelayWatcher(new SimpleWatcher(){
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                System.out.println(StrUtil.format("文件路径({})被创建", event.context().toString()));
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                System.out.println(StrUtil.format("文件路径({})被修改", event.context().toString()));
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                System.out.println(StrUtil.format("文件路径({})被删除", event.context().toString()));
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                System.out.println(StrUtil.format("文件路径({})的时间被丢失", event.context().toString()));
            }
        }, 100));
        watchMonitor.setMaxDepth(Integer.MAX_VALUE);
        watchMonitor.start();
        try {
            Thread.sleep(1000);
            Path filePath = Paths.get(watchPath.toString(), "file.text");
            Files.createFile(filePath);
            Files.write(filePath, "my name is chenkd.".getBytes(StandardCharsets.UTF_8));
            System.out.println("wait wait wait.....and delete file.");
            Thread.sleep(1000 * 3);
            Files.delete(filePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wait a moment and thread will close ");
        watchMonitor.close();
    }
}
