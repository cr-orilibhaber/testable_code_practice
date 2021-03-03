package com.cybereason.trip_finder;

import com.cybereason.trip_finder.trip.TripService;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TripServiceTest {

	private TripService SUT;
	
	@Before
	public void before() {

	}
	
	@Test public void
	getTripsByUser_givenUserNotLoggedIn_thenShouldThrowException() throws Exception {

		// GIVEN

		// WHEN

		// INVOKE

		// THEN
		fail("Not implemented");
	}
	
	@Test public void
	getTripsByUser_givenLoggedUserIsNotAFriend_thenShouldNotReturnTrips() throws Exception {

		// GIVEN

		// WHEN

		// INVOKE

		// THEN
		fail("Not implemented");
	}
	
	@Test public void
	getTripsByUser_givenLoggedUserIsFriend_thenShouldReturnTrips() throws Exception {

		// GIVEN

		// WHEN

		// INVOKE

		// THEN
		fail("Not implemented");
	}

}
