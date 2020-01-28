package tsp;
import java.util.*;


public class Population{
    private Individual population[];
    private double PopulationFitness = -1;

    /**
     * initilialize population with individual
     * @param takes the size of population
     */
    public Population(int populationSize){  
        this.population  = new Individual[populationSize];
    }

    /**
     * @param populationSize
     * @param chromosomeLength
     *      takes pop size and individual constract a new population
     */
    public Population(int populationSize, int chromosomeLength){
        this.population = new Individual[populationSize];
        for(int individualCount = 0 ; individualCount < populationSize ; individualCount++){
            Individual individual = new Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }

    }
    /**
     * 
     * @param index
     * @return the individual population from the array
     */
    public Individual[] getIndividuals(){
        return this.population;
    }
    public Individual getIndividual(int index){
        return this.population[index];
    }
    public void setIndividual(Individual individual, int index) {
        this.population[index] = individual;
    }

    public Individual getFittest(int index) {
		// Order population by fitness
		Arrays.sort(this.population, new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});

		// Return the fittest individual
		return this.population[index];
	}
    /**
     * 
     * @param fitness set fitness to the population fitness
     */ 
    public void setPopulationFitness(double fitness){
        this.PopulationFitness = fitness;
    }
    /**
     * @return the population fitness
     */
    public double getPopulation(){
        return this.PopulationFitness;
    }
    /** 
     * @return the size of the 
     */
    public int getSize(){
        return this.population.length;
    }

    /**
     * shuffle the population in-place
     * 
     */
    public void shuffle(){
        Random rnd = new Random();
        for(int i = population.length-1 ; i>0 ; i--){
            int index = rnd.nextInt(i+1);
            Individual temp = this.population[index];
            this.population[index] = this.population[i];
            this.population[i] = temp;
        }
    }
}