package com.example.federation.datafetchers;

import com.netflix.dgs.codgen.generated.types.Review;
import com.netflix.dgs.codgen.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;

import java.util.List;
import java.util.Map;


@DgsComponent
public class ReviewsDataFetcher {

    private static final Map<String, List<Review>> reviews = Map.of(
            "1", List.of(new Review(1), new Review(2), new Review(3)),
            "2", List.of(new Review(4), new Review(5))
    );

    @DgsEntityFetcher(name = "Show")
    public Show showExternalEntityFetcher(Map<String, Object> values) {
        return new Show((String) values.get("id"), null);
    }

    @DgsData(parentType = "Show", field = "reviews")
    public List<Review> reviewsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment) {
        Show show = dataFetchingEnvironment.getSource();
        return reviews.get(show.getId());
    }

}
