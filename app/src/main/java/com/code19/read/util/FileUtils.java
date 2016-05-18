package com.code19.read.util;

import android.content.Context;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Gh0st on 2016/4/29 029.
 */
public class FileUtils {
    /**
     * 将字符串写入文件
     *
     * @param filename 完整的文件路径
     * @param content  需要写入文件的字符出
     * @return
     */
    public static boolean writeFile(String filename, String content) {
        boolean isSuccess = false;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename, false));
            bufferedWriter.write(content);
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }


    /**
     * 从文件读出字符串
     *
     * @param filename 完整的文件路径
     * @return 从文件读取的字符串
     */
    public static String readFile(String filename) {
        File file = new File(filename);
        BufferedReader bufferedReader = null;
        String str = null;
        try {
            if (file.exists()) {
                bufferedReader = new BufferedReader(new FileReader(filename));
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 格式化文件大小
     *
     * @param context
     * @param size
     * @return
     */
    public static String formatFileSize(Context context, long size) {
        return Formatter.formatFileSize(context, size);
    }


}
