package com.codeclan.FinalProject;

import com.codeclan.FinalProject.models.Comment;
import com.codeclan.FinalProject.models.Destination;
import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CamperVanJourneyAppApplicationTests {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	DestinationRepository destinationRepository;

	@Autowired
	RouteRepository routeRepository;

	@Autowired
	SustainabilityActionsRepository sustainabilityActionsRepository;

//	@Autowired
//	TripRepository tripRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSustainabilityHistoryRepository userSustainabilityHistoryRepository;


	@Test
	void contextLoads() {
	}


//COMMENTS..................................................................................
	@Test
	public void testSaveComment() {
		Comment comment = new Comment("Test Comment");
		commentRepository.save(comment);

		Comment retrievedComment = commentRepository.findById(comment.getComment_id()).orElse(null);

		assertEquals("Test Comment", retrievedComment.getText());
	}

	@Test
	public void testUpdateComment() {
		Comment comment = new Comment("Original Comment");
		commentRepository.save(comment);

		// Retrieve the comment from the repository
		Comment retrievedComment = commentRepository.findById(comment.getComment_id()).orElse(null);
		assertNotNull(retrievedComment);

		// Update the comment's text
		retrievedComment.setText("Updated Comment");
		commentRepository.save(retrievedComment);

		// Retrieve the comment again
		Comment updatedComment = commentRepository.findById(comment.getComment_id()).orElse(null);
	}

//	USER....................................................................................
@Test
public void testCreateUser() {
	User user = new User("Kepler", "Password", "picture", null);
	userRepository.save(user);

	// Retrieve the user from the repository
	User retrievedUser = userRepository.findById(user.getUserId()).orElse(null);
	assertNotNull(retrievedUser);

	// Check if the user's properties match the expected values
	assertEquals("Kepler", retrievedUser.getUsername());
	assertEquals("Password", retrievedUser.getPassword());
	assertEquals("picture", retrievedUser.getProfilePicture());
	assertNull(retrievedUser.getSustainability_points());
}

	@Test
	public void testUpdateUser() {
		User user = new User("Kepler", "Password", "picture", null);
		userRepository.save(user);

		// Retrieve the user from the repository
		User retrievedUser = userRepository.findById(user.getUserId()).orElse(null);
		assertNotNull(retrievedUser);

		// Update user properties
		retrievedUser.setProfilePicture("newPicture");
		userRepository.save(retrievedUser);

		// Retrieve the user again
		User updatedUser = userRepository.findById(user.getUserId()).orElse(null);
		assertNotNull(updatedUser);

		// Check if the properties have been updated
		assertEquals("newPicture", updatedUser.getProfilePicture());
	}

	@Test
	public void testDeleteUser() {
		User user = new User("Kepler", "Password", "picture", null);
		userRepository.save(user);

		// Delete the user from the repository
		userRepository.deleteById(user.getUserId());

		// Try to retrieve the user, it should be null after deletion
		User deletedUser = userRepository.findById(user.getUserId()).orElse(null);
		assertNull(deletedUser);
	}
//DESTINATION..................................................................

	@Test
	public void testCreateDestination() {
		Destination destination = new Destination("Kylesku", 58.25724, -5.01827, "NW Scotland Trip");
		destinationRepository.save(destination);

		// Retrieve the destination from the repository
		Destination retrievedDestination = destinationRepository.findById(destination.getDestination_id()).orElse(null);
		assertNotNull(retrievedDestination);

		// Check if the destination's properties
		assertEquals("Kylesku", retrievedDestination.getName());
		assertEquals(58.25724, retrievedDestination.getLatitude(), 0.001); // Use delta for double comparison
		assertEquals(-5.01827, retrievedDestination.getLongitude(), 0.001); // Use delta for double comparison
		assertEquals("NW Scotland Trip", retrievedDestination.getDescription());
	}

	@Test
	public void testUpdateDestination() {
		Destination destination = new Destination("Oban", 56.4152, 5.4710, "Great seafood");
		destinationRepository.save(destination);

		// Retrieve the destination from the repository
		Destination retrievedDestination = destinationRepository.findById(destination.getDestination_id()).orElse(null);
		assertNotNull(retrievedDestination);

		// Update destination properties
		retrievedDestination.setName("Oban");
		retrievedDestination.setDescription("Great Seafood");
		destinationRepository.save(retrievedDestination);

		// Retrieve the destination again
		Destination updatedDestination = destinationRepository.findById(destination.getDestination_id()).orElse(null);
		assertNotNull(updatedDestination);

		// Check if the properties have been updated
		assertEquals("Oban", updatedDestination.getName());
		assertEquals("Great Seafood", updatedDestination.getDescription());
	}




}
