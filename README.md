# Scientific Calculator (Java)

A feature-rich command-line scientific calculator written in pure Java with help from AI for formatting and syntax.

## Features

| # | Module | Details |
|---|--------|---------|
| 1 | **Basic Operations** | Addition, Subtraction, Multiplication, Division, Modulus, Power, Square Root, Absolute Value |
| 2 | **Fraction ↔ Decimal** | Convert fractions to decimals and decimals back to simplified fractions |
| 3 | **Trigonometric** | sin, cos, tan, asin, acos, atan, sinh, cosh, tanh, csc, sec, cot — degrees or radians |
| 4 | **Exponential & Logarithmic** | e^x, 10^x, b^x, ln, log10, log_b (custom base) |
| 5 | **Calculus** | Symbolic differentiation & integration (polynomials), numerical derivative, definite integral, indefinite integral table |
| 6 | **Combinatorics** | Factorial (exact), Permutation, Combination, GCD, LCM, Prime check |
| 7 | **Statistics** | Mean, Median, Mode, Min, Max, Range, Variance, Standard Deviation |
| 8 | **Number Base Converter** | Decimal ↔ Binary, Octal, Hexadecimal |

## Calculus Details

- **Symbolic Differentiation / Integration** — enter a polynomial (e.g. `4x^3 - 2x^2 + 7x - 5`) and get the exact formula using the power rule
- **Numerical Derivative** — type any `f(x)` expression and evaluate `f'(x)` at a specific point using the central difference method
- **Numerical Definite Integral** — evaluate `∫f(x)dx` from `a` to `b` using Simpson's Rule
- **Indefinite Integral Table** — for functions with no closed form (e.g. `sin(x^2)`), displays `F(x)` at evenly-spaced x values

### Supported expression syntax (numerical calculus)
```
Operators : +  -  *  /  ^
Functions : sin  cos  tan  asin  acos  atan  sinh  cosh  tanh  sqrt  ln  log  exp  abs
Constants : pi  e
Variable  : x
Example   : 3*x^2 + sin(x)*exp(-x)
```

## Requirements

- Java 16 or higher (uses records and switch expressions)

## Getting Started

**Compile:**
```bash
javac ScientificCalculator.java
```

**Run:**
```bash
java ScientificCalculator
```

## Usage Example

```
Scientific Calculator
1. Basic Operations
2. Fraction ↔ Decimal
...
Choose: 5

--- Calculus ---
1. Symbolic Differentiation    (polynomial -> exact f'(x) formula)
...
Choose: 1

Enter f(x): 4x^3 - 2x^2 + 7x - 5
f(x)  = 4x^3 - 2x^2 + 7x - 5
f'(x) = 12x^2 - 4x + 7
```
