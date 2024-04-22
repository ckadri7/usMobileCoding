package com.example.usmobile.service.serviceImpl;
import com.example.usmobile.domain.User;
import com.example.usmobile.exception.EntityAlreadyExists;
import com.example.usmobile.repository.UserRepository;
import com.example.usmobile.request.UserRequest;
import com.example.usmobile.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_UserExists() throws EntityAlreadyExists {
        UserRequest userRequest = new UserRequest("Cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        User existingUser = new User("cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        when(userRepository.findUserByEmail(userRequest.getEmail())).thenReturn(Optional.of(existingUser));

        EntityAlreadyExists exception = assertThrows(EntityAlreadyExists.class, () -> userService.createUser(userRequest));
        assertEquals("User already exists", exception.getMessage());

        verify(userRepository, times(1)).findUserByEmail(userRequest.getEmail());
        verify(userRepository, never()).insert((User) any());
    }

    @Test
    public void testCreateUser_UserDoesNotExist() throws EntityAlreadyExists {
        UserRequest userRequest = new UserRequest("cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        when(userRepository.findUserByEmail(userRequest.getEmail())).thenReturn(Optional.empty());

        UserResponse userResponse = userService.createUser(userRequest);

        verify(userRepository, times(1)).findUserByEmail(userRequest.getEmail());
        verify(userRepository, times(1)).insert((User) any());
        assertEquals("cherif", userResponse.getFirstName());
        assertEquals("Kadri", userResponse.getLastName());
        assertEquals("cherif.kadri@usmobile.com", userResponse.getEmail());
        assertEquals("1234567890", userResponse.getMdn());
    }

    @Test
    public void testUpdateUser() {
        UserRequest userRequest = new UserRequest("cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        User user = new User("cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        when(userRepository.save(any())).thenReturn(user);

        UserResponse userResponse = userService.updateUser(userRequest);

        verify(userRepository, times(1)).save(any());
        assertEquals("cherif", userResponse.getFirstName());
        assertEquals("Kadri", userResponse.getLastName());
        assertEquals("cherif.kadri@usmobile.com", userResponse.getEmail());
        assertEquals("1234567890", userResponse.getMdn());
    }

    @Test
    public void testTransferMdn_SourceUserNotFound() {
        when(userRepository.findById("sourceUserId")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.transferMdn("sourceUserId", "targetUserId"));
        assertEquals("Source user not found", exception.getMessage());

        verify(userRepository, times(1)).findById("sourceUserId");
        verify(userRepository, never()).findById("targetUserId");
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testTransferMdn_TargetUserNotFound() {
        User sourceUser = new User("Cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        when(userRepository.findById("sourceUserId")).thenReturn(Optional.of(sourceUser));
        when(userRepository.findById("targetUserId")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.transferMdn("sourceUserId", "targetUserId"));
        assertEquals("Target user not found", exception.getMessage());

        verify(userRepository, times(1)).findById("sourceUserId");
        verify(userRepository, times(1)).findById("targetUserId");
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testTransferMdn_Success() {
        User sourceUser = new User("Cherif", "Kadri", "cherif.kadri@usmobile.com", "password", "1234567890");
        User targetUser = new User("Misbah", "Seddiki", "Misbah.Seddiki@usmobile.com", "password", "9876543210");
        when(userRepository.findById("sourceUserId")).thenReturn(Optional.of(sourceUser));
        when(userRepository.findById("targetUserId")).thenReturn(Optional.of(targetUser));

        userService.transferMdn("sourceUserId", "targetUserId");

        verify(userRepository, times(1)).findById("sourceUserId");
        verify(userRepository, times(1)).findById("targetUserId");
        verify(userRepository, times(1)).save(sourceUser);
        verify(userRepository, times(1)).save(targetUser);
    }

}
