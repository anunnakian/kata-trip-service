package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.user.User;

public interface Trips {
  List<Trip> find(User user);
}
