package com.merilairon.calculator;

import org.springframework.stereotype.Service;

/**
 * Calculator Class to make basic math calculations
 */
@Service
public class Calculator {
    int sum(int a, int b) {
        return a + b;
    }
}
