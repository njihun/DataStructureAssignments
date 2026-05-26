package Domain;

public record Product(int sales, int views, int reviews, double avgRate) implements Comparable<Product> {
    private static final double SALES_WEIGHT = 0.65;
    private static final double VIEWS_WEIGHT = 0.25;
    private static final double REVIEWS_WEIGHT = 0.1;

    public double score() {
        return (sales * SALES_WEIGHT + views * VIEWS_WEIGHT + reviews * REVIEWS_WEIGHT) * avgRate;
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.score(), other.score());
    }
}
