package org.leochen.utils;

import lombok.extern.slf4j.Slf4j;
import org.leochen.sample.Sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class Util {

    public static long getPID() {
        String processName
                = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }

    public static void simpleSleep(long millis){
        System.out.println("This is simple Sleep, thread will Sleep for " + millis + " milliseconds");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean canBeNumber(String input) {
        if (input == null) { return false; }
        try {
            Integer check = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            log.info("{} 不是數字. {}", input, e.getMessage());
            return false;
        }
    }

    /* Data Sort */
    public static ArrayList<Integer> sortStringList(List<String> inputs) {
        ArrayList<Integer> intList = new ArrayList();

        inputs.forEach((item) -> {
            if (canBeNumber(item)) {
                intList.add( Integer.parseInt(item) );
            }
        });

        intList.sort(Integer::compareTo);

        return intList;
    }

    public static String getClassLocation() {
        return Util.class.getResource("/").getPath().replaceAll("classes/", "").replaceAll("%20", "\\ ");
    }

    public static Properties loadProperties(String fileName){
        Properties prop = new Properties();
        try{
            InputStream input = Sample.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(input);
        } catch (FileNotFoundException e) {
            prop = null;
            e.printStackTrace();
        }catch (IOException e){
            prop = null;
            e.printStackTrace();
        }
        return prop;
    }

    public static void makeFolderExist(String folderPath){
        File file = new File(folderPath);
        if (file.isDirectory()) {
            log.info("{} 目錄存在", folderPath);
        }else{
            log.info("{} 目錄不存在，建立目錄", folderPath);
            boolean createdFlag = file.mkdirs(); // mkdir: create single folder / mkdirs: create multi-level folder
            log.info("目錄建立結果: {}", createdFlag);
        }
    }

    public static void listFiles(String folderPath){
        listFiles(folderPath, "*");
    }

    public static void listFiles(String folderPath, String fileNamePattern){

        log.info("列出指定目錄下副檔名({})的檔案: ", fileNamePattern);
        Path path = Path.of(folderPath);
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(path, fileNamePattern)){
            for (Path entry: stream){
                log.info("" + entry.getFileName());
            }
        }
        catch (IOException e){
            log.error("error while retrieving update configuration files " + e.getMessage());
        }
    }

}
