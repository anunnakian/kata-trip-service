package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private final UserSession userSession;
    private final Trips trips;

    public TripService(UserSession userSession, Trips trips) {
        this.userSession = userSession;
        this.trips = trips;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = userSession.getLoggedUser();
        assertUserIsAuthenticated(loggedUser);

        return user
                .getFriends()
                .stream()
                .filter(friend -> friend.equals(loggedUser))
                .findFirst()
                .map(friend -> findTrips(user))
                .orElse(new ArrayList<>());
    }

    protected List<Trip> findTrips(User user) {
        return trips.find(user);
    }

    private void assertUserIsAuthenticated(User loggedUser) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }
}
