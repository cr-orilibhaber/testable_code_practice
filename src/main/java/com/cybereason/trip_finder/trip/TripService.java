package com.cybereason.trip_finder.trip;

import com.cybereason.trip_finder.exception.UserNotLoggedInException;
import com.cybereason.trip_finder.user.User;
import com.cybereason.trip_finder.user.UserSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TripService {

	public List<Trip> getTripsByUser(User user, String description) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<>();
		User loggedUser = getLoggedUser();

		if (loggedUser != null) {
			boolean isFriend = isFriend(user, loggedUser);

			if (isFriend) {
				tripList = relatedTripsWithGivenUser(description, user);
			}

			return tripList;

		} else {
			throw new UserNotLoggedInException();
		}
	}

	public User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

	private List<Trip> relatedTripsWithGivenUser(String description, User user) {
		List<Trip> tripList = findTripsByUser(user);

		for(Iterator<Trip> iterator = tripList.iterator(); iterator.hasNext() ; ){
			if(!iterator.next().getInformation().contains(description))
				iterator.remove();
		}
		return tripList;
	}

	public List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	public boolean isFriend(User user, User loggedUser) {
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		return isFriend;
	}
}
