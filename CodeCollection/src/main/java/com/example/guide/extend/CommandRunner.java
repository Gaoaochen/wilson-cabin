package com.example.guide.extend;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandRunner {

    //通过java执行linux命令的方式对性能的消耗比较大
    public static String doCommand(String command) {

        Process process = null;
        Scanner scanner;
        String result = "";
        InputStream inputStream = null;
        try {
            if (command.contains("|") || command.contains("<") || command.contains(">")) {
                List<String> list = new ArrayList<>();
                list.add("/bin/sh");
                list.add("-c");
                list.add(command);
                process = Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
            } else {
                process = Runtime.getRuntime().exec(command);
            }
            process.waitFor(1, TimeUnit.SECONDS);
            inputStream = process.getInputStream();
            scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                result += scanner.nextLine();
            }
            result = command + '\n' + result;
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        doCommand("ps -ef|grep x");
    }
}
