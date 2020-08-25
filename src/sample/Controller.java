package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.text.NumberFormat;

public class Controller {

    @FXML
    ImageView cardAnimation;
    @FXML
    ImageView moneyAnimation;

    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
    @FXML
    Button button5;
    @FXML
    Button button6;
    @FXML
    Button button7;
    @FXML
    Button button8;
    @FXML
    Button button9;
    @FXML
    Button cancelButton;
    @FXML
    Button enterButton;
    @FXML
    Button clearButton;

    @FXML
    Button buttonS1;
    @FXML
    Button buttonS2;
    @FXML
    Button buttonS3;
    @FXML
    Button buttonS4;
    @FXML
    Button buttonS5;
    @FXML
    Button buttonS6;

    @FXML
    TextField textField1;
    @FXML
    TextField textField2;
    @FXML
    TextField textField3;
    @FXML
    TextField textField4;
    @FXML
    TextField textField5;
    @FXML
    TextField textField6;

    @FXML
    Label textArea;
    @FXML
    Label amountLabel;
    @FXML
    PasswordField pinField;
    @FXML
    Label accountNumberLabel;

    private Card card;


    private boolean isPin = false;
    private boolean isAmount = false;
    private boolean isNewPin = false;
    private boolean isDeposit = false;
    private boolean isAccountNr = false;

    private final StringBuilder pin = new StringBuilder();
    private StringProperty pinProperty = new SimpleStringProperty();
    private final StringBuilder amount = new StringBuilder();
    private StringProperty amountProperty = new SimpleStringProperty();
    private final StringBuilder accountNumber = new StringBuilder();
    private StringProperty accountNumberProperty = new SimpleStringProperty();

    private Image[] cardAnimationIN;
    private Image[] cardAnimationOUT;
    private Image[] moneyAnimationIN;
    private Image[] moneyAnimationOUT;


    public void initialize(){

        setImages();
        setButtons();
        setLabelsTextProperty();
        textArea.setText("Press the card\n\t to continue...");
    }

    //setUp
    public void setButtons(){

        try{
            Image arrowL = new Image((new File(System.getProperty("user.dir") + "\\buttonL1.png")).toURI().toString());
            Image arrowR = new Image((new File(System.getProperty("user.dir")+"\\buttonR1.png")).toURI().toString());

            ImageView icon1 = new ImageView(arrowL);
            buttonS1.setGraphic(icon1);
            ImageView icon2 = new ImageView(arrowL);
            buttonS2.setGraphic(icon2);
            ImageView icon3 = new ImageView(arrowL);
            buttonS3.setGraphic(icon3);
            ImageView icon4 = new ImageView(arrowR);
            buttonS4.setGraphic(icon4);
            ImageView icon5 = new ImageView(arrowR);
            buttonS5.setGraphic(icon5);
            ImageView icon6 = new ImageView(arrowR);
            buttonS6.setGraphic(icon6);
        }catch (Exception ignored){

        }


    }
    public void setImages(){
        cardAnimationIN = loadCardAnimationIN();
        cardAnimationOUT = loadCardAnimationOUT();
        moneyAnimationIN = loadMoneyAnimationIN();
        moneyAnimationOUT = loadMoneyAnimationOUT();
        cardAnimation.setImage(new Image(
                (new File(System.getProperty("user.dir")+"\\cardAnimation\\cardIN\\d (0).gif")).toURI().toString()));
        moneyAnimation.setImage(new Image(
                (new File(System.getProperty("user.dir")+"\\moneyAnimation\\m (0).gif")).toURI().toString()));
        cardAnimation.setDisable(false);
        runFlashAnimation(cardAnimation);
    }
    public void setLabelsTextProperty(){
        amountLabel.textProperty().bind(amountProperty);
        pinField.textProperty().bind(pinProperty);
        accountNumberLabel.textProperty().bind(accountNumberProperty);
        amountLabel.setVisible(false);
        accountNumberLabel.setVisible(false);
    }

