package cn.agg.day01;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStream01 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 对某本书的长单词计数
        // 将所有单词放到一个列表
        /*String contents = new String(
                Files.readAllBytes(Paths.get("E:\\study-corejava2\\src\\main\\resources\\alice30.txt")),
                StandardCharsets.UTF_8);*/
        String contents = new String(
                Files.readAllBytes(Paths.get(TestStream01.class.getClassLoader().getResource("alice30.txt").toURI())),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        // 普通迭代
        int count = 0;
        for (String word : words) {
            if (word.length() > 12) count++;
        }
        System.out.println("count = " + count);

        // 流迭代 顺序流
        long count1 = words.stream()
                .filter(word -> word.length() > 12)
                .count();
        System.out.println("count1 = " + count1);

        // 并行流
        long count2 = words.parallelStream().filter(word -> word.length() > 12).count();
        System.out.println("count2 = " + count2);

        // 前五个长串
        words.parallelStream()
                .filter(word -> word.length() > 12)
                .limit(5)
                .forEach(System.out::println);

        // 创建流并添加元素
        Stream<String> stringStream = Stream.of("changan", "tianming", "wukong");
        Stream<String> stream = Stream.of(contents.split("\\PL+"));
        Stream<List<Object>> listStream = Stream.of(Arrays.asList('a', 'b', new String(), new Object()));

        // 创建空流
        Stream<Object> empty = Stream.empty();

        // 无限流
//        Stream.generate(()->1).forEach(System.out::println);

        // 0 1 2 3 4 ... 10
        Stream.iterate(BigInteger.ZERO,n -> n.add(BigInteger.ONE))
                .limit(10)
                .forEach(System.out::println);


//        BigInteger limit = BigInteger.TEN;
//        Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ZERO,
//                n -> n.compareTo(limit) < 0,
//                n -> n.add(BigInteger.ONE));

    }
}
