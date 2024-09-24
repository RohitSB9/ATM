package AtmSystem;

public class Person {
  private String name;
  private String address;
  private String phoneNum;

  public Person(String name, String address, String phoneNum) {
    this.name = name;
    this.address = address;
    this.phoneNum = phoneNum;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNum;
  }

  public void setName(String newName) {
    name = newName;
  }

  public void setAddress(String newAddress) {
    address = newAddress;
  }

  public void setPhoneNumber(String newPhoneNumber) {
    phoneNum = newPhoneNumber;
  }
}