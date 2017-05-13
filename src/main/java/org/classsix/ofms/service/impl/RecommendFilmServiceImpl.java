package org.classsix.ofms.service.impl;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.classsix.ofms.domin.FilmScore;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.repository.ScoreFilmRepository;
import org.classsix.ofms.service.RecommendFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/5/3.
 */
@Service
public class RecommendFilmServiceImpl implements RecommendFilmService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScoreFilmRepository scoreFilmRepository;

    public Page<MovieItem> recommendByBuyCount(Pageable page) {
        Page<MovieItem> movieItems;
        movieItems = movieRepository.orderByBuyCount(page);
        return movieItems;
    }

    public List<MovieItem> recommendByFavor(int uid) throws Exception{
        FastByIDMap<PreferenceArray> genericUserPreferenceArrayFastByIDMap = new FastByIDMap<>();
        List<MovieItem> movieItems = new ArrayList<>();
        try {
            for(int i = 0;i < 10;i++) {
                List<FilmScore> oneFilmScore = scoreFilmRepository.findByUid(i + 1);
                List<Preference> onePreferences = new ArrayList<>();
                for(FilmScore f : oneFilmScore) {
                    Preference preference = new GenericPreference(f.getUid(), f.getFid(), f.getScore());
                    onePreferences.add(preference);
                }
                GenericUserPreferenceArray oneGenericUserPreferenceArray = new GenericUserPreferenceArray(onePreferences);
                genericUserPreferenceArrayFastByIDMap.put(i + 1, oneGenericUserPreferenceArray);
            }
//            List<FilmScore> filmScores = scoreFilmRepository.findAll();
//            List<Preference> preferences = new ArrayList<>();
//            for(FilmScore f : filmScores) {
//                Preference preference = new GenericPreference(f.getUid(), f.getFid(), f.getScore());
//                preferences.add(preference);
//            }
//            GenericUserPreferenceArray genericUserPreferenceArray = new GenericUserPreferenceArray(preferences);
//
//            genericUserPreferenceArrayFastByIDMap.put(1, genericUserPreferenceArray);
            GenericDataModel genericDataModel = new GenericDataModel(genericUserPreferenceArrayFastByIDMap);
            movieItems = recommendResult(uid, genericDataModel);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return movieItems;
    }

    public List<MovieItem> recommendResult(int uid, DataModel dataModel) throws Exception{
        List<MovieItem> movieItems = new ArrayList<>();
        try {
            UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(9, userSimilarity, dataModel);
//            UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.01, userSimilarity, dataModel);
//            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
            Recommender recommender = new GenericBooleanPrefUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
            List<RecommendedItem> recommend = recommender.recommend(uid, 10);
            for (RecommendedItem r : recommend) {
                MovieItem movieItem = new MovieItem();
                movieItem = movieRepository.findById(r.getItemID());
                movieItems.add(movieItem);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return movieItems;
    }
}
