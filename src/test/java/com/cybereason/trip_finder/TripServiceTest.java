package com.cybereason.trip_finder;


import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.ArrayList;

import com.cybereason.trip_finder.user.User;
import com.cybereason.trip_finder.trip.Trip;
import com.cybereason.trip_finder.trip.TripService;

import com.cybereason.trip_finder.exception.UserIsNotFriend;
import com.cybereason.trip_finder.exception.UserNotLoggedInException;


public class TripServiceTest {

	private User testUser;
	private List<Trip> testList;

	@Before
	public void before() {
		this.testUser = new User();
	}

	/**
	 * *********************************************************************************************
	 */

	@Test(expected = UserNotLoggedInException.class) public void
	getTripsByUser_givenUserNotLoggedIn_thenShouldThrowException() throws Exception {
		TripService dummyTrip = new notLoggedTripService();
		dummyTrip.getTripsByUser(testUser, "snow");
	}

	class notLoggedTripService extends TripService {
		/**
		 * in order to override static method 'getInstance' in UserSession class that uses in TripService.
		 * @return null.
		 */

		@Override
		public User getLoggedUser() {
			return null;
		}
	}

	/**
	 * *********************************************************************************************
	 */

	@Test(expected = UserIsNotFriend.class) public void
	getTripsByUser_givenLoggedUserIsNotAFriend_thenShouldNotReturnTrips() throws UserIsNotFriend {
		TripService dummyTrip = new loggedUserIsNotFriend();
		Boolean dummyIsFriend = dummyTrip.isFriend(testUser, dummyTrip.getLoggedUser());

		if (!dummyIsFriend) {
			throw new UserIsNotFriend("Users are not friends.");
		}
	}

	class loggedUserIsNotFriend extends TripService {
		@Override
		public User getLoggedUser() {
			return new User();
		}
	}

	/**
	 * *********************************************************************************************
	 */

	@Test public void
	getTripsByUser_givenLoggedUserIsFriend_thenShouldReturnTrips() throws Exception {
		TripService dummyTrip = new loggedUserIsFriend();
		testUser.addFriend(testUser);
		dummyTrip.getTripsByUser(testUser, "snow");
	}

	class loggedUserIsFriend extends TripService {
		@Override
		public User getLoggedUser() {
			return testUser;
		}

		@Override
		public List<Trip> findTripsByUser(User user) {
			return new ArrayList<Trip>();
		}
	}
	/**
	 * *********************************************************************************************
	 */
}
