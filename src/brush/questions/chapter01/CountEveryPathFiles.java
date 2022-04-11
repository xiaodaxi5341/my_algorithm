package brush.questions.chapter01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: my_algorithm
 * @description 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 * @author: 34371
 * @create: 2022-04-01 22:19
 **/
public class CountEveryPathFiles {


    public static int countFiles(String path) {
        File directory = new File(path);
        int num = 0;

        Queue<File> queue = new LinkedList<>();
        queue.add(directory);
        while (!queue.isEmpty()){
            File[] files = queue.poll().listFiles();
            for (File file : files){
                if (file.isDirectory()){
                    queue.add(file);
                }else{
                    num++;
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(countFiles("D:\\develop"));
    }

}
