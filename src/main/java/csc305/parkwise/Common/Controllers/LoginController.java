package csc305.parkwise.Common.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import csc305.parkwise.Common.Models.User;
import csc305.parkwise.Common.Utils.*;
import csc305.parkwise.Common.Utils.Router.RouteMapper;
import csc305.parkwise.Common.Utils.Router.RoutesEnum.ParkDirectorRoutes;
import csc305.parkwise.Common.Utils.Router.SceneSwitcher;
import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Router.RoutesEnum.CampgroundManagerRoutes;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Users.Asif.ParkDirector.PDDashboardController;
import csc305.parkwise.Users.Asif.CampgroundManager.CMDashboardController;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {
	@javafx.fxml.FXML
	private TextField userIdInput;
	@javafx.fxml.FXML
	private TextField passwordInput;

	@javafx.fxml.FXML
	public void initialize() throws IOException {
	}

	@javafx.fxml.FXML
	public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
		if (userIdInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
			Utilities.showAlert("Username or password cannot be blank", Alert.AlertType.ERROR);
			return;
		}

		int userId = Integer.parseInt(userIdInput.getText());
		String password = passwordInput.getText();

		if (userId < 1001 || userId > 9999) {
			Utilities.showAlert("Username must be 4 digits", Alert.AlertType.ERROR);
			return;
		}

		if (password.length() < 8) {
			Utilities.showAlert("Password must be at least 8 digits", Alert.AlertType.ERROR);
			return;
		}

		User user = new User(userId, password);
		StaffAccount staffAccount = user.loginUser();

		if (staffAccount != null) {
			RouteMapper routes = new RouteMapper();
			String route = "";

			switch (staffAccount.getUserType()) {
				case "Park Director":
					route = routes.getParkDirectorRoute(ParkDirectorRoutes.PDDashboardView);
					SceneSwitcher pdSceneSwitcher = new SceneSwitcher(route);
					pdSceneSwitcher.navigateTo(actionEvent);

					PDDashboardController pdDashboardController = pdSceneSwitcher.getFxmlLoader().getController();
					pdDashboardController.setUserId(String.valueOf(staffAccount.getUserId()));
					pdDashboardController.setUserType(staffAccount.getUserType());
					break;
				case "Campground Manager":
					route = routes.getCampgroundManagerRoute(CampgroundManagerRoutes.CMDashboardView);
					SceneSwitcher cmSceneSwitcher = new SceneSwitcher(route);
					cmSceneSwitcher.navigateTo(actionEvent);

					CMDashboardController cmDashboardController = cmSceneSwitcher.getFxmlLoader().getController();
					cmDashboardController.setUserId(String.valueOf(staffAccount.getUserId()));
					cmDashboardController.setUserType(staffAccount.getUserType());
					break;
				default:
					break;
			}
		} else {
			Utilities.showAlert("Username or password incorrect!", Alert.AlertType.ERROR);
		}

	}
}