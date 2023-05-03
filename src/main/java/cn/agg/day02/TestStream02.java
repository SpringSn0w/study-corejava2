package cn.agg.day02;

import cn.agg.day01.TestStream01;
import com.sun.java_cup.internal.runtime.Scanner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestStream02 {
    public static Pattern compile = Pattern.compile("\\PL+");
    public static void main(String[] args) throws URISyntaxException, IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get(TestStream01.class.getClassLoader().getResource("alice30.txt").toURI())),
                StandardCharsets.UTF_8);

        // 配合正则创建流
        Stream<String> words = compile.splitAsStream(contents);
        words.forEach(System.out::println);

        // 文件所有内容
        Stream<String> lines = Files.lines(Paths.get(TestStream01.class.getClassLoader().getResource("alice30.txt").toURI()));



    }
}
