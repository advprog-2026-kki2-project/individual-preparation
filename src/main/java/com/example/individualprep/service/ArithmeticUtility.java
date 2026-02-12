package com.example.individualprep.service;

import org.springframework.stereotype.Service;

@Service
public class ArithmeticUtility {

    public double add(double o1, double o2) {
        return o1 + o2;
    }

    public double subtract(double o1, double o2) {
        return o1 - o2;
    }

    public double multiply(double o1, double o2) {
        // TODO: Implement me properly!
        return 0.0;
    }

    public double divide(double o1, double o2) {
        if (o2 == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return o1 / o2;
    }

    public double exponent(double o1, int n) {
        if (n == 0)
            return 1.0; // Any number to the power of 0 is 1

        double result = 1.0;
        int exponent = Math.abs(n);

        for (int i = 0; i < exponent; i++) {
            result *= o1;
        }

        // If n was negative, return the reciprocal
        return (n < 0) ? 1.0 / result : result;
    }
}
