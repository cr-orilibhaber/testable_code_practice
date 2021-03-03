package com.cybereason.trip_finder.trip;

import com.cybereason.trip_finder.exception.UserNotLoggedInException;
import com.cybereason.trip_finder.user.User;

import java.util.List;

public class TripAPI {

    private TripService tripService;

    public List<Trip> findTrips(User user, String description) {
        try {
            return tripService.getTripsByUser(user, description);

        } catch (UserNotLoggedInException e) {
            return null;

        }
    }

}