    //Animations
    public Image[] loadCardAnimationIN(){
        Image[] images = new Image[14];
        for(int i=0; i<14; i++){
            File imageFile = new File(System.getProperty("user.dir")+"\\cardAnimation\\cardIN\\d (" + i + ").gif");
            try {
                images[i] = new Image(imageFile.toURI().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return images;
    }
    public Image[] loadCardAnimationOUT(){
        Image[] images = new Image[21];
        for(int i=1; i<22; i++){
            File imageFile = new File(System.getProperty("user.dir")+"\\cardAnimation\\cardOUT\\o (" + i + ").gif");
            try {
                images[i-1] = new Image(imageFile.toURI().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return images;
    }
    public Image[] loadMoneyAnimationOUT(){
        Image[] images = new Image[25];
        for(int i=0; i<25; i++){
            File imageFile = new File(System.getProperty("user.dir")+"\\moneyAnimation\\m (" + i + ").gif");
            try {
                images[i] = new Image(imageFile.toURI().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return images;
    }
    public Image[] loadMoneyAnimationIN(){
        Image[] images = new Image[25];
        for(int i=24,j=0; i>=0; i--,j++){
            File imageFile = new File(System.getProperty("user.dir")+"\\moneyAnimation\\m (" + i + ").gif");
            try {
                images[j] = new Image(imageFile.toURI().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return images;
    }
    public void runAnimation(ImageView animation, Image[] images ){
        new Thread(() -> {
            animation.setDisable(true);                     //to not mess up the animation
            for (Image image : images) {
                try {
                    animation.setImage(image);
                    Thread.sleep(70);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Card View
    public void handleCardHover1(){
        cardAnimation.setOpacity(0.80);
        cardAnimation.setScaleX(1.01);
        cardAnimation.setScaleY(1.01);
    }
    public void handleCardHover2(){
        cardAnimation.setOpacity(1);
        cardAnimation.setScaleX(1);
        cardAnimation.setScaleY(1);
    }
    public void handleCardMouseClick(){

        if(textArea.getText().equals("CLICK ON YOU CARD")){
            runAnimation(cardAnimation, cardAnimationOUT);
            sayGoodBye();
            return;
        }
        runAnimation(cardAnimation, cardAnimationIN);
        new Thread(() -> {
            try {
                Thread.sleep(1500);                 //waiting for the animation to finish
            }catch (Exception e){
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                textArea.setText("WELCOME!\nENTER YOU PIN:");
                isPin = true;
                cardAnimation.setDisable(true);
            });
        }).start();
    }

    //Numbers
    public void handleNumberClick(ActionEvent event){
        if(!(isPin||isNewPin) && !isAmount && !isAccountNr){
            return;
        }
        Button button = (Button) event.getSource();
        String id = "" + button.getId().substring(button.getId().length() - 1); //taking last char "button1"->"1"

        if(isPin||isNewPin){
            if(pin.length() < 4){
                pin.append(id);
            }
            pinProperty.setValue(pin.toString());
        }
        if(isAmount){
            amount.append(id);
            amountProperty.setValue(amount.toString());
        }else if(isAccountNr){
            accountNumber.append(id);
            accountNumberProperty.setValue(accountNumber.toString());
        }

    }

    //Buttons
    public void handleEnterButton(){
        if(isPin && pin.length() == 4){
            //validation
            showOptions();
            pinProperty.setValue("");
            card = new Card(pin.toString());
            isPin = false;
        }else if(isNewPin && pin.length() == 4){
            card.setPin(pin.toString());
            pin.delete(0,pin.length());
            pinProperty.setValue(pin.toString());
            isNewPin = false;
            textArea.setText("PIN CHANGED SUCCESSFULLY ");
            goBack();
        }else if(isAmount && !isAccountNr && amountProperty.getValue().length() > 0){
            //proceed
            withdrawal(Double.parseDouble(amount.toString()));
            isAmount = false;
            amountLabel.setVisible(false);
        }else if(isAccountNr && !isAmount && accountNumber.length()>10){             // other validation may be applied
            accountNumber.delete(0,accountNumber.length());
            textArea.setText("PUT THE AMOUNT YOU WANT TO TRANSFER");
            isAmount = true;
            amount.delete(0,amount.length());
            amountProperty.setValue(amount.toString());
            accountNumberLabel.setVisible(false);
            amountLabel.setVisible(true);
        }
        if(isAmount && isAccountNr && amount.length()>0){
            System.out.println(amount);
            double amountToTransfer = Double.parseDouble(amount.toString());
            if(card.getBalance() >= amountToTransfer){
                card.setBalance(card.getBalance() - amountToTransfer);
                textArea.setText("TRANSACTION SUCCEEDED");
                isAmount = false;
            }else {
                textArea.setText("YOU DON`T HAVE ENOUGH BALANCE!");
                isAmount = false;
            }
            goBack();
        }

    }
    public void handleClearButton(){
        if((isPin || isNewPin) && pin.length()>0){
            pin.deleteCharAt(pin.length()-1);
            pinProperty.setValue(pin.toString());
        }else if(isAmount && amount.length()>0){
            amount.deleteCharAt(amount.length() - 1);
            amountProperty.setValue(amount.toString());
        }else if(isAccountNr && accountNumber.length()>0){
            accountNumber.deleteCharAt(accountNumber.length()-1);
            accountNumberProperty.setValue(accountNumber.toString());
        }
    }
    public void handleCancelButton(ActionEvent event){
        runAnimation(cardAnimation, cardAnimationOUT);
        exit();
    }


    //Side buttons
    public void handleSideButton1(){
        if(textField1.getText().equals("WITHDRAWAL")){
            cashWithdrawal();
        }else if(textField1.getText().equals("10")){
            withdrawal(10);
        }
    }
    public void handleSideButton2(){
        if(textField2.getText().equals("CHANGE PIN")){
            changePIN();
        }else if(textField2.getText().equals("20")){
            withdrawal(20);
        }
    }
    public void handleSideButton3(){
        if(textField3.getText().equals("BALANCE")){
            showBalance();
        }else if(textField3.getText().equals("50")){
            withdrawal(50);
        }else if(textField3.getText().equals("GO BACK")){
            showOptions();
        }
    }
    public void handleSideButton4(){
        if(textField4.getText().equals("DEPOSIT")){
            deposit();
        }else if(textField4.getText().equals("100")){
            runAnimation(moneyAnimation, moneyAnimationOUT);
            withdrawal(100);
        }
    }
    public void handleSideButton5(){
        if(textField5.getText().equals("TRANSFER")){
            transfer();
        }else if(textField5.getText().equals("200")){
            runAnimation(moneyAnimation, moneyAnimationOUT);
            withdrawal(200);
        }
    }
    public void handleSideButton6(){
        if(textField6.getText().equals("EXIT")){
            clearSideButtons();
            runAnimation(cardAnimation, cardAnimationOUT);
            exit();
        }else if(textField6.getText().equals("OTHER AMOUNT")){
            clearSideButtons();
            amount.delete(0, amount.length());
            amountProperty.setValue(amount.toString());
            textArea.setVisible(true);
            textArea.setText("PLACE THE AMOUNT");
            isAmount = true;
            amountLabel.setVisible(true);
        }
    }

    //Money Animation
    public void handleMoneyClick(){
        moneyAnimation.setImage(moneyAnimationOUT[0]);
        if(isDeposit){
            runAnimation(moneyAnimation, moneyAnimationIN);
            new Thread(()->{
                try{
                    Thread.sleep(2500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    textArea.setText("COUNTING...");
                });
                try{
                    Thread.sleep(3500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    double amountAdded = countMoney();
                    card.setBalance(card.getBalance() + amountAdded);
                    textArea.setText(amountAdded+ "$\nadded to your account.");
                    textArea.setVisible(true);
                });
            }).start();
        }
    }
    public double countMoney(){
        //taking the input from the counting device
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        String amountAdded = nf.format(Math.random()*1000);
        return Double.parseDouble(amountAdded);
    }
    public void showOptions(){
        textArea.setVisible(false);
        pinField.setVisible(false);
        amountLabel.setVisible(false);
        textField1.setText("WITHDRAWAL");
        textField2.setText("CHANGE PIN");
        textField3.setText("BALANCE");
        textField4.setText("DEPOSIT");
        textField5.setText("TRANSFER");
        textField6.setText("EXIT");
    }

    //Options 1-6
    public void cashWithdrawal(){
        textField1.setText("10");
        textField2.setText("20");
        textField3.setText("50");
        textField4.setText("100");
        textField5.setText("200");
        textField6.setText("OTHER AMOUNT");
    }
    public void withdrawal(double amount){
        if(card.getBalance() >= amount){
            runAnimation(moneyAnimation, moneyAnimationOUT);
            textArea.setVisible(true);
            pinField.setVisible(true);
            textArea.setText("CLICK ON YOU CARD");
            cardAnimation.setDisable(false);
            clearSideButtons();
            card.setBalance(card.getBalance() - amount);
        }else {
            textArea.setVisible(true);
            textArea.setText("YOU DO NOT HAVE ENOUGH BALANCE");
            amountProperty.setValue("");
            amountLabel.setVisible(true);
            textField3.setText("GO BACK");
            textField3.setVisible(true);
        }

    }

    public void changePIN(){
        clearSideButtons();
        textArea.setText("ENTER NEW PIN");
        textArea.setVisible(true);
        pinField.setVisible(true);
        pin.delete(0,pin.length());
        isNewPin = true;
    }

    public void showBalance(){
        textArea.setVisible(true);
        textArea.setText("YOU CURRENT BALANCE IS: ");
        amount.delete(0,amount.length());
        amount.append(card.getBalance());
        amountProperty.setValue(amount.toString());
        amountLabel.setVisible(true);
        clearSideButtons();
        goBack();
    }

    public void deposit(){
        clearSideButtons();
        isDeposit = true;
        textArea.setText("ENTER THE MONEY BY CLICKING THE SLOT");
        textArea.setVisible(true);
        pinField.setVisible(true);
        runFlashAnimation(moneyAnimation);
        moneyAnimation.setDisable(false);
        goBack();

    }

    public void transfer(){
        clearSideButtons();
        textArea.setVisible(true);
        textArea.setText("ENTER THE ACCOUNT NUMBER OF THE PERSON: ");
        accountNumberLabel.setVisible(true);
        accountNumber.delete(0,accountNumber.length());
        accountNumberProperty.setValue(accountNumber.toString());
        amountLabel.setVisible(false);
        isAccountNr = true;
    }

    public void exit(){
        sayGoodBye();
    }


    //utilities
    public void clearSideButtons(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    public void sayGoodBye(){
        textArea.setVisible(true);
        pinField.setVisible(true);
        textArea.setText("THANK YOU FOR CHOOSING OUR BANK");
        isPin = false;
        isAmount = false;
    }

    public void goBack(){
        textField3.setText("GO BACK");
        textField3.setVisible(true);
        pinField.setVisible(false);
    }

    public void runFlashAnimation(ImageView animation){
        FadeTransition flash = new FadeTransition(Duration.seconds(1), animation);
        flash.setFromValue(0.5);
        flash.setToValue(1.0);
        flash.setCycleCount(5);
        flash.setAutoReverse(true);
        flash.play();
    }

}
