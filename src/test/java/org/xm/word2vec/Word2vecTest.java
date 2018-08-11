package org.xm.word2vec;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import org.junit.Test;
import org.xm.tokenizer.Tokenizer;

import java.util.List;

/**
 * @author xuming
 */
public class Word2vecTest {
    private static final Segment SEGMENT = HanLP.newSegment().enableAllNamedEntityRecognize(true)
            .enableCustomDictionary(true).enableMultithreading(4);
    private static final String RAW_CORPUS = "corpus/tianlongbabu.txt";
    private static final String RAW_CORPUS_SPLIT = "corpus/tianlongbabu.split.txt";
    private static final String RAW_CORPUS_SPLIT_MODEL = "corpus/tianlongbabu.split.txt.model";

    public static final String RAW_QTALK_CORPUS = "qtalk/searchPmo.log";

    private static final String RAW_QTALK_SPLIT = "qtalk/searchPmo.split.txt";
    private static final String RAW_QTALK_SPLIT_MODEL = "qtalk/searchPmo.split.txt.model";


    @Test
    public void trainModel() throws Exception {
        HanLP.Config.ShowTermNature = false;// 关闭词性标注
        Tokenizer.fileSegment(SEGMENT, RAW_CORPUS, RAW_CORPUS_SPLIT);
        String outputModelPath = Word2vec.trainModel(RAW_CORPUS_SPLIT, RAW_CORPUS_SPLIT_MODEL);
        System.out.println("outputModelPath：" + outputModelPath);
    }

    @Test
    public void trainModelQtalk()throws Exception{
        HanLP.Config.ShowTermNature = false;// 关闭词性标注
        Tokenizer.fileSegment(SEGMENT, RAW_QTALK_CORPUS, RAW_QTALK_SPLIT);
        String outputModelPath = Word2vec.trainModel(RAW_QTALK_SPLIT, RAW_QTALK_SPLIT_MODEL);

        System.out.println("outputModelPath：" + outputModelPath);
    }

    @Test
    public void testHomoionymQtalk() throws Exception {
        List<String> result = Word2vec.getHomoionym(RAW_QTALK_SPLIT_MODEL, "pmo", 10);
        System.out.println("pmo 近似词：" + result);
    }

    @Test
    public void testHomoionym() throws Exception {
        List<String> result = Word2vec.getHomoionym(RAW_CORPUS_SPLIT_MODEL, "武功", 10);
        System.out.println("武功 近似词：" + result);
    }
    @Test
    public void testHomoionymNameQtalk()throws Exception{
        String model = RAW_QTALK_SPLIT_MODEL;
        List<String> result = Word2vec.getHomoionym(model, "pmo", 10);
        System.out.println("pmo 近似词：" + result);

        List<String> result2 = Word2vec.getHomoionym(model, "qunar", 10);
        System.out.println("qunar 近似词：" + result2);

        List<String> result3 = Word2vec.getHomoionym(model, "qtalk", 10);
        System.out.println("qtalk 近似词：" + result3);
    }

    @Test
    public void testHomoionymName() throws Exception {
        String model = RAW_CORPUS_SPLIT_MODEL;
        List<String> result = Word2vec.getHomoionym(model, "乔帮主", 10);
        System.out.println("乔帮主 近似词：" + result);

        List<String> result2 = Word2vec.getHomoionym(model, "阿朱", 10);
        System.out.println("阿朱 近似词：" + result2);

        List<String> result3 = Word2vec.getHomoionym(model, "少林寺", 10);
        System.out.println("少林寺 近似词：" + result3);
    }

}