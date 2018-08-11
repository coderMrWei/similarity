package org.xm;

/**
 * @author xuming
 */
public class BaseDemo {
    public static void main(String[] args) {
        System.out.println("首次编译运行...");
        double result = Similarity.conceptSimilarity("换一个pmo","updatepmo");
        System.out.println(result);
    }
}
