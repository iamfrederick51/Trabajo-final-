package logico;

public class Customer {
    private static int idCounter = 1;
    private int id;
    private String firstName;
    private String lastName;
    private String address;

    public Customer(String firstName, String lastName, String address) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    // Getters y setters...
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


