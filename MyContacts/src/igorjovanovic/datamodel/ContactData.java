package igorjovanovic.datamodel;

import igorjovanovic.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;



public class ContactData {
    public static final String FILE_NAME="contacts.db";
    public static final String FILE_LOCATION=
            "jdbc:sqlite:E:\\Java\\javaProjekti\\Contacts\\"+FILE_NAME;

    private static ContactData instance=new ContactData();
    private static Connection connection;

    private static ObservableList<Contact> contacts;



    private ContactData() {
        contacts = FXCollections.observableArrayList();
    }

    public static ContactData getInstance(){return instance;}


    public  ObservableList<Contact> getContacts() {
        return  contacts;
    }

    public void addContact(Contact item) {
        contacts.add(item);
    }

    public void deleteContact(Contact item) {
        contacts.remove(item);
    }

    public static void loadContacts() {
        ResultSet rs=null;

        try {
           connection=DriverManager.getConnection(FILE_LOCATION);
            Statement statement=connection.createStatement();
             rs=statement.executeQuery("SELECT * FROM contacts");

            while (rs.next()){
                String name=rs.getString("name");
                String lastname=rs.getString("lastname");
                String phoneNumber=rs.getString("phonenumber");
                String note=rs.getString("notes");

                contacts.add(new Contact(name,lastname,phoneNumber,note));

            }
            statement.execute("DELETE FROM contacts");
        }catch (SQLException e){
            System.out.println("How that Happened");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            try {
                if (rs != null){
                    rs.close();
                }
            }catch (SQLException e){
                System.out.println("close");
            }

            try {
                if (connection != null){connection.close();}

                }catch(SQLException e){
                    System.out.println("connection close");
                } } }


    public static void saveContacts() {
        Statement statement=null;

        try{
            connection=DriverManager.getConnection(FILE_LOCATION);
             statement=connection.createStatement();

            for (Contact c:contacts){
                statement.execute("INSERT INTO contacts values('"+c.getFirstName()+"','"+
                        c.getLastName()+"','"+c.getPhoneNumber()+"','"+c.getNote()+"')");
            }
        }catch (SQLException e){
            System.out.println(" Ohh well");
            e.printStackTrace();
        }finally {
            try{
                if (statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("Statement closing");
                e.printStackTrace();
            }

            try{
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println("conn closing");
                e.printStackTrace();
            }
        }
    }
}