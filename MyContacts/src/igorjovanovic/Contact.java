package igorjovanovic;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName=new SimpleStringProperty("");
    private SimpleStringProperty lastName=new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber=new SimpleStringProperty("");
    private SimpleStringProperty note=new SimpleStringProperty("");

    public Contact() {
    }

    public Contact(String firstName, String lastName, String phoneNumber, String note) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.note.set(note);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleStringProperty noteProperty() {
        return note;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getNote() {
        return note.get();
    }

    public void setNotes(String data) {
        this.note.set(data);
    }

    public void setFirstName(String data) {
        this.firstName.set(data);
    }

    public void setPhoneNumber(String data) {
        this.phoneNumber.set(data);
    }

    public void setLastName(String data) {
        this.lastName.set(data);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", note=" + note +
                '}';
    }

}
