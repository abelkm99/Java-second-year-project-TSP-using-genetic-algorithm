package tsp;

class ran {
    public static int maxGenerations = 1000;

    public void run() {
        int numCitites = 1000;
        City cities[] = new City[numCitites];

        for (int cityIndex = 0; cityIndex < numCitites; cityIndex++) {
            int xPos = (int) (Math.random() * 100);
            int yPos = (int) (Math.random() * 100);
            cities[cityIndex] = new City(xPos, yPos);
        }

        // initiate Genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.9, 2, 5);
        Population population = ga.initPopulation(cities.length);

        ga.evalPopulation(population, cities);
        Route startRoute = new Route(population.getFittest(0), cities);

        System.out.println("Start Distance: " + startRoute.getDistance());

        int generation = 1;
        while (ga.isTerminationConditionMer(generation, maxGenerations)) {
            // print fittest individual from population
            Route route = new Route(population.getFittest(0), cities);

            System.out.println("G" + generation + " Best distance: " + route.getDistance());
            // apply cross over
            population = ga.crossoverPopulation(population);
            ga.evalPopulation(population, cities);
            // System.out.println("fitness after crossover "+ route.getDistance());
            population = ga.mutatePopulation(population);
            // ga.evalPopulation(population, cities);
            // System.out.println("fitness after mutation "+ route.getDistance());
            population = ga.mutatePopulation(population);

            generation++;
        }
        System.out.println("Stopped after " + maxGenerations + " generations.");
        Route route = new Route(population.getFittest(0), cities);
        System.out.println("Best distance: " + route.getDistance());
        route.printCity();
        System.out.println();
    }
}

public class TraveSaleMan {

    public static void main(String[] args) {
        ran a = new ran();
        a.run();

    }
}