package com.lms.app.com.lms.app.Controller;
/*
 * for opinion mining  we are using  Spring NLP 
 * this is NLP configurations 
 *  
 */

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class Pipeline {

    private static Properties properties;
    private static String propertiesName="tokenize,ssplit,pos,lemma,parse,sentiment";
   private static StanfordCoreNLP stanfordCoreNLP;



   private Pipeline(){


   }


   static {

       properties =new Properties();
       properties.setProperty("annotators" ,propertiesName);
   }

     public static StanfordCoreNLP getPipeline(){

       if (stanfordCoreNLP == null){

           stanfordCoreNLP = new StanfordCoreNLP(properties);
       }return stanfordCoreNLP;

     }

}
