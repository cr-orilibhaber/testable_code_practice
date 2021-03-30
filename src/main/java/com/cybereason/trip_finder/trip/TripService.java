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
			boolean isFriend = user.isFriend(loggedUser);

			if (isFriend) {
				tripList = findTripsByUser(user);
			}

			tripsWithGivenDescription(description, tripList);

			return tripList;

		} else {
			throw new UserNotLoggedInException();

		}
	}

	private void tripsWithGivenDescription(String description, List<Trip> tripList) {
		for(Iterator<Trip> iterator = tripList.iterator(); iterator.hasNext() ; ){
			if(!iterator.next().getInformation().contains(description))
				iterator.remove();
		}
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
