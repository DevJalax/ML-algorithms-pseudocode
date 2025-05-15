import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationSystem {

    /**
     * Recommend top N items based on average ratings.
     *
     * @param itemRatings Map of item IDs to average ratings.
     * @param topN Number of items to recommend.
     * @return List of recommended item IDs.
     */
    public List<String> recommendTopItems(Map<String, Double> itemRatings, int topN) {
        return itemRatings.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Example usage
    public static void main(String[] args) {
        RecommendationSystem recommender = new RecommendationSystem();

        // Sample item ratings (itemId -> average rating)
        Map<String, Double> itemRatings = Map.of(
            "item1", 4.5,
            "item2", 3.8,
            "item3", 4.7,
            "item4", 4.2,
            "item5", 3.5
        );

        int topN = 3;
        List<String> recommendedItems = recommender.recommendTopItems(itemRatings, topN);

        System.out.println("Top " + topN + " recommended items: " + recommendedItems);
    }
}
