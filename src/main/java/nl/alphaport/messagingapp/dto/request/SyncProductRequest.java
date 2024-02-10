package nl.alphaport.messagingapp.dto.request;

import java.time.ZonedDateTime;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-30   10:53 AM
 */
public record SyncProductRequest(String engineName, ZonedDateTime dateTime){}
