package com.cybereason.trip_finder.trip;

import com.cybereason.trip_finder.exception.UserNotLoggedInException;
import com.cybereason.trip_finder.user.User;
import com.cybereason.trip_finder.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user, String description) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<>();

		User loggedUser = getLoggedUser();

		if (loggedUser != null) {
			if (getIsFriend(loggedUser)) {
				tripList = filterTripsByUser(user, description);
			}

			return tripList;

		} else {
			throw new UserNotLoggedInException();

		}
	}

	// moved to outside function to support unit testing
	public User getLoggedUser() {
		User loggedUser = UserSession.getInstance().getLoggedUser();

		return loggedUser;
	}

	public boolean getIsFriend(User user) {
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(user)) {
				isFriend = true;
				break;
			}
		}
		return isFriend;
	}

	public List<Trip> filterTripsByUser(User user, String description) {
		List<Trip> tripList = TripDAO.findTripsByUser(user);
		tripList.removeIf(trip -> !trip.getInformation().contains(description));
		return tripList;
	}
	
}
