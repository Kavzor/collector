package nu.rolandsson.collector.contract;

/**
 * Implemented to ensure that builder pattern is capable of building
 * @param <B> The builder
 */
public interface Builder<B extends BuildEvaluator<?>> {
  /**
   * Used to construct an instance of the builders object
   * @param builder a constructed builder object
   * @return the builder
   */
  B build(B builder);
}
