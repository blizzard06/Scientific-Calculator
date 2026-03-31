import java.math.BigInteger;
import java.util.*;

public class ScientificCalculator {

    static Scanner sc = new Scanner(System.in);

    // ══════════════════════════════════════════════════════════════════════════
    //  MAIN MENU
    // ══════════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nScientific Calculator");
            System.out.println("1. Basic Operations");
            System.out.println("2. Fraction ↔ Decimal");
            System.out.println("3. Trigonometric");
            System.out.println("4. Exponential & Logarithmic");
            System.out.println("5. Calculus");
            System.out.println("6. Combinatorics");
            System.out.println("7. Statistics");
            System.out.println("8. Number Base Converter");
            System.out.println("0. Exit");
            System.out.print("Choose any one of the options: ");

            switch (readInt()) {
                case 1 -> basicOperations();
                case 2 -> fractionConverter();
                case 3 -> trigonometricCalculator();
                case 4 -> expLogCalculator();
                case 5 -> calculusMenu();
                case 6 -> combinatorics();
                case 7 -> statistics();
                case 8 -> baseConverter();
                case 0 -> { System.out.println("We hope you enjoyed your experience"); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    
    //  1. BASIC OPERATIONS
    static void basicOperations() {
        System.out.println("\n--- Basic Operations ---");
        System.out.println("1. Addition       2. Subtraction    3. Multiplication");
        System.out.println("4. Division       5. Modulus        6. Power (x^y)");
        System.out.println("7. Square Root    8. Absolute Value");
        System.out.print("Choose: ");

        int op = readInt();

        if (op == 7 || op == 8) {
            System.out.print("Enter number: ");
            double x = readDouble();
            if (op == 7) {
                if (x < 0) System.out.println("Error: sqrt of negative number undefined.");
                else System.out.printf("√%.4f = %.6f%n", x, Math.sqrt(x));
            } else {
                System.out.printf("|%.4f| = %.6f%n", x, Math.abs(x));
            }
            return;
        }

        System.out.print("Enter first number:  ");
        double a = readDouble();
        System.out.print("Enter second number: ");
        double b = readDouble();

        switch (op) {
            case 1 -> System.out.printf("%.4f + %.4f = %.6f%n", a, b, a + b);
            case 2 -> System.out.printf("%.4f - %.4f = %.6f%n", a, b, a - b);
            case 3 -> System.out.printf("%.4f x %.4f = %.6f%n", a, b, a * b);
            case 4 -> { if (b == 0) System.out.println("Error: Division by zero.");
                        else System.out.printf("%.4f / %.4f = %.6f%n", a, b, a / b); }
            case 5 -> { if (b == 0) System.out.println("Error: Modulus by zero.");
                        else System.out.printf("%.4f %% %.4f = %.6f%n", a, b, a % b); }
            case 6 -> System.out.printf("%.4f ^ %.4f = %.6f%n", a, b, Math.pow(a, b));
            default -> System.out.println("Invalid operation.");
        }
    }

    //  2. FRACTION <-> DECIMAL
    static void fractionConverter() {
        System.out.println("\n--- Fraction <-> Decimal ---");
        System.out.println("1. Fraction to Decimal");
        System.out.println("2. Decimal  to Fraction");
        System.out.print("Choose: ");

        switch (readInt()) {
            case 1 -> {
                System.out.print("Numerator:   "); long num = readLong();
                System.out.print("Denominator: "); long den = readLong();
                if (den == 0) { System.out.println("Error: denominator cannot be zero."); return; }
                System.out.printf("%d/%d = %.6f%n", num, den, (double) num / den);
            }
            case 2 -> {
                System.out.print("Enter decimal: "); double d = readDouble();
                long[] f = decimalToFraction(d);
                System.out.printf("%.6f = %d/%d%n", d, f[0], f[1]);
            }
            default -> System.out.println("Invalid option.");
        }
    }

    static long[] decimalToFraction(double decimal) {
        final long PRECISION = 1_000_000L;
        long numerator   = Math.round(decimal * PRECISION);
        long denominator = PRECISION;
        long g = gcd(Math.abs(numerator), denominator);
        return new long[]{numerator / g, denominator / g};
    }

    static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }

    //  3. TRIGONOMETRIC
    static void trigonometricCalculator() {
        System.out.println("\n--- Trigonometric Calculator ---");
        System.out.println("1.sin   2.cos   3.tan   4.asin  5.acos  6.atan");
        System.out.println("7.sinh  8.cosh  9.tanh  10.csc  11.sec  12.cot");
        System.out.print("Choose: ");

        int func = readInt();
        double value, angleRad;

        if (func >= 4 && func <= 6) {
            System.out.print("Enter value: "); value = readDouble();
            double res = switch (func) {
                case 4 -> { if (Math.abs(value) > 1) { System.out.println("Error: |value| must be <= 1"); yield Double.NaN; }
                             yield Math.asin(value); }
                case 5 -> { if (Math.abs(value) > 1) { System.out.println("Error: |value| must be <= 1"); yield Double.NaN; }
                             yield Math.acos(value); }
                case 6 -> Math.atan(value);
                default -> Double.NaN;
            };
            if (!Double.isNaN(res))
                System.out.printf("Result = %.6f rad  =  %.6f degrees%n", res, Math.toDegrees(res));
            return;
        }

        System.out.print("Enter angle: "); value = readDouble();
        System.out.print("Unit (1=Degrees, 2=Radians): ");
        int unit = readInt();
        angleRad = (unit == 1) ? Math.toRadians(value) : value;

        String[] names = {"","sin","cos","tan","","","","sinh","cosh","tanh","csc","sec","cot"};
        double result = switch (func) {
            case 1  -> Math.sin(angleRad);
            case 2  -> Math.cos(angleRad);
            case 3  -> { if (nearOddMultiple(angleRad, Math.PI/2)) { System.out.println("Undefined."); yield Double.NaN; }
                         yield Math.tan(angleRad); }
            case 7  -> Math.sinh(angleRad);
            case 8  -> Math.cosh(angleRad);
            case 9  -> Math.tanh(angleRad);
            case 10 -> { double s = Math.sin(angleRad);
                         if (Math.abs(s) < 1e-10) { System.out.println("Undefined."); yield Double.NaN; }
                         yield 1/s; }
            case 11 -> { double c = Math.cos(angleRad);
                         if (Math.abs(c) < 1e-10) { System.out.println("Undefined."); yield Double.NaN; }
                         yield 1/c; }
            case 12 -> { double t = Math.tan(angleRad);
                         if (nearOddMultiple(angleRad, Math.PI)) { System.out.println("Undefined."); yield Double.NaN; }
                         yield 1/t; }
            default -> { System.out.println("Invalid."); yield Double.NaN; }
        };
        if (!Double.isNaN(result))
            System.out.printf("%s(%.4f%s) = %.6f%n", names[func], value, unit==1?" deg":" rad", result);
    }

    static boolean nearOddMultiple(double angle, double target) {
        double ratio = angle / target;
        return Math.abs(ratio - Math.round(ratio)) < 1e-10 && (Math.round(ratio) % 2 != 0);
    }

    //  4. EXPONENTIAL & LOGARITHMIC
    static void expLogCalculator() {
        System.out.println("\n--- Exponential & Logarithmic ---");
        System.out.println("1. e^x          2. 10^x         3. b^x (custom base)");
        System.out.println("4. ln(x)        5. log10(x)     6. log_b(x) (custom base)");
        System.out.print("Choose: ");

        int op = readInt();
        System.out.print("Enter x: ");
        double x = readDouble();

        switch (op) {
            case 1 -> System.out.printf("e^%.4f = %.6f%n", x, Math.exp(x));
            case 2 -> System.out.printf("10^%.4f = %.6f%n", x, Math.pow(10, x));
            case 3 -> {
                System.out.print("Enter base b: "); double b = readDouble();
                if (b <= 0 || b == 1) System.out.println("Error: base must be > 0 and != 1.");
                else System.out.printf("%.4f^%.4f = %.6f%n", b, x, Math.pow(b, x));
            }
            case 4 -> { if (x <= 0) System.out.println("Error: ln requires x > 0.");
                        else System.out.printf("ln(%.4f) = %.6f%n", x, Math.log(x)); }
            case 5 -> { if (x <= 0) System.out.println("Error: log requires x > 0.");
                        else System.out.printf("log10(%.4f) = %.6f%n", x, Math.log10(x)); }
            case 6 -> {
                if (x <= 0) { System.out.println("Error: log requires x > 0."); return; }
                System.out.print("Enter base b: "); double b = readDouble();
                if (b <= 0 || b == 1) System.out.println("Error: base must be > 0 and != 1.");
                else System.out.printf("log_%.4f(%.4f) = %.6f%n", b, x, Math.log(x)/Math.log(b));
            }
            default -> System.out.println("Invalid option.");
        }
    }

    //  5. CALCULUS
    static void calculusMenu() {
        System.out.println("\n--- Calculus ---");
        System.out.println("1. Symbolic Differentiation    (polynomial -> exact f'(x) formula)");
        System.out.println("2. Symbolic Integration        (polynomial -> exact integral formula)");
        System.out.println("3. Numerical Derivative        (any f(x), evaluate f'(x) at a point)");
        System.out.println("4. Numerical Definite Integral (any f(x), evaluate integral a to b)");
        System.out.println("5. Indefinite Integral Table   (any f(x), F(x) values at x steps)");
        System.out.print("Choose: ");

        switch (readInt()) {
            case 1 -> symbolicDiff();
            case 2 -> symbolicIntegrate();
            case 3 -> numericalDerivative();
            case 4 -> numericalDefiniteIntegral();
            case 5 -> numericalIndefiniteTable();
            default -> System.out.println("Invalid option.");
        }
    }

    // ── Polynomial symbolic engine ─────────────────────────────────────────
    record Term(double coeff, double power) {}

    static List<Term> parsePoly(String expr) {
        List<Term> terms = new ArrayList<>();
        expr = expr.replaceAll("\\s+", "");
        // Insert '+' before '-' that is not at position 0 or after '^'
        StringBuilder norm = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '-' && i > 0 && expr.charAt(i-1) != '^' && expr.charAt(i-1) != '+') {
                norm.append('+');
            }
            norm.append(c);
        }
        String[] parts = norm.toString().split("\\+");
        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;
            if (!part.contains("x")) {
                try { terms.add(new Term(Double.parseDouble(part), 0)); }
                catch (NumberFormatException ignored) {}
                continue;
            }
            double power = 1;
            if (part.contains("^")) {
                int idx = part.lastIndexOf('^');
                power = Double.parseDouble(part.substring(idx + 1));
                part  = part.substring(0, idx);
            }
            int xIdx = part.indexOf('x');
            String cStr = part.substring(0, xIdx);
            double coeff;
            if (cStr.isEmpty() || cStr.equals("+")) coeff = 1;
            else if (cStr.equals("-"))               coeff = -1;
            else coeff = Double.parseDouble(cStr);
            terms.add(new Term(coeff, power));
        }
        return terms;
    }

    static String polyToString(List<Term> terms) {
        if (terms.isEmpty()) return "0";
        terms = new ArrayList<>(terms);
        terms.sort((a, b) -> Double.compare(b.power(), a.power()));
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Term t : terms) {
            if (t.coeff() == 0) continue;
            if (!first) sb.append(t.coeff() > 0 ? " + " : " - ");
            else if (t.coeff() < 0) sb.append("-");
            double abs = Math.abs(t.coeff());
            if (t.power() == 0) sb.append(fmtN(abs));
            else {
                if (abs != 1) sb.append(fmtN(abs));
                sb.append("x");
                if (t.power() != 1) { sb.append("^"); sb.append(fmtN(t.power())); }
            }
            first = false;
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }

    static String fmtN(double d) {
        if (d == Math.floor(d) && !Double.isInfinite(d) && Math.abs(d) < 1e15)
            return String.valueOf((long) d);
        return String.format("%.4f", d).replaceAll("0+$","").replaceAll("\\.$","");
    }

    static void symbolicDiff() {
        System.out.println("\nSymbolic Differentiation -- Polynomial");
        System.out.println("Enter f(x)  e.g.  4x^3 - 2x^2 + 7x - 5");
        System.out.print("> ");
        String expr = sc.nextLine().trim();
        List<Term> poly = parsePoly(expr);
        List<Term> diff = new ArrayList<>();
        for (Term t : poly)
            if (t.power() != 0) diff.add(new Term(t.coeff() * t.power(), t.power() - 1));

        System.out.println("f(x)  = " + polyToString(poly));
        System.out.println("f'(x) = " + polyToString(diff));

        System.out.println("\nCommon symbolic derivatives (reference):");
        System.out.println("  d/dx[sin x] = cos x        d/dx[cos x] = -sin x");
        System.out.println("  d/dx[tan x] = sec^2 x      d/dx[e^x]   = e^x");
        System.out.println("  d/dx[ln x]  = 1/x          d/dx[x^n]   = n*x^(n-1)");
        System.out.println("  d/dx[a^x]   = a^x * ln(a)  d/dx[log x] = 1/(x*ln 10)");
    }

    static void symbolicIntegrate() {
        System.out.println("\nSymbolic Integration -- Polynomial");
        System.out.println("Enter f(x)  e.g.  6x^2 + 4x - 3");
        System.out.print("> ");
        String expr = sc.nextLine().trim();
        List<Term> poly = parsePoly(expr);
        List<Term> intg = new ArrayList<>();
        for (Term t : poly)
            intg.add(new Term(t.coeff() / (t.power() + 1), t.power() + 1));

        System.out.println("f(x)    = " + polyToString(poly));
        System.out.println("F(x)dx  = " + polyToString(intg) + " + C");

        System.out.println("\nCommon symbolic integrals (reference):");
        System.out.println("  integral(sin x)dx  = -cos x + C      integral(cos x)dx  = sin x + C");
        System.out.println("  integral(e^x)dx    = e^x + C         integral(1/x)dx    = ln|x| + C");
        System.out.println("  integral(x^n)dx    = x^(n+1)/(n+1) + C");
        System.out.println("  integral(a^x)dx    = a^x / ln(a) + C");
    }

    static void numericalDerivative() {
        System.out.println("\nNumerical Derivative  f'(x0)");
        System.out.println("Use 'x' as variable. Supported: +  -  *  /  ^");
        System.out.println("Functions: sin cos tan asin acos atan sinh cosh tanh sqrt ln log exp abs");
        System.out.println("Constants: pi  e");
        System.out.println("Example:   3*x^2 + sin(x)*exp(-x)");
        System.out.print("Enter f(x): ");
        String expr = sc.nextLine().trim();
        System.out.print("Enter x0: ");
        double x0 = readDouble();

        try {
            double h = 1e-7;
            double deriv = (evalExpr(expr, x0 + h) - evalExpr(expr, x0 - h)) / (2 * h);
            System.out.printf("f'(%.6f) ~= %.8f%n", x0, deriv);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void numericalDefiniteIntegral() {
        System.out.println("\nNumerical Definite Integral  integral f(x)dx  [a, b]");
        System.out.println("Same expression syntax as derivative.");
        System.out.print("Enter f(x): ");
        String expr = sc.nextLine().trim();
        System.out.print("Enter lower bound a: "); double a = readDouble();
        System.out.print("Enter upper bound b: "); double b = readDouble();

        try {
            double result = simpsonIntegral(expr, a, b, 10_000);
            System.out.printf("integral f(x)dx from %.4f to %.4f ~= %.8f%n", a, b, result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void numericalIndefiniteTable() {
        System.out.println("\nIndefinite Integral Table");
        System.out.println("Shows F(x) = integral[from start to x] f(t) dt");
        System.out.println("Useful for functions with no closed form, e.g. sin(x^2)");
        System.out.print("Enter f(x): ");
        String expr = sc.nextLine().trim();
        System.out.print("Start x : "); double start = readDouble();
        System.out.print("End   x : "); double end   = readDouble();
        System.out.print("Steps   : "); int    steps = readInt();
        if (steps < 1 || steps > 1000) { System.out.println("Steps must be 1-1000."); return; }

        try {
            double step = (end - start) / steps;
            System.out.printf("%-14s %-16s %-16s%n", "x", "f(x)", "F(x)");
            System.out.println("-".repeat(48));
            double F = 0;
            double prevX = start;
            System.out.printf("%-14.4f %-16.6f %-16.6f%n", start, evalExpr(expr, start), 0.0);
            for (int i = 1; i <= steps; i++) {
                double x = start + i * step;
                F += simpsonIntegral(expr, prevX, x, 100);
                System.out.printf("%-14.4f %-16.6f %-16.6f%n", x, evalExpr(expr, x), F);
                prevX = x;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static double simpsonIntegral(String expr, double a, double b, int n) {
        if (n % 2 != 0) n++;
        double h = (b - a) / n;
        double sum = evalExpr(expr, a) + evalExpr(expr, b);
        for (int i = 1; i < n; i++)
            sum += (i % 2 == 0 ? 2 : 4) * evalExpr(expr, a + i * h);
        return sum * h / 3;
    }

   
    //  EXPRESSION PARSER  (recursive descent, supports x variable)
    static double evalExpr(String expr, double x) {
        return new ExprParser(expr.replaceAll("\\s+",""), x).parse();
    }

    static class ExprParser {
        final String s;
        final double xVal;
        int pos;

        ExprParser(String s, double xVal) { this.s = s; this.xVal = xVal; this.pos = 0; }

        double parse() {
            double r = expr();
            if (pos < s.length()) throw new RuntimeException("Unexpected: '" + s.charAt(pos) + "'");
            return r;
        }

        double expr() {
            double v = term();
            while (pos < s.length() && (s.charAt(pos) == '+' || s.charAt(pos) == '-')) {
                char op = s.charAt(pos++);
                v = op == '+' ? v + term() : v - term();
            }
            return v;
        }

        double term() {
            double v = factor();
            while (pos < s.length() && (s.charAt(pos) == '*' || s.charAt(pos) == '/')) {
                char op = s.charAt(pos++);
                double f = factor();
                v = op == '*' ? v * f : v / f;
            }
            return v;
        }

        double factor() {
            double base = unary();
            if (pos < s.length() && s.charAt(pos) == '^') { pos++; return Math.pow(base, unary()); }
            return base;
        }

        double unary() {
            if (pos < s.length() && s.charAt(pos) == '-') { pos++; return -unary(); }
            if (pos < s.length() && s.charAt(pos) == '+') { pos++; return  unary(); }
            return primary();
        }

        double primary() {
            if (pos >= s.length()) throw new RuntimeException("Unexpected end of expression");
            char c = s.charAt(pos);

            if (Character.isDigit(c) || c == '.') {
                int start = pos;
                while (pos < s.length() && (Character.isDigit(s.charAt(pos)) || s.charAt(pos) == '.')) pos++;
                return Double.parseDouble(s.substring(start, pos));
            }

            if (c == '(') {
                pos++;
                double v = expr();
                if (pos >= s.length() || s.charAt(pos) != ')') throw new RuntimeException("Missing ')'");
                pos++;
                return v;
            }

            if (Character.isLetter(c)) {
                int start = pos;
                while (pos < s.length() && Character.isLetter(s.charAt(pos))) pos++;
                String name = s.substring(start, pos);

                if (name.equals("x"))  return xVal;
                if (name.equals("pi")) return Math.PI;
                if (name.equals("e"))  return Math.E;

                if (pos < s.length() && s.charAt(pos) == '(') {
                    pos++;
                    double arg = expr();
                    if (pos >= s.length() || s.charAt(pos) != ')') throw new RuntimeException("Missing ')'");
                    pos++;
                    return switch (name) {
                        case "sin"  -> Math.sin(arg);
                        case "cos"  -> Math.cos(arg);
                        case "tan"  -> Math.tan(arg);
                        case "asin" -> Math.asin(arg);
                        case "acos" -> Math.acos(arg);
                        case "atan" -> Math.atan(arg);
                        case "sinh" -> Math.sinh(arg);
                        case "cosh" -> Math.cosh(arg);
                        case "tanh" -> Math.tanh(arg);
                        case "sqrt" -> Math.sqrt(arg);
                        case "ln"   -> Math.log(arg);
                        case "log"  -> Math.log10(arg);
                        case "exp"  -> Math.exp(arg);
                        case "abs"  -> Math.abs(arg);
                        default -> throw new RuntimeException("Unknown function: " + name);
                    };
                }
                throw new RuntimeException("Unknown identifier: " + name);
            }
            throw new RuntimeException("Unexpected character: '" + c + "'");
        }
    }


    //  6. COMBINATORICS
    static void combinatorics() {
        System.out.println("\n--- Combinatorics & Number Theory ---");
        System.out.println("1. Factorial n!         2. Permutation P(n,r)");
        System.out.println("3. Combination C(n,r)   4. GCD");
        System.out.println("5. LCM                  6. Is Prime?");
        System.out.print("Choose: ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                System.out.print("Enter n: "); int n = readInt();
                if (n < 0) { System.out.println("n must be >= 0"); return; }
                System.out.printf("%d! = %s%n", n, factorial(n));
            }
            case 2, 3 -> {
                System.out.print("Enter n: "); int n = readInt();
                System.out.print("Enter r: "); int r = readInt();
                if (n < 0 || r < 0 || r > n) { System.out.println("Need 0 <= r <= n"); return; }
                if (op == 2) System.out.printf("P(%d,%d) = %s%n", n, r, permutation(n,r));
                else         System.out.printf("C(%d,%d) = %s%n", n, r, combination(n,r));
            }
            case 4, 5 -> {
                System.out.print("Enter a: "); long a = readLong();
                System.out.print("Enter b: "); long b = readLong();
                long g = gcd(Math.abs(a), Math.abs(b));
                if (op == 4) System.out.printf("GCD(%d,%d) = %d%n", a, b, g);
                else         System.out.printf("LCM(%d,%d) = %d%n", a, b, (g == 0 ? 0 : Math.abs(a/g*b)));
            }
            case 6 -> {
                System.out.print("Enter n: "); long n = readLong();
                System.out.printf("%d is %s%n", n, isPrime(n) ? "PRIME" : "NOT prime");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    static BigInteger factorial(int n) {
        BigInteger r = BigInteger.ONE;
        for (int i = 2; i <= n; i++) r = r.multiply(BigInteger.valueOf(i));
        return r;
    }
    static BigInteger permutation(int n, int r) {
        BigInteger r2 = BigInteger.ONE;
        for (int i = n; i > n - r; i--) r2 = r2.multiply(BigInteger.valueOf(i));
        return r2;
    }
    static BigInteger combination(int n, int r) { return permutation(n,r).divide(factorial(r)); }

    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6)
            if (n % i == 0 || n % (i+2) == 0) return false;
        return true;
    }

    //  7. STATISTICS
    static void statistics() {
        System.out.println("\n--- Statistics ---");
        System.out.print("How many numbers? "); int n = readInt();
        if (n <= 0) { System.out.println("Need at least 1 number."); return; }
        double[] data = new double[n];
        for (int i = 0; i < n; i++) { System.out.printf("  Number %d: ", i+1); data[i] = readDouble(); }

        double sum = 0; for (double v : data) sum += v;
        double mean = sum / n;

        double[] sorted = data.clone(); Arrays.sort(sorted);
        double median = (n % 2 == 0)
            ? (sorted[n/2 - 1] + sorted[n/2]) / 2.0
            : sorted[n/2];

        double variance = 0;
        for (double v : data) variance += (v - mean) * (v - mean);
        variance /= n;
        double stddev = Math.sqrt(variance);

        double min = sorted[0], max = sorted[n-1];

        System.out.println("\n--- Results ---");
        System.out.printf("Count       : %d%n",   n);
        System.out.printf("Sum         : %.6f%n",  sum);
        System.out.printf("Mean        : %.6f%n",  mean);
        System.out.printf("Median      : %.6f%n",  median);
        System.out.printf("Mode        : %s%n",    findMode(data));
        System.out.printf("Min         : %.6f%n",  min);
        System.out.printf("Max         : %.6f%n",  max);
        System.out.printf("Range       : %.6f%n",  max - min);
        System.out.printf("Variance    : %.6f%n",  variance);
        System.out.printf("Std Dev (s) : %.6f%n",  stddev);
    }

    static String findMode(double[] data) {
        Map<Double, Integer> freq = new LinkedHashMap<>();
        for (double v : data) freq.merge(v, 1, Integer::sum);
        int maxFreq = Collections.max(freq.values());
        if (maxFreq == 1) return "No mode (all values unique)";
        List<String> modes = new ArrayList<>();
        for (var e : freq.entrySet())
            if (e.getValue() == maxFreq) modes.add(fmtN(e.getKey()));
        return String.join(", ", modes) + " (freq=" + maxFreq + ")";
    }

    //  8. NUMBER BASE CONVERTER
    static void baseConverter() {
        System.out.println("\n--- Number Base Converter ---");
        System.out.println("1. Decimal  -> Binary / Octal / Hex");
        System.out.println("2. Binary   -> Decimal");
        System.out.println("3. Octal    -> Decimal");
        System.out.println("4. Hex      -> Decimal");
        System.out.print("Choose: ");

        switch (readInt()) {
            case 1 -> {
                System.out.print("Enter decimal integer: ");
                long d = readLong();
                System.out.printf("Binary : %s%n", Long.toBinaryString(d));
                System.out.printf("Octal  : %s%n", Long.toOctalString(d));
                System.out.printf("Hex    : %s%n", Long.toHexString(d).toUpperCase());
            }
            case 2 -> { System.out.print("Enter binary  : ");
                        System.out.println("Decimal: " + Long.parseLong(sc.nextLine().trim(), 2)); }
            case 3 -> { System.out.print("Enter octal   : ");
                        System.out.println("Decimal: " + Long.parseLong(sc.nextLine().trim(), 8)); }
            case 4 -> { System.out.print("Enter hex     : ");
                        System.out.println("Decimal: " + Long.parseLong(sc.nextLine().trim(), 16)); }
            default -> System.out.println("Invalid option.");
        }
    }

    //  INPUT HELPERS
    static int readInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Invalid -- enter integer: "); }
        }
    }
    static long readLong() {
        while (true) {
            try { return Long.parseLong(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Invalid -- enter integer: "); }
        }
    }
    static double readDouble() {
        while (true) {
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Invalid -- enter number: "); }
        }
    }
}
