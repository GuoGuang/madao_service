package com.madao.config;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagationConfig.SingleBaggageField;
import brave.baggage.BaggagePropagationCustomizer;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.baggage.CorrelationScopeCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SleuthConfig {

  @Bean
  public BaggagePropagationCustomizer baggagePropagationCustomizer() {
    return (factoryBuilder) -> {
      factoryBuilder.add(
          SingleBaggageField.remote(BaggageField.create("userId")));
    };
  }

  @Bean
  public CorrelationScopeCustomizer correlationScopeCustomizer() {
    return builder -> {
      builder.add(
          SingleCorrelationField.newBuilder(BaggageField.create("userId"))
              .flushOnUpdate()
              .build());
    };
  }

}
