import java.io.*;
import java.util.*;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;


public class BibleScholar {
    public static void main(String[] args) {
        //long a=System.currentTimeMillis();
        BibleScholar bibleScholar = new BibleScholar();
        for (String result : bibleScholar.resolve()) {
            System.out.println(result);
        }
        // System.out.println((System.currentTimeMillis()-a)/1000f+ "秒");

    }

    private HashMap<String, Integer> statistics() {
        HashMap<String, Integer> wordStatistics = new HashMap<>();
        ArrayList<String> stopWords = new ArrayList<>();
        try {
            String stopWordsPath = this.getClass().getClassLoader().getResource("stopwords.txt").getPath();
            File stopWordsFile = new File(stopWordsPath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(stopWordsFile));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String eachLine;
            while (true) {
                eachLine = bufferedReader.readLine();
                if (eachLine != null) {
                    stopWords.add(eachLine);
//                  System.out.println(eachLine);
                } else {
                    reader.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String biblePath = this.getClass().getClassLoader().getResource("kjv.txt").getPath();
            File bible = new File(biblePath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(bible));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String content;
            while (true) {
                content = bufferedReader.readLine();
                if (content != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(content, " ");
                    while (stringTokenizer.hasMoreTokens()) {
                        String word = stringTokenizer.nextToken();
                        String newWord = word.replaceAll("[^a-zA-Z]", "");
                        if (newWord.length() > 0) {
                            String wordLowCase = newWord.toLowerCase();
                            if (!stopWords.contains(wordLowCase)) {
                                if (!wordStatistics.containsKey(wordLowCase)) {
                                    wordStatistics.put(wordLowCase, 1);
                                } else {
                                    wordStatistics.put(wordLowCase, wordStatistics.get(wordLowCase) + 1);
                                }
                            }
                        }
                    }
                } else {
                    reader.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordStatistics;
    }


    public String[] resolve() {
        HashMap<String, Integer> words = statistics();
        class PQComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                if (words.get(o1) > words.get(o2)) {
                    return 1;
                } else if (words.get(o1) < words.get(o2)) {
                    return -1;
                } else if (o1.compareTo(o2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        MaxPQ<String> maxPQ = new MaxPQ<>(new PQComparator());
        MinPQ<String> minPQ = new MinPQ<>(new PQComparator());
        for (String key : words.keySet()) {
            maxPQ.insert(key);
            minPQ.insert(key);
        }


        String[] result = new String[24];
        Iterator<String> iterator1 = maxPQ.iterator();
        for (int i = 0; i < 12; i++) {
            String str = iterator1.next();
            result[i] = str + ":" + words.get(str).toString();
        }
        Iterator<String> iterator2 = minPQ.iterator();
        for (int i = 11; i >= 0; i--) {
            String str = iterator2.next();
            result[12 + i] = str + ":" + words.get(str).toString();
        }
        return result;

    }
}

