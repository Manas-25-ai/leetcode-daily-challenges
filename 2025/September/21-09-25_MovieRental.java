import java.util.*;

class MovieRentingSystem {

    // Helper: pair of (price, shop) for available movies
    record ShopEntry(int price, int shop) implements Comparable<ShopEntry> {
        @Override
        public int compareTo(ShopEntry o) {
            if (price != o.price) return price - o.price;
            return shop - o.shop;
        }
    }

    // Helper: triple (price, shop, movie) for rented movies
    record Rental(int price, int shop, int movie) implements Comparable<Rental> {
        @Override
        public int compareTo(Rental o) {
            if (price != o.price) return price - o.price;
            if (shop != o.shop) return shop - o.shop;
            return movie - o.movie;
        }
    }

    // Maps and sets to maintain state
    private final Map<Integer, TreeSet<ShopEntry>> available = new HashMap<>();   // movie -> shops sorted by (price, shop)
    private final Map<Integer, Map<Integer, Integer>> shopPrices = new HashMap<>(); // movie -> (shop -> price)
    private final TreeSet<Rental> rented = new TreeSet<>();  // all rented movies sorted

    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            available.computeIfAbsent(movie, _ -> new TreeSet<>())
                     .add(new ShopEntry(price, shop));
            shopPrices.computeIfAbsent(movie, _ -> new HashMap<>())
                      .put(shop, price);
        }
    }

    // Get up to 5 cheapest shops for a movie
    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        if (available.containsKey(movie)) {
            for (ShopEntry entry : available.get(movie)) {
                result.add(entry.shop());
                if (result.size() == 5) break;
            }
        }
        return result;
    }

    // Rent a movie from a shop
    public void rent(int shop, int movie) {
        int price = shopPrices.get(movie).get(shop);
        available.get(movie).remove(new ShopEntry(price, shop));
        rented.add(new Rental(price, shop, movie));
    }

    // Drop (return) a movie to a shop
    public void drop(int shop, int movie) {
        int price = shopPrices.get(movie).get(shop);
        available.get(movie).add(new ShopEntry(price, shop));
        rented.remove(new Rental(price, shop, movie));
    }

    // Report top 5 rented movies
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        for (Rental r : rented) {
            result.add(Arrays.asList(r.shop(), r.movie()));
            if (result.size() == 5) break;
        }
        return result;
    }
}
