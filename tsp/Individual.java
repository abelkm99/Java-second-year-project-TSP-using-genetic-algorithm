package tsp;
public class Individual{
    /**
     * chromosome is an array of intigers
     * assign fitness to -1 // it's like a convention 
     */
    private int[] chromosome;

    private double fitness = -1;

    /**
     * initialize individuals with specific chromosome 
     * constracter is overloaded with one takes chromosome length and the other take the chromosome it self
     */
    /**
     * add the chromosome to the new 
     * @param chromosome 
     *              and assign it to this.chromosome
     */
    public Individual(int[] chromosome){
        this.chromosome = chromosome;
    }

    public Individual(int chromosomeLength){
        int[] individual = new int[chromosomeLength];
        for(int gene = 0; gene < chromosomeLength; gene++){
            individual[gene] = gene;
        }
        this.chromosome = individual;
    }

    /**
     *  get the chromosome of individual
     *   @return  
     *          the chromosome         
     */
    public int[] getChromosome(){
        return this.chromosome;
    }
    /**
     * @return the length of chromosome
     */
    public int getChromosomeLenght(){
        return this.chromosome.length;
    }
    /**
     * 
     * @param index
     * @param gene
     *          assigne gene to specific index inside chromosome
     */ 
    public void setGene(int index, int gene){
        this.chromosome[index] = gene;
    }
    /**
     * 
     * @param index
     * @return the gene at at ageven index of chromosome
     */
    public int intetGene(int index){
        return this.chromosome[index];
    }
    /**
     * set fitness to the individual fitness
     * @param fitness
     */
    public void setFitness(double fitness){
        this.fitness = fitness;
    }
    /**
     * get fitness of the individual
     * @return  the fitness of individual
     */
    public double getFitness(){
        return this.fitness;
    }
    /**
     * conver the individual to string it is easier to print
     */
    public String toString(){
        String output="";
        for(int x:this.chromosome){
            output+=x+",";
        }
        return output;
    }
    /**
     * 
     * @param gene
     * @return chech if specific gene exist in the cromosome genes are this case the city
     */
    public boolean containGene(int gene){
        for(int c : this.chromosome){
            if (gene==c) return true;
        }
        return false;
    }
    public int getGene(int index){
        return this.chromosome[index];
    }
}