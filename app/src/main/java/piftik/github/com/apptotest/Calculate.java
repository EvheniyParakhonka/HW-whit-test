package piftik.github.com.apptotest;

class Calculate implements ICalculate {

    @Override
    public double add(double d1, double d2) {
        return d1 + d2;
    }

    @Override
    public double subtract(double d1, double d2) {
        return d1 - d2;
    }

    @Override
    public double multiply(double d1, double d2) {
        return d1 * d2;
    }

    @Override
    public double divide(double d1, double d2)  {
        return d1 / d2;
    }


}
