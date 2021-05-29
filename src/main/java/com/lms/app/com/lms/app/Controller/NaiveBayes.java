package com.lms.app.com.lms.app.Controller;


import java.io.*;
import java.util.*;


public class NaiveBayes {

    private final Set<String> vocabularly;
    private final HashMap<String, Integer> positiveWordList;
    private final HashMap<String, Integer> negativeWordList;
    private int numberOfPositiveDocuments;
    private int numberOfNegativeDocuments;

    public NaiveBayes() {
        this.vocabularly = new HashSet<>();
        this.positiveWordList = new HashMap<>();
        this.negativeWordList = new HashMap<>();
    }

    // Get array list of words from a file
    public ArrayList<String> getWords(String filepath) throws FileNotFoundException {

        File directory = new File(filepath);
        File[] filesInDirectory = directory.listFiles();
        ArrayList<String> commonWords;
        ArrayList<String> wordList = new ArrayList<>();

        // Add the number of positive and negative files
        if (filepath.contains("pos")) {
            numberOfPositiveDocuments = filesInDirectory.length;
        } else {
            numberOfNegativeDocuments = filesInDirectory.length;
        }

        // Get array list of of common words from text file

//        commonWords = getCommonWordsList("/common_words.txt");
        commonWords= new ArrayList<String>(
                Arrays.asList("the","be","to","of","and","a","in","that","have","I","it","for","on","with","he","as","you","do","at","this","but","his","by","from","they","we","say","her","she","or","an","will","my","one","all","would","there","their","what","so","up","out","if","about","who","get","which","go","me","when","make","can","like","time","no","just","him","know","take","into","year","your","some","could","them","see","other","than","then","now","look","only","come","its","over","also","back","after","use","two","how","our","first","way","even","new","want","because","these","give","day","most","us",",",".",":",";","!","|","$","%","*","(",")","�","&","^","@","~","#","/","|",">","<","\"","?","=","+","_","�","/"));
        // For each file convert the text to lower case and remove the common words
        for (File file : filesInDirectory) {
            String contents = readFile(file);
            contents = contentsToLowerCase(contents);
            contents = sanitizeContents(contents, commonWords);
            String[] wordArray = contents.split("\\s+");
            for (String word : wordArray) {
                String w = word.replaceAll("[^A-Za-z0-9]", "");
                wordList.add(w);
            }
        }
        return wordList;
    }

    // Finds the probability of a word for each class (negative - positive)
    public void computeWordProbability(HashMap<String, Double> wordProbability, int totalWords,HashMap<String, Integer> catalog, Set<String> vocabulary) {

        Iterator<String> it = this.vocabularly.iterator();
        while (it.hasNext()) {
            String word = it.next();
            if ((word != null) && (!word.equals(""))) {
                double probWordGivenClass;
                int wordFrequency;
                double wordLogValue;
                // P(W|C) = number of times word occurs in class + 1 / total number of words in class + 2
                if (catalog.containsKey(word)) {
                    wordFrequency = catalog.get(word);
                    wordLogValue = (double) (wordFrequency + 1) / (double) (totalWords + 2);
                    probWordGivenClass = Math.log(wordLogValue);
                    wordProbability.put(word, probWordGivenClass);
                } else {
                    wordLogValue = 1.0 / (double) (totalWords + 2);
                    probWordGivenClass = Math.log(wordLogValue);
                    wordProbability.put(word, probWordGivenClass);
                }
            }
        }
    }

    // Classify a test document
    public String testReviewClassification(ArrayList<String> reviewWords, double probabilityOfClassPositive,double probabilityOfClassNegative,HashMap<String, Double> wordVocabProbabilityPositive,HashMap<String, Double> wordVocabProbabilityNegative) {

        String reviewClass = null;
        double positiveReviewResult = probabilityOfClassPositive;
        double negativeReviewResult = probabilityOfClassNegative;
        String word;

        // Find probability of  train review for positive train reviews
        for (int x = 0; x < reviewWords.size(); x++) {
            word = reviewWords.get(x);

            if (wordVocabProbabilityPositive.containsKey(word)) {
                positiveReviewResult += wordVocabProbabilityPositive.get(word);
            }
        }

        // Find probability of train review for negative train reviews
        for (int y = 0; y < reviewWords.size(); y++) {
            word = reviewWords.get(y);

            if (wordVocabProbabilityNegative.containsKey(word)) {
                negativeReviewResult += wordVocabProbabilityNegative.get(word);
            }
        }
        // Get the best value from the Positive and negative values
        double result = Math.max(positiveReviewResult, negativeReviewResult);

        // Set the result variable to be Positive or Negative depending on result
        if (result == positiveReviewResult) {
            reviewClass = "Positive";
        } else {
            reviewClass = "Negative";
        }

        return reviewClass;
    }

    // Get  an array of files
    public File[] getReviews(String filepath) {

        File directory = new File(filepath);
        return directory.listFiles();
    }

    // Read the contents of a file and return it as a String
    public String readFile(File file) {

        String text = null;
        String fileContents = null;

        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((text = br.readLine()) != null) {
                sb.append(text);
                sb.append("\n");
            }

            fileContents = sb.toString();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContents;
    }

    // Get array list of words from the common_words.txt
    private static ArrayList<String> getCommonWordsList(String filename) {

        ArrayList<String> listOfCommonWords = new ArrayList<>();

        try {
            InputStream inputStream = NaiveBayes.class.getClassLoader().getResourceAsStream(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            for (String word; (word = br.readLine()) != null;) {
                listOfCommonWords.add(word);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfCommonWords;
    }

    // Remove common words to decrease word map size and improve performance of algorithm
    private static String sanitizeContents(String contents, ArrayList<String> wordsToRemove) {

        String wordArray[] = contents.split("\\s+");
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(wordArray));
        String sanitizedContents = "";

        for (int x = 0; x < wordsToRemove.size(); x++) {

            String word = wordsToRemove.get(x);

            if (wordList.contains(word)) {

                wordList.removeAll(Collections.singleton(word));
            }
        }

        for (int x = 0; x < wordList.size(); x++) {
            sanitizedContents += wordList.get(x) + " ";
        }

        return sanitizedContents;
    }

    // Add a list of words to a catalog
    public HashMap<String, Integer> createCatalog(HashMap<String, Integer> wordMap, ArrayList<String> wordList) {

        for (int x = 0; x < wordList.size(); x++) {

            String word = wordList.get(x);

            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }

        return wordMap;
    }

    // Convert text lower case
    private static String contentsToLowerCase(String text) {
        return text.toLowerCase();
    }

    public Set<String> getVocabularly() {
        return vocabularly;
    }

    public HashMap<String, Integer> getPositiveWordList() {
        return positiveWordList;
    }

    public HashMap<String, Integer> getNegativeWordList() {
        return negativeWordList;
    }

    public double getNumberOfDocuments() {
        return numberOfPositiveDocuments + numberOfNegativeDocuments;
    }

    public double getNumberOfPositiveDocuments() {
        return numberOfPositiveDocuments;
    }

    public double getNumberOfNegativeDocuments() {
        return numberOfNegativeDocuments;
    }

}
