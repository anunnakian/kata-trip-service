package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO implements Trips {

    public static List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException("TripDAO should not be invoked on an unit test.");
    }

    @Override
    public List<Trip> find(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
