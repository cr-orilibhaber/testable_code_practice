package com.cybereason.trip_finder.trip;

import com.cybereason.trip_finder.exception.DependendClassCallDuringUnitTestException;
import com.cybereason.trip_finder.user.User;

import java.util.List;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}