import java.util.*;

public class Drink {

    private String _name;
    private double _price;
    private String _currency;
    private Map<String, Resource> _requirements;

    public String getName() {
        return _name;
    }

    public double getPrice() {
        return _price;
    }

    public String getCurrency() {
        return _currency;
    }

    public Drink(String name, double price, String currency, Resource... requirements) {
        this(name, price, currency, Arrays.asList(requirements));
    }

    public Drink(String name, double price, String currency, List<Resource> requirementList) {
        _name = name;
        _price = price;
        _currency = currency;
        _requirements = new HashMap<String, Resource>();
        for (Resource resource : requirementList) {
            _requirements.put(resource.getName(), resource);
        }
    }

    public boolean hasRequireResources(Map<String, Resource> resources) {
        // does the resources provide for the requirements
        for (Resource requirement : _requirements.values()) {
            Resource resource = resources.getOrDefault(requirement.getName(), null);
            if (resource == null || resource.getAmount() < requirement.getAmount()) {
                return false;
            }
        }

        return true;
    }

    public void brew(Map<String, Resource> resources) throws Exception {
        for (Resource requirement : _requirements.values()) {
            Resource resource = resources.getOrDefault(requirement.getName(), null);
            resource.useResource(requirement.getAmount());
        }
    }
}
