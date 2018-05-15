package nu.rolandsson.collector.contract;

public interface Builder<B extends BuildEvaluator<?>> {
  B build(B builder);
}
