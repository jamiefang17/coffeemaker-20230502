public class Resource {
    private String _name;
    private double _amount;
    private String _measure;

    public Resource(String name, String measure, double amount) {
        _name = name;
        _measure = measure;
        _amount = amount;
    }

    public String getName() {
        return _name;
    } 

    public String getMeasure() {
        return _measure;
    }

    public double getAmount() {
        return _amount;
    }

    public void useResource(double value) throws Exception {
        if (value < 0 ) throw new Exception("Value must be greater than zero");
        if (_amount - value < 0) throw new Exception("Not enougn resources left");

        _amount -= value;
    }

    public void addResource(double value) throws Exception {
        if (value < 0 ) throw new Exception("Value must be greater than zero");
        
        _amount += value;
    }
}
