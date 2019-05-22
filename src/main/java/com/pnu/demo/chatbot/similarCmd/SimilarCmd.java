package com.pnu.demo.chatbot.similarCmd;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class SimilarCmd {


    public SimilarCmd() throws IOException {

        SentenceIterator iter = new LineSentenceIterator(new File("cmd.txt"));

        // Split on white spaces in the line to get words
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        int batchSize = 1000;
        int iterations = 3;
        int layerSize = 150;

        Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize) //# words per minibatch.
                .minWordFrequency(5) //
                .useAdaGrad(false) //
                .layerSize(layerSize) // word feature vector size
                .iterations(iterations) // # iterations to train
                .learningRate(0.025) //
                .minLearningRate(1e-3) // learning rate decays wrt # words. floor learning
                .negativeSample(10) // sample size 10 words
                .iterate(iter) //
                .tokenizerFactory(t)
                .build();
        vec.fit();

        System.out.println(vec.wordsNearest("학식",5));

    };
}
