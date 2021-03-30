package com.cybereason.trip_finder;

import com.cybereason.trip_finder.exception.UserNotLoggedInException;
import com.cybereason.trip_finder.trip.Trip;
import com.cybereason.trip_finder.trip.TripService;
import com.cybereason.trip_finder.user.User;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TripServiceTest {

	@Before
	public void before() {

	}

	@Test(expected = UserNotLoggedInException.class) public void
	getTripsByUser_givenUserNotLoggedIn_thenShouldThrowException() throws Exception {
		final String testDescription = "testOneDescription";
		final User testUser = new User();

		TripService sut = new TripService(){
			@Override
			protected User getLoggedUser() {
				return null;
			}
		};

		sut.getTripsByUser(testUser, testDescription);
	}

	@Test public void
	getTripsByUser_givenLoggedUserIsNotAFriend_thenShouldNotReturnTrips() throws Exception {
		final String testDescription = "testTwoDescription";
		final User dummyUser = new User();

		TripService sut = new TripService(){
			@Override
			protected User getLoggedUser() {
				return dummyUser;
			}
		};

		User testUser = new User(){
			@Override
			public boolean isFriend(User usr) {
				return false;
			}
		};

		assert(sut.getTripsByUser(testUser, testDescription).isEmpty());
	}

	@Test public void
	getTripsByUser_givenLoggedUserIsFriend_thenShouldReturnTrips() throws Exception {
		Trip firstTrip = new Trip();
		firstTrip.setInformation("testThreeDescription");
		Trip secondTrip = new Trip();
		secondTrip.setInformation("thirdTest");
		Trip thirdTrip = new Trip();
		thirdTrip.setInformation("thirdTest");

		List<Trip> testTripList = new ArrayList<>();
		Collections. addAll(testTripList, firstTrip, secondTrip, thirdTrip);

		final String testDescription = "testThreeDescription";
		final User dummyUser = new User();

		TripService sut = new TripService(){
			@Override
			protected User getLoggedUser() {
				return dummyUser;
			}
			protected List<Trip> findTripsByUser(User user) {
				return testTripList;
			}
		};

		User testUser = new User(){
			@Override
			public boolean isFriend(User usr) {
				return true;
			}
		};

		Assert.assertEquals(sut.getTripsByUser(testUser, testDescription).size(), 1);
		assert(sut.getTripsByUser(testUser, testDescription).contains(firstTrip));
	}

}
