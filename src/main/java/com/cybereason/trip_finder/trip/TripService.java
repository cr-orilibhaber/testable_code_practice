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
		User loggedUser = UserSession.getInstance().getLoggedUser();

		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}

			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}

			for(Iterator<Trip> iterator = tripList.iterator() ; iterator.hasNext() ; ){
				if(!iterator.next().getInformation().contains(description))
					iterator.remove();
			}

			return tripList;

		} else {
			throw new UserNotLoggedInException();

		}
	}
	
}
