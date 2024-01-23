package org.craftedsw.tripservicekata.trip;

import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

  private final User authenticatedUser = new User();

  private final User friend = new User();

  @Mock
  private UserSession userSession;

  @Mock
  private Trips trips;

  @InjectMocks
  private TripService service;

  @BeforeEach
  void loadAuthentication() {
    when(userSession.getLoggedUser()).thenReturn(authenticatedUser);
  }

  @Test
  void shouldNotGetTripsWithoutAuthenticatedUser() {
    when(userSession.getLoggedUser()).thenReturn(null);

    Assertions.assertThrows(UserNotLoggedInException.class, () -> service.getTripsByUser(authenticatedUser));
  }

  @Test
  void shouldGetEmptyTripsForNewUser() {
    Assertions.assertTrue(service.getTripsByUser(authenticatedUser).isEmpty());
  }

  @Test
  void shouldGetFriendTrips() {
    friend.addFriend(authenticatedUser);

    Trip tripToLasVegas = new Trip();
    friend.addTrip(tripToLasVegas);

    when(trips.find(friend)).thenReturn(Collections.singletonList(tripToLasVegas));

    Assertions.assertTrue(service.getTripsByUser(friend).contains(tripToLasVegas));
  }
}
