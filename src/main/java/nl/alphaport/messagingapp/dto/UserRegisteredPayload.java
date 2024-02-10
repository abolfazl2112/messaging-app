package nl.alphaport.messagingapp.dto;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-24   4:06 PM
 */
public record UserRegisteredPayload(String fullName, String emailAddress, int confirmationCode) {
}
