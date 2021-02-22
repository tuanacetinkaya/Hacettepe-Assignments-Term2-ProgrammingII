package Assignment3;

/**
 * This interface is the root of the Decorator Design Pattern and will not supply any further
 *      information in its implementations. This only provides a dynamic decoration mechanic.
 */
public interface IExamination {
    /**
     * getCost method adds all Examination costs dynamically
     * @return the total cost
     */
    int getCost();

    /**
     * this prints the examination details
     * @return type <tab> operations
     */
    String getDescription();
}
