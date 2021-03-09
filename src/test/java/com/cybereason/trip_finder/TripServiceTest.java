package com.cybereason.trip_finder;

import com.cybereason.trip_finder.exception.UserNotLoggedInException;
import com.cybereason.trip_finder.trip.Trip;
import com.cybereason.trip_finder.trip.TripService;
import com.cybereason.trip_finder.user.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TripServiceTest {

	private TripService tripService;
	private User user;
	private ArrayList<Trip> trips;
	
	@Before
	public void before() {
		tripService = spy(new TripService());

		Trip trip1 = new Trip();
		trip1.setInformation("trip 1 information");
		Trip trip2 = new Trip();
		trip2.setInformation("this is trip 2 information");
		Trip trip3 = new Trip();
		trip3.setInformation("test-desc");

		user = new User();
		user.addTrip(trip1);
		user.addTrip(trip2);
		trips = new ArrayList<>(Arrays.asList(trip1, trip2));
	}
	
	@Test public void
	getTripsByUser_givenUserNotLoggedIn_thenShouldThrowException() throws Exception {

		// GIVEN
		List<Trip> tripsByUser = new ArrayList<>();

		// WHEN
		doReturn(null).when(tripService).getLoggedUser();

		// INVOKE
		// THEN
		try {
			tripsByUser = tripService.getTripsByUser(user, "test-desc");
		} catch (UserNotLoggedInException e) {
			assertTrue(true);
			assertTrue(tripsByUser.isEmpty());
		}
	}

	@Test public void
	getTripsByUser_givenLoggedUserIsNotAFriend_thenShouldNotReturnTrips() throws Exception {

		// GIVEN
		User otherUser = new User();
		user.addFriend(otherUser);

		// WHEN
		doReturn(user).when(tripService).getLoggedUser();

		// INVOKE
		List<Trip> tripsByUser = tripService.getTripsByUser(user, "test-desc");

		// THEN
		assertTrue(tripsByUser.isEmpty());
	}
	
	@Test public void
	getTripsByUser_givenLoggedUserIsFriend_thenShouldReturnTrips() throws Exception {

		// GIVEN
		User otherUser = new User();
		user.addFriend(otherUser);
		user.addFriend(user);

		// WHEN
		doReturn(user).when(tripService).getLoggedUser();
		doReturn(trips).when(tripService).filterTripsByUser(any(),any());

		// INVOKE
		List<Trip> tripsByUser = tripService.getTripsByUser(user, "test-desc");

		// THEN
		assertEquals(2, tripsByUser.size());
	}

}
