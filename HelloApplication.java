package com.example.javafx.main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private TextField inputField1, inputField2;
    private TextArea chatArea1, chatArea2;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Stage stage1 = creatChatWindow1("chat area 1", true);
        Stage stage2 = creatChatWindow1("chat area 2", false);
        stage1.show();
        stage2.show();
    }

    private Stage creatChatWindow1(String s, boolean isframe1) {
        Stage stage = new Stage();
        stage.setTitle(s);
        TextArea chatArea = new TextArea();
        chatArea.setWrapText(true);
        chatArea.setEditable(false);
        chatArea.setPadding(new Insets(10, 10, 10, 10));
        BackgroundFill background1 = new BackgroundFill(Color.LIMEGREEN,CornerRadii.EMPTY,Insets.EMPTY);
        Background background2 = new Background(background1);
        chatArea.setBackground(background2);

        TextField inputfield = new TextField();
        inputfield.setPromptText("Type your masage");
        inputfield.setPadding(new Insets(10, 10, 10, 10));
        inputfield.setMaxWidth(Double.MAX_VALUE);

        Button sendButton = new Button("send");
        sendButton.setPadding(new Insets(10));
        sendButton.setTextFill(Color.WHITE);
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(backgroundFill);
        sendButton.setBackground(background);
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sendMessage(isframe1);
            }
        });
        HBox bottomlayout = new HBox(10);
        bottomlayout.setPadding(new Insets(10));
        bottomlayout.getChildren().addAll(inputfield, sendButton);
        inputfield.setPrefWidth(200);
        BorderPane root = new BorderPane();
        root.setCenter(chatArea);
        root.setBottom(bottomlayout);
        Scene scene = new Scene(root, 300, 500);
        stage.setScene(scene);
        if (isframe1) {
            chatArea1 = chatArea;
            inputField1 = inputfield;
        } else {
            chatArea2 = chatArea;
            inputField2 = inputfield;
        }
        return stage;

    }

    private void sendMessage(boolean fromFrame1) {
        String message;
        if (fromFrame1) {
            message = inputField1.getText().trim();
            if (!message.isEmpty()) {
                chatArea1.appendText("You:" + message + "\n");
                chatArea2.appendText("Friend:" + message + "\n");
                inputField2.clear();
            }
        } else {
            message = inputField2.getText().trim();
            if (!message.isEmpty()) {
                chatArea2.appendText("You:" + message + "\n");
                chatArea1.appendText("Friend:" + message + "\n");
                inputField1.clear();
            }
        }


    }
}