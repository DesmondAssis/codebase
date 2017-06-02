//package com.desmond.codebase.mahout;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.recommender.Recommender;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import static com.desmond.codebase.print.Print.print;
//import static com.desmond.codebase.print.Print.printnb;
//
///**
// * http://blog.fens.me/hadoop-mahout-maven-eclipse/
// * Created by Li.Xiaochuan on 15/12/8.
// */
//public class UserCF {
//    final static int NEIGHBORHOOD_NUM = 2;
//    final static int RECOMMENDER_NUM = 3;
//
//    public static void main(String[] args) throws IOException, TasteException {
//        String file = App.outputFile;
//        DataModel model = new FileDataModel(new File(file));
//        UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(model);
//        NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, userSimilarity, model);
//        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);
//        LongPrimitiveIterator iterator = model.getUserIDs();
//
//        while (iterator.hasNext()) {
//            long uuid = iterator.nextLong();
//            List<RecommendedItem> list = recommender.recommend(uuid, RECOMMENDER_NUM);
//            printnb("id=" + uuid);
//            for(RecommendedItem item : list) {
//                print("[" + item.getItemID() + ":" + item.getValue() + "]");
//            }
//            printnb("");
//
//        }
//    }
//}
