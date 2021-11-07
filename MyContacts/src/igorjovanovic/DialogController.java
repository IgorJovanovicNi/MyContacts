package igorjovanovic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DialogController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField note;

    public Contact processResult(){
        String name=firstName.getText();
        String lastN=lastName.getText();
        String number= phoneNumber.getText();
        String stringNote=note.getText();

        return   new Contact(name,lastN,number,stringNote);
    }

    }
