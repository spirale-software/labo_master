package services.general;

import models.User;

public interface Notify {

	public void sendNotification(User userToNotify, String message);
}
