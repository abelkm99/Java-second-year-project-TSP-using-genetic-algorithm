package tsp;
import java.util.Arrays;


public class GeneticAlgorithm{
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    protected int tournamentSize;
    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize){
        this.populationSize = populationSize;
        this.mutationRate  = mutationRate; 
        this.crossoverRate = crossoverRate; 
        this.elitismCount = elitismCount; 
        this.tournamentSize = tournamentSize;
    }
    /**
     * Initialize population
     * @param chromosomeLength
     * @return population the initial population is generated
     */
    public Population initPopulation(int chromosomeLength){
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }
    public boolean isTerminationConditionMer(int generationCount, int maxGeneration){
        if(generationCount > maxGeneration) return false;
        return true;
    }

    public double calcFitness(Individual individual, City cities[]){
        Route route = new Route(individual, cities);
        double fitness = 1 / route.getDistance();
        individual.setFitness(fitness);
        return fitness;
    }
    /**
     * Evaluate population basically run calcFitness on each individual
     */
    public void evalPopulation(Population population , City cities[]){
        double populationFitness=0 ;
        // for(Individual individual: population.getIndividuals()){
        //     populationFitness+=calcFitness(individual, cities);
        // }
        for(int i= 0 ; i<population.getSize(); i++){
            populationFitness+=this.calcFitness(population.getIndividual(i), cities);
        }
        double avgFitness = populationFitness/population.getSize();
        population.setPopulationFitness(avgFitness);
    }
    public Individual selectParent(Population population){
        Population tournment = new Population(this.tournamentSize);

        population.shuffle();
        for(int i=0; i<this.tournamentSize; i++){
            Individual tournmentIndividual = population.getIndividual(i);
            tournment.setIndividual(tournmentIndividual, i);
        }
        return tournment.getFittest(0);

    }

    /**
     * * We need a more clever crossover algorithm here. What we can do is choose
	 * two pivot points, add chromosomes from one parent for one of the ranges,
	 * and then only add not-yet-represented cities to the second range. This
	 * ensures that no cities are skipped or visited twice, while also
	 * preserving ordered batches of cities.
     */
    public Population crossoverPopulation(Population population){
        // Create new population
        Population newPopulation = new Population(population.getSize());
        
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.getSize(); populationIndex++) {
            // Get parent1
            Individual parent1 = population.getFittest(populationIndex);
            
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Find parent2 with tournament selection
                Individual parent2 = this.selectParent(population);

                // Create blank offspring chromosome
                int offspringChromosome[] = new int[parent1.getChromosomeLenght()];
                Arrays.fill(offspringChromosome, -1);
                Individual offspring = new Individual(offspringChromosome);

                // Get subset of parent chromosomes
                int substrPos1 = (int) (Math.random() * parent1.getChromosomeLenght());
                int substrPos2 = (int) (Math.random() * parent1.getChromosomeLenght());

                // make the smaller the start and the larger the end
                final int startSubstr = Math.min(substrPos1, substrPos2);
                final int endSubstr = Math.max(substrPos1, substrPos2);

                // Loop and add the sub tour from parent1 to our child
                for (int i = startSubstr; i < endSubstr; i++) {
                    offspring.setGene(i, parent1.getGene(i));
                }

                // Loop through parent2's city tour
                for (int i = 0; i < parent2.getChromosomeLenght(); i++) {
                    int parent2Gene = i + endSubstr;
                    if (parent2Gene >= parent2.getChromosomeLenght()) {
                        parent2Gene -= parent2.getChromosomeLenght();
                    }

                    // If offspring doesn't have the city add it
                    if (offspring.containGene(parent2.getGene(parent2Gene)) == false) {
                        // Loop to find a spare position in the child's tour
                        for (int ii = 0; ii < offspring.getChromosomeLenght(); ii++) {
                            // Spare position found, add city
                            if (offspring.getGene(ii) == -1) {
                                offspring.setGene(ii, parent2.getGene(parent2Gene));
                                break;
                            }
                        }
                    }
                }

                // Add child
                newPopulation.setIndividual(offspring, populationIndex);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(parent1 , populationIndex);
            }
        }
        
        return newPopulation;
    }

    /**
     * mutation 
     */

     public Population mutatePopulation(Population population){
         Population newPopulation = new Population(population.getSize());
         for(int populationCount = 0; populationCount<population.getSize(); populationCount++){
            Individual individual = population.getFittest(populationCount);
            //skip mutation if this is an elite individual
            if(populationCount >= this.elitismCount){
                // System.out.println("Mutating population member "+populationIndex);
                // Loop over individual's genes
                for(int geneIndex= 0; geneIndex < individual.getChromosomeLenght(); geneIndex++){
                    int newGeneIndex = (int) Math.random() * individual.getChromosomeLenght();
                    int gene1 = individual.getGene(newGeneIndex);
                    int gene2 = individual.getGene(geneIndex);
                    individual.setGene(geneIndex, gene1);
                    individual.setGene(newGeneIndex, gene2);
                }
            }
            newPopulation.setIndividual(individual, populationCount);
         }
         return newPopulation;
     }



















}