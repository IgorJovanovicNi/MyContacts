package igorjovanovic;

import igorjovanovic.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView<Contact> tableView;

    @FXML
    private BorderPane mainBorderPane;


    public void initialize(){

        ContactData.loadContacts();

        tableView.setItems(ContactData.getInstance().getContacts());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.getSelectionModel().selectFirst();

    }

    @FXML
    public void newContact(){
        Dialog<ButtonType> openNewWindow=new Dialog<>();
        openNewWindow.initOwner(mainBorderPane.getScene().getWindow());
        openNewWindow.setTitle("Adding Contact");

        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContactDialog.fxml"));
        try{
            openNewWindow.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

        openNewWindow.getDialogPane().getButtonTypes().add(ButtonType.OK);
        openNewWindow.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result=openNewWindow.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.OK){
            DialogController controller=fxmlLoader.getController();
            Contact newContact=controller.processResult();

            ContactData.getInstance().addContact(newContact);


        }else{
            System.out.println("Canceled");
        }
    }

    public void deleteContact(){
        Contact contact=tableView.getSelectionModel().getSelectedItem();
        ContactData.getInstance().deleteContact(contact);
    }

    @FXML
    public void exit(){
        Platform.exit();
    }
}
