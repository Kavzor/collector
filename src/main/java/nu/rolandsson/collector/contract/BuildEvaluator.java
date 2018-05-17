package nu.rolandsson.collector.contract;

/**
 * Contract to ensure that there is an evaluation method in the builder pattern
 * @param <T> the returned value when evaluating
 */
public interface BuildEvaluator<T> {
  /**
   * Evaluates and returns a builder pattern result
   * @return the generic returned value
   */
  T evaluate();
}
