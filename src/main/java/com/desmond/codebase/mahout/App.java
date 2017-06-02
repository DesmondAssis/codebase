//package com.desmond.codebase.mahout;
//
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
//import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
//import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.recommender.Recommender;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//
//import java.io.*;
//import java.util.List;
//
//import static com.desmond.codebase.print.Print.print;
//import static com.desmond.codebase.print.Print.printnb;
//
///**
// * Created by Li.Xiaochuan on 15/12/7.
// */
//public class App {
//    protected static final String inputFile = "/usr/local/mahout/exercise/ml-1m/ratings.dat";
//    protected static final String outputFile = "/usr/local/mahout/exercise/ml-1m/ratings-star.csv";
//
//    public static void main(String[] args) throws IOException, TasteException {
//        createCVSRatingsFile();
//        File ratingFile = new File(outputFile);
//        FileDataModel model = new FileDataModel(ratingFile);
//        TanimotoCoefficientSimilarity userSimilarity = new TanimotoCoefficientSimilarity(model);
////        userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(model));
//        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, userSimilarity, model);
//
//        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);
//        for(LongPrimitiveIterator it = model.getUserIDs(); it.hasNext(); ) {
//            long userId = it.nextLong();
//            List<RecommendedItem> recommendedItemList = recommender.recommend(userId, 10);
//            if(recommendedItemList.size() == 0) {
//                printnb("user:" + userId + ",no recommendations");
//            }
//
//            for(RecommendedItem item : recommendedItemList) {
//                print("user:" + userId + ", recommendations:" + item);
//            }
//            printnb("");
//        }
//
//    }
//
//    private static void createCVSRatingsFile() throws IOException {
//        BufferedReader br = new BufferedReader(
//                new FileReader(inputFile)
//        );
//
//        BufferedWriter bw = new BufferedWriter(
//                new FileWriter(outputFile)
//        );
//
//        String line = null;
//        String line2Write = null;
//        String[] tmp;
//        int i = 0;
//        while( (line = br.readLine()) != null
//                && i < Long.MAX_VALUE) {
//            i++;
//            tmp = line.split("::");
//            line2Write = tmp[0] + "," + tmp[1] + "," + tmp[2];
//            bw.write(line2Write);
//            bw.newLine();
//            bw.flush();
//        }
//
//        br.close();
//        bw.close();
//    }
//}
