package com.breje.xclient;

import com.breje.network.protocols.rpc.LibraryServerRpcProxy;
import com.breje.services.ILibraryServer;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibraryXStarter extends Application {

	String user = "JavaFX2";
	String pw = "password";
	String checkUser, checkPw;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		launch(args);
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);

		// LibraryClientController libraryClientController = new
		// LibraryClientController(server);
		// LoginWindow loginWindow = new LoginWindow("Library",
		// libraryClientController);
		// loginWindow.setSize(200, 200);
		// loginWindow.setLocation(600, 300);
		// loginWindow.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("X | OLibrary Log In");

		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 50, 50, 50));

		HBox hb = new HBox();
		hb.setPadding(new Insets(20, 20, 20, 30));

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPasswd = new Label("Password");
		final PasswordField txtPasswd = new PasswordField();
		Button btnLogin = new Button("Login");
		final Label lblMessage = new Label();

		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPasswd, 0, 1);
		gridPane.add(txtPasswd, 1, 1);
		gridPane.add(btnLogin, 2, 1);
		gridPane.add(lblMessage, 1, 2);

		Reflection r = new Reflection();
		r.setFraction(0.7f);
		gridPane.setEffect(r);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		Text text = new Text("X | OLibrary Login");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);

		hb.getChildren().add(text);

		bp.setId("bp");
		gridPane.setId("root");
		btnLogin.setId("btnLogin");
		text.setId("text");
		// Action for btnLogin
		btnLogin.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				checkUser = txtUserName.getText().toString();
				checkPw = txtPasswd.getText().toString();
				if (checkUser.equals(user) && checkPw.equals(pw)) {
					lblMessage.setText("Congratulations!");
					lblMessage.setTextFill(Color.GREEN);
				} else {
					lblMessage.setText("Incorrect user or pw.");
					lblMessage.setTextFill(Color.RED);
				}
				txtUserName.setText("");
				txtPasswd.setText("");
			}

		});
		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);

		bp.setCenter(gridPane);
		Scene scene = new Scene(bp);
		// scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.titleProperty()
				.bind(scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString()));
		// primaryStage.setResizable(false);
		primaryStage.show();
	}

}
