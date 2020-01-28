package tsp;

public class Route{
    private City route[];
    private double distance = 0;

    public Route(Individual individual, City cities[]){
        //get individual chromosome
        int chromosome[] = individual.getChromosome();
        // create route
        this.route = new City[cities.length];
        for(int geneIndex = 0 ; geneIndex <chromosome.length;  geneIndex++ ){
            this.route[geneIndex] = cities[chromosome[geneIndex]];
        }
    }
    public void printCity(){
        for(City c:route){
            System.out.print(c.Name+ "->");
        }
    }
    /**
     * calculate the distance total distance to start from the beggining and ends
     * @return
     */
    public double getDistance() {
		if (this.distance > 0) {
			return this.distance;
		}

		// Loop over cities in route and calculate route distance
		double totalDistance = 0;
		for (int cityIndex = 0; cityIndex + 1 < this.route.length; cityIndex++) {
			totalDistance += this.route[cityIndex].distanceFrom(this.route[cityIndex + 1]);
		}

		totalDistance += this.route[this.route.length - 1].distanceFrom(this.route[0]);
		this.distance = totalDistance;

		return totalDistance;
	}

}